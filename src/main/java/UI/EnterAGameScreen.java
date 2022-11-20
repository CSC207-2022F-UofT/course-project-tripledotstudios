package UI;

import Controller.StorylineInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Start a new game or resume a saved game
 */
public class EnterAGameScreen {

    StorylineInterface controller;

    public void buildEnterAGameScreen(StorylineInterface contr) {
        controller = contr;
        JFrame frame = new JFrame("Enter A Game");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());

        JButton startANewGame = new JButton("Start a new game");
        JButton resumeASavedGame = new JButton("Resume a saved game");
        JButton logout = new JButton("Log out");

        startANewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = controller.getUsername();
                controller.setNewGame();
            }
        });
        resumeASavedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.display_event();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.returnCreateAccountOrLoginScreen();
            }
        });

        frame.add(startANewGame);
        frame.add(resumeASavedGame);
        frame.add(logout);
        frame.setVisible(true);

    }

}

