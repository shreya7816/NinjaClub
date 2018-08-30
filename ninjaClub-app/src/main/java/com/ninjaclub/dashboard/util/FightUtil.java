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

				
			//	int i =sc.nextInt();
			//	System.out.println("input" +i);
				switch(sc.nextInt()) {

				case 1: player.processFight(enemy);
				break;

				case 2: player.drinkHealthPostion();
				break;

				case 3: fightDialogueFlag = false;
				gameUtil.quit();
				break;

				}
			}
		}
	}
	public void fightDialogue() {
		System.out.println(MenuConstants.FIGHT_DIALOGUE);
	}
}
