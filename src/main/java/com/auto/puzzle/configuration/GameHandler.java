package com.auto.puzzle.configuration;

import com.auto.puzzle.games.BaseGame;

public class GameHandler<T extends BaseGame> {

	IOHandler io = ContextHandler.getBean(IOHandler.class);

	private T game;
	private String characterName;

	public GameHandler(T game) {
		this.game = game;
	}

	public void explore() {
		game.explore(this.characterName);
	}

	public void fight() {
		game.fight(this.characterName);
	}

	public void saveGame() {
		game.saveGame(this.characterName);
	}

	public void loadGame() {
		game.loadGame(this.characterName);
	}

	public T getGame() {
		return game;
	}
	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
}
