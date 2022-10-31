package dataclasses;

import java.util.List;

/**
 * The data class that stores a question that can be asked by the enemy.
 * An array of which is present in CombatEvent.
 */
public class QuestionData {
    private final String question;  // The question that the enemy is going to ask the player.
    /**
     * Return the question itself.
     * @return the question (as a String)
     */
    public String getQuestion() {
        return question;
    }

    private final List<String> answers;  // List of valid answers as Strings.
    private final List<Integer> attackValues;  // List of attack values corresponding to the answers (as Integers).
    // Note that the indexes of the two lists ALIGN WITH EACH OTHER.
    /**
     * Return the list of valid answers to the question.
     * @return the list of valid answers (as a list of Strings)
     */
    public List<String> getAnswers() {
        return answers;
    }
    /**
     * Return the attack values of the valid answers.
     * @return the list of attack values of the valid answers (as a list of Integers)
     */
    public List<Integer> getAttackValues() {
        return attackValues;
    }

    public QuestionData(String question, List<String> answers, List<Integer> attackValues) {
        this.question = question;
        this.answers = answers;
        this.attackValues = attackValues;
    }
}
