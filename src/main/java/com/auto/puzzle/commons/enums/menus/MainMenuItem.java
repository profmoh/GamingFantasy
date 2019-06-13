package com.auto.puzzle.commons.enums.menus;

import com.auto.puzzle.commons.menus.BaseMenuItem;

public enum MainMenuItem implements BaseMenuItem<MainMenuItem> {
	EXIT("exit"),
	GAME("");

	private String description;

	private MainMenuItem(String description) {
		this.description = description;
	}

	public MainMenuItem getValue(int value) {
		if(value != 0) {
			GAME.description = Integer.toString(value);
			return GAME;
//			value = GAME.toString();
		}

		return EXIT;
//		return MainMenuItem.valueOf(value);
	}

	public String getDescription() {
		return description;
	}
}
