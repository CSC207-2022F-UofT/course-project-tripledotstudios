package UI;

import controller.CombatController;
import controller.StorylineController;

import javax.swing.*;
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

    public void take_event_choice(Player player) {
        System.out.println("Please type in your choice");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        storylineController.updateEventID(player, answer);
    }

    public void display_lose() { //display losing screen
        JOptionPane.showMessageDialog(null, "Ahh...You lose.Try it again!");
    }
}
