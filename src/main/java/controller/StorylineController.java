package controller;

public class StorylineController {
    private final StorylineInteractor storylineInteractor;

    public StorylineController(StorylineInteractor storylineInter) {
        storylineInteractor = storylineInter;
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

    public void updateEventID(Player player, String narrationChoices) {
        storylineInteractor.updateEventID(player, narrationChoices);
        storylineInteractor.playEvent(player);
    }

}
