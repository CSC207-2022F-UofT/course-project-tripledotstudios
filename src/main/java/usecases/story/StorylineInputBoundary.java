package usecases.story;

import entities.player.PlayerData;

import java.io.IOException;

public interface StorylineInputBoundary {
    void startGame() throws IOException;

    void updateEventID(int choice);

    void loadGame() throws IOException, ClassNotFoundException;

    void saveGame() throws IOException;

    void endGame();

    void lose();

    @SuppressWarnings("all")
        // function is reserved for debugging
    void exitGame(PlayerData player);

    void soundSwitch();

    void homeSoundPlay();

    void homeSoundStop();

    void playEvent() throws IOException;
}
