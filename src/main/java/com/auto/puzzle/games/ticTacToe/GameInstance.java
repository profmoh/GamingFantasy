package com.auto.puzzle.games.ticTacToe;

import com.auto.puzzle.configuration.IOHandler;

public class GameInstance {

	private String characterName;
	private Long experienceScore;

	private GameState gameState;

	private CellValue currentPlayerValue;
	private CellValue characterCellValue;

	private Board board;
//	f	%	PLAYING	%	CROSS	%	CROSS	%	3#2#1#CROSS,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,NOUGHT,EMPTY

	public GameInstance() {
		this.characterName = null;
		this.experienceScore = 0L;
		this.gameState = null;
		this.currentPlayerValue = null;
		this.characterCellValue = null;
		this.board = null;
	}

	public GameInstance solveFileLine(String fileLine) {
		String[] values = fileLine.split(TicTacToeGame.FIELD_SEP);

		if(values.length != 6)
			return null;

		this.characterName = values[0];
		this.experienceScore = Long.parseLong(values[1]);
		this.gameState = GameState.valueOf(values[2]);
		this.currentPlayerValue = CellValue.valueOf(values[3]);
		this.characterCellValue = CellValue.valueOf(values[4]);

		String boardLine = values[5];

		String[] boardValues = boardLine.split(Board.FIELD_SEP);

		if(boardValues.length != 4)
			return null;

		String cellLine = boardValues[3];

		String[] cellValues = cellLine.split(Board.CELL_SEP);

		int boardSize = Integer.valueOf(boardValues[0]);
		int currentRow = Integer.valueOf(boardValues[1]);
		int currentCol = Integer.valueOf(boardValues[2]);

		if(cellValues.length != (boardSize * boardSize))
			return null;

		Cell[][] boardCell = new Cell[boardSize][boardSize];

		int i = 0, j = 0;

		for(String cellValue : cellValues ) {
			Cell cell = new Cell(i, j);

			cell.setContent(CellValue.valueOf(cellValue));

			boardCell[i][j] = cell;

			i++;

			if(i == boardSize) {
				i = 0;
				j++;
			}
		}

		this.board = new Board(boardSize, currentRow, currentCol, boardCell);

		return this;
	}

	public void print(IOHandler io) {
		io.showMessageWithNewLine("------------ record ------------");

		io.showMessageWithNewLine("\nExperience Score : " + this.experienceScore.toString());

		io.showMessageWithNewLine("\tStatus : " + this.gameState.toString());
		io.showMessageWithNewLine("\tYou was the : " + this.characterCellValue.toString());

		io.showMessageWithSpace("The game State : ");
		board.print(io);

		io.showMessageWithNewLine("================================");
	}

	public String getCharacterName() {
		return characterName;
	}

	public Long getExperienceScore() {
		return experienceScore;
	}

	public GameState getGameState() {
		return gameState;
	}

	public CellValue getCurrentPlayerValue() {
		return currentPlayerValue;
	}

	public CellValue getCharacterCellValue() {
		return characterCellValue;
	}

	public Board getBoard() {
		return board;
	}
}
