package UI;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Start a new game or resume a saved game
 */
public class EnterAGameScreen {
    CreateAccountOrLogin createAccountOrLogin;
    Controller controller;
    JFrame frame;

    public EnterAGameScreen(CreateAccountOrLogin cl, Controller contr) {
        createAccountOrLogin = cl;
        controller = contr;
        frame = new JFrame("Enter A Game");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton startANewGame = new JButton("Start a new game");
        JButton resumeASavedGame = new JButton("Resume a saved game");
        JButton logout = new JButton("Log out");
        JButton sound = new JButton("Sound On/Off");
        startANewGame.setBounds(200,30,200,50);
        resumeASavedGame.setBounds(200,110,200,50);
        sound.setBounds(200,190,200,50);
        sound.setBounds(450,200,130,40);

        startANewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                controller.setNewGame();
            }
        });
        resumeASavedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                controller.loadGame();
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                controller.logOut();
                createAccountOrLogin.setVisible();
            }
        });

        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.soundSwitch();
            }
        });

        frame.add(startANewGame);
        frame.add(resumeASavedGame);
        frame.add(logout);
        frame.add(sound);
    }

    public void setVisible() {
        frame.setVisible(true);
    }
}

