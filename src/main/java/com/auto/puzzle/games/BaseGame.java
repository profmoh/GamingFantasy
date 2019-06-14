package com.auto.puzzle.games;

public interface BaseGame {

	String getName();

	void explore(String characterName);

	void fight(String characterName);

	void gainExperience();

	void saveGame(String characterName);

	void loadGame(String characterName);
}
