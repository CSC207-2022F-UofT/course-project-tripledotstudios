package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class LoginInteractor {
    private HashMap<String, String> accounts;
    private String filePath;

    /**
     * Constructor.
     * @param filePath a String of the csv file containing all registered accounts
     */
    public LoginInteractor(String filePath) {
        // save the name of the file that LoginInteractor will read from and write to
        this.filePath = filePath;
        // read csv file and create hashmap mapping usernames to passwords
        String line = "";
        // assign new HashMap to accounts attribute
        accounts = new HashMap<>();
        // read csv file and map usernames to passwords in <accounts>
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] accountInfo = line.split(",");
                // Puts key (a username) and value (a password) in accounts
                accounts.put(accountInfo[0], accountInfo[1]);
            }
        } catch (IOException e) { System.out.println(e); }
    }

    /**
     * Getter method for accounts, the HashMap mapping usernames to passwords.
     * @return accounts.
     */
    public HashMap<String, String> getAccounts() {
        return accounts;
    }

    /**
     * Check whether the user can log into an account with <username> and <password>.
     * @param username a String of a username attempt
     * @param password a String of a password attempt
     * @return true iff <username> and <password> represent a created account.
     */
    public boolean validateLogin(String username, String password) {
        return (accounts.containsKey(username)) && (Objects.equals(accounts.get(username), password));
    }

    /**
     * Create and record a new account.
     * @param username a String of a proposed username for a new account
     * @param password a String of a proposed password for a new account
     */
    public void createAccount(String username, String password, String filepath) {
        if (validateNewUsername(username) && validateNewPassword(password)) {
            try {
                // create new FileWriter
                FileWriter fw = new FileWriter(filepath, true);
                fw.write("\n" + username + "," + password);
                fw.close();
                // update <accounts> so that LoginInteractor doesn't have to be reconstructed
                accounts.put(username, password);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Check that an account with <username> does not already exist.
     * @param username a String of a proposed username for a new account
     * @return true iff the proposed <username> is unique.
     */
    private boolean validateNewUsername(String username) {
        return !accounts.containsKey(username);
    }

    /**
     * Check that the proposed <password> is strong enough.
     * @param password a String of a proposed password for a new account
     * @return true iff the proposed <password> is at least 8 characters long.
     */
    private boolean validateNewPassword(String password) {
        return password.length() > 7;
    }
}