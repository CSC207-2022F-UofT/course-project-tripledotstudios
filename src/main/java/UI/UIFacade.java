package UI;

import adapters.combat.CombatController;
import adapters.login.LoginController;
import adapters.story.StorylineController;
import entities.events.EventManager;
import entities.items.ItemData;
import entities.player.PlayerData;
import entities.player.PlayerInteractor;
import adapters.combat.CombatPresenter;
import adapters.login.LoginPresenter;
import adapters.story.StorylinePresenter;
import usecases.combat.CombatInteractor;
import adapters.gamesave.LoadInteractor;
import adapters.gamesave.SaveInteractor;
import usecases.login.LoginInputBoundary;
import usecases.login.LoginInteractor;
import usecases.sound.SoundInteractor;
import usecases.story.StorylineInputBoundary;
import usecases.story.StorylineInteractor;

import java.util.ArrayList;
import java.util.HashMap;

public class UIFacade { //facade method that contains all UIs

    // set up all UIs
    private final GameScreen GAME_SCREEN = new GameScreen();
    private final RegisterScreen REGISTER_SCREEN = new RegisterScreen();
    private final LoginScreen LOGIN_SCREEN = new LoginScreen();
    private final CreateAccountOrLogin CREATE_ACCOUNT_OR_LOGIN = new CreateAccountOrLogin(this);
    private final EnterAGameScreen ENTER_A_GAME_SCREEN = new EnterAGameScreen(this);
    private final ExitOptionsScreen EXIT_OPTIONS_SCREEN = new ExitOptionsScreen(this);


    // UI getters

    public GameScreen getGameScreen() {
        return GAME_SCREEN;
    }

    public RegisterScreen getRegisterScreen() {
        return REGISTER_SCREEN;
    }

    public LoginScreen getLoginScreen() {
        return LOGIN_SCREEN;
    }

    public CreateAccountOrLogin getCreateAccountOrLogin() {
        return CREATE_ACCOUNT_OR_LOGIN;
    }

    public EnterAGameScreen getEnterAGameScreen() {
        return ENTER_A_GAME_SCREEN;
    }

    public ExitOptionsScreen getExitOptionsScreen() {
        return EXIT_OPTIONS_SCREEN;
    }


    // set up presenters
    LoginPresenter loginPresenter = new LoginPresenter(this);
    StorylinePresenter storylinePresenter = new StorylinePresenter(this);
    CombatPresenter combatPresenter = new CombatPresenter(this);


    //set up use cases

    //// set up LoginInteractor
    LoginInputBoundary loginInteractor = new LoginInteractor("data/sample_usernames.csv", loginPresenter);

    //// set up StorylineInteractor and CombatInteractor
    SoundInteractor soundInteractor = new SoundInteractor();
    SaveInteractor saveInteractor = new SaveInteractor();
    LoadInteractor loadInteractor = new LoadInteractor();
    String username = loginInteractor.getCurrentUser();
    HashMap<String, ArrayList<ItemData>> inventory = new HashMap<>(); //empty Hash Map
    PlayerData player = new PlayerData(username, 1, 100, inventory);
    PlayerInteractor playerInteractor = new PlayerInteractor(player);
    EventManager eventManager = new EventManager();
    CombatInteractor combatInteractor = new CombatInteractor(combatPresenter, eventManager, playerInteractor);
    StorylineInputBoundary storylineInteractor = new StorylineInteractor(storylinePresenter, soundInteractor,
            saveInteractor, loadInteractor, playerInteractor, combatInteractor, eventManager, loginInteractor);


    // set up controllers and make getters
    public LoginController getLoginController() {
        return new LoginController(loginInteractor);
    }

    public CombatController getCombatController() {
        return new CombatController(combatInteractor);
    }

    public StorylineController getStorylineController() {
        return new StorylineController(storylineInteractor);
    }

    // put controllers in UIs
    public void putControllerInUI() {
        GAME_SCREEN.setController(this);
        REGISTER_SCREEN.setController(this);
        LOGIN_SCREEN.setController(this);
        CREATE_ACCOUNT_OR_LOGIN.setController(this);
        ENTER_A_GAME_SCREEN.setController(this);
        EXIT_OPTIONS_SCREEN.setController(this);
    }

}
