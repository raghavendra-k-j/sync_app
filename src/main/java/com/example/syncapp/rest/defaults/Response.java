package com.example.syncapp.rest.defaults;

import com.example.syncapp.exceptions.DefaultException;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Response {
    Map<String, Object> data;

    public Response() {
        data = new HashMap<>();
    }

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Response addProperty(String key, Object value) {
        data.put(key, value);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Response setError(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("canRetry", true);
        error.put("isUserFriendly", true);
        error.put("message", message);
        addProperty("error", error);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Response setError(String message, boolean canRetry) {
        Map<String, Object> error = new HashMap<>();
        error.put("canRetry", canRetry);
        error.put("isUserFriendly", true);
        error.put("message", message);
        addProperty("error", error);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Response setError(String message, boolean canRetry, boolean isUserFriendly) {
        Map<String, Object> error = new HashMap<>();
        error.put("canRetry", canRetry);
        error.put("isUserFriendly", isUserFriendly);
        error.put("message", message);
        addProperty("error", error);
        return this;
    }

    public Response setError(DefaultException defaultException) {
        Map<String, Object> error = new HashMap<>();
        error.put("canRetry", defaultException.isCanRetry());
        error.put("isUserFriendly", defaultException.isUserFriendly());
        error.put("message", defaultException.getMessage());
        addProperty("error", error);
        return this;
    }

}
