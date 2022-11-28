package UI;

import controller.LoginController;
import controller.StorylineController;

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
     * The login controller
     */
    LoginController loginController;

    /**
     * The storyline controller
     */
    StorylineController storylineController;

    JFrame frame;

    /**
     * Build a register screen to sign up or cancel.
     */
    public LoginScreen(LoginController lc, StorylineController sc) {
        loginController = lc;
        storylineController = sc;

        frame = new JFrame("Login");
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
                    loginController.login(username.getText(), String.valueOf(password.getPassword()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eve) {
                username.setText("");
                password.setText("");
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(signIn);
        buttons.add(cancel);

        JButton sound = new JButton("Sound On/Off");
        sound.setBounds(450,200,130,40);
        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { storylineController.soundSwitch();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);
        frame.add(this);
        frame.add(sound);

    }

    public void setVisible() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}