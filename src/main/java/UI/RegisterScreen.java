package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Create an account
 */
public class RegisterScreen extends JPanel {
    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);
    /**
     * The second password to make sure the user understands
     */
    JPasswordField repeatPassword = new JPasswordField(15);

    /**
     * The controller
     */
    StorylineInterface controller;

    /**
     * Build a register screen to sign up or cancel.
     */
    public void BuildRegisterScreen(StorylineInterface contr) {

        controller = contr;

        JFrame frame = new JFrame("Register");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        JLabel title = new JLabel("Register");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel usernameInfo = new JPanel();
        usernameInfo.add(new JLabel("Choose username"));
        usernameInfo.add(username);

        JPanel passwordInfo = new JPanel();
        passwordInfo.add(new JLabel("Choose password"));
        passwordInfo.add(password);

        JPanel repeatPasswordInfo = new JPanel();
        repeatPasswordInfo.add(new JLabel("Enter password again"));
        repeatPasswordInfo.add(repeatPassword);

        JButton signUp = new JButton("Sign up");
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
                try {
                    controller.createAccount(username.getText(),
                            String.valueOf(password.getPassword()),
                            String.valueOf(repeatPassword.getPassword()));
                    JOptionPane.showMessageDialog(null, String.format("%s created.", username.getText()));
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
                repeatPassword.updateUI();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(signUp);
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);

    }

}
