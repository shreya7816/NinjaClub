package com.ninjaclub.dashboard.model;

public class Player {

private String name;
	
	private int hp;
	
	private int reputation;
	
	private int healthPotions;
	
	private int attackDamage;
	
	private String armor;

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

	public int getHealthPotions() {
		return healthPotions;
	}

	public void setHealthPotions(int healthPotions) {
		this.healthPotions = healthPotions;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public String getArmor() {
		return armor;
	}

	public void setArmor(String armor) {
		this.armor = armor;
	}
	
	
}
