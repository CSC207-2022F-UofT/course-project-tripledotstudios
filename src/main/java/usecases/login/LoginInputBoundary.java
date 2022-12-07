package usecases.login;

import java.io.IOException;

public interface LoginInputBoundary {
    /**
     * Returns the current user.
     * @return the name of the current user
     */
    String getCurrentUser();

    /**
     * Check whether the user can log into an account with username and password. Record that user is logged in if login
     * attempt is valid.
     * @param username a String of a username attempt.
     * @param password a String of a password attempt.
     */
    void validateLogin(String username, String password);

    /**
     * Create and record a new account. Log into the new account.
     * @param username a String of a proposed username for a new account.
     * @param password1 a String of a proposed password for a new account.
     * @param password2 a String of a proposed password for a new account.
     */
    void createAccount(String username, String password1, String password2) throws IOException;

    /**
     * The user logs out.
     */
    void logOut();
}
