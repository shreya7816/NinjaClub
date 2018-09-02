package com.ninjaclub.dashboard.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.ninjaclub.dashboard.constants.PlayerConstants;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Enemy;
import com.ninjaclub.dashboard.model.enums.Weapon;

public class GameUtilTest {

	GameUtil gameUtil;
	
	Player enemy;
	
	@Before
	public void init() {
		gameUtil = new GameUtil();
		enemy = new Player(Enemy.values()[3].getDisplayName(), PlayerConstants.MAX_HP, PlayerConstants.INITIAL_REPUTATION, PlayerConstants.MAX_HEALTH_POTIONS,Weapon.values()[3]);

	}
	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testCreateEnemyForNotNull() {
		systemInMock.provideLines("4", "another line");
		assertNotNull(gameUtil.createEnemy(enemy));
	}
	
	@Test
	public void testCreateEnemyForCorrectValue() {
		systemInMock.provideLines("4", "another line");
		assertEquals(enemy.getName(), gameUtil.createEnemy(enemy).getName());
	}
	
	@Test
	public void testselectWeapon() {
		systemInMock.provideLines("2", "another line");
		assertEquals(Weapon.values()[1], gameUtil.selectWeapon());
	}
	
	@Test
	public void tesvalidateWeaponForCorrectIndex() {
		assertTrue(!gameUtil.validateWeapon(2));
	}
	
	@Test
	public void tesvalidateWeaponForWrongIndex() {
		assertFalse(!gameUtil.validateWeapon(100));
	}
	
	
}
