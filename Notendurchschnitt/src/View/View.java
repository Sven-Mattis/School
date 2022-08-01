package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private final Model data;

    public View(String title, int x, int y, int width, int height, Model data) {
        super(title);
        this.setBounds(new Rectangle(width, height));
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.data = data;
    }

    public Model getData() {
        return this.data;
    }
}