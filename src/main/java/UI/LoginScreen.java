package UI;

import adapters.login.LoginController;
import adapters.story.StorylineController;

import javax.swing.*;
import java.awt.*;

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
    public LoginScreen() {

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
        signIn.addActionListener(eve -> {
            try {
                loginController.login(username.getText(), String.valueOf(password.getPassword()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(eve -> {
            username.setText("");
            password.setText("");
        });

        JPanel buttons = new JPanel();
        buttons.add(signIn);
        buttons.add(reset);

        JButton sound = new JButton("Sound On/Off");
        sound.setBounds(450,200,130,40);
        buttons.add(sound);
        sound.addActionListener(e -> storylineController.soundSwitch());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);

        frame.add(this);

    }

    public void setController(UIFacade uiFacade) {
        loginController = uiFacade.getLoginController();
        storylineController = uiFacade.getStorylineController();
    }

    public void setVisible() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
