import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListener implements KeyListener {

    public keyListener () {
        JFrame f = new JFrame();
        f.setSize(100,100);
        f.addKeyListener(this);
        f.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((e.isActionKey()))
            System.exit(0);

        try {
            main.render("" + e.getKeyChar());
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
