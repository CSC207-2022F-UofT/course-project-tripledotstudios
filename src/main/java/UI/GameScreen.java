package UI;

import controller.CombatController;
import controller.StorylineController;
import entities.ItemData;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreen {
    StorylineController storylineController;
    CombatController combatController;
    JFrame frame;
    JPanel dialoguePanel;
    JPanel topPanel;
    JPanel bottomPanel;

    JPanel gridPanel;

    static Color SC_COLOUR = new Color(29, 26, 38);
    static Font FONT = new Font("Consolas", Font.PLAIN, 20);

    /**
     * ignoring this for now because it sucks
     */
    public GameScreen(StorylineController sc, CombatController cc) {
        storylineController = sc;
        combatController = cc;

        // setting up the JFrame itself
        frame = new JFrame("Mwahahah the game");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(SC_COLOUR);

        // creating the top panel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(SC_COLOUR);
        topPanel.setPreferredSize(new Dimension(800, 100));

        // creating the dialogue panel
        dialoguePanel = new JPanel();
        dialoguePanel.setPreferredSize(new Dimension(800,520));
        dialoguePanel.setBackground(SC_COLOUR);
        dialoguePanel.setLayout(new BorderLayout());

        // bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(800, 120));
        bottomPanel.setBackground(SC_COLOUR);
        bottomPanel.add(drawButtons(), BorderLayout.PAGE_END);

        // adding all the main panels to the frame
        frame.add(topPanel, BorderLayout.PAGE_START);
        frame.add(bottomPanel, BorderLayout.PAGE_END);
        frame.add(dialoguePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void askIfPlayerUsesItem() {
        int answer = JOptionPane.showConfirmDialog(frame, "Would you like to use an item from your inventory?");
        if (answer == JOptionPane.YES_OPTION) {
            //cc.respondItemUse(true);
        } else if (answer == JOptionPane.NO_OPTION) {
            //cc.respondItemUse(false);
        }
    }

    public void letPlayerChooseItem(ArrayList<ItemData> playerItems) {
        JFrame itemFrame = new JFrame("Select an item");
        itemFrame.setSize(600, 600);
        itemFrame.setLayout(new BorderLayout());
        itemFrame.getContentPane().setBackground(SC_COLOUR);
    }

    public void informPlayerOfItemUse(ItemData item) {
        int input = JOptionPane.showConfirmDialog(null,
                "Click ok if you are ok", "Be ok!", JOptionPane.DEFAULT_OPTION);
    }

    public JTextArea statsMaker(String f) {
        JTextArea pN = new JTextArea(f);
        pN.setFont(FONT);
        pN.setPreferredSize(new Dimension(100, 30));
        pN.setForeground(Color.green);
        pN.setBackground(SC_COLOUR);

        return pN;
    }

    public void displayHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
        JPanel playerStats = new JPanel(new GridLayout(2,1));
        playerStats.setPreferredSize(new Dimension(100, 60));
        playerStats.setBackground(Color.cyan);

        JPanel enemyStats = new JPanel(new GridLayout(2,1));
        enemyStats.setPreferredSize(new Dimension(100, 60));
        enemyStats.setBackground(Color.cyan);

        JTextArea pN = statsMaker(playerName);
        JTextArea pH = statsMaker(playerCurrentHealth + "/" + playerMaxHealth);
        JTextArea eN = statsMaker(enemyName);
        JTextArea eH = statsMaker(enemyCurrentHealth + "/" + enemyMaxHealth);

        playerStats.add(pN);
        playerStats.add(pH);
        enemyStats.add(eN);
        enemyStats.add(eH);

        topPanel.add(playerStats, BorderLayout.LINE_START);
        topPanel.add(enemyStats, BorderLayout.LINE_END);

        frame.setVisible(true);
    }

    public void updateHealthBar(String playerName, int playerMaxHealth, int playerCurrentHealth, String enemyName, int enemyMaxHealth, int enemyCurrentHealth) {
        displayHealthBar(playerName, playerMaxHealth, playerCurrentHealth, enemyName, enemyMaxHealth, enemyCurrentHealth);
    }

    /**
     * Displays the narration for the current event
     * @param narration The output string that will be printed to the interface
     */
    public void displayNarration(String narration) {  //display string objects
        JTextArea textArea = new JTextArea(narration);
        textArea.setLayout(new BorderLayout());
        textArea.setPreferredSize(new Dimension(frame.getWidth(), 200));
        textArea.setBackground(SC_COLOUR);
        textArea.setForeground(Color.green);
        textArea.setFont(FONT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JPanel eventNarration = new JPanel();
        eventNarration.setPreferredSize(new Dimension(frame.getWidth(), 310));
        eventNarration.setBackground(SC_COLOUR);
        eventNarration.setLayout(new BorderLayout());
        eventNarration.add(textArea, BorderLayout.CENTER);

        dialoguePanel.add(eventNarration, BorderLayout.PAGE_START);
        frame.setVisible(true);
    }

    public void askQuestion(String question, ArrayList<String> answers, ArrayList<String> responses) { //display choices string narration
        JPanel questionEventPanel = new JPanel(new BorderLayout());
        JTextArea questionP = new JTextArea(question);
        questionP.setPreferredSize(new Dimension(frame.getWidth(), 70));
        questionP.setFont(FONT);
        questionP.setForeground(Color.green);
        questionP.setBackground(SC_COLOUR);
        questionP.setWrapStyleWord(true);
        questionP.setLineWrap(true);

        JPanel eventOptions = new JPanel();
        eventOptions.setPreferredSize(new Dimension(frame.getWidth(), 250));
        eventOptions.setBackground(SC_COLOUR);
        eventOptions.setLayout(new GridLayout(answers.size(),1));

        JPanel responsePanel = new JPanel();
        responsePanel.setLayout(new BorderLayout());
        responsePanel.setPreferredSize(new Dimension(800,150));
        responsePanel.setBackground(SC_COLOUR);

        String prompt = "\nType your response... ";
        NumberFormatter format = new NumberFormatter();
        format.setValueClass(Integer.class);
        format.setMinimum(1);
        format.setMaximum(answers.size());
        format.setCommitsOnValidEdit(false);
        format.setAllowsInvalid(false);

        JFormattedTextField textField = new JFormattedTextField(format);
        // JOptionPane.showMessageDialog(null, textField);
        textField.setFont(FONT);
        textField.setForeground(Color.green);
        textField.setBackground(SC_COLOUR);
        textField.setBorder(null);
        textField.setCaretColor(Color.green);
        textField.setPreferredSize(new Dimension(frame.getWidth(), 15));
        textField.setText(prompt);

        JTextArea message = new JTextArea(prompt);
        message.setPreferredSize(new Dimension(240, 15));
        message.setFont(FONT);
        message.setForeground(Color.green);
        message.setBackground(SC_COLOUR);

        int c = 0;
        for (String option: answers) {
            JTextArea textArea = new JTextArea(c+1 + ") " + option);
            textArea.setBackground(SC_COLOUR);
            textArea.setForeground(Color.green);
            textArea.setFont(FONT);
            textArea.setPreferredSize(new Dimension(600, 50));
            textArea.setLineWrap(true);
            eventOptions.add(textArea);
            c++;
        }

        final int[] response = {0};
        textField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                try {
                    response[0] = Integer.parseInt(textField.getText());
                }
                catch (NumberFormatException v) {
                    try {
                        storylineController.updateEventID(-1);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                // storylineController.updateEventID(player, answer);
            }});

        System.out.println(response[0]);
        questionEventPanel.add(questionP, BorderLayout.PAGE_START);

        questionEventPanel.add(eventOptions);

        responsePanel.add(message, BorderLayout.LINE_START);
        responsePanel.add(textField, BorderLayout.CENTER);

        bottomPanel.add(responsePanel, BorderLayout.LINE_START);
        dialoguePanel.add(questionEventPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    private int verifyInt(String num, String prompt) {
        int val = -1;
        if (num.contains(prompt)) {
            num = num.substring(prompt.length());
        }
        try {
            val = Integer.parseInt(num);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("brans");
        }
        return val;
    }

    /**
     * The fail message will appear if the player loses the game.
     */
    public void displayLose() { //display losing screen
        JOptionPane.showMessageDialog(null, "Ahh...You lose.Try it again!");
    }

    //public static void displayheat

    /**
     * Helper function to create a JPanel of buttons in order
     * to de-clutter the constructor
     * @return JPanel containing buttons
     */
    public static JPanel drawButtons() {
        // creating the buttons and adjusting the size
        JButton b1 = new JButton("Save");
        b1.setPreferredSize(new Dimension(100, 50));

        JButton b2 = new JButton("Pause");
        b2.setPreferredSize(new Dimension(100, 50));

        JButton b3 = new JButton("Sound On/Off");
        b3.setPreferredSize(new Dimension(100, 50));

        JButton b4 = new JButton("Skip");
        b4.setPreferredSize(new Dimension(100, 50));

        // creating the methods that will be executed after clicking
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saved!!");
                // storyline.savegamelskjdflk
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Paused!!");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sound!!");
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SKIP!!");
            }
        });

        // adding the buttons to the button panel
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBackground(new Color(0,0,0));
        buttons.setBounds(0,0,800,60);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);

        return buttons;
    }

//    public static void main(String args[]) {
//        GameScreen g = new GameScreen();
//        ArrayList<String> a = new ArrayList<>();
//        a.add("cats");
//        a.add("beans");
//        a.add("potato");
//        a.add("crimes");
//        g.displayNarration("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna " +
//                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
//                "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
//                "sunt in culpa qui officia deserunt mollit anim id est laborum.");
//        g.askQuestion("Hello this is a question", a, a);
//        //g.take_event_choice();
//        g.displayHealthBar("hm", 1, 1, "M", 1, 1);
//        g.askIfPlayerUsesItem();
//        g.updateHealthBar("hm", 1, 0, "m", 1, 0);
//    }

}
