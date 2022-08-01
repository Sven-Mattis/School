package Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JFrame {

    // Variables
    private int n1 = 0;
    private int n2 = 0;
    private String cn = "", operator = "";
    private final JTextField tf;

    /**
     * ActionListener for the buttons / the Numpad
     */
    private final ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // get the text of the Button
            String label = e.getActionCommand();

            setAll(label.toCharArray()[0]);
        }
    };

    /**
     * KeyListener for using the keyboard to enter values
     */
    private final KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            // get the char of the key
            if(e.isActionKey())
                System.out.println("Actionkey " + e.getKeyChar());
            char code = e.getKeyChar();

            setAll(code);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    /**
     * MouseListener for making onHover and onClick visible
     */
    private final MouseListener mouseListener = new MouseListener() {
        private Color start = Color.GRAY;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            ((JButton) e.getSource()).setBackground(new Color(100,100,100));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseEntered(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((JButton) e.getSource()).setBackground(new Color(150,150,150));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((JButton) e.getSource()).setBackground(start);
        }
    };

    public static void main(String[] args) {
        new Grid();
    }

    /**
     * Creates an Calculator GUI
     */
    public Grid() {
        setSize(500, 300);
        setTitle("Grid-Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // force the focus to have the KeyListener always active
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                requestFocus();
            }
        });

        // Set the outer layout
        setLayout(new BorderLayout());

        // Create the gridlayout panel
        JPanel grid = new JPanel();

        // create the TextField
        tf = new JTextField();
        tf.setBackground(new Color(175,175,175));
        tf.setEditable(false);
        tf.setPreferredSize(new Dimension(100, 100));
        tf.setFont(Font.getFont(Font.MONOSPACED));
        tf.setBorder(null);
        add(tf, BorderLayout.NORTH);

        // create the "=" button
        JButton eq = new JButton("=");
        eq.setBackground(Color.GRAY);
        eq.setBorder(null);
        eq.addMouseListener(mouseListener);
        eq.setPreferredSize(new Dimension(40, 40));
        add(eq, BorderLayout.EAST);
        eq.addActionListener(actionListener);

        // add the GridLayoutPanel to the main layout
        add(grid, BorderLayout.CENTER);

        grid.setLayout(new GridLayout(4, 3));

        // the labels for the Buttons
        String[] labels = {"7", "8", "9",
                "4", "5", "6",
                "1", "2", "3",
                "+", "0", "-"};

        // Create the Buttons
        for (int x = 0; x < labels.length; x++) {
            JButton p = new JButton(labels[x]);
            p.setBackground(Color.GRAY);
            p.setBorder(null);
            p.addMouseListener(mouseListener);
            grid.add(p);

            p.addActionListener(actionListener);
        }

        this.addKeyListener(keyListener);


        setVisible(true);
        setFocusable(true);

    }

    /**
     * Calculate the new Number based on the Operator
     */
    private void calc() {
        switch (operator) {
            case "+":
                n1 += n2;
                break;
            case "-":
                n1 -= n2;
                break;
            case "*":
                n1 *= n2 == 0 ? 1 : n2;
                break;
            case "/":
                n1 /= n2 == 0 ? 1 : n2;
                break;
        }
    }

    /**
     * Set the new number, from String to number
     */
    private void set() {
        if (n1 == 0 || !isOperator(operator)) {
            n1 = Integer.parseInt(cn);
        } else {
            if (!cn.equals(""))
                n2 = Integer.parseInt(cn);
        }
        cn = "";
    }

    /**
     * Checks if the given String is a valid operator
     *
     * @param oper The String that needs to be checked
     * @return boolean | true if it is a operator
     */
    private boolean isOperator(String oper) {
        if (oper == null)
            return false;
        return oper.matches("\\+|\\-|\\*|\\/");
    }

    /**
     * This Method is to set everything
     *
     * @param code the character that was entered vis key or button
     */
    private void setAll(char code) {

        // if it is a number
        if (("" + code).matches("\\d")) {
            // append it to the current number
            cn += "" + code;
            // if n1 is 0 and the character isnt a valid operator
            if (n1 == 0 && !isOperator(operator)) {
                tf.setText(cn);
            } else {
                // if the character isnt a valid operator make a cleanup, this is the case if the previous action is "="
                if (!isOperator(operator)) {
                    n1 = 0;
                    n2 = 0;
                    cn = "";
                    setAll(code);
                } else {
                    tf.setText(n1 + " " + operator + " " + cn);
                }
            }
        } else
            // if the character is a a operator
            if (isOperator("" + code)) {
                // save the operator
                operator = "" + code;

                try {
                    set();

                    calc();

                    tf.setText(n1 + " " + operator);

                } catch (NumberFormatException ignored) {
                    tf.setText("Fehler!");
                }
            } else
                //  if the character is "=" or enter
                if ((("" + code).equals("=") || code == 10) && !operator.equals("")) {
                    set();

                    // save the n1 before calculation
                    int prev = n1;

                    calc();

                    tf.setText(prev + " " + operator + " " + n2 + " = " + n1);

                    // cleanup
                    operator = "";
                    n2 = 0;
                    cn = "";
                } else if (code == 27) {
                    clear();
                }
    }

    private void clear() {
        n1 = 0;
        n2 = 0;
        operator = "";
        cn = "";
        tf.setText("");
    }

}
