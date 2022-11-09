package com.example.nutribem.domain.contexts;

public class LoggedOutState implements AuthenticationState {
    public void login() {
        AuthenticationContext.setState(new LoggedInState());
        AuthenticationContext.setIsLogged(true);
    }

    public void logout() {}
}
