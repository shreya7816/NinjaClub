package com.ninjaclub.dashboard.model.enums;

import java.util.stream.Stream;

public enum Weapon {

	SWORD("Sword"),
	AXE("Axe"),
	HAMMER("Hammer"),
	SPEAR("Spear"),
	DAGGER("Dagger"),
	ROPE_DART("Rope Dart"),
	BLOWGUN("Blowgun"),
	BIDENT("Bident");
	
	private String displayName;
	
	private Weapon(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public static Stream<Weapon> stream() {
	     return Stream.of(Weapon.values()); 
	}
	
}
