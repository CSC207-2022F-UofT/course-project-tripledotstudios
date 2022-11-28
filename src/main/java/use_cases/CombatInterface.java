package use_cases;

import java.util.*;

public interface CombatInterface {

    /**
     * Displays the health bar.
     */
    void displayHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth,
                                 String enemyName, int enemyMaxHealth, int enemyCurrentHealth);

    /**
     * Update the health bar.
     */
    void updateHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth,
                          String enemyName, int enemyMaxHealth, int enemyCurrentHealth);

    /**
     * Displays the narration before combat begins.
     */
    void displayNarration(String narr);

    /**
     * Asks the player whether the player wants to use an item or not.
     * @return whether the player wants to use an item in the current turn
     */
    boolean playerUsesItem();

    /**
     * Asks the player the question, and let the player choose between a list of answers.
     * After the player chooses an answer, display the corresponding response (the indexes match)
     * And return the index of the answer within "answers" that the player has chosen.
     * @return index of the answer [0 to length(answers)-1] that the player has chosen.
     */
    int askQuestion(String question, List<String> answers, List<String> responses);
}
