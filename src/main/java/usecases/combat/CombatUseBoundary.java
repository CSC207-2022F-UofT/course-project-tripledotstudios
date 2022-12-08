package usecases.combat;

public interface CombatUseBoundary {
    /**
     * Used for Storyline to initiate combat.
     * @param UUID the UUID of the CombatEvent
     * @return whether the battle is won
     */
    boolean combat(int UUID);
}
