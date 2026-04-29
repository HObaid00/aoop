package models;

import javax.swing.*;

import controllers.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import view.GUIView;
import view.Observer;
import view.TerminalView;

public class Game extends JPanel implements ActionListener {

    private List<Observer> observers;
    private LevelLoader levelLoader;
    private MapDesign map;
    
    private JDialog dialog;
    private JButton nextButton;
    private JButton restartButton;
    private JLabel label;
    private JFrame window;

    /**
     * Constructor for the Game
     */
    public Game() {
        this.levelLoader = new LevelLoader();
        this.map = new MapDesign(levelLoader.getLevel());
        this.observers = new ArrayList<Observer>();
        initilizeObservers();
    }

    /**
     * Initiliziation of the Observer-pattern, you can add how many
     * Observers you want here.
     */
    private void initilizeObservers(){
        this.observers.add(new GUIView(this));
        this.observers.add(new TerminalView());
    }

    /**
     * Method to update all of the Observers.
     */
    private void notifyObservers(){
        for(Observer observer : observers){
            observer.update(map.getMap());
        }
    }

    /**
     * Method to get the Next Level if it exists.
     */
    private void nextLevel() {
        if (levelLoader.hasNext())
            levelLoader.nextLevel();
        this.map = new MapDesign(levelLoader.getLevel());
        System.out.println(levelLoader.getLevel().name());
        updateData(Direction.REST);
    }

    /**
     * Method used to get the Models to update, and calling for observers to update after
     * and possibly print the stats to 
     * @param direction
     */
    public void updateData(Direction direction) {
        setDirection(direction);
        notifyObservers();    
        map.printStats();
        if (map.winCondition()){
            levelCompleted();
        }
    }

    /**
     * Method called to reset the current Level to its initial state
     */
    private void reset() {
        this.levelLoader.reset();
        this.map = new MapDesign(levelLoader.getLevel());
        updateData(Direction.REST);
    }

    /**
     * Calling the model to move the player.
     * @param direction is the direction to move the player
     */
    private void setDirection(Direction direction) {
        switch (direction) {
            case UP -> {
                map.moveUp();
            }
            case DOWN -> {
                map.moveDown();
            }
            case RIGHT -> {
                map.moveRight();
            }
            case LEFT -> {
                map.moveLeft();
            }
            case RESET -> {
                reset();
            }
            case REST -> {
            }
        }
    }

    /**
     * Returns the model that updates the Player.
     * @return 
     */
    public MapDesign getMap() {
        return map;
    }


    /**
     * Method to be called when the Level is Completed to call for
     * the post game window to either get the Next Level or Restart
     * or Close the Program.
     */
    public void levelCompleted(){
        dialog = new JDialog(window, "level finished", true);
        if (levelLoader.hasNext()) {
            label = new JLabel("congrats, you've done it!");

            nextButton = new JButton("press me to continue");
            restartButton = new JButton("press me to restart");

            label.setHorizontalAlignment(SwingConstants.CENTER);
            dialog.setLayout(new FlowLayout());
            nextButton.addActionListener(this);
            restartButton.addActionListener(this);

            nextButton.setBounds(0, 0, 200, 30);
            restartButton.setBounds(0, 30, 200, 30);

            dialog.setSize(300, 150);

            dialog.getContentPane().add(label);
            dialog.add(nextButton);
            dialog.add(restartButton);

        } else {

            label = new JLabel("Game cleared, nice jobb!");
            nextButton = new JButton("Close Window");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            dialog.setLayout(new FlowLayout());
            nextButton.addActionListener(this);

            nextButton.setBounds(0, 0, 200, 30);

            dialog.setSize(200, 100);

            dialog.getContentPane().add(label);
            dialog.add(nextButton);

        }
        dialog.setLocationRelativeTo(window);
        dialog.setVisible(true);

    }

    /**
     * Actions to be executed in the Post Level Completed Window.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton && levelLoader.hasNext()) {
            nextLevel();
            dialog.dispose();

        } else if (e.getSource() == nextButton && !levelLoader.hasNext()) {
            System.exit(1);

        } else if (e.getSource() == restartButton) {
            reset();
            dialog.dispose();

        }
    }
}
