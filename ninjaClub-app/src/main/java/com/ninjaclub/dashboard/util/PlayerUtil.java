package com.ninjaclub.dashboard.util;

import com.ninjaclub.dashboard.constants.PlayerConstants;
import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.model.enums.Weapon;

/**
 * @author shreya
 *
 */
public class PlayerUtil {

	public Player createNewPlayer(String name, Weapon weaponName){
		Player player = new Player();
		player.setName(name);
		player.setHp(PlayerConstants.MAX_HP);
		player.setReputation(PlayerConstants.INITIAL_REPUTATION);
		player.setHealthPotionsNum(PlayerConstants.MAX_HEALTH_POTIONS);
		player.setWeapon(weaponName);
		return player;
	}
}
