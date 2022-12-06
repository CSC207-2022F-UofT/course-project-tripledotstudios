package presenter;

import UI.*;

import java.util.ArrayList;
import java.util.List;

public class StorylinePresenter implements StorylineInterface {
    ExitOptionsScreen exitOptionsScreen;
    EnterAGameScreen enterAGameScreen;
    GameScreen gameScreen;

    public StorylinePresenter(UIFacade uiFacade) {
        enterAGameScreen = uiFacade.getEnterAGameScreen();
        exitOptionsScreen = uiFacade.getExitOptionsScreen();
        gameScreen = uiFacade.getGameScreen();
    }


    @Override
    public void displayNarration(String narration) {  //display string objects
        gameScreen.displayNarration(narration);
    }

    @Override
    public void askQuestion(String question, List<String> answers, List<String> responses, boolean isCombatEvent) { //display choices string narration
        gameScreen.askQuestion(question, (ArrayList<String>) answers, (ArrayList<String>) responses, isCombatEvent);
    }

    @Override
    public void printWin() {
        gameScreen.printWin();
    }

    @Override
    public void displayLose() { //display losing screen
        gameScreen.displayLose();
    }

    @Override
    public void display_exit_options() { //display exit option screen
        exitOptionsScreen.setVisible();
    }

    @Override
    public void returnHomeScreen() { //return to the HomeScreen
        gameScreen.displayWin();
        enterAGameScreen.setVisible();
    }
}
