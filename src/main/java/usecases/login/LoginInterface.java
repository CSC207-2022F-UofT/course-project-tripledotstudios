package usecases.login;

public interface LoginInterface {
    /**
     * Present the window when the user clicks sign in button.
     * @param view the ID of the view displayed
     * @param username the username
     */
    void updateLogin(int view, String username);

    /**
     * Present the window when the user clicks sign up button.
     * @param view the ID of the view displayed
     * @param username the username
     */
    void updateRegister(int view, String username);
}
