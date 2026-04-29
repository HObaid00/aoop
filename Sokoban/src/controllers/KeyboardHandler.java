package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GUIView;

public class KeyboardHandler extends ControllerTemplate implements KeyListener {

    private Direction direction;
    private GUIView view;
   
    /**
     * Constructor to initialize the KeyboardHandler
     * @param view the GUIView to be attached.
     */
    public KeyboardHandler(GUIView view) {
        super(view);
        this.view = view;
        this.view.addKeyListener(this);
        this.direction = Direction.REST;
        
    }

    /**
     * @return the direction registered from the keyboard
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Listens to the keyboard and registers specific keys to their
     * corresponding direction. Will also call it to be used in the
     * Models and update the Views.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_UP), (KeyEvent.VK_W) -> {
                direction = Direction.UP;
            }
            case (KeyEvent.VK_DOWN), (KeyEvent.VK_S)-> {
                direction = Direction.DOWN;
            }
            case (KeyEvent.VK_RIGHT), (KeyEvent.VK_D) -> {
                direction = Direction.RIGHT;
            }
            case (KeyEvent.VK_LEFT), (KeyEvent.VK_A) -> {
                direction = Direction.LEFT;
            }
            case KeyEvent.VK_SPACE-> {
               direction = Direction.RESET;
            }
            
        }
        view.keyboardRegistration();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }
}
