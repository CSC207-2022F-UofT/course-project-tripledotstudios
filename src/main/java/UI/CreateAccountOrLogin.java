package UI;

import adapters.story.StorylineController;

import javax.swing.*;

/**
 *Create an account or login option screen
 */
public class CreateAccountOrLogin {
    RegisterScreen registerScreen;
    LoginScreen loginScreen;
    StorylineController storylineController;
    JFrame frame;

    public CreateAccountOrLogin(UIFacade uiFacade) {
        registerScreen = uiFacade.getRegisterScreen();
        loginScreen = uiFacade.getLoginScreen();

        frame = new JFrame("Create an account or login?");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton createAccount = new JButton("Create an account");
        JButton login = new JButton("Login");
        JButton sound = new JButton("Sound On/Off");
        createAccount.setBounds(200,50,200,50);
        login.setBounds(200,130,200,50);
        sound.setBounds(450,200,130,40);

        createAccount.addActionListener(e -> {
            frame.dispose();
            registerScreen.setVisible(); //jump to RegisterScreen
        });
        login.addActionListener(e -> {
            frame.dispose();
            loginScreen.setVisible(); //jump to LoginScreen
        });

        sound.addActionListener(e -> storylineController.soundSwitch());

        frame.add(createAccount);
        frame.add(login);
        frame.add(sound);
    }

    public void setController(UIFacade uiFacade) {
        storylineController = uiFacade.getStorylineController();
    }

    public void setVisible() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        storylineController.homeSoundPlay();
    }
}
