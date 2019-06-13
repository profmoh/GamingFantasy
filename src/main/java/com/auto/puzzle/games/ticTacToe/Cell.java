package com.auto.puzzle.games.ticTacToe;

import com.auto.puzzle.configuration.IOHandler;

public class Cell {

	CellValue content;

	int row, col;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;

		clear();
	}

	public void clear() {
		content = CellValue.EMPTY;
	}

	public void print(IOHandler io) {
		switch (content) {
		case CROSS:
			io.showMessage("  X  ");
			break;
		case NOUGHT:
			io.showMessage("  O  ");
			break;
		case EMPTY:
			io.showMessage("     ");
			break;
		}
	}

	public void setContent(CellValue content) {
		this.content = content;
	}
}
