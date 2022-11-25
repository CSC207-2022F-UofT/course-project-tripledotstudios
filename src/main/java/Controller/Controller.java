package Controller;

public class Controller {

    private final StorylineInteractor storylineInteractor;
    private final LoginInteractor loginInteractor;


    public Controller(StorylineInteractor storylineInter, LoginInteractor loginInter) {
        storylineInteractor = storylineInter;
        loginInteractor = loginInter;
    }

    public void setNewGame() { //set a new game
        storylineInteractor.startGame();
    }

    public void loadGame() { //load the saved game
        storylineInteractor.loadGame();
    }

    public void soundSwitch() {
        storylineInteractor.soundSwitch();
    }

    public void createAccount(String username, String password, String repeatPassword) {
        // ask LoginInteractor to create an account, if not successful, give a warning
        loginInteractor.createAccount(username, password, repeatPassword);
    }

    public void login(String username, String password) {
        //ask LoginInteractor to login, if not successful, give a warning
        loginInteractor.validatelogin(username, password);
    }

    public void logOut() {
        loginInteractor.logOut();
    }

}
