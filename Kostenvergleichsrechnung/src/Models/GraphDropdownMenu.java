package Models;

import View.Graph;

import java.awt.*;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Objects;

public class GraphDropdownMenu extends GraphComponent {

    private final Graph owner;

    private int x, y, width, height;

    private String value = "", content = "";

    private boolean focus = false, hover = true;

    private ArrayList<GraphComponent> components = new ArrayList<>();

    public GraphDropdownMenu(Graph parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height, "");

        this.owner = parent;

        this.content = null;

        this.x = x-10;
        this.y = y-10;

        this.width = width;
        this.height = height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Graph getOwner() {
        return this.owner;
    }

    @Override
    public void setDimension(Dimension d) {
        this.width = d.width;
        this.height = d.height;
    }

    @Override
    public Dimension getDimension() {
        return new Dimension(this.width, this.height);
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public Graphics show(Graphics g, Object caller) throws IllegalAccessException {
        if(!this.hover) {
            for(GraphComponent c : this.components) {
                c.remove();
            }
            this.remove();
        }

        Color oldColor = g.getColor();

        g.setColor(new Color(0,0,0,200));

        g.fillRect(this.x, this.y, this.width, this.height);

        for(GraphComponent c : this.components) {
            c.show(g, caller);
        }

        g.setColor(oldColor);

        return g;
    }

    @Override
    public void setFocus(boolean b) {
        this.focus = b;
    }

    @Override
    public boolean hasFocus() {
        return this.focus;
    }

    @Override
    public void setValue(char keyChar) {
        throw new BufferOverflowException();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOwner(), getX(), getY(), getWidth(), getHeight(), getValue(), content, focus, hover);
    }

    @Override
    public void valueBack() {
        throw new BufferUnderflowException();
    }

    @Override
    public void setHover(boolean b) {
        this.hover = b;
    }

    public void add(GraphButton graphButton) {
        this.components.add(graphButton);
    }
}
