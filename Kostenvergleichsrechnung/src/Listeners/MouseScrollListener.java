package Listeners;

import View.Graph;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseScrollListener implements MouseWheelListener {

    private Graph parent;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        this.parent.setMiddleX(e.getX());
        this.parent.setMiddleY(e.getY());

        this.parent.setScale(e.getUnitsToScroll());
    }

    public void setParent (Graph g) {
        this.parent = g;
    }
}
