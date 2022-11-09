package com.example.nutribem.domain.usecases.nutricionista;

import com.example.nutribem.domain.contexts.AuthenticationContext;

public class LogoutUseCase {

    public void logout() {
        AuthenticationContext.logout();
    }
}
