package adapters.combat;

import usecases.combat.CombatInputBoundary;

public class CombatController {
    private final CombatInputBoundary combatInputBoundary;

    public CombatController(CombatInputBoundary combatInter) {
        combatInputBoundary = combatInter;
    }

    public void respondItemUse(boolean playerWantsItemUse) {
        combatInputBoundary.respondItemUse(playerWantsItemUse);
    }

    public void playerChooseItem(int itemChoice) {
        combatInputBoundary.playerChoosesItem(itemChoice);
    }

    public void returnAnswer(int answerIndex) {
        combatInputBoundary.returnAnswer(answerIndex);
    }
}
