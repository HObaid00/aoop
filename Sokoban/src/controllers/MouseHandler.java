package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.GUIView;
public class MouseHandler extends ControllerTemplate implements MouseListener {
    private GUIView view;
    private Direction direction;
    

    /**
     * Constructor for the MouseHandler
     * @param view to be attached
     */
    public MouseHandler(GUIView view) {
        super(view);
        this.view = view;
        this.direction = Direction.REST;
        this.view.addMouseListener(this);
    }

    /**
     * @return the direction that has been registered
     */
    public Direction getDirection(){
        return this.direction;
    }

    /**
     * By checking the relation between the Mouse and the Player position
     * it will determine a certain direction to be used and calls for the 
     * View and Model to be updated accordingly.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseRow = e.getX() / 32;
        int mouseColumn = e.getY() / 32;
        int playerRow = view.getPlayerRow();
        int playerColumn = view.getPlayerColumn();

        double distanceToMouse = Math.sqrt(Math.pow(mouseRow - playerRow, 2) + Math.pow(mouseColumn - playerColumn, 2));

        if (distanceToMouse >= 1.5) {
            if (mouseRow > playerRow) {
                direction = Direction.RIGHT;
            } else if (mouseRow < playerRow) {
                direction = Direction.LEFT;
            } else if (mouseColumn > playerColumn) {
                direction = Direction.DOWN;
            } else if (mouseColumn < playerColumn) {
                direction = Direction.UP;
            }
        } else {
            if (Math.abs(mouseRow - playerRow) > Math.abs(mouseColumn - playerColumn)) {
                if (mouseRow > playerRow) {
                    direction = Direction.RIGHT;
                } else if (mouseRow < playerRow) {
                    direction = Direction.LEFT;
                }
            } else {
                if (mouseColumn > playerColumn) {
                    direction = Direction.DOWN;
                } else if (mouseColumn < playerColumn) {
                    direction = Direction.UP;
                }
            }
        }
        view.mouseRegistration();
        
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}