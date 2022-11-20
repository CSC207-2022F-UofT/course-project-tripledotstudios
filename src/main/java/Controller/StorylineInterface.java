package Controller;

public interface StorylineInterface {
    void display_event(); //display string objects
    void display_event_choices(); //display choices string narration
    void display_lose(); //display losing screen
    void display_exit_options(); //display exit option screen
    void returnHomeScreen(); //return to the HomeScreen
    String getUsername(); //get the username
    String setNewGame(); //set a new game
    String loadGame(); //load the saved game
    int createAccount(String username, String password, String repeatPassword);
    //ask LoginInteractor to create an account, if not successful, give a warning
    int login(String username, String password);
    //ask LoginInteractor to login, if not successful, give a warning
    void logOut(); //ask LoginInteracor to logOut.
    void buildRegisterScreen(); //jump to RegisterScreen
    void buildLoginScreen(); //jump to LoginScreen
    void returnCreateAccountOrLoginScreen(); //return to CreateAccountOrLoginScreen
}
