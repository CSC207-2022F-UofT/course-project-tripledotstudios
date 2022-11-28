package presenter;

import UI.*;

import javax.swing.*;
import java.util.List;

public class StorylinePresenter implements StorylineInterface {
    ExitOptionsScreen exitOptionsScreen;
    EnterAGameScreen enterAGameScreen;
    GameScreen gameScreen;

    public StorylinePresenter(EnterAGameScreen egs, ExitOptionsScreen eos, GameScreen gv) {
        enterAGameScreen = egs;
        exitOptionsScreen = eos;
        gameScreen = gv;
    }


    @Override
    public void display_event(String narration) {  //display string objects
        gameScreen.display_event(narration);
    }

    @Override
    public void display_event_choices(List<String> narrationChoices) { //display choices string narration
        gameScreen.display_event_choices(narrationChoices);
    }

    @Override
    public void take_event_choice(Player player) {
        gameScreen.take_event_choice(player);
    }

    @Override
    public void display_lose() { //display losing screen
        gameScreen.display_lose();
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
