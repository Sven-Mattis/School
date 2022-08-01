package Listeners;

import Models.GraphDropdownMenu;
import Models.GraphButton;
import Models.GraphComponent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseClickListener implements MouseListener {

    private MouseMoveListener mml;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
            for(GraphComponent c : mml.getParent().getComponent()) {
                if(e.getY() > c.getY() && e.getY() < c.getY() + c.getHeight() &&
                e.getX() > c.getX() && e.getX() < c.getX() + c.getWidth() && c.getClass().getCanonicalName().contains("Button")) {
                    ((GraphButton) c).clicked();
                }
            }
            mml.x = e.getX();
            mml.y = e.getY();
        } else if(e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
            GraphDropdownMenu menu = new GraphDropdownMenu(mml.getParent(), e.getX(), e.getY(), 100, 200);
            menu.add(new GraphButton(mml.getParent(), e.getX(), e.getY(), 100, 20, "Test", menu));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(GraphComponent g : mml.getParent().getComponent()) {
            Dimension dim = g.getDimension();
            int x = g.getX(),
            y = g.getY();
            g.setFocus((e.getX() > x && x+dim.width > e.getX() && e.getY() > y && y+dim.height > e.getY()));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setMouseMoveListener( MouseMoveListener mml ) {
        this.mml = mml;
    }
}
