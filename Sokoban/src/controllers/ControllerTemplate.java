package controllers;

import view.GUIView;

public abstract class ControllerTemplate{
    
    @SuppressWarnings("unused")
    private final GUIView window;
    private Direction direction;

    /**
     * Constructor that has to use the same GUIView instance
     * @param view
     */
    public ControllerTemplate(GUIView view){
        this.window = view;
        this.direction = Direction.REST;
    }

    /**
     * Template method that the controllers should implement for usage.
     * @return
     */
    public Direction getDirection(){
        return direction;
    }

    
}
