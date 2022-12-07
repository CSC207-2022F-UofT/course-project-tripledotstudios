package usecases.story;

import entities.player.*;

import java.io.IOException;

public interface StorylineInputBoundary {
    /**
     * Starts the game by setting the player on their first Event.
     */
    void startGame() throws IOException;

    /**
     * Updates the Player's current event based on the choice Player makes that correspond
     * to the UUID from ChoicesNextUUIDs in Event
     * @param choice the integer choice the Player makes
     */
    void updateEventID(int choice);

    /**
     * Loads the current event of the Player
     */
    void loadGame() throws IOException, ClassNotFoundException;

    /**
     * Saves PLayer data to the file
     */
    void saveGame() throws IOException;

    /**
     * Returns the Player back to the home screen
     */
    void endGame();

    /**
     * Player loses the game; Displays exit options
     */
    void lose();

    @SuppressWarnings("all")  // function is reserved for debugging
    void exitGame(PlayerData player);

    /**
     * Toggles sound on or off
     */
    void soundSwitch();

    /** Plays Home screen sound */
    void homeSoundPlay();

    /** Stops Home screen sound */
    void homeSoundStop();

    /**
     * Tells the UI to play the current event that the player is on.
     */
    void playEvent() throws IOException;
}
