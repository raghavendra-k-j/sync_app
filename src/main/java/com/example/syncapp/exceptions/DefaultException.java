package com.example.syncapp.exceptions;

public class DefaultException extends RuntimeException {
    private boolean isUserFriendly = true;
    private boolean canRetry = true;
    private final String message;

    public DefaultException(String message) {
        super(message);
        this.message = message;
    }

    public DefaultException(String message, boolean canRetry) {
        super(message);
        this.message = message;
        this.canRetry = canRetry;
    }

    public DefaultException(String message, boolean canRetry, boolean isUserFriendly) {
        super(message);
        this.message = message;
        this.canRetry = canRetry;
        this.isUserFriendly = isUserFriendly;
    }

    public boolean isUserFriendly() {
        return isUserFriendly;
    }

    public void setUserFriendly(boolean userFriendly) {
        isUserFriendly = userFriendly;
    }

    public boolean isCanRetry() {
        return canRetry;
    }

    public void setCanRetry(boolean canRetry) {
        this.canRetry = canRetry;
    }

    @Override
    public String getMessage() {
        return message;
    }
}