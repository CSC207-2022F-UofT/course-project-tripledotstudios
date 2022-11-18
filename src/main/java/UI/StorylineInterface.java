package UI;

public interface StorylineInterface {
    public String display_event(); //display string objects
    public int display_event_choices(); //display choices string narration
    public void display_lose(); //display losing screen
    public void display_exit_options(); //display exit option screen
    public void returnHomeScreen(); //return to the HomeScreen
    public String getUsername(); //get the username
    public String setNewGame(String username); //set a new game
    public String loadGame(); //load the saved game
    public void createAccount(String username, String password, String repeatPassword);
    //ask LoginInteractor to create an account, if not successful, give a warning
    public void login(String username, String password);
    //ask LoginInteractor to login, if not successful, give a warning
    public void BuildRegisterScreen(); //jump to RegisterScreen
    public void BuildLoginScreen(); //jump to LoginScreen
}
