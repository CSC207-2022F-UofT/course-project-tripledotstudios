package presenter;

import UI.EnterAGameScreen;
import UI.ExitOptionsScreen;

public class CombatPresenter implements CombatInterface{
    GameScreen gameScreen;

    public CombatPresenter(GameScreen gv) {
        gameScreen = gv;
    }
    @Override
    public void displayHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
        gameScreen.displayHealthBar(playerName, playerMaxHealth, playerCurrentHealth, enemyName, enemyMaxHealth, enemyCurrentHealth);
    }

    @Override
    public void updateHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
        gameScreen.updateHealthBar(playerName, playerMaxHealth,playerCurrentHealth, enemyName, enemyMaxHealth, enemyCurrentHealth);
    }

    @Override
    public void displayNarration(String narr) {
        gameScreen.displayNarration(narr);
    }

    @Override
    public boolean playerUsesItem() {
        return gameScreen.playerUsesItem();
    }

    @Override
    public int askQuestion(String question, List<String> answers, List<String> responses) {
        return gameScreen.askQuestion(qustion, answers, reponses);
    }

}
