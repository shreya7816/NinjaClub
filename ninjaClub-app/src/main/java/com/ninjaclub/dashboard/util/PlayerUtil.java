package com.ninjaclub.dashboard.util;

import com.ninjaclub.dashboard.constants.PlayerConstants;
import com.ninjaclub.dashboard.model.Player;

public class PlayerUtil {

	public Player createNewPlayer(String name){
		Player player = new Player();
		player.setName(name);
		player.setHp(PlayerConstants.MAX_HP);
		player.setAttackDamage(PlayerConstants.MIN_ATTACK_DAMAGE);
		player.setReputation(PlayerConstants.INITIAL_REPUTATION);
		player.setHealthPotions(PlayerConstants.MAX_HEALTH_POTIONS);
		return player;
	}
}
