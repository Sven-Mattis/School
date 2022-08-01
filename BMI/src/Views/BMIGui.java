package Views;

import Models.Person;

import javax.swing.*;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BMIGui extends View{

    private final ArrayList<JTextField> textFields = new ArrayList<>(4);

    public BMIGui() {
        super("BMI-Rechner", 450, 450);

        this.setResizable(false);

        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel img = new JLabel();
        img.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("bmi.png"))));
        img.setBounds(20 ,35, 155, 155);
        panel.add(img);

        String[] labels = new String[] {"Vorname:", "Nachname:", "Gewicht (kg):", "Größe (cm):"};
        for(int i = 0; i < 4; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(200, 40*(i+1), 100, 25);
            label.setPreferredSize(new Dimension(100, 25));
            panel.add(label);
            JTextField tf = new JTextField();
            tf.setBounds(300, 40*(i+1), 100, 25);
            tf.setPreferredSize(new Dimension(100, 25));
            this.textFields.add(tf);
            panel.add(tf);
        }

        labels = new String[] {"Person", "BMI", "Auswertung"};
        for(int i = 0; i < 3; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(20, 40*(i+1) + 170, 230, 25);
            label.setPreferredSize(new Dimension(230, 25));
            panel.add(label);
            JTextField tf = new JTextField();
            tf.setEditable(false);
            tf.setBounds(200, 40*(i+1) + 180, 200, 25);
            tf.setPreferredSize(new Dimension(200, 25));
            this.textFields.add(tf);
            panel.add(tf);
        }
        labels = new String[] {"Neu", "Berechnen", "Beenden"};
        for(int i = 0; i < 3; i++) {
            JButton btn = new JButton(labels[i]);
            btn.setBounds(140*i + 10, 360, 125, 25);
            btn.setPreferredSize(new Dimension(125, 25));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = e.getActionCommand().toLowerCase();

                    for(JTextField tf : textFields) {
                        if (tf.getText().equals("") && textFields.indexOf(tf) < 4) {
                            tf.setText("Needed");
                        }
                    }

                    if(text.equals("neu")) {
                        for(JTextField tf : textFields) {
                            tf.setText("");
                        }
                    } else if(text.equals("beenden")) {
                        System.exit(1);
                    } else if(text.equals("berechnen")) {
                        String Vorname = textFields.get(0).getText();
                        String Nachname = textFields.get(1).getText();
                        String Gewicht = textFields.get(2).getText();
                        String Große = textFields.get(3).getText();

                        try {
                            Person p = new Person(Vorname, Nachname, Double.parseDouble(Gewicht), Double.parseDouble(Große));

                            textFields.get(4).setText(Vorname + " " + Nachname);
                            textFields.get(5).setText(""+p.getBMI());
                            textFields.get(6).setText(p.getOutEvaluation());

                            p.save();
                        } catch (NumberFormatException | IOException eee ) {
                            textFields.get(5).setText("Unknown Number");
                            textFields.get(6).setText("Unknown Number");
                        }
                    } else {
                        System.exit(255);
                    }
                }
            });
            panel.add(btn);
        }

        panel.setBounds(0,0, this.getWidth(), this.getHeight());

        this.add(panel);

        this.dispose();

        this.setVisible(true);



    }
}
