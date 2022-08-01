package View;

import Listeners.MouseMoveListener;
import Listeners.MouseScrollListener;
import Models.Function;
import Models.GraphComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Graph extends JPanel {

    private final View Owner;
    private int xOff = 1;
    private int yOff = 1;
    private int middleX;
    private int middleY;
    private int scale = 1000;
    private final int scaleInit = 1000 * (1000 / 2) / 10;
    private double step = .1;

    private final boolean fps = true;
    private long start = System.currentTimeMillis();
    private int frames = 0;
    private String lastFPS;
    private final int baseMargin = 50;
    private ArrayList<Integer> yList = new ArrayList<>();
    private final ArrayList<Function> functions = new ArrayList<>();
    private final ArrayList<GraphComponent> component = new ArrayList<>();


    /**
     * Constructor
     *
     * @param Owner - the owner view
     */
    public Graph(View Owner) {
        this.Owner = Owner;
        this.Owner.add(this);

        MouseMoveListener mouse = new MouseMoveListener();
        mouse.setParent(this);

        this.addMouseMotionListener(mouse);

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                for(GraphComponent g : component) {
                    if(!g.hasFocus())
                        continue;
                    System.out.println(e.getKeyCode());
                    if(e.getKeyCode() == 8)
                        g.valueBack();
                    else if((e.getKeyCode() > 31 && 127 > e.getKeyCode()) || e.getKeyCode() == 130)
                        g.setValue(e.getKeyChar());
                    return;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'ü') {
                    xOff = 0;
                    yOff = 0;
                }
            }
        });

        MouseScrollListener msl = new MouseScrollListener();
        msl.setParent(this);

        this.addMouseWheelListener(msl);

        this.drawing();
        this.requestFocus();
        functions.add(new Function("1x^2"));
        functions.add(new Function("-1x^2"));
        System.out.println(functions.get(0).f(-.75));
    }

    /**
     * Start the Drawing
     */
    public void drawing() {
        this.repaint();
        this.Owner.setVisible(true);
    }

    /**
     * Set the xOffset for the objects, to get Drawn correctly
     *
     * @param toAdd - the amount of Offset to add to it
     */
    public void setXOff(int toAdd) {
        this.xOff -= toAdd;
    }

    /**
     * Set the xOffset for the objects, to get Drawn correctly
     *
     * @param toAdd - the amount of Offset to add to it
     */
    public void setYOff(int toAdd) {
        this.yOff -= toAdd;
    }

    /**
     * Set the Scale
     *
     * @param scale - amount to add to the Scaling higher value means that objects scale down
     */
    public void setScale(int scale) {
        if (this.scale + scale < 0)
            return;

        this.step /= this.scale;
        this.scale += scale;
        this.step *= this.scale;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g = this.getBase(g);

        int start = yList.get(0), end = yList.get(yList.size() - 1),
                base = Math.abs(Math.abs(yList.get(0)) - Math.abs(yList.get(1)));

        for (Function f : functions) {
            for (double i = start - base; i < end + base; i += step) {
                int x1 = convertX(i), y1 = convertY(f.f(i)), x2 = convertX((i + step)), y2 = convertY(f.f(i + step));
                g.drawLine(x1, y1, x2, y2);
            }
        }

        for (Object obj : this.component.toArray()) {
            if(obj.hashCode() == 0x500 || !obj.getClass().getCanonicalName().contains("Graph"))
                continue;

            try {
                ((GraphComponent) obj).show(g, this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        repaint();
    }

    /**
     * Creates a base Mesh with x and y axis
     * and the Values, based on zoom as Well
     * <p>
     * !IMPORTANT
     *
     * @param g the Graphic to draw on
     * @return the Drawn Graphic
     */
    private Graphics getBase(Graphics g) {

        yList = new ArrayList<>();

        g.setFont(new Font(Font.DIALOG, 1, 10));

        g.setColor(new Color(0, 0, 0, 100));

        // The Main axis
        g.drawLine((int) Math.floor(-Math.pow(this.getWidth(), 2) / this.baseMargin) * this.baseMargin, convertYUnscale((int) (Math.floor(this.getHeight() / this.baseMargin) * this.baseMargin) / 2), (int) Math.floor(Math.pow(this.getWidth(), 2) / this.baseMargin) * this.baseMargin, convertYUnscale((int) (Math.floor(this.getHeight() / this.baseMargin) * this.baseMargin) / 2));
        g.drawLine(convertXUnscale((int) (Math.floor(this.getWidth() / this.baseMargin) * this.baseMargin / 2)), (int) Math.floor(-Math.pow(this.getHeight(), 2) / this.baseMargin) * this.baseMargin, convertXUnscale((int) (Math.floor(this.getWidth() / this.baseMargin) * this.baseMargin / 2)), (int) Math.floor(Math.pow(this.getHeight(), 2) / this.baseMargin) * this.baseMargin);

        // create Vertical lines | x
        int hl = 1;
        boolean nullPoint = false;

        // Fill only the Screen with the correct lines
        while (hl < this.getWidth() / this.baseMargin + 4) {
            // Get the Position
            int pos = ((hl - 1) * this.baseMargin) - ((this.xOff + this.middleX) / this.baseMargin) * this.baseMargin;
            // Check if it is on screen
            if ((pos > (this.xOff + this.middleX) && pos < this.getWidth()) || !(pos > (this.xOff + this.middleX) && pos < this.getWidth())) {

                // Draw the line
                g.setColor(new Color(0, 0, 0, 25));
                g.drawLine(convertXUnscale(pos), convertYUnscale(-(this.yOff + this.middleY)), convertXUnscale(pos), convertYUnscale(this.getHeight() - (this.yOff + this.middleY)));

                // Correct the pos for the value opf this line
                pos = ((hl - 1) * this.baseMargin) - ((this.xOff + this.middleX) / this.baseMargin) * this.baseMargin;
                // Get the Value of this line
                float number = (float) (this.getNumber(hl, (this.xOff + this.middleX), this.getWidth()) * .001);
                // Fix shifting
                if ((this.xOff + this.middleX) < 0)
                    number = (float) (this.getNumber(hl - 1, (this.xOff + this.middleX), this.getWidth()) * .001);

                // Fix the double null
                if (nullPoint)
                    pos = ((hl - 2) * this.baseMargin) - ((this.xOff + this.middleX) / this.baseMargin) * this.baseMargin;

                if (number == 0)
                    nullPoint = true;


                // Get the correct placement
                int y = 0;

                // If it is either on top nor on bottom
                if ((this.yOff + this.middleY) < this.getHeight() / 2 && (this.yOff + this.middleY) > -this.getHeight() / 2) {
                    y = this.getHeight() / 2 + 10 + (this.yOff + this.middleY);
                } else
                    // If its on top
                    if ((this.yOff + this.middleY) < -this.getHeight() / 2) {
                        y = 10;
                    } else
                    // Otherwise it must be on the bottom
                    {
                        y = this.getHeight() - 10;
                    }

                yList.add((int) -number);

                // Draw the Value
                g.setColor(new Color(0, 0, 0, 100));
                g.drawChars(
                        (-number + "").toCharArray(),
                        0,
                        (-number + "").toCharArray().length,
                        convertXUnscale(pos),
                        y
                );

                // Count up for Lines
                hl++;
            }
        }

        // Reset
        nullPoint = false;
        hl = 1;

        // Create the Horizontal lines - y
        // Fill only the the Screen with lines
        while (hl < this.getHeight() / this.baseMargin + 4) {
            // Get the Position of the Line
            int pos = ((hl - 1) * this.baseMargin) - ((this.yOff + this.middleY) / this.baseMargin) * this.baseMargin;
            // Check if it is visible
            if ((pos > (this.yOff + this.middleY) && pos < this.getHeight()) || !(pos > (this.yOff + this.middleY) && pos < this.getHeight())) {

                g.setColor(new Color(0, 0, 0, 25));
                // Draw the line
                g.drawLine(convertXUnscale(-(this.xOff + this.middleX)), convertYUnscale(pos), convertXUnscale(this.getWidth() - (this.xOff + this.middleX)), convertYUnscale(pos));

                int x = 0;
                // get the correct position for the Value of this line
                if ((this.xOff + this.middleX) < this.getWidth() / 2 && (this.xOff + this.middleX) > -this.getWidth() / 2) {
                    x = this.getWidth() / 2 + 10 + (this.xOff + this.middleX);
                } else if ((this.xOff + this.middleX) < -this.getWidth() / 2) {
                    x = 10;
                } else {
                    x = this.getWidth() - 50;
                }

                // Get the Value of this line
                float number = (float) (this.getNumber(hl, (this.yOff + this.middleY), this.getHeight()) * .001);
                if ((this.yOff + this.middleY) < 0)
                    number = (float) (this.getNumber(hl - 1, (this.yOff + this.middleY), this.getHeight()) * .001);

                //  fix double null
                if (nullPoint)
                    pos = ((hl - 2) * this.baseMargin) - ((this.yOff + this.middleY) / this.baseMargin) * this.baseMargin;

                if (number == 0)
                    nullPoint = true;

                // Draw the Value
                g.setColor(new Color(0, 0, 0, 100));
                g.drawChars(
                        (number + "").toCharArray(),
                        0,
                        (number + "").toCharArray().length,
                        x,
                        convertYUnscale(pos)
                );

                // Count up for the lines
                hl++;
            }
        }

        // FPS Anzeige
        if (this.fps && System.currentTimeMillis() - this.start > 500) {
            this.lastFPS = "" + (this.frames * 2);
            this.frames = 0;
            this.start = System.currentTimeMillis();
        } else if (this.fps) {
            this.frames++;
            g.setColor(new Color(0, 0, 0, 150));
            String str = "" + this.lastFPS + " fps";
            g.drawChars(str.toCharArray(), 0, str.toCharArray().length, 0, 10);
        }

        return g;
    }

    /**
     * Get the Correct value for the lines
     *
     * @param hl  Line number
     * @param off the offset
     * @param wh  the Width / Height
     * @return an Integer representing the Value of the line
     */
    private int getNumber(int hl, int off, int wh) {
        return (((wh / this.baseMargin) * this.baseMargin / 2 + this.baseMargin + off - (hl * this.baseMargin)) / this.baseMargin) * scale;
    }

    /**
     * Convert the base to the movable base
     *
     * @param i base number
     * @return movable number
     */
    private int convertX(double i) {
        i *= scaleInit;

        try {
            if (i != -0)
                i /= ((scale));
        } catch (ArithmeticException e) {
            i = 0;
        }

        i = -i;

        i = ((this.getWidth() / this.baseMargin) * this.baseMargin / 2) - i;


        return (int) (i + (this.xOff) + this.middleX);
    }

    /**
     * Convert the base to the movable base
     *
     * @param i base number
     * @return movable number
     */
    private int convertY(double i) {
        i *= scaleInit;

        try {
            if (i != -0)
                i /= ((scale));
        } catch (ArithmeticException e) {
            i = 0;
        }

        i = ((this.getHeight() / this.baseMargin) * this.baseMargin / 2) - i;

        return (int) (i + (this.yOff) + this.middleY);
    }

    /**
     * Convert the base to the movable base but without Scaling
     *
     * @param i base number
     * @return movable number
     */
    private int convertXUnscale(int i) {
        return ((i + this.xOff + this.middleX));
    }

    /**
     * Convert the base to the movable base btu with out Scaling
     *
     * @param i base number
     * @return movable number
     */
    private int convertYUnscale(int i) {
        return ((i + this.yOff + this.middleY));
    }

    // TODO
    public void setMiddleX(int middle) {
        this.middleX = 0;
    }

    // TODO
    public void setMiddleY(int middle) {
        this.middleY = 0;
    }

    public void addComponent(GraphComponent obj) {
        this.component.add(obj);
    }

    public ArrayList<GraphComponent> getComponent() {
        return component;
    }

    public void addPoint(int x, int y) {

    }

    public void removeComponent(GraphComponent g) {
        this.component.remove(g);
    }

    public void addFunction (Function f) {
        this.functions.add(f);
    }
}