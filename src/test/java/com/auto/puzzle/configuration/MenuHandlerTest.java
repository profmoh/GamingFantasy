package com.auto.puzzle.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MenuHandlerTest {

	MenuHandler<?> menuHandler;
	IOHandler ioHandler;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		menuHandler = ContextHandler.getBean(MenuHandler.class);

		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		ioHandler = new IOHandler(System.in, System.out);

		ContextHandler.addClassToContext(IOHandler.class, ioHandler);
	}

	@Test
	public void testShowMenu() {
		menuHandler.showMenu();

		String mainMenuExpectOutput = "\r\n" + 
				"Welcome .. select a game ^_^\r\n" + 
				"\r\n" + 
				"Please choose one of those options:\r\n" + 
				"	1: Tic Tac Toe\r\n" + 
				"	2: Connect Four\r\n" + 
				"	0: exit";

		System.out.println(outContent.toString());

		assertEquals(mainMenuExpectOutput, outContent.toString());
	}

	@Test
	public void testSelectOption() {
		fail("Not yet implemented");
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
}
