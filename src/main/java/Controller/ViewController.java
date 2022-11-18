package Controller;

import UI.*;

import javax.swing.*;

public class ViewController implements StorylineInterface {

    private final StorylineInteractor storylineInteractor;
    private final EnterAGameScreen enterAGameScreen;
    private final ExitOptionsScreen exitOptionsScreen;

    private final LoginInteractor loginInteractor;
    private final RegisterScreen register;
    private final LoginScreen login;



    public ViewController(StorylineInteractor storylineInter, EnterAGameScreen enterA, ExitOptionsScreen exitO, LoginInteractor loginInter, RegisterScreen reg, LoginScreen log) {
        storylineInteractor = storylineInter;
        enterAGameScreen = enterA;
        exitOptionsScreen = exitO;
        loginInteractor = loginInter;
        register = reg;
        login = log;
    }

    @Override
    public String display_event() {  //display string objects
        return storylineInteractor.getEventNarration();
    }

    @Override
    public int display_event_choices() { //display choices string narration
        return storylineInteractor.getEventChoice();
    }

    @Override
    public void display_lose() { //display losing screen
        JOptionPane.showMessageDialog(null, "Ahh...You lose.Try it again!");
    }

    @Override
    public void display_exit_options() { //display exit option screen
        exitOptionsScreen.BuildExitOptionScreen();
    }

    @Override
    public void returnHomeScreen() { //return to the HomeScreen
        enterAGameScreen.BuildEnterAGameScreen(this);
    }

    @Override
    public String getUsername() { //get the username
        return storylineInteractor.getUsername();
    }

    @Override
    public String setNewGame(String username) { //set a new game
        storylineInteractor.createPlayer(username);
        System.out.println(storylineInteractor.getEventNarration());
        return storylineInteractor.getEventNarration();
    }

    @Override
    public String loadGame() { ////load the saved game
        System.out.println(storylineInteractor.loadGame());
        return storylineInteractor.loadGame();
    }

    @Override
    public void createAccount(String username, String password, String repeatPassword) {
        // ask LoginInteractor to create an account, if not successful, give a warning
        loginInteractor.createAccount(username, password, repeatPassword);
    }

    @Override
    public void login(String username, String password) {
        //ask LoginInteractor to login, if not successful, give a warning
        loginInteractor.login(username, password);
    }

    @Override
    public void BuildRegisterScreen() { //jump to RegisterScreen
        register.BuildRegisterScreen(this);
    }

    @Override
    public void BuildLoginScreen() { //jump to LoginScreen
        login.BuildLoginScreen(this);
    }
}
