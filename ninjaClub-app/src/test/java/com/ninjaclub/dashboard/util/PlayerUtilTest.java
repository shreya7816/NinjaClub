package com.ninjaclub.dashboard.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class PlayerUtilTest {

	String playerName;
	
	PlayerUtil playerUtil;
			
	@Before
	public void init() {
		playerName = "HARRY POTTER";
		playerUtil = new PlayerUtil();
	}
	
	@Test
	public void testCreateNewPlayer() {
		assertNotNull(playerUtil.createNewPlayer(playerName));
	}

}
