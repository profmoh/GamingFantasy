package com.auto.puzzle.commons.enums.menus;

import com.auto.puzzle.commons.menus.BaseMenuItem;

public enum GameMenuItem implements BaseMenuItem<GameMenuItem> {
	EXPLORE(1, "explore"),
	FIGHT(2, "fight"),
	SAVE(3, "save"),
	BACK(0, "back"),
	FIRST(-1, "first menu");

	private int key;
	private String description;

	private GameMenuItem(int key, String description) {
		this.key = key;
		this.description = description;
	}

	public GameMenuItem getValue(int value) {
		switch (value) {
		case -1:
			return FIRST;
		case 0:
			return BACK;
		case 1:
			return EXPLORE;
		case 2:
			return FIGHT;
		case 3:
			return SAVE;
		default:
			return null;
		}
	}

	public int getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
}
