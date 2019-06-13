package com.auto.puzzle.commons.menus;

import com.auto.puzzle.commons.enums.menus.GameMenuItem;
import com.auto.puzzle.configuration.ContextHandler;
import com.auto.puzzle.configuration.GameHandler;
import com.auto.puzzle.games.BaseGame;

public class GameMenu implements BaseMenu<GameMenuItem> {

	GameHandler<? extends BaseGame> gameHandler;

	@SuppressWarnings("unchecked")
	@Override
	public void printOptions() {
		gameHandler = ContextHandler.getBean(GameHandler.class);

		io.showMessageWithSpace("Please choose one of those options:");

		for(GameMenuItem item : GameMenuItem.values())
			io.showMessageWithNewLine(formatMessage(item, Integer.toString(item.getKey())));
	}

	@Override
	public BaseMenu<?> handleInput(GameMenuItem input) {
		switch (input) {
		case FIRST:
			return ContextHandler.getBean(MainMenu.class);
		case BACK:
			return ContextHandler.getBean(CharacterMenu.class);
		case EXPLORE:
			gameHandler.explore();
			break;
		case FIGHT:
			gameHandler.fight();
			break;
		case SAVE:
			gameHandler.saveGame();
			break;
		default:
			break;
		}

		return null;
	}

	@Override
	public GameMenuItem getMenuItemClass() {
		return GameMenuItem.EXPLORE;
	}
}
