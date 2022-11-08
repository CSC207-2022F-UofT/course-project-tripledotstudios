package dataclasses;

import java.util.List;

/**
 * The data class that stores a CombatEvent. TODO: INCOMPLETE
 */
public class CombatEvent extends Event {
    private EnemyData enemy;  // The enemy encountered
    private List<QuestionData> questions;  // The questions that are within the CombatEvent

    /**
     * Default constructor.
     * TODO: Refine the constructor to better initialize enemy and questions.
     */
    public CombatEvent(int UUID, String narration, List<String> choicesNarrations, List<Integer> choicesNextUUIDs,
                       String soundFile, EnemyData enemy, List<QuestionData> questions) {
        super(UUID, narration, choicesNarrations, choicesNextUUIDs, soundFile);
        this.enemy = enemy;
        this.questions = questions;
    }
}
