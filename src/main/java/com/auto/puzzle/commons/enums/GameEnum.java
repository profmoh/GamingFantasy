package com.auto.puzzle.commons.enums;

import com.auto.puzzle.games.BaseGame;
import com.auto.puzzle.games.connectFour.ConnectFourGame;
import com.auto.puzzle.games.ticTacToe.TicTacToeGame;;

public enum GameEnum {

	TIC_TAC_TOE("Tic Tac Toe", TicTacToeGame.class),
	CONNECT_FOUR("Connect Four", ConnectFourGame.class);

	private String name;
	private Class<?> gameClass;

	private <T extends BaseGame> GameEnum(String name, Class<T> gameClass) {
		this.name = name;
		this.gameClass = gameClass;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseGame> Class<T> getGameClass() {
		return (Class<T>) gameClass;
	}

	public String getName() {
		return name;
	}
}
