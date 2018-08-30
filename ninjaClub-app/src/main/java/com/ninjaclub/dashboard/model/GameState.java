package com.ninjaclub.dashboard.model;

import java.io.Serializable;

public class GameState implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Player player;
	
	Player enemy;
	
	int level;

	
	public GameState() {
		
	}

	public GameState(Player player, Player enemy, int level) {
		super();
		this.player = player;
		this.enemy = enemy;
		this.level = level;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	

	
	
}
