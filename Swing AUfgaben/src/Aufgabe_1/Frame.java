package Aufgabe_1;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame (String title, int w, int h) {
        super(title);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds((screen.width/2)-(w/2), (screen.height/2)-(h/2), w, h);

    }
}
