package com.ninjaclub.dashboard.model;

import java.io.Serializable;

import com.ninjaclub.dashboard.util.GameStateUtil;

/**
 * @author shreya
 *
 */
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

	@Override
	public String toString() {
		return "GameState [player=" + player + ", enemy=" + enemy + ", level=" + level + "]";
	}

	public void setGameState(GameState gameState) {
		this.player = gameState.player;
		this.enemy = gameState.enemy;
		this.level = gameState.level;
	}
	
	public void saveAndQuit() {
		GameStateUtil gameStateUtil = new GameStateUtil(this);
		try {
			gameStateUtil.writeGameStateJDK7();
			//gameStateUtil.writeGameState();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GameState resumeGame() {
		GameStateUtil gameStateUtil = new GameStateUtil(this);
		setGameState(gameStateUtil.readGameStateJDK7());
		//setGameState(gameStateUtil.readGameState());  
		System.out.println("Loading game...\n" +this.toString());
		return this;
	}

	
	
}
