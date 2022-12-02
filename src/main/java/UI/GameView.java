package UI;

import controller.CombatController;
import controller.StorylineController;
import entities.PlayerData;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameView {
    StorylineController storylineController;
    CombatController combatController;

    public GameView(StorylineController sc, CombatController cc) {
        storylineController = sc;
        combatController = cc;
    }

    public void display_event(String narration) {  //display string objects
        System.out.println(narration);
    }

    public void display_event_choices(String narrationChoices) { //display choices string narration
        System.out.println(narrationChoices);
    }

    public void take_event_choice(PlayerData player) throws IOException, ClassNotFoundException {
        System.out.println("Please type in your choice");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        storylineController.updateEventID(player, Integer.parseInt(answer));
    }

    public void display_lose() { //display losing screen
        JOptionPane.showMessageDialog(null, "Ahh...You lose.Try it again!");
    }

    public void updateHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
    }

    public void displayNarration(String narr) {

    }

    public boolean playerUsesItem() {


        return false;
    }

    public int askQuestion(String question, List<String> answers, List<String> responses) {


        return 0;
    }

    public void displayHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {

    }
}

