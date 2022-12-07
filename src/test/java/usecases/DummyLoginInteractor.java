package usecases;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class DummyLoginInteractor {
    private final HashMap<String, String> accounts; // a HashMap mapping usernames to passwords for all registered accounts
    private final String filepath; // a String of the csv file containing all registered accounts
    private String currentUser; // a String of which, if any, user is currently logged in

    /**
     * Constructor.
     * @param filepath a String of the csv file containing all registered accounts
     */
    public DummyLoginInteractor(String filepath) {
        // save the name of the file that LoginInteractor will read from and write to
        this.filepath = filepath;
        // save the LoginInterface that LoginInteractor will use
        // read csv file and create hashmap mapping usernames to passwords
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
     * Getter method for accounts, the HashMap mapping usernames to passwords.
     * @return <accounts>.
     */
    public HashMap<String, String> getAccounts() {
        return accounts;
    }

    /**
     * Getter method for currentUser, the String containing the username of the player that is currently logged in.
     * @return <currentUser>.
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Check whether the user can log into an account with <username> and <password>.
     * @param username a String of a username attempt
     * @param password a String of a password attempt
     * @return true iff the login was successful.
     */
    public boolean validateLogin(String username, String password) {
        // an account with this username does not exist
        if (!(accounts.containsKey(username))) {
            return false;
        }
        // password does not match username
        else if (!(Objects.equals(accounts.get(username), password))) {
            return false;
        }
        // login is successful
        else {
            currentUser = username;
            return true;
        }
    }

    /**
     * Create and record a new account. Log into the new account.
     * @param username a String of a proposed username for a new account
     * @param password1 a String of a proposed password for a new account
     * @param password2 a String of a proposed password for a new account
     * @return true iff account creation was successful.
     */
    public boolean createAccount(String username, String password1, String password2) {
        // case that username is unique, password is long enough, and passwords match (i.e., valid account creation)
        if (validateNewUsername(username) && validateNewPassword(password1) && (password1.equals(password2))) {
            try {
                // create new FileWriter
                FileWriter fw = new FileWriter(filepath, true);
                fw.write("\n" + username + ", " + password1);
                fw.close();
                // update <accounts> so that LoginInteractor doesn't have to be reconstructed
                accounts.put(username, password1);
                return true;
            }
            catch (IOException e) { e.printStackTrace(); }
        }
        return false;
    }

    /**
     * Check that an account with <username> does not already exist.
     * @param username a String of a proposed username for a new account
     * @return true iff the proposed <username> is unique and is longer than 3 characters.
     */
    private boolean validateNewUsername(String username) {
        return (!accounts.containsKey(username)) && (username.length() > 3);
    }

    /**
     * Check that the proposed <password> is strong enough.
     * @param password a String of a proposed password for a new account
     * @return true iff the proposed <password> is at least 8 characters long.
     */
    private boolean validateNewPassword(String password) {
        return password.length() > 7;
    }

    /**
     * Records that no user is currently logged in.
     */
    public void logOut() {
        currentUser = null;
    }
}