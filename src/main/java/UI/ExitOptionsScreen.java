package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Exit the game or continue the game
 */
public class ExitOptionsScreen {

    StorylineInterface controller;

    public void BuildExitOptionScreen() {
        JFrame frame = new JFrame("Exit the Game or Continue?");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());

        JButton exitGame = new JButton("Exit the game");
        JButton continueGame = new JButton("Continue the game");

        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.returnHomeScreen();
            }
        });
        continueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadGame();
            }
        });

        frame.add(exitGame);
        frame.add(continueGame);
        frame.setVisible(true);
    }
}
