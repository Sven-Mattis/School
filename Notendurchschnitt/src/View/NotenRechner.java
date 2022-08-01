package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NotenRechner extends View {

    public NotenRechner(String title, int width, int height, Model data, int kas) {
        super(title, 0, 0, width, height, data);

        this.setResizable(false);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBounds(this.getBounds());

        panel.setPreferredSize(new Dimension(this.getBounds().width, this.getBounds().height));

        Model data1 = super.getData();


        // Variables
        int topHeight = 20*(kas+1)+20,
                topWidth = (150+40+20+100+20),
                bottomHeight = 20*(7),
                bottomWidth = topWidth;

        // Klassenarbeiten Textfelder
        ArrayList<JTextField> kasTF = new ArrayList<>();

        // Buttonnames
        String[] btnNames = new String[] {"Neu", "Berechnen", "Beenden"};

        // Outputs
        ArrayList<JTextField> outer = new ArrayList<>();
        String[] outs = new String[] {"Notendurchschnitt:", "Zeugnisnote:"};


        this.setSize(topWidth + 20, topHeight + bottomHeight + 100);



        // Klassenarbeiten
        for(int i = 0; i < kas; i++) {
            JLabel label = new JLabel("Klassenarbeit Nr. " + (i+1) + ":");
            label.setBounds(10,20*(i+1),150,20);
            panel.add(label);
            JTextField tf = new JTextField();
            kasTF.add(tf);
            tf.setHorizontalAlignment(SwingConstants.CENTER);
            tf.setBounds(170,20*(i+1),40,20);
            panel.add(tf);
        }

        // Mündliche note
        JLabel label = new JLabel("Mündliche Note:");
        label.setBounds(10,20*(kas+1)+2,150,20);
        panel.add(label);
        JTextField mnN = new JTextField();
        mnN.setHorizontalAlignment(SwingConstants.CENTER);
        mnN.setBounds(170,20*(kas+1),40,20);
        panel.add(mnN);


        // Die Knöpfe
        for(int i = 0; i < btnNames.length; i++) {
            JButton btn = new JButton(btnNames[i]);
            btn.setBounds(170+10+40, (topHeight/3*(i))+topHeight/6, 100,20);
            panel.add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String btnName = e.getActionCommand();

                    if( btnName.equals(btnNames[0]) ) {
                        for(JTextField tf : kasTF) {
                            tf.setText("");
                        }
                        mnN.setText("");

                        outer.get(0).setText("");
                        outer.get(1).setText("");
                    } else if ( btnName.equals(btnNames[1]) ) {

                        int[] notes = new int[kasTF.size()];
                        for(int i = 0; i < kasTF.size(); i++) {
                            try {
                                notes[i] = Integer.parseInt(kasTF.get(i).getText());
                            } catch ( NumberFormatException ignored) {
                                kasTF.get(i).setText("Keine Zahl!");
                                i = kasTF.size();
                                return;
                            }
                        }

                        Model m = new Model(notes, Integer.parseInt(mnN.getText()));

                        outer.get(0).setText(""+ ((float) (Math.round(m.getAverage()*100)) / 100));
                        outer.get(1).setText(m.toString());

                    } else if ( btnName.equals(btnNames[2]) ) {
                        dispose();
                        new MainMenu().showView();
                    }
                }
            });
        }

        JLabel img = new JLabel();
        img.setIcon(new ImageIcon(this.getClass().getResource("Grades.png")));
        img.setBounds(20, topHeight, 200, 200);
        img.setPreferredSize(new Dimension(img.getBounds().width, img.getBounds().height));
        panel.add(img);



        // Outputs
        for(int i = 0; i < outs.length; i++) {
            JLabel outLabel = new JLabel(outs[i]);
            outLabel.setBounds(150,topHeight+(50*(i+1)+2),120,20);
            panel.add(outLabel);
            JTextField outField = new JTextField();
            outField.setBounds(270,topHeight+(50*(i+1)+2),60,20);
            outField.setEditable(false);
            outField.setHorizontalAlignment(SwingConstants.CENTER);
            outer.add(outField);
            panel.add(outField);
        }

        this.add(panel);
        this.setVisible(true);
    }

}