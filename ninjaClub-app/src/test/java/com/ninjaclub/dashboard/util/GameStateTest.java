package com.ninjaclub.dashboard.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ninjaclub.dashboard.constants.PlayerConstants;
import com.ninjaclub.dashboard.model.GameState;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Weapon;

public class GameStateTest {

String playerName;
	
	Player player;
	
	Player enemy;
	
	GameState gameState;
			
	@Before
	public void init() {
		player = new Player("THOR", PlayerConstants.MAX_HP, PlayerConstants.INITIAL_REPUTATION, PlayerConstants.MAX_HEALTH_POTIONS, Weapon.values()[1]);
		enemy = new Player("THANOS", PlayerConstants.MAX_HP, PlayerConstants.INITIAL_REPUTATION, PlayerConstants.MAX_HEALTH_POTIONS, Weapon.values()[2]);
		gameState = new GameState(player, enemy, 1);
	}
	
	@Test
	public void testSaveAndResume() {
		gameState.saveAndQuit();
		assertEquals("Are the game states same: ", gameState, gameState.resumeGame());
	}
	
}
	
	
	
	

