package com.auto.puzzle.commons.exceptions;

import static com.auto.puzzle.commons.Messages.NO_SUCH_BEAN_EXCEPTION;

public class InputParsingException extends RuntimeException {

	private static final long serialVersionUID = 2155629249758836643L;

    public InputParsingException() {
        super(NO_SUCH_BEAN_EXCEPTION);
    }

    public InputParsingException(Throwable cause) {
        super(NO_SUCH_BEAN_EXCEPTION, cause);
    }
}
