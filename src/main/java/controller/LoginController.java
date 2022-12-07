package controller;

import usecases.LoginInteractor;

import java.io.IOException;

public class LoginController {
    private final LoginInteractor loginInteractor;

    public LoginController(LoginInteractor loginInter) {
        loginInteractor = loginInter;
    }

    public void createAccount(String username, String password, String repeatPassword) throws IOException {
        // ask LoginInteractor to create an account, if not successful, give a warning
        loginInteractor.createAccount(username, password, repeatPassword);
    }

    public void login(String username, String password) {
        //ask LoginInteractor to login, if not successful, give a warning
        loginInteractor.validateLogin(username, password);
    }

    public void logOut() {
        loginInteractor.logOut();
    }

}

