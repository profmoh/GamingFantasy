package com.auto.puzzle.commons.menus;

import static com.auto.puzzle.configuration.Config.SEPARATOR;
import static com.auto.puzzle.configuration.Config.TAB;

import java.util.logging.Logger;

import com.auto.puzzle.configuration.ContextHandler;
import com.auto.puzzle.configuration.IOHandler;
import com.auto.puzzle.games.BaseGame;;

public interface BaseMenu<I extends BaseMenuItem<I>> {
	final static Logger LOG = Logger.getLogger(BaseMenu.class.getName());

	IOHandler io = ContextHandler.getBean(IOHandler.class);

	void printOptions();

	BaseMenu<?> handleInput(I input);

	I getMenuItemClass();

	default I selectOption() {
//		String value = io.readUserInputAsString();
		int keyValue = BaseMenu.io.tryReadingInputAsInt();

		return getMenuItemClass().getValue(keyValue);
	}

	default I showMenu() {
		printOptions();

		return selectOption();
	}

	default String formatMessage(BaseGame game, String key) {
		return TAB + key + SEPARATOR + game.getName();
	}

	default String formatMessage(BaseMenuItem<?> item, String key) {
		return TAB + key + SEPARATOR + item.getDescription();
	}
}
