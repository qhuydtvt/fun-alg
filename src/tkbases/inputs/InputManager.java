package tkbases.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by huynq on 2/4/18.
 */
public class InputManager implements MouseListener {
    public static final InputManager instance = new InputManager();

    public final InputInfo rightMouseInfo = new InputInfo();

    public final InputInfo leftMouseInfo = new InputInfo();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouseInfo.pressed = true;
            leftMouseInfo.position.set(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouseInfo.pressed = false;
            leftMouseInfo.position.set(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
