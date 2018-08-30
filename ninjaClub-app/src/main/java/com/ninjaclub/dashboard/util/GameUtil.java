package com.ninjaclub.dashboard.util;

import java.util.Scanner;

import com.ninjaclub.dashboard.model.Player;

public class GameUtil {

boolean flag;
	
	PlayerUtil playerUtil;
	
	public static final Scanner sc = new Scanner(System.in);
	
	
	
	public GameUtil() {
		this.flag = true;
		this.playerUtil = new PlayerUtil();
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
		System.out.println("################ Welcome to Ninja Club #######################");
		System.out.println("1. New Game");
		System.out.println("2. Resume");
		System.out.println("3. Quit");
	}
	
	public void newGame() {
		System.out.println("what would you like to call yourself?");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println(name);
		Player player = playerUtil.createNewPlayer(name);
		
		System.out.println("Hey! " +player.getName() +" are you ready for a fight?");
		System.out.println("With every fight your reputation and damage you can cause increases!");
		System.out.println("Your stats are: \n" +"HP:" +player.getHp() +"\n" +"Reputation:" +player.getReputation());
		System.out.println("are you ready for a fight? \n1. Yes \n2.No");
		switch(sc.nextInt()) {
		case 1: fight();
		break;
		
		case 2: quit();
		break;
		}
	}
	
	public void quit() {
		System.out.println("Don't be coward! Are you sure you want to leave?");
		System.out.println("1. Quit");
		System.out.println("2. Play");
		switch(sc.nextInt()) {
		
		case 1: flag = false;
		System.exit(0);
		break;
		
		case 2: start();
		}
		
	}
	
	public void fight() {
		System.out.println("");
	}
	public void resumeGame() {
		
	}
}
