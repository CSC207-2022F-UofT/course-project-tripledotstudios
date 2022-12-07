package UI;

/* importing from other packages in the project **/
import controller.CombatController;
import controller.StorylineController;
import entities.items.ItemData;

/* relevant imports */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The GameScreen class handles the graphic user interface while the game is running.
 */
public class GameScreen {
    StorylineController storylineController;
    CombatController combatController;
    /** The main window of the game */
    JFrame frame;

    /** The panels that construct the frame and separate the elements of the screen*/
    JPanel dialoguePanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel eventNarration;

    /** final values in this class*/
    private final Color SC_COLOUR = new Color(29, 26, 38);
    private final Font FONT = new Font("Consolas", Font.PLAIN, 20);
    private final Border BORDER = BorderFactory.createLineBorder(Color.green, 1);


    /**
     * The Constructor for GameScreen which sets up the initial layout of the screen.
     */
    public GameScreen() {
        // setting up the JFrame itself
        frame = new JFrame("Useless Facts, Life or Death: A Trivia RPG Game");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(SC_COLOUR);
        frame.setResizable(false);

        // creating the top panel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(SC_COLOUR);
        topPanel.setPreferredSize(new Dimension(frame.getWidth(), 110));

        // creating the dialoge panel that covers the center of the screen
        dialoguePanel = new JPanel();
        dialoguePanel.setPreferredSize(new Dimension(frame.getWidth(), 510));
        dialoguePanel.setBackground(SC_COLOUR);
        dialoguePanel.setOpaque(true);
        dialoguePanel.setLayout(new BorderLayout());

        // creating the bottom panel which covers the bottom of the screen
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
        bottomPanel.setBackground(SC_COLOUR);
        bottomPanel.add(drawButtons(), BorderLayout.PAGE_END);

        // adding all the main panels to the frame
        frame.add(topPanel, BorderLayout.PAGE_START);
        frame.add(bottomPanel, BorderLayout.PAGE_END);
        frame.add(dialoguePanel, BorderLayout.CENTER);
    }

    /**
     * Displays the status of the game at the top left and right corner of the screen.
     * @param playerName The username of the player
     * @param playerMaxHealth The max health of the player
     * @param playerCurrentHealth The current health of the player
     * @param enemyName The enemy's name
     * @param enemyMaxHealth The maximum health of the enemy
     * @param enemyCurrentHealth The current health of the enemy.
     */
    public void displayHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
        // creating the panel in the top left corner
        JPanel playerStats = new JPanel(new GridLayout(2,1));
        playerStats.setPreferredSize(new Dimension(200, 60));
        playerStats.setBackground(SC_COLOUR);

        // creating the panel in the top right corner
        JPanel enemyStats = new JPanel(new GridLayout(2,1));
        enemyStats.setPreferredSize(new Dimension(200, 60));
        enemyStats.setBackground(SC_COLOUR);

        // creating the JTextAreas for each value using the helper method 'statsMaker'
        JTextArea pN = statsMaker(playerName);
        JTextArea pH = statsMaker(playerCurrentHealth + "/" + playerMaxHealth);
        JTextArea eN = statsMaker(enemyName);
        JTextArea eH = statsMaker(enemyCurrentHealth + "/" + enemyMaxHealth);

        // adding the JTextAreas to the panels
        playerStats.add(pN);
        playerStats.add(pH);
        enemyStats.add(eN);
        enemyStats.add(eH);

        // adding the local subpanels to the main panels in this class
        topPanel.add(playerStats, BorderLayout.LINE_START);
        topPanel.add(enemyStats, BorderLayout.LINE_END);

