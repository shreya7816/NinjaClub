package com.ninjaclub.dashboard.util;

import java.util.Scanner;

import com.ninjaclub.dashboard.constants.MenuConstants;
import com.ninjaclub.dashboard.model.GameState;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Enemy;

/**
 * @author shreya
 *
 */
public class GameUtil {

	boolean flag;

	public static int level;

	PlayerUtil playerUtil;

	FightUtil fightUtil;

	GameState gameState;

	public static final Scanner sc = new Scanner(System.in);

	public GameUtil() {
		this.flag = true;
		this.playerUtil = new PlayerUtil();
		this.fightUtil = new FightUtil();

	}

	public void start(Player player, Player enemy) {
		level++;
		if(level > 1 && player.getHp() <= 1) level = 0;
		welcomeMessage(level, player);
		while(flag) {
			if(level > 1 && player.getHp() > 1) {
				switch(sc.nextInt()) {
				case 1:	levelUp(player, enemy);
				break;

				case 2: quit(player, enemy);
				break;

				default : System.out.println(MenuConstants.INVALID_CHOICE);
				}

			}else {

				switch(sc.nextInt()) {
				case 1:	newGame(player, enemy);
				break;

				case 2: System.out.println("Loading last saved game...");
				gameState = new GameState();
				gameState = gameState.resumeGame();
				readyForFight(gameState.getPlayer(), gameState.getEnemy());
				break;

				case 3: newPlayersQuit();
				break;

				default : System.out.println(MenuConstants.INVALID_CHOICE);
				}
			}
		}

	}

	public void welcomeMessage(int level, Player player) {
		System.out.println(MenuConstants.WELCOME_MSG);

		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println("This is level " +level);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		if(level > 1 && player.getHp() > 1)
			System.out.println(MenuConstants.WELCOME_OPTIONS_2);
		else {	
			System.out.println(MenuConstants.WELCOME_OPTIONS_1);
		}
	}

	public void levelUp(Player player, Player enemy) {
		displayStats(player);
		System.out.println(MenuConstants.NEW_GAME_MSG_2);
		Enemy.stream().forEach(System.out::println);
		try {
			sc.nextLine();
			enemy = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Your must be brave to choose " +enemy.getName() +"!\n");
		displayStats(player);
		readyForFight(player, enemy);
		
	}

	
	public void newGame(Player player, Player enemy) {
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_STORY);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_QUESTION_1);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		sc.nextLine();
		try {
			player = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println("\nHey! " +player.getName() +MenuConstants.GREETING_MSG);
		System.out.println(MenuConstants.NEW_GAME_MSG_1);
		displayStats(player);
		System.out.println(MenuConstants.NEW_GAME_MSG_2);

		Enemy.stream().forEach(System.out::println);
		try {
			enemy = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Your must be brave to choose " +enemy.getName() +"!\n");
		displayStats(player);
		readyForFight(player, enemy);

	}

	public void readyForFight(Player player, Player enemy) {
		System.out.println(MenuConstants.NEW_GAME_MSG_3);

		switch(sc.nextInt()) {
		case 1: fightUtil.fight(player, enemy);
		break;

		case 2: quit(player, enemy);
		break;

		default : System.out.println(MenuConstants.INVALID_CHOICE);
		readyForFight(player, enemy);
		}
	}

	public void newPlayersQuit() {
		System.out.println(MenuConstants.QUIT_GAME_MSG_3);
		switch(sc.nextInt()) {

		case 1: flag = false;
		System.out.println("Closing the game.");
		System.exit(0);
		break;

		case 2: Player player = new Player();
		Player enemy = new Player();
		level--;
		start(player, enemy);

		default : System.out.println(MenuConstants.INVALID_CHOICE);
		newPlayersQuit();
		}
	}

	public void quit(Player player, Player enemy) {
		System.out.println(MenuConstants.QUIT_GAME_MSG_1);
		switch(sc.nextInt()) {

		case 1: flag = false;
		System.out.println(MenuConstants.QUIT_GAME_MSG_2);
		System.exit(0);
		break;

		case 2: gameState = new GameState(player, enemy, level);
		gameState.saveAndQuit();
		System.exit(0);
		default : System.out.println(MenuConstants.INVALID_CHOICE);
		quit(player, enemy);
		}

	}

	public void restart(Player player, Player enemy) {
		System.out.println(MenuConstants.RESTART_MSG);
		switch(sc.nextInt()) {

		case 1: System.out.println("Restarting the game");
		start(player, enemy);
		break;

		case 2: quit(player, enemy);
		break;

		default : System.out.println(MenuConstants.INVALID_CHOICE);
		restart(player, enemy);
		}

	}

	public void playNextLevel(Player player, Player enemy) {
		System.out.println(MenuConstants.CONTINUE_MSG);
		switch(sc.nextInt()) {

		case 1: System.out.println("Loading next level...");
		start(player, enemy);
		break;

		case 2: quit(player, enemy);
		break;

		default : System.out.println(MenuConstants.INVALID_CHOICE);
		playNextLevel(player, enemy);
		}
	}

	public void gameOver(Player player, Player enemy) {
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.GAMEOVER_MSG);
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		restart(player, enemy);
	}

	public void victory(Player player, Player enemy) {
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.VICTORY_MSG);
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		playNextLevel(player, enemy);
	}

	private void displayStats(Player player) {
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(player.getName() +" stats are:\t|\t" +"HP:" +player.getHp() +"\t|\t" +"Reputation:" +player.getReputation() +"\t|");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
	}

}
