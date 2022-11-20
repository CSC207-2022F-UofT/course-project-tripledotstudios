package Controller;

import UI.*;

import javax.swing.*;

public class ViewController implements StorylineInterface {

    private final StorylineInteractor storylineInteractor;
    private final EnterAGameScreen enterAGameScreen;
    private final ExitOptionsScreen exitOptionsScreen;

    private final LoginInteractor loginInteractor;
    private final RegisterScreen register;
    private final CreateAccountOrLogin createAccountOrLogin;
    private final LoginScreen login;



    public ViewController(StorylineInteractor storylineInter, EnterAGameScreen enterA, ExitOptionsScreen exitO,
                          LoginInteractor loginInter, RegisterScreen reg, CreateAccountOrLogin cre, LoginScreen log) {
        storylineInteractor = storylineInter;
        enterAGameScreen = enterA;
        exitOptionsScreen = exitO;
        loginInteractor = loginInter;
        register = reg;
        createAccountOrLogin = cre;
        login = log;
    }

    @Override
    public void display_event() {  //display string objects
        System.out.println(storylineInteractor.getEventNarration());
    }

    @Override
    public void display_event_choices() { //display choices string narration
        System.out.println(storylineInteractor.getEventChoice());
    }

    @Override
    public void display_lose() { //display losing screen
        JOptionPane.showMessageDialog(null, "Ahh...You lose.Try it again!");
    }

    @Override
    public void display_exit_options() { //display exit option screen
        exitOptionsScreen.buildExitOptionScreen();
    }

    @Override
    public void returnHomeScreen() { //return to the HomeScreen
        enterAGameScreen.buildEnterAGameScreen(this);
    }

    @Override
    public String getUsername() { //get the username
        return storylineInteractor.getUsername();
    }

    @Override
    public String setNewGame() { //set a new game
        storylineInteractor.startGame();
        System.out.println(storylineInteractor.getEventNarration());
        return storylineInteractor.getEventNarration();
    }

    @Override
    public String loadGame() { ////load the saved game
        System.out.println(storylineInteractor.loadGame());
        return storylineInteractor.loadGame();
    }

    @Override
    public int createAccount(String username, String password, String repeatPassword) {
        // ask LoginInteractor to create an account, if not successful, give a warning
        loginInteractor.createAccount(username, password, repeatPassword);
    }

    @Override
    public int login(String username, String password) {
        //ask LoginInteractor to login, if not successful, give a warning
        loginInteractor.validatelogin(username, password);
    }

    @Override
    public void buildRegisterScreen() { //jump to RegisterScreen
        register.buildRegisterScreen(this);
    }

    @Override
    public void buildLoginScreen() { //jump to LoginScreen
        login.buildLoginScreen(this);
    }

    @Override
    public void returnCreateAccountOrLoginScreen() { //return to CreateAccountOrLoginScreen
        createAccountOrLogin.buildCreateAccountOrLoginScreen();
    }
}
