package com.auto.puzzle.commons.menus;

import java.util.List;

import com.auto.puzzle.commons.enums.menus.MainMenuItem;
import com.auto.puzzle.configuration.ContextHandler;
import com.auto.puzzle.configuration.GameHandler;
import com.auto.puzzle.games.BaseGame;

public class MainMenu implements BaseMenu<MainMenuItem> {

	@Override
	public void printOptions() {
		io.showMessageWithNewLine("\nWelcome .. select a game ^_^");

		io.showMessageWithSpace("Please choose one of those options:");

		List<BaseGame> gameList = ContextHandler.getGameList();

		if(gameList != null) {
			for(int i = 0; i < gameList.size(); i++)
				io.showMessageWithNewLine(formatMessage(gameList.get(i), Integer.toString(i + 1)));
		}

		io.showMessageWithNewLine(formatMessage(MainMenuItem.EXIT, Integer.toString(0)));
	}

	@Override
	public BaseMenu<?> handleInput(MainMenuItem input) {
		switch (input) {
		case EXIT:
			io.showMessageWithNewLine("Good Bey");
			System.exit(1);
			break;
		case GAME:
			int key = Integer.parseInt(input.getDescription());

			List<BaseGame> gameList = ContextHandler.getGameList();

			if(gameList.size() >= key) {
				Class<? extends BaseGame> gameClass = gameList.get(key - 1).getClass();

				@SuppressWarnings({ "unchecked", "rawtypes" })
				GameHandler<? extends BaseGame> handler = new GameHandler(ContextHandler.getBean(gameClass));

				ContextHandler.addClassToContext(GameHandler.class, handler);

				return ContextHandler.getBean(CharacterMenu.class);
			}
		default:
			break;
		}

		return null;
	}

	@Override
	public MainMenuItem getMenuItemClass() {
		return MainMenuItem.EXIT;
	}
}
