package UI;

import controller.LoginController;
import controller.StorylineController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Create an account or login option screen
 */
public class CreateAccountOrLogin {
    RegisterScreen registerScreen;
    LoginScreen loginScreen;
    LoginController loginController;
    StorylineController storylineController;
    JFrame frame;

    public CreateAccountOrLogin(RegisterScreen rs, LoginScreen ls, LoginController lc, StorylineController sc) {
        registerScreen = rs;
        loginScreen = ls;
        loginController = lc;
        storylineController = sc;

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

        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                registerScreen.setVisible();; //jump to RegisterScreen
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loginScreen.setVisible(); //jump to LoginScreen
            }
        });

        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storylineController.soundSwitch();
            }
        });

        frame.add(createAccount);
        frame.add(login);
        frame.add(sound);
    }

    public void setVisible() {
        frame.setVisible(true);
        storylineController.homeSoundPlay();
    }
}
