package com.ninjaclub.dashboard.util;

import java.util.Scanner;

import com.ninjaclub.dashboard.constants.MenuConstants;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Enemy;

/**
 * @author shreya
 *
 */
public class GameUtil {

	boolean flag;
	
	public static int level;
	
	Player player;
	
	Player enemy;
	
	PlayerUtil playerUtil;
	
	FightUtil fightUtil;
	
	public static final Scanner sc = new Scanner(System.in);

	public GameUtil() {
		this.flag = true;
		this.playerUtil = new PlayerUtil();
		this.fightUtil = new FightUtil();
		//this.player = new Player();
	}

	public void start() {
		level++;
		if(level > 1 && player.getHp() <= 1) level = 0;
		welcomeMessage(level);
		while(flag) {
			if(level > 1 && player.getHp() > 1) {
				switch(sc.nextInt()) {
				case 1:	levelUp();
				break;

				case 2: quit();
				break;
				
				default : System.out.println("Enter a valid choice");
				}

			}else {
				
				switch(sc.nextInt()) {
				case 1:	newGame();
				break;

				case 2: resumeGame();
				break;

				case 3: quit();
				break;

				default : System.out.println("Enter a valid choice");
				}
			}
		}
	}

	public void welcomeMessage(int level) {
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

	public void levelUp() {
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println("Your stats are:\t|\t" +"HP:" +player.getHp() +"\t|\t" +"Reputation:" +player.getReputation() +"\t|");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_MSG_2);
		
		Enemy.stream().forEach(System.out::println);
		try {
			sc.nextLine();
			enemy = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Your must be brave to choose " +enemy.getName() +"!\n");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(enemy.getName() +" stats are:\t|\t" +"HP:" +enemy.getHp() +"\t|\t" +"Reputation:" +enemy.getReputation() +"\t|");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_MSG_3);
		
		switch(sc.nextInt()) {
		case 1: fightUtil.fight(player, enemy);
		break;

		case 2: quit();
		break;
		
		//default : System.out.println("Enter a valid choice");
		}
	
	}
	
	public void newGame() {
		Player player = new Player();
		Player enemy = new Player();
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
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println("Your stats are:\t|\t" +"HP:" +player.getHp() +"\t|\t" +"Reputation:" +player.getReputation() +"\t|");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_MSG_2);
		
		Enemy.stream().forEach(System.out::println);
		try {
			enemy = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Your must be brave to choose " +enemy.getName() +"!\n");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(enemy.getName() +" stats are:\t|\t" +"HP:" +enemy.getHp() +"\t|\t" +"Reputation:" +enemy.getReputation() +"\t|");
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_MSG_3);
		
		switch(sc.nextInt()) {
		case 1: fightUtil.fight(player, enemy);
		break;

		case 2: quit();
		break;
		
		//default : System.out.println("Enter a valid choice, let's try again.");
		}
	}

	public void quit() {
		System.out.println(MenuConstants.QUIT_GAME_MSG_1);
		switch(sc.nextInt()) {

		case 1: flag = false;
		System.out.println(MenuConstants.QUIT_GAME_MSG_2);
		System.exit(0);
		break;

		case 2: saveAndQuit();
		
		//default : System.out.println("Enter a valid choice");
		}

	}

	public void restart(Player player, Player enemy) {
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.RESTART_MSG);
		switch(sc.nextInt()) {
		
		case 1: System.out.println("Restarting the game");
		this.player = player;
		this.enemy = enemy;
		start();
		break;
		
		case 2: quit();
		break;
		
		//default : System.out.println("Enter a valid choice");
		}
		
	}
	
	public void gameOver(Player player, Player enemy) {
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.GAMEOVER_MSG);
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		restart(player, enemy);
	}
	
	public void victory(Player player, Player enemy) {
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.VICTORY_MSG);
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		restart(player, enemy);
	}
	
	public void saveAndQuit() {
		
	}
	
	public void resumeGame() {

	}
}
