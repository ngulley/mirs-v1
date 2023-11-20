package com.mirs.model.business.exception;

public class PropertyFileNotFoundException extends Exception {
    public PropertyFileNotFoundException(final String inMessage, final Throwable inNestedException) {
        super(inMessage, inNestedException);
    }
}
