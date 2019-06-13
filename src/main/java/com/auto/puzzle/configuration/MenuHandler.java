package com.auto.puzzle.configuration;

import com.auto.puzzle.commons.menus.BaseMenu;
import com.auto.puzzle.commons.menus.BaseMenuItem;

public class MenuHandler<I extends BaseMenuItem<I>> {

	private BaseMenu<I> menu;

	public MenuHandler(BaseMenu<I> menu) {
		super();

		this.menu = menu;
	}

	@SuppressWarnings("unchecked")
	public void showMenu() {
		do {
			I input = menu.showMenu();

			if(input == null) {
				BaseMenu.io.showMessageWithNewLine("\ninvalied input");
				continue;
			}

			BaseMenu<?> newMenu = menu.handleInput(input);

			if (newMenu != null)
				menu = ContextHandler.getBean(newMenu.getClass());
		} while (true);
	}
}
