package com.ninjaclub.dashboard.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.ninjaclub.dashboard.model.enums.Weapon;

public class PlayerUtilTest {

	String playerName;
	
	PlayerUtil playerUtil;
	
	Weapon weapon;
			
	@Before
	public void init() {
		playerName = "HARRY POTTER";
		weapon = Weapon.values()[1];
		playerUtil = new PlayerUtil();
	}
	
	@Test
	public void testCreateNewPlayer() {
		assertNotNull(playerUtil.createNewPlayer(playerName, weapon));
	}

}
