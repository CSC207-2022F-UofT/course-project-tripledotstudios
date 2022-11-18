package UI;

import Controller.StorylineInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login
 */
public class LoginScreen extends JPanel {
    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);

    /**
     * The controller
     */
    StorylineInterface controller;

    /**
     * Build a register screen to sign up or cancel.
     */
    public void BuildLoginScreen(StorylineInterface contr) {

        controller = contr;

        JFrame frame = new JFrame("Login");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel usernameInfo = new JPanel();
        usernameInfo.add(new JLabel("Enter username"));
        usernameInfo.add(username);

        JPanel passwordInfo = new JPanel();
        passwordInfo.add(new JLabel("Enter password"));
        passwordInfo.add(password);

        JButton signIn = new JButton("Sign in");
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
                try {
                    controller.login(username.getText(),
                            String.valueOf(password.getPassword()));
                    JOptionPane.showMessageDialog(null, String.format("%s logged in.", username.getText()));
                    controller.returnHomeScreen(); //jump to the EnterAGameScreen
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
                username.updateUI();
                password.updateUI();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(signIn);
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);

    }

}