package usecases;

import entities.ItemData;

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
     * .
     * Return this function by calling respondItemUse(bool) from Controller.
     */
    void askIfPlayerUsesItem();

    /**
     * Lets the player choose which item to use from a list of items.
     * @param playerItems the list (of ItemData) that contains all the players items
     *
     * Return the result (index of the item in the array) by calling playerChoosesItem(int) from Controller
     */
    void letPlayerChooseItem(List<ItemData> playerItems);

    /**
     * Tell the player that s/he used this item, and display its effects,
     * e.g. "You used [item name] and it healed you by [...] points" or
     *      "You used [item name] and it damaged the enemy by [...] points"
     * @param item the item being used
     */
    void informPlayerOfItemUse(ItemData item);

    /**
     * Asks the player the question, and let the player choose between a list of answers.
     * After the player chooses an answer, display the corresponding response (the indexes match)
     * .
     * Note that CombatController must use returnAnswer() to return the index of the answer chosen
     */
    void askQuestion(String question, List<String> answers, List<String> responses);
}
