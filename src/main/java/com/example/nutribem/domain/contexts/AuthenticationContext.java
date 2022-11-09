package com.example.nutribem.domain.contexts;

public class AuthenticationContext {
    private static AuthenticationState state = new LoggedOutState();
    private static Boolean isLogged = false;

    public static void login() {
        state.login();
    }

    public static void logout() {
        state.logout();
    }

    public static Boolean isLogged() {
        return isLogged;
    }

    protected static void setState(AuthenticationState state) {
        AuthenticationContext.state = state;
    }

    protected static void setIsLogged(Boolean isLogged) {
        AuthenticationContext.isLogged = isLogged;
    }
}
