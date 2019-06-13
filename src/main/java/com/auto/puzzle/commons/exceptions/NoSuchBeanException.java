package com.auto.puzzle.commons.exceptions;

import static com.auto.puzzle.commons.Messages.NO_SUCH_BEAN_EXCEPTION;

public class NoSuchBeanException extends RuntimeException {

	private static final long serialVersionUID = 2155629249758836643L;

    public NoSuchBeanException() {
        super(NO_SUCH_BEAN_EXCEPTION);
    }

    public NoSuchBeanException(Throwable cause) {
        super(NO_SUCH_BEAN_EXCEPTION, cause);
    }
}
