package com.ninjaclub.dashboard.model;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ninjaclub.dashboard.constants.PlayerConstants;
import com.ninjaclub.dashboard.model.Player;

public class PlayerTest {

	Player player;
	
	Player enemy;
	
	@Before
	public void init() {
		player = new Player("THOR", PlayerConstants.MAX_HP, PlayerConstants.INITIAL_REPUTATION, PlayerConstants.MAX_HEALTH_POTIONS);
		enemy = new Player("THANOS", PlayerConstants.MAX_HP, PlayerConstants.INITIAL_REPUTATION, PlayerConstants.MAX_HEALTH_POTIONS);
	}
	
	@Test
	public void testdrinkHealthPostion() {
		assertEquals("Health Potions Left", 4 , player.drinkHealthPostion());
	}
	
	@Test
	public void testProcessFight() {
		player.processFight(enemy);
		System.out.println(player.getName() +" strike " +enemy.getName() +" and gain reputation " +player.getReputation() +". " +player.getName()+" HP is "
				+player.getHp() +". " +enemy.getName() +" reputation is " +enemy.getReputation() +" and HP is " +enemy.getHp()
				+". \n" +player.getName() +" have " +player.getHealthPotionsNum() +" health poitions left!");
		
	}
}
