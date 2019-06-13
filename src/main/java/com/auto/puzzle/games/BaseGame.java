package com.auto.puzzle.games;

import com.auto.puzzle.commons.enums.GameEnum;

public interface BaseGame {

	GameEnum getGameEnum();

	default String getName() {
		return getGameEnum().getName();
	}

	void explore(String characterName);

	void fight(String characterName);

	void gainExperience();

	void saveGame(String characterName);

	void loadGame(String characterName);
}
