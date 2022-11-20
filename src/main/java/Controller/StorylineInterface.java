package Controller;

public interface StorylineInterface {
    public void display_event(); //display string objects
    public void display_event_choices(); //display choices string narration
    public void display_lose(); //display losing screen
    public void display_exit_options(); //display exit option screen
    public void returnHomeScreen(); //return to the HomeScreen
    public String getUsername(); //get the username
    public String setNewGame(); //set a new game
    public String loadGame(); //load the saved game
    public int createAccount(String username, String password, String repeatPassword);
    //ask LoginInteractor to create an account, if not successful, give a warning
    public int login(String username, String password);
    //ask LoginInteractor to login, if not successful, give a warning
    public void buildRegisterScreen(); //jump to RegisterScreen
    public void buildLoginScreen(); //jump to LoginScreen
    public void returnCreateAccountOrLoginScreen(); //return to CreateAccountOrLoginScreen
}
