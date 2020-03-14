import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Assessment 3 CO520, GUI Application.
 * The aim of the game is for a player to click on a square and hopefully avoid a bomb that is hidden under the square.
 * Each time a player clicks on a square and the bomb is not on that square, the player gains a point.
 * The player continues to receive points until the square with the bomb is clicked and then a player loses the game.
 * A player can choose difficulty levels, where the number of clicks on a square is reduced.
 *
 * @author Daniel Bartolini
 * @version 03/2020
 */
public class Game extends JFrame implements MouseListener {
    private JPanel panel_a, panel_b, panel_c;
    private MyBox[] panels = new MyBox[10]; //Boxes that make the game board.
    private JButton playBtn, exitBtn, easyBtn, intermediateBtn, difficultBtn;
    private int score;
    private int goal;
    private JLabel mode, msg;

    /**
     * Constructor for the bombs game.
     * Calls the method makeFrame() to create the GUI.
     * Initialises the player's score to 0 and sets easy mode to default.
     */
    public Game() {
        super("Chasing-bombs-db666");
        score = 0;
        goal = 5; //easy mode
        setSize(800, 500);
        makeFrame();
    }

    //Main class to launch the game.
    public static void main(String[] args) {
        Game g = new Game();
    }

    /**
     * Creates a frame with 3 panels:
     * the first panel contains the game board;
     * the second panel contains buttons that allow the user to restart or exit the game;
     * the third panel contains buttons that allow the user to change the game's difficulty level.
     * The board can be made playable or not.
     */
    public void makeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        panel_a = new JPanel();
        panel_b = new JPanel();
        panel_c = new JPanel();

        //Panel A setup
        panel_a.setLayout(new GridLayout(2, 5, 2, 2));
        panel_a.setBackground(new Color(0, 122, 51));

        add(panel_a);

        //Panel B setup
        panel_b.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel_b.setBackground(Color.white);

        playBtn = new JButton("Play a game");
        //the message to be shown in end game.
        msg = new JLabel();
        exitBtn = new JButton("Exit");
        panel_b.add(playBtn);
        panel_b.add(exitBtn);
        panel_b.add(msg);

        add(panel_b);

        //Panel C Setup
        panel_c.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel_c.setBackground(new Color(203, 51, 59));
        easyBtn = new JButton("Easy");
        mode = new JLabel("Easy mode selected");
        intermediateBtn = new JButton("Intermediate");
        difficultBtn = new JButton("Difficult");
        panel_c.add(easyBtn);
        panel_c.add(intermediateBtn);
        panel_c.add(difficultBtn);
        panel_c.add(mode);
        add(panel_c);

        //When play button is clicked reset the left panel as well as setting the score to 0.
        playBtn.addActionListener(e -> {
            msg.setText("");
            panel_a.removeAll();
            reset();
            panel_a.revalidate();
            score = 0;

            easyBtn.addActionListener(easy -> {
                goal = 5;
                mode.setText("Easy mode selected");
            });
            intermediateBtn.addActionListener(inter -> {
                goal = 7;
                mode.setText("Intermediate mode selected");
            });
            difficultBtn.addActionListener(diff -> {
                goal = 9;
                mode.setText("Difficult mode selected");
            });
        });
        exitBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    /**
     * Resets every component on the game board panel to start a new game.
     * A random bomb box is selected.
     */
    public void reset() {
        //Add a mouse listener to each box, then add it to the game board.
        for (int i = 0; i < 10; i++) {
            panels[i] = new MyBox();
            panels[i].addMouseListener(this);
            panel_a.add(panels[i]);
        }
        //Set a random bomb
        Random r = new Random();
        int rand = r.nextInt(10);
        panels[rand].setBomb(true);
    }

    /**
     * Ends the game selecting every box in panel A.
     */
    public void gameEnds(MouseEvent e) {
        for (MyBox b : panels)
            b.mouseClicked(e);
    }

    /**
     * When a box is clicked check if it's not been selected yet, if not:
     * if it is a bomb, then end the game and output a message;
     * otherwise, the player gains a point.
     * At last, if the score goal has been reached the player wins and the game ends with a victory message.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        MyBox src = (MyBox) e.getSource();
        if (src.isBomb()) {
            gameEnds(e);
            msg.setText("You lose! You scored: " + score + " points.");
        } else if (!src.isSelected()) {
            src.setSelected(true);
            score++;
        }
        if (score >= goal) {
            gameEnds(e);
            msg.setText("You win! You scored: " + score + " points.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

