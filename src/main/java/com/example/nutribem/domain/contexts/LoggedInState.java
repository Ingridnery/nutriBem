package com.example.nutribem.domain.contexts;

public class LoggedInState implements AuthenticationState {
    public void login() {}

    public void logout() {
        AuthenticationContext.setState(new LoggedOutState());
        AuthenticationContext.setIsLogged(false);
    }
}
