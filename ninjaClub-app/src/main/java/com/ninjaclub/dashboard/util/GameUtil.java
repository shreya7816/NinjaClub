package com.ninjaclub.dashboard.util;

import java.util.Scanner;

import com.ninjaclub.dashboard.constants.MenuConstants;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Enemy;

public class GameUtil {

	boolean flag;

	PlayerUtil playerUtil;
	
	public static final Scanner sc = new Scanner(System.in);

	public GameUtil() {
		this.flag = true;
		this.playerUtil = new PlayerUtil();
		//this.enemy = new Enemy();
	}

	public void start() {
		welcomeMessage();
		while(flag) {
			switch(sc.nextInt()) {
			case 1: newGame();
			break;

			case 2: resumeGame();
			break;

			case 3: quit();
			break;

			}

		}
	}

	public void welcomeMessage() {
		System.out.println(MenuConstants.WELCOME_MSG);
	}

	public void newGame() {
		Player player = new Player();
		Player enemy = new Player();
		System.out.println(MenuConstants.NEW_GAME_STORY);
		System.out.println(MenuConstants.NEW_GAME_QUESTION_1);
		sc.nextLine();
		try {
			player = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Hey! " +player.getName() +MenuConstants.GREETING_MSG);
		System.out.println(MenuConstants.NEW_GAME_MSG_1);
		System.out.println("Your stats are: \n" +"HP:" +player.getHp() +"\n" +"Reputation:" +player.getReputation());
		System.out.println(MenuConstants.NEW_GAME_MSG_2);
		
		Enemy.stream().forEach(System.out::println);
		try {
			enemy = playerUtil.createNewPlayer(sc.nextLine());
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Your must be brave to choose " +enemy.getName() +"!\n"
				+enemy.getName() +"'s stats are: \n" +"HP:" +enemy.getHp() +"\n" +"Reputation:" +enemy.getReputation());
				
		System.out.println(MenuConstants.NEW_GAME_MSG_3);
		switch(sc.nextInt()) {
		case 1: fight();
		break;

		case 2: quit();
		break;
		}
	}

	public void quit() {
		System.out.println(MenuConstants.QUIT_GAME_MSG_1);
		switch(sc.nextInt()) {

		case 1: flag = false;
		System.exit(0);
		break;

		case 2: start();
		}

	}

	public void fight() {
		System.out.println("FIGHTING");
	}
	public void resumeGame() {

	}
}
