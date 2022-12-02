package presenter;

import UI.*;
import entities.PlayerData;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class StorylinePresenter implements StorylineInterface {
    ExitOptionsScreen exitOptionsScreen;
    EnterAGameScreen enterAGameScreen;
    GameView gameScreen;

    public StorylinePresenter(EnterAGameScreen egs, ExitOptionsScreen eos, GameView gv) {
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
        gameScreen.display_event_choices(narrationChoices.toString());
    }

    @Override
    public void take_event_choice(PlayerData player) throws IOException, ClassNotFoundException {
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
