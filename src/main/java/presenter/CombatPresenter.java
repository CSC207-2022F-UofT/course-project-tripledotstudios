package presenter;

import UI.GameScreen;
import entities.ItemData;
import usecases.CombatInterface;

import java.util.ArrayList;
import java.util.List;

public class CombatPresenter implements CombatInterface {
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
    public void askIfPlayerUsesItem() {
        gameScreen.askIfPlayerUsesItem();
    }

    @Override
    public void letPlayerChooseItem(List<ItemData> playerItems) {
        gameScreen.letPlayerChooseItem((ArrayList<ItemData>) playerItems);
    }

    @Override
    public void informPlayerOfItemUse(ItemData item) {
        gameScreen.informPlayerOfItemUse(item);
    }

    @Override
    public void askQuestion(String question, List<String> answers, List<String> responses) {
        gameScreen.askQuestion(question, (ArrayList<String>) answers, (ArrayList<String>) responses);
    }

}
