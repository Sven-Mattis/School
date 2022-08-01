package Aufgabe_2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Frame extends Aufgabe_1.Frame {

    private JPanel contentPane;

    public Frame(String title, int w, int h) {
        super(title, w, h);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(this.contentPane);

        this.contentPane.setLayout(null);
    }
    public JPanel getPane () {
        return this.contentPane;
    }
}
