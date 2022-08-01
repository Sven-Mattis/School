package Views;

import javax.swing.*;

public abstract class View extends JFrame{

    public View (String title, int height, int width) {
        super(title);
        this.setBounds(0, 0, width, height);

        this.setLocationRelativeTo(null);

        this.setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}