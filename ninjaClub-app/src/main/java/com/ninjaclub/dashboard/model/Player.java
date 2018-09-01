package com.ninjaclub.dashboard.model;

import java.io.Serializable;
import java.util.Random;

import com.ninjaclub.dashboard.constants.PlayerConstants;

/**
 * @author shreya
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private int hp;
	
	private int reputation;
	
	private int healthPotionsNum;
	
	//private String armor;
	
	public Player() {
		
	}
	
	public Player(String name, int hp, int reputation, int healthPotionsNum) {
		super();
		this.name = name;
		this.hp = hp;
		this.reputation = reputation;
		this.healthPotionsNum = healthPotionsNum;
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

	/*public String getArmor() {
		return armor;
	}

	public void setArmor(String armor) {
		this.armor = armor;
	}*/
	
	public int getHealthPotionsNum() {
		return healthPotionsNum;
	}

	public void setHealthPotionsNum(int healthPotionsNum) {
		this.healthPotionsNum = healthPotionsNum;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", hp=" + hp + ", reputation=" + reputation + ", healthPotionsNum="
				+ healthPotionsNum + "]";
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
		
	}
	
	public int drinkHealthPostion() {
		if(this.getHealthPotionsNum() > 0) {
			int playerHP = this.getHp();
			int playerHealthPotionNum = this.getHealthPotionsNum();
			playerHP += PlayerConstants.HEALTH_POTION_HEAL_AMOUNT;
			this.setHp(playerHP);
			playerHealthPotionNum--;
			this.setHealthPotionsNum(playerHealthPotionNum);
			return playerHealthPotionNum;
			
		}else {
			return 0;
		}
	}

}
