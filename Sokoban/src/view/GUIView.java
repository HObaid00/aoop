package view;
import models.*;
import javax.swing.*;

import java.awt.*;

import controllers.*;


public class GUIView extends JPanel implements Observer{

    private Game gameWindow;
    private MapDesign map;
    private BlockManager Blocks;
    private JFrame frame;
    private MouseHandler mouseHandler;
    private KeyboardHandler keyboardHandler;


    /**
     * Contructor of the GuiView 
     */
    public GUIView(Game game){
        this.gameWindow = game;
        this.map = game.getMap();
        this.Blocks = new BlockManager(map.mapRows(), map.mapColumns());
        this.Blocks.readTextMap(map.getMap());
        initilizeFrame();
        this.mouseHandler = new MouseHandler(this);
        this.keyboardHandler = new KeyboardHandler(this);
    }

    /**
     * Intilize the Frame
     */
    public void initilizeFrame(){
        this.setPreferredSize(new Dimension(map.mapRows()*32, map.mapColumns()*32));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
        this.frame = new JFrame("Sokoban");

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(this);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        requestFocusInWindow();
    }

    /**
     * Function checks and returns the Row of the Player
     * @return Player Row Position 
     */
    public int getPlayerRow(){
        return map.getPlayerRow();
    }

    /**
     * Function checks and return the Column of the Player
     * @return Player Column Position
     */
    public int getPlayerColumn(){
        return map.getPlayerColumn();
    }

    /**
     * Method used to get the Direction that the keyboard inputs.
     */
    public void keyboardRegistration() {
        gameWindow.updateData(keyboardHandler.getDirection());
    }

    /**
     * Method used to get the direction that the mouse inputs.
     */
    public void mouseRegistration() {
        gameWindow.updateData(mouseHandler.getDirection());
    }

    /**
     * Method to update this Observer and repaint the Graphical Interface
     */
    @Override
    public void update(char[][] mapState) {
        this.Blocks.readTextMap(mapState);
        repaint();
    }

    /**
     * Method for getting the Pixel index from the used Matrix
     * @param index is the given position in the Matrix
     * @return Pixel Index based on the given Matrix.
     */
    private int getPixelIndex(int index) {
        return index*32;
    }

    /**
     * Using the JPanel and javax.swing we will get the model to graphical interface.
     * @param g2
     */
    public void drawMap(Graphics2D g2) {
        for (int i = 0; i < Blocks.getColumns(); i++) {
            for (int j = 0; j < Blocks.getRows(); j++) {
                g2.drawImage(Blocks.getBlockMap()[j][i], getPixelIndex(j), getPixelIndex(i), null);
            }
        }
    }

    /**
     * Paint component from the JPanel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawMap(g2);
        g2.dispose();
    }

}
