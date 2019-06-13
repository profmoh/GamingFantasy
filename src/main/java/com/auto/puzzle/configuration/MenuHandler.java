package com.auto.puzzle.configuration;

import com.auto.puzzle.commons.menus.BaseMenu;
import com.auto.puzzle.commons.menus.BaseMenuItem;

public class MenuHandler<I extends BaseMenuItem<I>> {

	private BaseMenu<I> menu;

	public MenuHandler(BaseMenu<I> menu) {
		super();

		this.menu = menu;
	}

	public void showMenuLoop() {
		do {
			showMenu();
			selectOption();
		} while (true);
	}

	public void showMenu() {
		menu.showMenu();
	}

	@SuppressWarnings("unchecked")
	public void selectOption() {
		I input = menu.selectOption();

		if(input == null) {
			BaseMenu.io.showMessageWithNewLine("invalied input");
			return;
		}

		BaseMenu<?> newMenu = menu.handleInput(input);

		if (newMenu != null)
			menu = ContextHandler.getBean(newMenu.getClass());
	}
}
