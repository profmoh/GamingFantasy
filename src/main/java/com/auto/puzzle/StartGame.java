package com.auto.puzzle;

import com.auto.puzzle.configuration.ContextHandler;
import com.auto.puzzle.configuration.MenuHandler;

public class StartGame {

//	private final static Logger LOG = Logger.getLogger(Game.class.getName());

	private static MenuHandler<?> menuHandler = ContextHandler.getBean(MenuHandler.class);

	public static void main(String[] args) {
		launch();
	}

	public static void launch() {
		try {
//			LOG.info("CLI calling MainMenuManager...");
			menuHandler.showMenu();
		} catch (Exception e) {
			Exit("", e);
		}
//		} catch (UserInputParseException e) {
//			shutdown(e.getMessage(), e);
//		} catch (ConfigurationException e) {
//			if (null != e.getMessage()) {
//				shutdown(e.getMessage(), e);
//			} else {
//				String msg = "There was a problem with the configuration. Please ask your local IT for support.\n"
//						+ "I'm sure they will come up with a solution to your problem (for example 'have you tried turning it off and on again')";
//				shutdown(msg, e);
//			}
//
//		} catch (Throwable t) {
//			String msg = "There was a general problem with the game. Pray to God that it will work next time.";
//			shutdown(msg, t);
//		}
	}

	private static void Exit(String msg, Throwable e) {
		System.out.println(msg);
//		LOG.log(Level.WARNING, msg, e);
		System.exit(1);
	}
}
