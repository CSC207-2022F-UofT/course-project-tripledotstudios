package controller;

import usecases.CombatInteractor;

public class CombatController {
    private final CombatInteractor combatInteractor;

    public CombatController(CombatInteractor combatInter) {
        combatInteractor = combatInter;
    }

    public void respondItemUse(boolean playerWantsItemUse) {
        combatInteractor.respondItemUse(playerWantsItemUse);
    }

    public void playerChooseItem(int itemChoice) {
        combatInteractor.playerChoosesItem(itemChoice);
    }

    public void returnAnswer(int answerIndex) {
        combatInteractor.returnAnswer(answerIndex);
    }
}
