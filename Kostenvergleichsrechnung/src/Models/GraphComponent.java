package Models;

import View.Graph;

import java.awt.*;

public abstract class GraphComponent {

    private final Graph owner;

    private int x, y, width, height;

    private String value = "", content = "";

    private boolean focus = false, hover = false;

    public GraphComponent (Graph owner, int x, int y, int width, int height, String str) {
        this.owner = owner;

        this.content = str;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.owner.addComponent(this);

    }

    public abstract void setHeight(int height);

    public abstract void setWidth(int width);

    public abstract void setX(int x);

    public abstract void setY(int y);

    public abstract Graph getOwner();

    public abstract void setDimension (Dimension d);

    public abstract Dimension getDimension ();

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract int getX();

    public abstract int getY();

    public abstract String getValue();

    public abstract Graphics show (Graphics g, Object caller) throws IllegalAccessException;

    public int hashCode () {
        return 0x500;
    }

    public abstract void setFocus(boolean b);

    public abstract boolean hasFocus();

    public abstract void setValue(char keyChar);

    public abstract void valueBack();

    public abstract void setHover(boolean b);

    protected void remove() {
        this.owner.removeComponent(this);
    }
}
