package Listeners;

import Models.GraphComponent;
import View.Graph;

import java.awt.*;
import java.awt.event.*;

public class MouseMoveListener implements MouseMotionListener {

    protected int x = 0, y = 0, movementX = 0, movementY = 0;
    private Graph parent;

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getModifiersEx());
        if(e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
            this.movementX = this.x - e.getX();
            this.movementY = this.y - e.getY();
            this.x = e.getX();
            this.y = e.getY();
            this.parent.setXOff(this.movementX);
            this.parent.setYOff(this.movementY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(GraphComponent g : this.parent.getComponent()) {
            Dimension dim = g.getDimension();
            int x = g.getX(),
                    y = g.getY();
            g.setHover((e.getX() > x && x+dim.width > e.getX() && e.getY() > y && y+dim.height > e.getY()));
        }
    }

    /**
     * Set the parent Graph, to set apply the new Offsets
     * Also add a new MouseListener, to track when the DragEvent starts
     * @param graph - The Callable Object for the Update of the Offsets
     */
    public void setParent(Graph graph) {
        this.parent = graph;
        MouseClickListener mcl = new MouseClickListener();

        mcl.setMouseMoveListener(this);
        this.parent.addMouseListener(mcl);
    }

    public Graph getParent() {
        return parent;
    }
}
