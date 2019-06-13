package com.auto.puzzle.configuration;

import static com.auto.puzzle.commons.utils.IntUtils.isNotWithinRangeExclusive;
import static com.auto.puzzle.configuration.Config.MAX_INPUT_RETRY;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.auto.puzzle.commons.exceptions.NoSuchBeanException;

public class IOHandler {

//	private final static Logger LOG = Logger.getLogger(Game.class.getName());

	private final Scanner scanner;
	private final PrintStream out;

	public IOHandler(InputStream source, PrintStream out) {
		this.out = out;
		this.scanner = new Scanner(source);
	}

	public void showMessageWithSpace(String msg) {
		out.println();
		showMessageWithNewLine(msg);
	}

	public void showMessage(String msg) {
		out.print(msg);

//		LOG.info("User got the following message:\n{ " + msg + " }");
	}

	public void showMessageWithNewLine(String msg) {
		out.println(msg);

//		LOG.info("User got the following message:\n{ " + msg + " }");
	}

	public String readUserInputAsString() {
        return scanner.nextLine();
    }

	public String readUserInputAsString(String message) {
        showMessageWithNewLine(message);
        return readUserInputAsString();
    }

    public int tryReadingMenuChoice(String message, int optionsSize) {
        showMessageWithNewLine(message);
        return tryReadingMenuChoice(optionsSize);
    }

    /*
     * menu choice is specific, because menu items are numbered from 1, not from 0.
     */
    public int tryReadingMenuChoice(int optionsSize) {
        return tryReadingInputAsInt(optionsSize + 1) - 1;
    }

    public int tryReadingInputAsInt(String message) {
        showMessageWithNewLine(message);
        return tryReadingInputAsInt();
    }

    public int tryReadingInputAsInt() {
        return tryReadingInputAsInt(Integer.MAX_VALUE);
    }

    public int tryReadingInputAsInt(String message, int optionsSize) {
        showMessageWithNewLine(message);
        return tryReadingInputAsInt(optionsSize);
    }

    public int tryReadingInputAsInt(int optionsSize) {
        for (int i = 1; i <= MAX_INPUT_RETRY; i++) {
            try {
                return readInt(optionsSize);
            } catch (Exception e) {
                if (MAX_INPUT_RETRY == i)
                    throw e;
               else
                    showMessageWithNewLine(e.getMessage());
            }
        }

        return -1;
    }

    int readInt(int optionsSize) {
        try {
            int result = Integer.parseInt(scanner.nextLine());
         
            if (isNotWithinRangeExclusive(result, -1, optionsSize)) {
                throw new NoSuchBeanException();
            } else {
                return result;
            }
        } catch (NumberFormatException | InputMismatchException e) {
            throw new NoSuchBeanException();
        }
    }
}
