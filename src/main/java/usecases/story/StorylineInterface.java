package usecases.story;

import java.util.List;

public interface StorylineInterface {
    /**
     * Display the narration of the event
     * @param narration the narration of the event
     */
    void displayNarration(String narration);

    /**
     * Ask a question to the player.
     * Can either be during a normal event, or a combat event.
     * @param question the text of the question
     * @param answers potential answers for the player to choose from
     * @param responses the responses from the enemy or game to the player's choice
     */
    void askQuestion(String question, List<String> answers, List<String> responses, boolean isCombatEvent);

    /** Displays losing screen */
    void displayLose();

    /** Displays exit options screen */
    void display_exit_options();

    /** Returns to the Home Screen */
    void returnHomeScreen();

    /** Prints the win screen */
    void printWin();
}
