package com.ninjaclub.dashboard.util;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import com.ninjaclub.dashboard.constants.MenuConstants;
import com.ninjaclub.dashboard.model.GameState;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Enemy;
import com.ninjaclub.dashboard.model.enums.ResponseCode;
import com.ninjaclub.dashboard.model.enums.Weapon;
import com.ninjaclub.dashboard.service.common.nao.ResultNAO;

/**
 * @author shreya
 *
 */
public class GameUtil {

	PlayerUtil playerUtil;

	FightUtil fightUtil;

	GameState gameState;
	
	CommonUtil commonUtil;
	
	boolean flag;

	public static int level;

	int count;

	public static final Scanner sc = new Scanner(System.in);

	public GameUtil() {
		this.flag = true;
		this.playerUtil = new PlayerUtil();
		this.fightUtil = new FightUtil();
		this.commonUtil = new CommonUtil();
	}

	public GameUtil(PlayerUtil playerUtil, FightUtil fightUtil, GameState gameState, CommonUtil commonUtil,
			boolean flag, int count) {
		super();
		this.playerUtil = playerUtil;
		this.fightUtil = fightUtil;
		this.gameState = gameState;
		this.commonUtil = commonUtil;
		this.flag = flag;
		this.count = count;
	}

	public void start(Player player, Player enemy) throws IOException {
		ResultNAO response = new ResultNAO();
		level++;
		if(level > 1 && player.getHp() <= 1) level = 0;
		welcomeMessage(level, player);
		while(flag) {
			if(level > 1 && player.getHp() > 1) {
				
				response = commonUtil.validateUserInputIsInt();
				if(response.getCode() == ResponseCode.SUCCESS) {
					switch(response.getData()) {

					case 1:	levelUp(player, enemy);
					break;

					case 2: quit(player, enemy);
					break;

					default : System.out.println(MenuConstants.INVALID_CHOICE);
					}
				}
			}else {
				response = commonUtil.validateUserInputIsInt();
				if(response.getCode() == ResponseCode.SUCCESS) {
				switch(response.getData()) {
				
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

	public void levelUp(Player player, Player enemy) throws IOException {
		displayStats(player);
		enemy = createEnemy(enemy);
		displayStats(enemy);
		readyForFight(player, enemy);
	}

	public Player createEnemy(Player enemy) {
		int enemyEnumIndex = 0;
		int counter = 0;
		do {
			counter++;
			if(counter > 1)
				System.out.println(MenuConstants.INVALID_CHOICE);
			System.out.println(MenuConstants.NEW_GAME_MSG_2);
			resetCount();
			Enemy.stream().forEach( a -> System.out.println(getCount()  +a.getDisplayName()));
			
			ResultNAO response = new ResultNAO();
			response = commonUtil.validateUserInputIsInt();
			if(response.getCode() == ResponseCode.SUCCESS) {
				enemyEnumIndex = response.getData();
			}
			
		}while(validateEnemy(enemyEnumIndex));
		enemyEnumIndex--;
		String enemyName = Enemy.values()[enemyEnumIndex].getDisplayName();
		Random random = new Random();
		Weapon weaponName = Weapon.values()[random.nextInt(Weapon.values().length)];

		enemy = playerUtil.createNewPlayer(enemyName, weaponName);
		System.out.println("Your must be brave to choose " +enemy.getName() +"!\n");
		
		return enemy;
	}
	
	public void newGame(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.NEW_GAME_STORY);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		//sc.nextLine();
		String playerName;
		do {
			System.out.println(MenuConstants.NEW_GAME_QUESTION_1);
			System.out.println(MenuConstants.SEPARATOR_STR_2);
			playerName = sc.nextLine();
		}while(validatePlayerName(playerName));
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println("\nHey! " +playerName +MenuConstants.GREETING_MSG);
		Weapon weaponName = selectWeapon();
		player = playerUtil.createNewPlayer(playerName, weaponName);
		System.out.println(MenuConstants.NEW_GAME_MSG_1);
		displayStats(player);
		enemy = createEnemy(enemy);
		displayStats(enemy);
		readyForFight(player, enemy);
	}
	
	private Weapon selectWeapon() {
		int weaponEnumIndex = 0;
		int counter = 0;
		do {
			counter++;
			if(counter > 1)
				System.out.println(MenuConstants.INVALID_CHOICE);
			System.out.println(MenuConstants.NEW_GAME_QUESTION_2);
			System.out.println(MenuConstants.SEPARATOR_STR_2);
			resetCount();
			Weapon.stream().forEach( a -> System.out.println(getCount()  +a.getDisplayName()));
			ResultNAO response = new ResultNAO();
			response = commonUtil.validateUserInputIsInt();
			if(response.getCode() == ResponseCode.SUCCESS) {
				weaponEnumIndex = response.getData();
			}
		}while(validateWeapon(weaponEnumIndex));
		weaponEnumIndex--;
		Weapon weaponName = Weapon.values()[weaponEnumIndex];
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(weaponName.getDisplayName() + " is an excellent choice. \n" );
		return weaponName;
	}

	private boolean validateWeapon(int weaponEnumIndex) {
		weaponEnumIndex--;
		if(weaponEnumIndex > Weapon.values().length || weaponEnumIndex < 0)
			return true;
		String weaponName = Weapon.values()[weaponEnumIndex].getDisplayName();
		for (Weapon enemyEnum : Weapon.values()) {
			if (enemyEnum.getDisplayName().equalsIgnoreCase(weaponName)) {
				return false;
			}
		}
		return true;
	}

	private boolean validatePlayerName(String playerName) {
		if (playerName.matches("^[\\p{L} .'-]+$")) {
			return false;
		}
		System.out.println("Hey! there " +playerName +" is no valid name. What's your real name??\nEnter your real name.");
		return true;
	}

	private boolean validateEnemy(int enemyEnumIndex) {
		enemyEnumIndex--;
		if(enemyEnumIndex > Enemy.values().length || enemyEnumIndex < 0)
			return true;
		String enemyName = Enemy.values()[enemyEnumIndex].getDisplayName();
		for (Enemy enemyEnum : Enemy.values()) {
			if (enemyEnum.getDisplayName().equalsIgnoreCase(enemyName)) {
				return false;
			}
		}
		return true;
	}

	private void resetCount() {
		count = 1;
	}
	private String getCount() {
		return count++ +". ";
	}

	public void readyForFight(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.NEW_GAME_MSG_3);
		ResultNAO response = new ResultNAO();
		response = commonUtil.validateUserInputIsInt();
		if(response.getCode() == ResponseCode.SUCCESS) {
			
			switch(response.getData()) {
			case 1: fightUtil.fight(player, enemy);
			break;

			case 2: quit(player, enemy);
			break;

			default : System.out.println(MenuConstants.INVALID_CHOICE);
			readyForFight(player, enemy);
			}
		}
		else if(response.getCode() == ResponseCode.ERROR) {
			System.out.println("Oops! Invalid Input");
			throw new IOException();
		}
	}

	public void newPlayersQuit() throws IOException {
		System.out.println(MenuConstants.QUIT_GAME_MSG_3);
		ResultNAO response = new ResultNAO();
		response = commonUtil.validateUserInputIsInt();
		if(response.getCode() == ResponseCode.SUCCESS) {
			switch(response.getData()) {

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
		}else if(response.getCode() == ResponseCode.ERROR) {
			System.out.println("Oops! Invalid Input");
			throw new IOException();
		}
		
	}

	public void quit(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.QUIT_GAME_MSG_1);
		ResultNAO response = new ResultNAO();
		response = commonUtil.validateUserInputIsInt();
		if(response.getCode() == ResponseCode.SUCCESS) {
			switch(response.getData()) {

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
		}else if(response.getCode() == ResponseCode.ERROR) {
			throw new IOException();
		}
	}

	public void restart(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.RESTART_MSG);
		ResultNAO response = new ResultNAO();
		response = commonUtil.validateUserInputIsInt();
		if(response.getCode() == ResponseCode.SUCCESS) {

			switch(response.getData()) {

			case 1: System.out.println("Restarting the game");
			start(player, enemy);
			break;

			case 2: quit(player, enemy);
			break;

			default : System.out.println(MenuConstants.INVALID_CHOICE);
			restart(player, enemy);
			}
		}else if(response.getCode() == ResponseCode.ERROR) {
			System.out.println("Oops! Invalid Input");
			throw new IOException();
		}
	}

	public void playNextLevel(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.CONTINUE_MSG);
		ResultNAO response = new ResultNAO();
		response = commonUtil.validateUserInputIsInt();
		if(response.getCode() == ResponseCode.SUCCESS) {

			switch(response.getData()) {

			case 1: System.out.println("Loading next level...");
			start(player, enemy);
			break;

			case 2: quit(player, enemy);
			break;

			default : System.out.println(MenuConstants.INVALID_CHOICE);
			playNextLevel(player, enemy);
			}
		}else {
			System.out.println("Oops! Invalid Input");
			throw new IOException();
		}
	}

	public void gameOver(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.GAMEOVER_MSG);
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		restart(player, enemy);
	}

	public void victory(Player player, Player enemy) throws IOException {
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.VICTORY_MSG);
		System.out.println(MenuConstants.SEPARATOR_STR_1);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		playNextLevel(player, enemy);
	}

	private void displayStats(Player player) {
		System.out.println(MenuConstants.SEPARATOR_STR_2);
		System.out.println(player.getName() +" stats are:\t|\t" +"HP: " +player.getHp() +"\t|\t" +"Reputation: " +player.getReputation() +"\t|\t" +"Weapon: "
		+player.getWeapon());
		System.out.println(MenuConstants.SEPARATOR_STR_2);
	}

}
