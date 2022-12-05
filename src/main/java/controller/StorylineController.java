package controller;

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

    public void saveGame() throws IOException {
        storylineInteractor.saveGame();
    }

    public void soundSwitch() {
        storylineInteractor.soundSwitch();
    }

    public void updateEventID(int choice) throws IOException, ClassNotFoundException {
        storylineInteractor.updateEventID(choice);
        storylineInteractor.playEvent();
    }

    /**Play HomeScreen sound*/
    public void homeSoundPlay() {
        storylineInteractor.homeSoundPlay();
    }

    /**Stop HomeScreen sound*/
    public void homeSoundStop() {
        storylineInteractor.homeSoundStop();
    }

}
