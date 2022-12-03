package controller;

import entities.PlayerData;
import usecases.StorylineInteractor;

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

    public void updateEventID(int choice) throws IOException, ClassNotFoundException {
        storylineInteractor.updateEventID(choice);
        storylineInteractor.playEvent();
    }

    /**Play Homescreen sound*/
    public void homeSoundPlay() {
        storylineInteractor.homeHomePlay;
    }

    /**Stop Homescreen sound*/
    public void homeSoundStop() {
        storylineInteractor.homeHomeStop;
    }

}
