package entities.events;

import java.util.Random;

/**
 * The data class that stores data of an enemy encountered in a CombatEvent.
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

	private final Integer health;  //The max health value of the enemy.
	/**
	 * Returns the max health of the enemy encountered.
	 * @return the max health of the enemy (as an Integer)
	 */
	public Integer getHealth() {
		return health;
	}

    // Enemy attack value parameters:
    private final Integer enemyAttackValueMean;
    private final Integer enemyAttackValueDeviation;

    /**
     * The default constructor.
     */
    public EnemyData(String name, int health, int enemyAttackValueMean, int enemyAttackValueDeviation) {
        this.name = name;
        this.health = health;
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
