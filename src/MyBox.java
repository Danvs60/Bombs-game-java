import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Panel with features the are useful to the bombs game.
 *
 * @author Daniel Bartolini
 * @version 03/2020
 */
public class MyBox extends JPanel implements MouseListener {
    private boolean isBomb;
    private boolean isSelected;
    private Color trueGreen = new Color(0, 122, 51);
    private Color unselected = new Color(0, 60, 25);
    private Color hover = new Color(80, 100, 51);

    public MyBox() {
        isBomb = false;
        isSelected = false;
        setBackground(unselected);
        addMouseListener(this);
    }

    /**
     * Returns true if the box is a bomb, false otherwise.
     *
     * @return boolean value that signifies if the box is a bomb.
     */
    public boolean isBomb() {
        return isBomb;
    }

    /**
     * Returns true if the box has been selected, false otherwise.
     *
     * @return true if selected, false otherwise.
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Set the box isBomb field.
     *
     * @param bomb true or false.
     */
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    /**
     * Change isSelected field to indicate that the box has been selected.
     *
     * @param selected true or false.
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Set the background of a box to black if it is a bomb or a different shade of green otherwise.
     * Then, remove the mouse listener from that box (because the box should not be selectable anymore).
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (isBomb) setBackground(Color.black);
        else setBackground(trueGreen);
        removeMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Hovering the mouse makes the box change colour.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(hover);
    }

    /**
     * Exiting the box with your mouse makes it change colour.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(unselected);
    }

}
