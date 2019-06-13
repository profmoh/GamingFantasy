package com.auto.puzzle.configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.auto.puzzle.commons.enums.GameEnum;
import com.auto.puzzle.commons.enums.menus.CharacterMenuItem;
import com.auto.puzzle.commons.enums.menus.GameMenuItem;
import com.auto.puzzle.commons.enums.menus.MainMenuItem;
import com.auto.puzzle.commons.exceptions.NoSuchBeanException;
import com.auto.puzzle.commons.menus.BaseMenu;
import com.auto.puzzle.commons.menus.CharacterMenu;
import com.auto.puzzle.commons.menus.GameMenu;
import com.auto.puzzle.commons.menus.MainMenu;
import com.auto.puzzle.games.BaseGame;

public class ContextHandler {

	private static final Map<Class<?>, Object> context = new HashMap<>();

	private static Set<BaseGame> gameSet = new HashSet<>();

	public static List<BaseGame> getGameList() {
		return new ArrayList<>(gameSet);
	}

	static {
		io();
		games();
		menus();
		handlers();
	}

	public ContextHandler() {
	}

	private static void io() {
		IOHandler ioHandler = new IOHandler(System.in, System.out);
		addClassToContext(IOHandler.class, ioHandler);
	}

	private static void games() {
		GameEnum[] gameEnumArr = GameEnum.values();

		for(GameEnum gameEnum : gameEnumArr) {
			Class<BaseGame> clazz = gameEnum.getGameClass();

			try {
				Constructor<?> ctor = clazz.getConstructor();
				
				BaseGame game = (BaseGame) ctor.newInstance();

				addClassToContext(gameEnum.getGameClass(), game);

				gameSet.add(game);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				continue;
			}
		}

//		BaseGame ticTacToeGame = new TicTacToeGame();
//		addClassToContext(TicTacToeGame.class, ticTacToeGame);

//		gameList = Arrays.asList(ticTacToeGame);

//		GameHandler gameHandler = new GameHandler(gameList, null);
//		addClassToContext(GameHandler.class, gameHandler);
	}

	private static void menus() {
		BaseMenu<MainMenuItem> mainMenu = new MainMenu();
		addClassToContext(MainMenu.class, mainMenu);

		BaseMenu<CharacterMenuItem> characterMenu = new CharacterMenu();
		addClassToContext(CharacterMenu.class, characterMenu);

		BaseMenu<GameMenuItem> gameMenu = new GameMenu();
		addClassToContext(GameMenu.class, gameMenu);
	}

	private static void handlers() {
		MenuHandler<?> menuHandler = new MenuHandler<>(getBean(MainMenu.class));
		addClassToContext(MenuHandler.class, menuHandler);
	}

	public static void addClassToContext(Class<?> tClass, Object obj) {
		context.put(obj.getClass(), obj);
		context.put(tClass, obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> tClass) {
		T bean;

		try {
			bean = (T) context.get(tClass);
		} catch (Throwable t) {
			throw new NoSuchBeanException(t);
		}

		if (null == bean) {
            throw new NoSuchBeanException();
		}

		return bean;
	}
}
