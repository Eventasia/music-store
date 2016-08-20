package com.github.eventasia.sample.musicstore.user.service.exception;

public class IllegalCommandException extends Exception {
    public IllegalCommandException() {
    }

    public IllegalCommandException(String s) {
        super(s);
    }

    public IllegalCommandException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalCommandException(Throwable throwable) {
        super(throwable);
    }

    public IllegalCommandException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
