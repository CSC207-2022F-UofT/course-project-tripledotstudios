package usecases;

import UI.UIFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import presenter.LoginPresenter;

import java.util.HashMap;

class LoginInteractorTest {

    UIFacade uiFacade = new UIFacade();
    LoginPresenter p = new LoginPresenter(uiFacade);
    LoginInteractor li = new LoginInteractor("data/test_usernames.csv", p);

    // tests for CreateAccount

    @Test
    public void LoginInteractorCreateAccountValid() {
        boolean actual = li.createAccount("generic_username", "password", "password");
        Assertions.assertTrue(actual);
    }

    @Test
    public void LoginInteractorCreateAccountInvalidUsername() {
        boolean actual = li.createAccount("generic_username", "password", "password");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorCreateAccountInvalidPassword() {
        boolean actual = li.createAccount("generic_username", "pass", "pass");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorCreateAccountNonMatchingPasswords() {
        boolean actual = li.createAccount("generic_username", "passwprd", "password");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorCreateAccountMissingUsername() {
        boolean actual = li.createAccount("", "hahaHA123!", "hahaHA123!");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorCreateAccountMissingPassword() {
        boolean actual = li.createAccount("generic_username", "", "");
        Assertions.assertFalse(actual);
    }

    // tests for ValidateLogin

    @Test
    public void LoginInteractorValidateLoginValidLogin() {
        boolean actual = li.validateLogin("silly-Username", "S1llyp@ssw0rd");
        Assertions.assertTrue(actual);
    }

    @Test
    public void LoginInteractorValidateLoginInvalidUsername() {
        boolean actual = li.validateLogin("SILLY-USERNAME", "S1llyp@ssw0rd");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorValidateLoginInvalidPassword() {
        boolean actual = li.validateLogin("silly-Username", "s1llyp@ssword");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorValidateLoginMissingUsername() {
        boolean actual = li.validateLogin("", "IKNOWURPASSWORD");
        Assertions.assertFalse(actual);
    }

    @Test
    public void LoginInteractorValidateLoginMissingPassword() {
        boolean actual = li.validateLogin("HACKER", "");
        Assertions.assertFalse(actual);
    }

    // tests for currentUser

    @Test
    public void LoginInteractorCurrentUserDefault() {
        Assertions.assertNull(li.getCurrentUser());
    }

    @Test
    public void LoginInteractorCurrentUserLoggedIn() {
        li.validateLogin("HACKER", "IKNOWURPASSWORD");
        Assertions.assertEquals("HACKER", li.getCurrentUser());
    }

    @Test
    public void LoginInteractorCurrentUserFailedLogin() {
        li.validateLogin("RandOmUs3rname!", "incorrectpassword");
    }

    @Test
    public void LoginInteractorCurrentUserLoggedOut() {
        li.validateLogin("RandOmUs3rname!", "randompassword");
        li.logOut();
        Assertions.assertNull(li.getCurrentUser());
    }

    // tests for changes in accounts

    LoginInteractor blankLi = new LoginInteractor("data/blank_usernames.csv", p);

    @Test
    public void LoginInteractorAccountsBlankFileDefault() {
        HashMap<String, String> actual = blankLi.getAccounts();
        HashMap<String, String> expected = new HashMap<>();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void LoginInteractorAccountsDefault() {
        HashMap<String, String> actual = li.getAccounts();
        HashMap<String, String> expected = new HashMap<>();
        expected.put("RandOmUs3rname!", "randompassword");
        expected.put("silly-Username", "S1llyp@ssw0rd");
        expected.put("HACKER", "IKNOWURPASSWORD");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void LoginInteractorAccountsAddedAccount() {
        HashMap<String, String> actual = li.getAccounts();
        li.createAccount("JavaFanatic", "ilovejava", "ilovejava");
        Assertions.assertEquals(actual.get("JavaFanatic"), "ilovejava");
    }
}
