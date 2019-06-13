package com.auto.puzzle.commons.menus;

import com.auto.puzzle.commons.enums.menus.CharacterMenuItem;
import com.auto.puzzle.configuration.ContextHandler;
import com.auto.puzzle.configuration.GameHandler;
import com.auto.puzzle.games.BaseGame;

public class CharacterMenu implements BaseMenu<CharacterMenuItem> {

	GameHandler<? extends BaseGame> gameHandler;

	@SuppressWarnings("unchecked")
	@Override
	public void printOptions() {
		gameHandler = ContextHandler.getBean(GameHandler.class);

		io.showMessageWithSpace("lets play " + gameHandler.getGame().getName());
		io.showMessageWithSpace("Please choose one of those options:");

		for(CharacterMenuItem item : CharacterMenuItem.values())
			io.showMessageWithNewLine(formatMessage(item, Integer.toString(item.getKey())));
	}

	@Override
	public BaseMenu<?> handleInput(CharacterMenuItem input) {
		String name;

		switch (input) {
		case BACK:
			io.showMessageWithNewLine(input.getDescription());
			return ContextHandler.getBean(MainMenu.class);
		case CREATE:
		case RESUME:
			name = io.readUserInputAsString("What is your name :");

			gameHandler.setCharacterName(name);

			ContextHandler.addClassToContext(GameHandler.class, gameHandler);

			if(input.equals(CharacterMenuItem.RESUME))
				gameHandler.loadGame();

			return ContextHandler.getBean(GameMenu.class);
		default:
			return null;
		}
	}

	@Override
	public CharacterMenuItem getMenuItemClass() {
		return CharacterMenuItem.CREATE;
	}
}
