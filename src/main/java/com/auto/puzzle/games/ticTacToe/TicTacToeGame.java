package com.auto.puzzle.games.ticTacToe;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.auto.puzzle.commons.enums.ExperienceGained;
import com.auto.puzzle.commons.enums.GameEnum;
import com.auto.puzzle.commons.utils.FileUtils;
import com.auto.puzzle.commons.utils.IntUtils;
import com.auto.puzzle.configuration.Config;
import com.auto.puzzle.configuration.ContextHandler;
import com.auto.puzzle.configuration.IOHandler;
import com.auto.puzzle.games.BaseGame;

public class TicTacToeGame implements BaseGame {

	private final String TTT_FILE_PATH = Config.RESOURCE_SAVINGS_FOLDER + getGameEnum().getName() + Config.RESOURCE_SAVINGS_EXTENSTION;

	public static final String FIELD_SEP = "\t%\t";

	IOHandler io = ContextHandler.getBean(IOHandler.class);

	private Board board; // the game board
	private GameState currentState = GameState.INIT; // the current state of the game

	private Long experienceScore = 0L;

	private CellValue currentPlayerValue;
	private CellValue characterCellValue = CellValue.CROSS;

	@Override
	public GameEnum getGameEnum() {
		return GameEnum.TIC_TAC_TOE;
	}

	@Override
	public void explore(String characterName) {
		List<GameInstance> gameInstanceList = loadGameFile(characterName);

		long noOfWon = gameInstanceList
				.stream()
				.filter(g -> g.getGameState().equals(GameState.WON))
				.count();

		io.showMessageWithNewLine("#####################################################");

		io.showMessageWithNewLine("You Won " + noOfWon + " time(s)");
		io.showMessageWithNewLine("Current Scoer " + experienceScore + " time(s)");

		if(gameInstanceList.isEmpty())
			io.showMessageWithSpace("No saved records");
		else
			io.showMessageWithSpace("All saved records");

		gameInstanceList
			.forEach(g -> g.print(io));

		io.showMessageWithNewLine("#####################################################");
	}

	@Override
	public void fight(String characterName) {
		if(currentState.equals(GameState.INIT))
			initGame();

		board.print(io);

		do {
			playerMove(currentPlayerValue, characterName);

			board.print(io);

			updateGame(currentPlayerValue);

			if (currentState == GameState.WON) {
				io.showMessageWithNewLine("Congrats you won! Bye!");
			} else if (currentState == GameState.LOSE) {
				io.showMessageWithNewLine("sorry you lose! hard luck next time Bye!");
			} else if (currentState == GameState.DRAW) {
				System.out.println("It's Draw! Bye!");
			}

			// Switch player
			currentPlayerValue = (currentPlayerValue == CellValue.CROSS) ? CellValue.NOUGHT : CellValue.CROSS;
		} while (currentState.equals(GameState.PLAYING)); // repeat until game-over
	}

	@Override
	public void gainExperience() {
		switch (currentState) {
		case PLAYING:
			this.experienceScore += ExperienceGained.STEP.getScore();
			break;
		case DRAW:
			this.experienceScore += ExperienceGained.DRAW.getScore();
			break;
		case WON:
			this.experienceScore += ExperienceGained.WON.getScore();
			break;
		case LOSE:
			this.experienceScore += ExperienceGained.LOSE.getScore();
			break;
		default:
			break;
		}
	}

