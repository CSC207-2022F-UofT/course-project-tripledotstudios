package presenter;

import UI.EnterAGameScreen;
import UI.ExitOptionsScreen;
import UI.GameView;

import java.util.List;

public class CombatPresenter implements CombatInterface{
    GameView gameScreen;

    public CombatPresenter(GameView gv) {
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
        return gameScreen.askQuestion(question, answers, responses);
    }

}
