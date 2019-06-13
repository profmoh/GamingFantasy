package com.auto.puzzle.games.ticTacToe;

import java.util.function.Supplier;
import java.util.stream.Stream;

import com.auto.puzzle.configuration.IOHandler;

public class Board {

	public static final String CELL_SEP = ",";
	public static final String FIELD_SEP = "#";

	public int SIZE = 3;

	Cell[][] board;

	int currentRow, currentCol;

	public Board() {
		board = new Cell[SIZE][SIZE];

		for (int row = 0; row < SIZE; ++row) {
			for (int col = 0; col < SIZE; ++col) {
				board[row][col] = new Cell(row, col);
			}
		}
	}

	public Board(int SIZE, int currentRow, int currentCol, Cell[][] board) {
		this.SIZE = SIZE;
		this.currentRow = currentRow;
		this.currentCol = currentCol;
		this.board = board;
	}

	public void init() {
		for (int row = 0; row < SIZE; ++row) {
			for (int col = 0; col < SIZE; ++col) {
				board[row][col].clear();
			}
		}
	}

	public boolean isDraw() {
		for (int row = 0; row < SIZE; ++row) {
			for (int col = 0; col < SIZE; ++col) {
				if (board[row][col].content.equals(CellValue.EMPTY))
					return false; 
			}
		}

		return true; // no empty cell, it's a draw
	}

	public boolean hasWon(CellValue playerValue) {
		Supplier<Stream<Integer>> streamSupplier = () -> Stream.iterate(0, n -> n + 1).limit(SIZE);

		boolean sameRow = streamSupplier.get().allMatch(c -> board[currentRow][c].content.equals(playerValue));

		if(sameRow) return true;

		boolean sameCol = streamSupplier.get().allMatch(c -> board[c][currentCol].content.equals(playerValue));

		if(sameCol) return true;

		boolean firstDiag = streamSupplier.get().allMatch(c -> board[c][c].content.equals(playerValue));

		if(firstDiag) return true;

		boolean secondDiag = streamSupplier.get().allMatch(c -> board[c][SIZE - 1 - c].content.equals(playerValue));

		if(secondDiag) return true;

		return false;
	}

	public void print(IOHandler io) {
		io.showMessageWithSpace("");

		for (int row = 0; row < SIZE; ++row) {
			for (int col = 0; col < SIZE; ++col) {
				board[row][col].print(io);

				if (col < SIZE - 1)
					io.showMessage("|");
			}

			io.showMessageWithSpace("");

			if (row < SIZE - 1) {
				io.showMessageWithNewLine("-------------------");
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append(SIZE).append(FIELD_SEP)
				.append(currentRow).append(FIELD_SEP)
				.append(currentCol).append(FIELD_SEP);

		for (int row = 0; row < SIZE; ++row)
			for (int col = 0; col < SIZE; ++col) {
				builder.append(board[row][col].content.toString());

				if(! (row == SIZE - 1 && col == SIZE - 1))
					builder.append(CELL_SEP);
			}

		return builder.toString();
	}
}
