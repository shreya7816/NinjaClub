package com.ninjaclub.dashboard.model;

import java.util.Random;

import com.ninjaclub.dashboard.constants.PlayerConstants;
import com.ninjaclub.dashboard.util.FightUtil;
import com.ninjaclub.dashboard.util.GameUtil;

/**
 * @author shreya
 *
 */
public class Player {

	private String name;
	
	private int hp;
	
	private int reputation;
	
	private int healthPotionsNum;
	
//	private int attackDamage;
	
	private String armor;
	
	FightUtil fightUtil;
	
	GameUtil gameUtil;
	
	
	
	public Player() {
		this.fightUtil = new FightUtil();
		this.gameUtil = new GameUtil();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public String getArmor() {
		return armor;
	}

	public void setArmor(String armor) {
		this.armor = armor;
	}
	
	public int getHealthPotionsNum() {
		return healthPotionsNum;
	}

	public void setHealthPotionsNum(int healthPotionsNum) {
		this.healthPotionsNum = healthPotionsNum;
	}

	
	public void processFight(Player enemy) {
		Random random = new Random();
		int damageDone = random.nextInt(PlayerConstants.PLAYER_ATTACK_DAMAGE);
		int damageTaken = random.nextInt(PlayerConstants.ENEMY_ATTACK_DAMAGE);
		int enemyHP = enemy.getHp();
		int enemyReputation = enemy.getReputation();
		int playerHP = this.getHp();
		int playerReputation = this.getReputation();
		
		playerReputation += damageDone;
		playerHP -= damageTaken;
		this.setHp(playerHP);
		this.setReputation(playerReputation);
		
		enemyReputation += damageTaken;
		enemyHP -= damageDone;
		enemy.setHp(enemyHP);
		enemy.setReputation(enemyReputation);
		
		System.out.println("You strike " +enemy.getName() +" and gain reputation " +this.getReputation() +". Your HP is "
				+this.getHp() +". " +enemy.getName() +" reputation is " +enemy.getReputation() +" and HP is " +enemy.getHp()
				+". \nYou have " +this.getHealthPotionsNum() +" health poitions left!");
		
	}
	
	public void drinkHealthPostion() {
		if(this.getHealthPotionsNum() > 0) {
			int playerHP = this.getHp();
			int playerHealthPostionNum = this.getHealthPotionsNum();
			playerHP += PlayerConstants.HEALTH_POTION_HEAL_AMOUNT;
			this.setHp(playerHP);
			playerHealthPostionNum--;
			this.setHealthPotionsNum(playerHealthPostionNum);
			System.out.println("You have "+this.getHealthPotionsNum() +" health potions remaining. Use them wisely. Your new HP is: " +this.getHp() +".");
			
		}else {
			System.out.println("Sorry! No health potions left.");
		}
		
	}

}
