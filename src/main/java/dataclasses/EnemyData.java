package dataclasses;

import java.util.Random;

/**
 * The data class that stores data of an enemy encountered in a CombatEvent.
 *
 */
public class EnemyData {
    private final String name;  // Name of the enemy encountered.
    /**
     * Returns the name of the enemy encountered.
     * @return the name of the enemy (as a String)
     */
    public String getName() {
        return name;
    }

    // Enemy attack value parameters:
    private final Integer enemyAttackValueMean;
    private final Integer enemyAttackValueDeviation;

    /**
     * The default constructor.
     */
    public EnemyData(String name, Integer enemyAttackValueMean, Integer enemyAttackValueDeviation) {
        this.name = name;
        this.enemyAttackValueMean = enemyAttackValueMean;
        this.enemyAttackValueDeviation = enemyAttackValueDeviation;
    }

    /**
     * Generates an attack value for the enemy, range of which is [Mean - Deviation, Mean + Deviation]/
     * @return a random attack value for the enemy
     */
    public Integer generateAttackValue(){
        Random random = new Random();
        int minAttackValue = enemyAttackValueMean - enemyAttackValueDeviation;
        return random.nextInt(enemyAttackValueDeviation) + minAttackValue;
    }

}
