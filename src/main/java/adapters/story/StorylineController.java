package adapters.story;

import usecases.story.StorylineInputBoundary;

import java.io.IOException;

public class StorylineController {
    private final StorylineInputBoundary storyline;

    public StorylineController(StorylineInputBoundary storylineInter) {
        storyline = storylineInter;
    }

    public void setNewGame() throws IOException, ClassNotFoundException { //set a new game
        storyline.startGame();
    }

    public void loadGame() throws IOException, ClassNotFoundException { //load the saved game
        storyline.loadGame();
    }

    public void saveGame() throws IOException {
        storyline.saveGame();
    }

    public void soundSwitch() {
        storyline.soundSwitch();
    }

    public void updateEventID(int choice) throws IOException, ClassNotFoundException {
        storyline.updateEventID(choice);
        storyline.playEvent();
    }

    /**Play HomeScreen sound*/
    public void homeSoundPlay() {
        storyline.homeSoundPlay();
    }

    /**Stop HomeScreen sound*/
    public void homeSoundStop() {
        storyline.homeSoundStop();
    }

}
