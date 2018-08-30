package com.ninjaclub.dashboard.model.enums;

import java.util.stream.Stream;

/**
 * @author shreya
 *
 */
public enum Enemy {

	DARTH_VADER("Darth Vader"),
	LORD_VOLDEMORT("Lord Voldemort"),
	BELLATRIX_LESTRANGE("Bellatrix Lestrange"),
	THE_JOKER("The Joker"),
	THANOS("Thanos"),
	HYDRA("Hydra"),
	PINHEAD("Pinhead"),
	DR_EVIL("Dr_Evil"),
	WHITE_WITCH("White Witch"),
	LEX_LUTHOR("Lex Luthor"),
	CAT_WOMAN("Catwoman");
	
	private String displayName;

	private Enemy(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	 public static Stream<Enemy> stream() {
	     return Stream.of(Enemy.values()); 
		
	}
	
} 









