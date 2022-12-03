package presenter;

import UI.*;
import entities.PlayerData;

import javax.swing.*;
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
    public void askQuestion(String question, List<String> answers, List<String> responses) { //display choices string narration
        gameScreen.askQuestion(question, (ArrayList<String>) answers, (ArrayList<String>) responses);
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
        enterAGameScreen.setVisible();
    }
}