	@Override
	public void saveGame(String characterName) {
		if(currentState.equals(GameState.INIT)) {
			io.showMessageWithNewLine("nothing to save");
			return;
		}

		StringBuilder builder = new StringBuilder()
				.append(characterName).append(FIELD_SEP)
				.append(experienceScore).append(FIELD_SEP)
				.append(currentState.toString()).append(FIELD_SEP)
				.append(currentPlayerValue.toString()).append(FIELD_SEP)
				.append(characterCellValue.toString()).append(FIELD_SEP)
				.append(board.toString());

		String content = builder.toString();

		try {
			FileUtils.writeContentToResourceFile(TTT_FILE_PATH, content);

			io.showMessageWithNewLine("state saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadGame(String characterName) {
		List<GameInstance> gameInstanceList = loadGameFile(characterName);

		boolean stateLoaded = loadLastGameStatus(gameInstanceList);

		if(stateLoaded)
			fight(characterName);
	}

	public void initGame() {
		board = new Board();
		board.init();

		characterCellValue = CellValue.CROSS;

		String value = io.readUserInputAsString("Choose 'X' or 'O'");

		if (value.equalsIgnoreCase("X"))
			characterCellValue = CellValue.CROSS;
		else if (value.equalsIgnoreCase("O"))
			characterCellValue = CellValue.NOUGHT;
		else
			io.showMessageWithSpace("Wrong entry. you are X, start playing");

		currentPlayerValue = CellValue.CROSS;

		currentState = GameState.PLAYING;
	}

	private void playerMove(CellValue playerValue, String characterName) {
		boolean validInput = false;

		do {
			int row, col;

			io.showMessageWithNewLine("to save your game press '0'");

			if (playerValue.equals(characterCellValue)) {
				row = io.tryReadingInputAsInt("enter your move, row number [1-" + board.SIZE + "]") - 1;

				if(row == -1) {
					saveGame(characterName);
					io.showMessageWithNewLine("state saved");
					row = io.tryReadingInputAsInt("enter your move, row number [1-" + board.SIZE + "]") - 1;
				}

				col = io.tryReadingInputAsInt("enter your move, column number [1-" + board.SIZE + "]") - 1;
			} else {
				do {
					row = IntUtils.randomIntInclusive(0, board.SIZE - 1);
					col = IntUtils.randomIntInclusive(0, board.SIZE - 1);
				} while (!board.board[row][col].content.equals(CellValue.EMPTY));
			}

			if (row >= 0 && row < board.SIZE && col >= 0 && col < board.SIZE
					&& board.board[row][col].content.equals(CellValue.EMPTY)) {
				board.board[row][col].content = playerValue;

				board.currentRow = row;
				board.currentCol = col;

				validInput = true;
			} else {
				io.showMessageWithNewLine("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
			}
		} while (!validInput); // repeat until input is valid
	}

	private void updateGame(CellValue playerValue) {
		if (board.hasWon(playerValue))
			currentState = (playerValue.equals(characterCellValue)) ? GameState.WON : GameState.LOSE;
		else if (board.isDraw())
			currentState = GameState.DRAW;

		gainExperience();
	}

	private List<GameInstance> loadGameFile(String characterName) {
		List<String> fileLines;

		try {
			fileLines = FileUtils.readFileLineByLineFromResources(TTT_FILE_PATH);

			fileLines = fileLines
					.stream()
					.map(l -> l.trim())
					.filter(l -> l != null && ! l.isEmpty())
					.filter(l -> l.split(FIELD_SEP)[0].equals(characterName))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			fileLines = null;
		}

		List<GameInstance> gameInstanceList = null;

		if(fileLines != null) {
			gameInstanceList = fileLines
				.stream()
				.map(l -> new GameInstance().solveFileLine(l))
				.filter(l -> l != null)
				.collect(Collectors.toList());
		}

		return gameInstanceList;
	}

	private boolean loadLastGameStatus(List<GameInstance> gameInstanceList) {
		if(gameInstanceList == null || gameInstanceList.isEmpty()) {
			io.showMessageWithNewLine("no records found for this user");
			return false;
		}

		GameInstance lastInstance = gameInstanceList.get(gameInstanceList.size() - 1);

		if(! lastInstance.getGameState().equals(GameState.PLAYING)) {
			io.showMessageWithNewLine("last game status is not active");
			return false;
		}

		this.board = lastInstance.getBoard();
		this.experienceScore = lastInstance.getExperienceScore();
		this.currentState = lastInstance.getGameState();
		this.characterCellValue = lastInstance.getCharacterCellValue();
		this.currentPlayerValue = lastInstance.getCurrentPlayerValue();

		return true;
	}
}
