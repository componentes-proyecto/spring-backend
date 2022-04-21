package com.project.springbackend.Session;

public final class UserSessionSingleton {
    public String username;
    private static UserSessionSingleton instance;

    private UserSessionSingleton(String username) {
        this.username = username;
    }

    public static UserSessionSingleton getInstance(String username) {
        if (instance == null) {
            instance = new UserSessionSingleton(username);
        }

        return instance;
    }
}
