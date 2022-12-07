package UI;

import controller.StorylineController;

import javax.swing.*;
import java.io.IOException;

/**
 * Exit the game or continue the game
 */
public class ExitOptionsScreen {
    EnterAGameScreen enterAGameScreen;
    StorylineController storylineController;
    JFrame frame;

    public ExitOptionsScreen(UIFacade uiFacade) {
        enterAGameScreen = uiFacade.getEnterAGameScreen();

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

        exitGame.addActionListener(e -> {
            frame.dispose();
            enterAGameScreen.setVisible();
        });
        continueGame.addActionListener(e -> {
            try {
                storylineController.loadGame();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        sound.addActionListener(e -> storylineController.soundSwitch());

        frame.add(exitGame);
        frame.add(continueGame);
        frame.add(sound);
    }

    public void setController(UIFacade uiFacade) {
        storylineController = uiFacade.getStorylineController();
    }

    public void setVisible() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
