package com.example.syncapp.rest.defaults;

import java.util.Map;
import java.util.Objects;

public class Error {
    private String message;
    private boolean isUserFriendly;
    private boolean canRetry;

    private Map<String, Objects> data;


    public Error() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Map<String, Objects> getData() {
        return data;
    }

    public void setData(Map<String, Objects> data) {
        this.data = data;
    }
}
