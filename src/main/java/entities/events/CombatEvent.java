package entities.events;

import java.util.List;
import java.util.Random;

/**
 * The data class that stores a CombatEvent.
 */
public class CombatEvent extends Event {
    protected EnemyData enemy;  // The enemy encountered
    protected List<QuestionData> questions;  // The questions that are within the CombatEvent

    /**
     * Default constructor.
     */
    public CombatEvent(int UUID, String narration, List<String> choicesNarrations, List<Integer> choicesNextUUIDs,
                       String soundFile, boolean doesAutoSave, EnemyData enemy, List<QuestionData> questions) {
        super(UUID, narration, choicesNarrations, choicesNextUUIDs, soundFile, doesAutoSave);
        this.enemy = enemy;
        this.questions = questions;
    }

    /**
     * Returns the name of the enemy in this CombatEvent.
     * @return name of the enemy of this CombatEvent
     */
    public String getEnemyName(){
        return this.enemy.getName();
    }

    /**
     * Return the max health of the enemy in this CombatEvent.
     * @return max health of the enemy of this CombatEvent (as Integer not int)
     */
    public Integer getEnemyMaxHealth(){
        return this.enemy.getHealth();
    }
    
    /**
     * Generates and returns an enemy attack value.
     * @return a possible attack value of the enemy of this CombatEvent
    */
    public Integer generateEnemyAttackValue(){
        return this.enemy.generateAttackValue();
    }

    /**
     * Returns a random QuestionData instance form the list of possible questions in this CombatEvent.
     * @return a random QuestionData object
     */
    public QuestionData getRandomQuestion(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(questions.size());  // Random index within "questions"
        return questions.get(randomIndex);
    }

    //May add more methods related to "questions"
}
