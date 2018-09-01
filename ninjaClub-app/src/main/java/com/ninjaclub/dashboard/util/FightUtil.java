package com.ninjaclub.dashboard.util;

import java.util.Scanner;

import com.ninjaclub.dashboard.constants.MenuConstants;
import com.ninjaclub.dashboard.model.Player;

public class FightUtil {

	Player player;
	
	GameUtil gameUtil;
	
	public static final Scanner sc = new Scanner(System.in);
	
	public FightUtil() {
		//this.player =  new Player();
		//this.gameUtil = new GameUtil();
	}
	
	public void fight(Player player, Player enemy) {
		boolean fightDialogueFlag = true;
		this.player = player;
		this.gameUtil = new GameUtil();
		while(fightDialogueFlag) {
			
			if(player.getHp() < 1) {
				fightDialogueFlag = false;
				gameUtil.gameOver(player, enemy);
			}else if(enemy.getHp() < 1) {
				fightDialogueFlag = false;
				gameUtil.victory(player, enemy);
			}else {
				fightDialogue();

				switch(sc.nextInt()) {

				case 1: player.processFight(enemy);
				displayFightStats(player, enemy);
				break;

				case 2: int healthPotionsLeft = player.drinkHealthPostion();
				if(healthPotionsLeft == 0 )
					System.out.println("Sorry! No health potions left.");
				else
					System.out.println("You have "+player.getHealthPotionsNum() +" health potions left. Use them wisely. Your new HP is: " +player.getHp() +".");
				
				break;

				case 3: fightDialogueFlag = false;
				gameUtil.quit(player, enemy);
				break;

				default: System.out.println("Enter valid choice");
				}
			}
		}
	}
	
	private void displayFightStats(Player player, Player enemy) {
		if(player.getHp() <= 0 ) {
			System.out.println("You are badly hurt in the fight. \nWarning HP too low. You are too weak to fight now.");
		}else if(enemy.getHp() <= 0) {
			System.out.println(enemy.getName() +" died in the fight.");
		}else {
			System.out.println("You strike " +enemy.getName() +" and gain reputation " +player.getReputation() +". Your HP is "
					+player.getHp() +". " +enemy.getName() +" reputation is " +enemy.getReputation() +" and HP is " +enemy.getHp()
					+". \nYou have " +player.getHealthPotionsNum() +" health poitions left!");
		}

	}

	public void fightDialogue() {
		System.out.println(MenuConstants.FIGHT_DIALOGUE);
	}
}