        // updating the frame
        frame.setVisible(true);
    }

    /**
     * Updates the visuals of the status bars at the top corners of the screen.
     * @param playerName The username of the player
     * @param playerMaxHealth The max health of the player
     * @param playerCurrentHealth The current health of the player
     * @param enemyName The enemy's name
     * @param enemyMaxHealth The maximum health of the enemy
     * @param enemyCurrentHealth The current health of the enemy.
     */
    public void updateHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
        // we call displayHealthBar to draw over the previous panels with new values
        displayHealthBar(playerName, playerMaxHealth, playerCurrentHealth, enemyName, enemyMaxHealth, enemyCurrentHealth);
    }

    /**
     * Displays the narration for the current event
     * @param narration The output string that will be printed to the interface
     */
    public void displayNarration(String narration) {
        // creating the text box that will contain the string describing the event
        JTextArea textArea = new JTextArea(narration);
        textArea.setLayout(new BorderLayout());
        textArea.setPreferredSize(new Dimension(frame.getWidth(), 200));
        textArea.setBackground(SC_COLOUR);
        textArea.setEditable(false);
        textArea.setForeground(Color.green);
        textArea.setFont(FONT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setHighlighter(null);

        // creating the panel that will act as the container for the event narration
        eventNarration = new JPanel();
        eventNarration.setPreferredSize(new Dimension(frame.getWidth(), 360));
        eventNarration.setBackground(SC_COLOUR);
        eventNarration.setLayout(new BorderLayout());
        eventNarration.add(textArea, BorderLayout.CENTER);

        // adding the local sub-panel to the main panel
        dialoguePanel.add(eventNarration, BorderLayout.PAGE_START);

        // updating the frame
        frame.setVisible(true);
    }

    /**
     * A popup that asks if the user would like to use an item from their inventory.
     */
    public void askIfPlayerUsesItem() {
        // setting the label for style purposes
        JLabel label = new JLabel("Would you like to use an item from your inventory?");
        label.setFont(FONT);
        label.setForeground(Color.green);

        // setting the colour of the popup
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // the popup
        int answer = JOptionPane.showConfirmDialog(frame, label);

        // handling the answer
        combatController.respondItemUse(answer == JOptionPane.YES_OPTION);
    }

    /**
     * Opens a new window to allow the player to select from their inventory.
     * This window cannot be closed unless the user selects some item.
     * @param playerItems The items to be displayed to the screen.
     */
    public void letPlayerChooseItem(ArrayList<ItemData> playerItems) {
        JDialog dialog = new JDialog(frame,"INVENTORY", true);

        // setting variables for button settings
        Font itemFont = new Font("Consolas", Font.PLAIN, 14);

        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();

        // looping through the player items.
        int c = 0;
        for (ItemData item : playerItems) {
            // creating a button based on an ItemData item.
            JButton i = new JButton("<html> <center> ["+(c + 1) + "] <br>" + item.getName()+ "<br>" +
                    item.getAttribute() + "<br>" + item.getValue() + "</center> </html>");

            // setting visuals for the buttons
            i.setFont(itemFont);
            i.setHorizontalAlignment(SwingConstants.CENTER);
            i.setBackground(SC_COLOUR);
            i.setForeground(Color.green);
            i.setBorder(BORDER);

            // add the button to the frame
            buttons.add(i);

            // incrementing the display count
            c++;

            // add the action listener to perform a function
            int finalC = c;
            i.addActionListener(e -> {
                System.out.println(finalC);
                // updating the choice
                combatController.playerChooseItem(finalC - 1);

                // closing the frame
                dialog.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            });
        }
        panel.add(buttons);
        dialog.setUndecorated(true);
        dialog.setContentPane(panel);
        dialog.add(buttons);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    /**
     * Informs the player that they've used an item.
     * @param item The item that is being used.
     */
    @SuppressWarnings("all")
    public void gainedItem(ItemData item) {
        // setting the label for style purposes
        JLabel label = new JLabel("Yikes you're terrible, here's an item.");
        label.setFont(FONT);
        label.setForeground(Color.green);

        // setting the colour of the popup
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // displaying the popup
        JOptionPane.showConfirmDialog(null, label,
                item.getName(), JOptionPane.DEFAULT_OPTION);
    }

    /**
     * Informs the user of the items that are being used
     * @param item The item whose information will be displayed
     */
    public void informPlayerOfItemUse(ItemData item) {
        String message = ("You have used " + item.getName());
        if (item.getValue() > 0) {
            if (item.getAttribute().equals("health")) {
                message += " and gained " + item.getValue() + " health ";
            }
            else {
                message += " and dealt " + item.getValue() + " damage ";
            }
        }
        else {
            message += " and lost " + item.getValue() + " health ";
        }

        // setting the label for style purposes
        JLabel label = new JLabel(message);
        label.setFont(FONT);
        label.setForeground(Color.green);

        // setting the colour of the popup
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // displaying the popup
        JOptionPane.showConfirmDialog(null, label,
                item.getName(), JOptionPane.DEFAULT_OPTION);
    }

    /**
     * Opens a popup to ask the user for their input and displays the responses.
     * @param question the string displayed to the frame for a question
     * @param answers the list of choices to be displayed to the screen
     * @param responses the responses displayed depending on the choice
     * @param isCombatEvent whether this is a combat event
     */
    public void askQuestion(String question, ArrayList<String> answers, ArrayList<String> responses, boolean isCombatEvent) { //display choices string narration
        if (!isCombatEvent) {
            // clear the status panels
            topPanel.removeAll();
            topPanel.setBackground(SC_COLOUR);
            topPanel.repaint(0,0,frame.getWidth(), 110);
            topPanel.revalidate();
        }

        // the text panel containing the question string
        JTextArea questionP = new JTextArea(question);
        questionP.setPreferredSize(new Dimension(frame.getWidth(), 70));
        questionP.setFont(FONT);
        questionP.setForeground(Color.green);
        questionP.setBackground(SC_COLOUR);
        questionP.setWrapStyleWord(true);
        questionP.setLineWrap(true);

        // the panel that will act as a container for the options
        JPanel eventOptions = new JPanel();
        eventOptions.setPreferredSize(new Dimension(frame.getWidth(), 200));
        eventOptions.setBackground(SC_COLOUR);
        eventOptions.setLayout(new GridLayout(answers.size(),1));

        // creating each option text panel
        int c = -1;
        for (String option: answers) {
            // creating the text panel
            JTextArea textArea = new JTextArea(c+1 + ") " + option);
            textArea.setBackground(SC_COLOUR);
            textArea.setForeground(Color.green);
            textArea.setFont(FONT);
            textArea.setPreferredSize(new Dimension(600, 30));
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            textArea.setHighlighter(null);

            // adding the text panel to the container for the options
            eventOptions.add(textArea);

            // incrementing the count
            c++;
        }

        // creating the question panel
        JPanel questionEventPanel = new JPanel(new BorderLayout());
        questionEventPanel.add(questionP, BorderLayout.PAGE_START);
        questionEventPanel.add(eventOptions);

        // adding the sub-panels to the main panels
        dialoguePanel.add(questionEventPanel, BorderLayout.CENTER);

        // displaying the visuals
        frame.setVisible(true);

        // asking a question by calling upon the method
        int response = getResponse(0, answers.size() - 1);

        // erasing the layered panels
        questionEventPanel.removeAll();
        eventNarration.removeAll();
        topPanel.removeAll();

        // if this is a combat event we will display the response and pass to CombatController
        if (isCombatEvent) {
            // setting the label for style purposes
            JLabel label = new JLabel(responses.get(response));
            label.setFont(FONT);
            label.setForeground(Color.green);

            // setting the colour of the popup
            UIManager.put("OptionPane.background", SC_COLOUR);
            UIManager.put("Panel.background", SC_COLOUR);

            // displaying the popup
            JOptionPane.showConfirmDialog(null, label,
                    "", JOptionPane.DEFAULT_OPTION);

            // passing the response to CombatController
            combatController.returnAnswer(response);
        }
        // otherwise this will be passed to StorylineController and no response is displayed
        else {
            try {
                // passing the response to StorylineController
                storylineController.updateEventID(response);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Helper method for askQuestion that returns the response of the user.
     * Ensures that no incorrect input is passed into the program.
     * @param min The minumum answer that can be given
     * @param max The maximum answer that can be given
     * @return The valid response given by the user.
     */
    public int getResponse(int min, int max) {
        // customizing the popup panel using UIManager
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // customizing the font in which the prompt is given
        JLabel label = new JLabel("Enter your response here.");
        label.setFont(FONT);
        label.setForeground(Color.green);

        // the variables containing the user's response
        int response;
        String ans = "";

        // attempt to parse the response of the player
        try {
            // collect the string response
            ans = JOptionPane.showInputDialog(null, label,"",JOptionPane.INFORMATION_MESSAGE);

            // parse this to an integer
            response = Integer.parseInt(ans);

            // if we parse successfully, check if min <= response <= max
            if (min > response || response > max) {
                // throw an exception otherwise
                throw new IndexOutOfBoundsException();
            }

            // update the frame
            frame.setVisible(true);
        }
        // if we are given a string or an invalid number
        catch (NumberFormatException | IndexOutOfBoundsException e) {
            // formatting JLabels
            JLabel l = new JLabel();
            l.setFont(FONT);
            l.setForeground(Color.green);

            // setting the colour of the popup
            UIManager.put("OptionPane.background", SC_COLOUR);
            UIManager.put("Panel.background", SC_COLOUR);

            if (ans == null) {
                l.setText("Confirm game exit.");
                int answer = JOptionPane.showConfirmDialog(frame, l);

                // handling the answer
                if (answer == JOptionPane.YES_OPTION) {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    return -1;
                } else {
                    return getResponse(min, max);
                }
            }

            // if given a valid letter, call upon the button functionalities
            switch (ans) {
                case "a":
                    // setting the label for style purposes
                    l.setText("Saved");

                    // displaying the popup
                    JOptionPane.showConfirmDialog(null, l,
                            "", JOptionPane.DEFAULT_OPTION);
                    try {
                        storylineController.saveGame();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "b":
                    // setting the label for style purposes
                    l.setText(generatePauseReply());

                    // displaying the popup
                    JOptionPane.showConfirmDialog(null, l,
                            "Alert!! Someone wants to pAuSe tHe gAmE", JOptionPane.DEFAULT_OPTION);
                    break;
                case "c":
                    storylineController.soundSwitch();
                    break;
                // otherwise it is simply an invalid input
                default:
                    l.setText("Don't mess around.");
                    JOptionPane.showConfirmDialog(null, l,
                            "Tomfoolery has occurred", JOptionPane.DEFAULT_OPTION);
                    break;
            }
            // call the method again until the correct response is given
            return getResponse(min, max);
        }

        // return a valid response
        return response;
    }

    public void printWin() {
        // setting the label for style purposes
        JLabel label = new JLabel("You have won. YAY.");
        label.setFont(FONT);
        label.setForeground(Color.green);

        // setting the colour of the popup
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // displaying the message
        JOptionPane.showMessageDialog(null, label);
    }

    /**
     * Helper method that returns a random pause message
     * @return returns a string that will be displayed in a popup
     */
    public String generatePauseReply() {
        // creating an array of replies
        String[] replies = new String[] {
                "Pause? You want to PAUSE? In this economy??? You've got things to do!!",
                "What exactly are you trying to pause... is there a time limit I'm not aware of?",
                "AHAHAHA PAUSE!?!?!? Good one.",
                "I am dissapointed but not surprised.",
                "Only mortals would want to pause time.",
                "Ur mom.", "<html>Fine.<br> paUsED.</html>", "Why would you pause, what are you pausing?",
                "<html><p>We're no strangers to love <br>" +
                        "You know the rules and so do I (do I) <br>" +
                        "A full commitment's what I'm thinking of <br>" +
                        "You wouldn't get this from any other guy <br>" +
                        "I just wanna tell you how I'm feeling <br>" +
                        "Gotta make you understand <br>" +
                        "Never gonna give you up <br>" +
                        "Never gonna let you down <br>" +
                        "Never gonna run around and desert you <br>" +
                        "Never gonna make you cry <br>" +
                        "Never gonna say goodbye <br>" +
                        "Never gonna tell a lie and hurt you" +
                        "We've known each other for so long<br>" +
                        "Your heart's been aching, but you're too shy to say it (say it)<br>" +
                        "Inside, we both know what's been going on (going on)<br>" +
                        "We know the game and we're gonna play it<br>" +
                        "And if you ask me how I'm feeling<br>" +
                        "Don't tell me you're too blind to see<br>" +
                        "Never gonna give you up<br>" +
                        "Never gonna let you down<br>" +
                        "Never gonna run around and desert you<br>" +
                        "Never gonna make you cry<br>" +
                        "Never gonna say goodbye<br>" +
                        "Never gonna tell a lie and hurt you<br>" +
                        "Never gonna give you up<br>" +
                        "Never gonna let you down<br>" +
                        "Never gonna run around and desert you<br>" +
                        "Never gonna make you cry<br>" +
                        "Never gonna say goodbye<br>" +
                        "Never gonna tell a lie and hurt you</p></html>"
        };

        // return a random element of the array
        return replies[(int)(Math.random() * ((replies.length)))];
    }

    /**
     * The fail message will appear if the player loses the game.
     */
    public void displayLose() {
        // setting the label for style purposes
        JLabel label = new JLabel("You have failed. Do better.");
        label.setFont(FONT);
        label.setForeground(Color.green);

        // setting the colour of the popup
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // displaying the message
        JOptionPane.showMessageDialog(null, label);

        // clear the status panels
        topPanel.removeAll();
        topPanel.setBackground(SC_COLOUR);
        topPanel.repaint(0,0,frame.getWidth(), 110);
        topPanel.revalidate();
    }

    public void displayWin() {
        // setting the label for style purposes
        JLabel label = new JLabel("HA U FELL FOR IT THERE'S NO REWARD BYE");
        label.setFont(FONT);
        label.setForeground(Color.green);

        // setting the colour of the popup
        UIManager.put("OptionPane.background", SC_COLOUR);
        UIManager.put("Panel.background", SC_COLOUR);

        // displaying the message
        JOptionPane.showMessageDialog(null, label);

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Helper function to create a JPanel of buttons in order
     * to de-clutter the constructor
     * @return JPanel containing buttons
     */
    public JPanel drawButtons() {
        // titles for the buttons
        String[] labels = new String[]{"a- Save", "b- Pause", "c- Sound \nOn/Off"};

        // creating buttons panel
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBackground(new Color(0,0,0));
        buttons.setBounds(0,0,800,60);

        // iterating through each label in the labels array
        for (String label : labels) {
            // creating a button for each label
            JButton button = new JButton(label);
            button.setFont(FONT);
            button.setForeground(Color.green);
            button.setBackground(SC_COLOUR);
            button.setBorder(BORDER);
            button.setPreferredSize(new Dimension(200, 50));

            // adding the button to the panel
            buttons.add(button);
        }

        // returning the panel of buttons
        return buttons;
    }

    /**
     * Helper method that draws the panels in the corners of the screen
     * @param f The text to be displayed
     * @return a JText area that can be used by the other methods.
     */
    public JTextArea statsMaker(String f) {
        // creating the text panel
        JTextArea pN = new JTextArea(f);
        pN.setFont(FONT);
        pN.setPreferredSize(new Dimension(300, 30));
        pN.setWrapStyleWord(true);
        pN.setLineWrap(true);
        pN.setBorder(BORDER);
        pN.setForeground(Color.green);
        pN.setBackground(SC_COLOUR);

        // return the panel
        return pN;
    }

    /**
     * Creates the controllers upon which this class depends
     * @param uiFacade The facade
     */
    public void setController(UIFacade uiFacade) {
        combatController = uiFacade.getCombatController();
        storylineController = uiFacade.getStorylineController();
    }

    /**
     * Sets the frame to be visible.
     */
    public void setVisible() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


