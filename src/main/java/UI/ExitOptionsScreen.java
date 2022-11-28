package UI;

import controller.StorylineController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Exit the game or continue the game
 */
public class ExitOptionsScreen {
    EnterAGameScreen enterAGameScreen;
    StorylineController storylineController;
    JFrame frame;

    public ExitOptionsScreen(EnterAGameScreen egs, StorylineController contr) {
        enterAGameScreen = egs;
        storylineController = contr;

        frame = new JFrame("Exit the Game or Continue?");
        frame.add(new JLabel("If you did not save, any unsaved progress will be lost."));
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
                enterAGameScreen.setVisible();
            }
        });
        continueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storylineController.loadGame();
            }
        });

        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storylineController.soundSwitch();
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
