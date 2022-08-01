package Aufgabe_3;

import javax.swing.*;
import java.awt.*;

public class Frame extends Aufgabe_2.Frame {

    private JPanel contentPane = super.getPane();
    private JTextField tf = new JTextField();

    public Frame(String title, int w, int h) {
        super(title, w, h);

        this.tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.tf.setBounds(20, 20, 200, 20);
        this.contentPane.add(this.tf);
    }



}
