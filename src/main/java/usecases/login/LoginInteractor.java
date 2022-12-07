package usecases.login;

import presenter.LoginInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class LoginInteractor {
    /** A HashMap mapping usernames to passwords for all registered accounts. */
    private final HashMap<String, String> accounts;

    /** A String of the csv file containing record of all registered accounts. */
    private final String filepath;

    /** A String containing the username of the user who is currently logged in. Null if no one is logged in. */
    public String currentUser;

    /** Interface which this use case interacts with to communicate with the presenter. */
    private final LoginInterface presenter;

    /**
     * Constructor.
     * @param filepath a String of the csv file containing record of all registered accounts.
     */
    public LoginInteractor(String filepath, LoginInterface presenter) {
        // save the name of the file that LoginInteractor will read from and write to
        this.filepath = filepath;
        // save the interface that LoginInteractor will use to communicate with the presenter
        this.presenter = presenter;
        String line;
        // assign new HashMap to accounts attribute
        accounts = new HashMap<>();
        // read csv file and map usernames to passwords in <accounts>
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            while ((line = br.readLine()) != null) {
                String[] accountInfo = line.split(", ");
                // Puts key (a username) and value (a password) in accounts
                accounts.put(accountInfo[0], accountInfo[1]);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Getter method for currentUser.
     * @return currentUser.
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Check whether the user can log into an account with username and password. Record that user is logged in if login
     * attempt is valid.
     * @param username a String of a username attempt.
     * @param password a String of a password attempt.
     */
    public void validateLogin(String username, String password) {
        // an account with this username does not exist
        if (!(accounts.containsKey(username))) {
            // communicate to user that username does not exist
            presenter.updateLogin(2, username);
        }
        // password does not match username
        else if (!(Objects.equals(accounts.get(username), password))) {
            // communicate to user that password does not match username on file
            presenter.updateLogin(3, username);
        }
        // login is successful
        else {
            currentUser = username;
            // communicate to user that login was successful
            presenter.updateLogin(1, username);
        }
    }

    /**
     * Create and record a new account. Log into the new account.
     * @param username a String of a proposed username for a new account.
     * @param password1 a String of a proposed password for a new account.
     * @param password2 a String of a proposed password for a new account.
     */
    public void createAccount(String username, String password1, String password2) throws IOException {
        // valid account creation: username is unique, password is long enough, and passwords match
        if (validateNewUsername(username) && validateNewPassword(password1) && (password1.equals(password2))) {
            // create new FileWriter
            FileWriter fw = new FileWriter(filepath, true);
            // write to file at <filepath> to record new account
            fw.write("\n" + username + ", " + password1);
            fw.close();
            // update <accounts> with record of new account
            accounts.put(username, password1);
            // communicate to user that account creation was successful
            presenter.updateRegister(1, username);
        }
        // username already exists
        else if (!(validateNewUsername(username))) {
            presenter.updateRegister(2, username); // communicate to user that username already exists
        }
        // passwords do not match
        else if (!Objects.equals(password1, password2)) {
            presenter.updateRegister(4, username); // communicate to user that passwords do not match
        }
        // passwords match but password is too short
        else if (!(validateNewPassword(password1))) {
            presenter.updateRegister(3, username); // communicate to user that password is too short
        }
        // some other case is true
        else {
            presenter.updateRegister(5, username); // communicate to user that account creation was unsuccessful
        }
    }

    /**
     * Check that an account with <username> does not already exist.
     * @param username a String of a proposed username for a new account.
     * @return true iff the proposed username is unique.
     */
    private boolean validateNewUsername(String username) {
        return !accounts.containsKey(username);
    }

    /**
     * Check that the proposed password is at least 8 characters long.
     * @param password a String of a proposed password for a new account.
     * @return true iff the proposed password is at least 8 characters long.
     */
    private boolean validateNewPassword(String password) {
        return password.length() > 7;
    }

    /**
     * Record that no user is currently logged in.
     */
    public void logOut() {
        currentUser = null;
    }
}