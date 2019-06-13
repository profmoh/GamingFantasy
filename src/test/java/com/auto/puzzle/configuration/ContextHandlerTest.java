package com.auto.puzzle.configuration;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.auto.puzzle.commons.enums.GameEnum;
import com.auto.puzzle.commons.exceptions.NoSuchBeanException;
import com.auto.puzzle.games.BaseGame;

public class ContextHandlerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetGameList() {
		List<BaseGame> gamesList = ContextHandler.getGameList();

		// check not null
		assertNotNull(gamesList);

		// check the list size equals to games stored in GameEnum
        assertThat(gamesList, hasSize(GameEnum.values().length));
	}

	@Test(expected = NoSuchBeanException.class)
	public void testGetBeanShouldThroughNoSuchBeanException() {
		ContextHandler.getBean(ContextHandlerTest.class);
	}

	@Test
	public void testAddClassToContext() {
		Class<ContextHandlerTest> tClass = ContextHandlerTest.class;

		ContextHandler.addClassToContext(tClass, new ContextHandlerTest());

		assertNotNull(ContextHandler.getBean(tClass));
	}
}
