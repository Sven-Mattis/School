package Border;

import javax.swing.*;
import java.awt.*;

public class Border extends JFrame {

    public static void main(String[] args) {
        new Border();
    }

    public Border () {
        setSize(500, 500);
        setTitle("asuzdgzasdjghasdhasdjhaskjdgaskjdhaksjdh");

        setLayout(new BorderLayout(0, 0));

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.black);
        add(p , BorderLayout.NORTH);

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.BLUE);
        add(p , BorderLayout.EAST);

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.green);
        add(p , BorderLayout.SOUTH);

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.red);
        add(p , BorderLayout.WEST);


        JPanel pp = new JPanel();
        pp.setLayout(new BorderLayout(0, 0));

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.green);
        pp.add(p , BorderLayout.NORTH);

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.BLUE);
        pp.add(p , BorderLayout.EAST);

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.red);
        pp.add(p , BorderLayout.SOUTH);

        p = new JPanel();
        p.setPreferredSize(new Dimension(100, 100));
        p.setBackground(Color.black);
        pp.add(p , BorderLayout.WEST);

        add(pp, BorderLayout.CENTER);
        setVisible(true);
    }

}
