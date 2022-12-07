package usecases.login;

import java.io.IOException;

public interface LoginInputBoundary {
    String getCurrentUser();

    void validateLogin(String username, String password);

    void createAccount(String username, String password1, String password2) throws IOException;

    void logOut();
}
