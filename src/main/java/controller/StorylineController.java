package controller;
import entities.PlayerData;
import use_cases.StorylineInteractor;

import java.io.IOException;

public class StorylineController {
    private final StorylineInteractor storylineInteractor;

    public StorylineController(StorylineInteractor storylineInter) {
        storylineInteractor = storylineInter;
    }

    public void setNewGame() throws IOException, ClassNotFoundException { //set a new game
        storylineInteractor.startGame();
    }

    public void loadGame() throws IOException, ClassNotFoundException { //load the saved game
        storylineInteractor.loadGame();
    }

    public void soundSwitch() {
        storylineInteractor.soundSwitch();
    }

    public void updateEventID(PlayerData player, int choice) throws IOException, ClassNotFoundException {
        storylineInteractor.updateEventID(player, choice);
        storylineInteractor.playEvent(player);
    }

}
