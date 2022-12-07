package adapters.login;

import usecases.login.LoginInputBoundary;

import java.io.IOException;

public class LoginController {
    private final LoginInputBoundary login;

    public LoginController(LoginInputBoundary loginInter) {
        login = loginInter;
    }

    public void createAccount(String username, String password, String repeatPassword) throws IOException {
        // ask LoginInteractor to create an account, if not successful, give a warning
        login.createAccount(username, password, repeatPassword);
    }

    public void login(String username, String password) {
        //ask LoginInteractor to login, if not successful, give a warning
        login.validateLogin(username, password);
    }

    public void logOut() {
        login.logOut();
    }

}

