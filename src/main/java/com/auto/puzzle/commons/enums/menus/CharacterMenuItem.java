package com.auto.puzzle.commons.enums.menus;

import com.auto.puzzle.commons.menus.BaseMenuItem;

public enum CharacterMenuItem implements BaseMenuItem<CharacterMenuItem> {
	CREATE(1, "create"),
	RESUME(2, "resume"),
	BACK(0, "back");

	private int key;
	private String description;

	private CharacterMenuItem(int key, String description) {
		this.key = key;
		this.description = description;
	}

	public CharacterMenuItem getValue(int value) {
		switch (value) {
		case 1:
			return CREATE;
		case 2:
			return RESUME;
		case 0:
			return BACK;
		default:
			return null;
		}

//		return CharacterMenuItem.valueOf(value);
	}

	public int getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
}
