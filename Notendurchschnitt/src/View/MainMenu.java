package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryNotEmptyException;

public class MainMenu extends View {

    private Model data;
    private JPanel panel = new JPanel();

    {
        this.add(this.panel);
    }


    public MainMenu () {
        super("Notenrechner-Menü", 0, 0, 425, (10+20) * 3 + 10, null);

        this.data = super.getData();
        this.setResizable(false);


        this.panel.setLayout(null);
        this.panel.setBounds(super.getBounds());
    }

    public void showView() {
        JLabel anzahlKa = new JLabel("Anzahl der Klassenarbeiten:");
        anzahlKa.setBounds( 5 , 5 , 200, 20);
        anzahlKa.setPreferredSize( new Dimension( 200, 20));
        this.panel.add(anzahlKa);

        JTextField anzahlKaTF = new JTextField();
        anzahlKaTF.setBounds( 205 , 10 , 200, 20);
        anzahlKaTF.setPreferredSize( new Dimension( 200, 20));
        this.panel.add(anzahlKaTF);

        JButton start = new JButton("Start");
        start.setBounds( 5 , 10+15+10 , 400, 20);
        start.setPreferredSize( new Dimension( 400, 20));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int kas = Integer.parseInt(anzahlKaTF.getText());
                    dispose();
                    new NotenRechner("Notenrechner", 1000, 1000, null, kas);
                } catch ( NumberFormatException ee ) {
                    anzahlKaTF.setText("");
                }
            }
        });
        this.panel.add(start);

        this.dispose();
        this.setVisible(true);
    }

}