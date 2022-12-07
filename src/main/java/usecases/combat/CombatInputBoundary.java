package usecases.combat;

public interface CombatInputBoundary {
    /**
     * The controller calls this function to return if the player wants to use an item before a turn.
     * @param playerWantsItemUse whether the player wants to use an item
     */
    void respondItemUse(boolean playerWantsItemUse);

    /**
     * The controller calls this function to return which item the player wants to use.
     * @param itemChoice the INDEX of the item the player has chosen
     */
    void playerChoosesItem(int itemChoice);

    /**
     * The controller calls this function to return which answer the player has chosen when confronted
     * with the enemy's question.
     * @param answerIndex the INDEX of the answer the player has chosen
     */
    void returnAnswer(int answerIndex);
}
