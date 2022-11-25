package UI;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Exit the game or continue the game
 */
public class ExitOptionsScreen {
    CreateAccountOrLogin createAccountOrLogin;
    Controller controller;
    JFrame frame;

    public ExitOptionsScreen(CreateAccountOrLogin cl, Controller contr) {
        createAccountOrLogin = cl;
        controller = contr;

        frame = new JFrame("Exit the Game or Continue?");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton exitGame = new JButton("Exit the game");
        JButton continueGame = new JButton("Continue the game");
        JButton sound = new JButton("Sound On/Off");
        exitGame.setBounds(200,50,200,50);
        continueGame.setBounds(200,130,200,50);
        sound.setBounds(450,200,130,40);

        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createAccountOrLogin.setVisible();
            }
        });
        continueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadGame();
            }
        });

        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.soundSwitch();
            }
        });

        frame.add(exitGame);
        frame.add(continueGame);
        frame.add(sound);
    }

    public void setVisible() {
        frame.setVisible(true);
    }
}
