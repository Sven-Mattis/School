package View;


import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    public View(String title, int x, int y, int width, int height) {
        super(title);
        this.setBounds(new Rectangle(width, height));
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}