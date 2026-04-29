package models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlockManager {
    private BufferedImage blank; // _ 
    private BufferedImage blankMarked; // o, A = player + blankMarked
    private BufferedImage crate; // 'C'
    private BufferedImage crateMarked; // 'M'
    private BufferedImage player; // @, A = player + target
    private BufferedImage wall; // 'X'

    private BufferedImage[][] Blocks;

    /**
     * Constructor for the BlockManager creates a 2D 
     * BufferedImage array to hold the Images of the blocks.
     * @param setRows will be the amount of row positions that the array will contain.
     * @param setColumns will be the amount of column positions that the array will contain.
     */
    public BlockManager(int setRows, int setColumns) {
        File path = new File("lib/sokoban_icons");
        File[] allPics = path.listFiles();
        assert allPics != null;
        try {
            this.blank = ImageIO.read(allPics[0]);
            this.blankMarked = ImageIO.read(allPics[1]);
            this.crate = ImageIO.read(allPics[2]);
            this.crateMarked = ImageIO.read(allPics[3]);
            this.player = ImageIO.read(allPics[4]);
            this.wall = ImageIO.read(allPics[5]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.Blocks = new BufferedImage[setRows][setColumns];
    }

    /**
     * Function to add specific Image to the 2D array at the specified position.
     * @param img is the BufferedImage to be placed.
     * @param Specified Row Index
     * @param Specified Colummn Index
     */
    private void addBlock(BufferedImage img, int row, int column) {
        Blocks[row][column] = img;
    }

    /**
     * Will specifically call to add wall at specified Position
     * @param row postion
     * @param column position
     */
    private void addWall(int row, int column) {
        addBlock(wall, row, column);
    }

    /**
     * Will specifically call to add Blank Space at specified Position
     * @param row postion
     * @param column position
     */
    private void addBlank(int row, int column) {
        addBlock(blank, row, column);
    }

    /**
     * Will specifically call to add Player at specified Position
     * @param row postion
     * @param column position
     */
    private void addPlayer(int row, int column) {
        addBlock(player, row, column);
    }

    /**
     * Will specifically call to add Crate at specified Position
     * @param row postion
     * @param column position
     */
    private void addCrate(int row, int column) {
        addBlock(crate, row, column);
    }

    /**
     * Will specifically call to add Marked Crate at specified Position
     * @param row postion
     * @param column position
     */
    private void addMarkedCrate(int row, int column) {
        addBlock(crateMarked, row, column);
    }

    /**
     * Will specifically call to add Target at specified Position
     * @param row postion
     * @param column position
     */
    private void addMarkedBlank(int row, int column) {
        addBlock(blankMarked, row, column);
                
    }

    /**
     * Get how many rows there is in the Rows
     * of the BlockManager. 
     * @return Integer number of rows in the BlockManager.
     */
    public int getRows() {
        return Blocks.length;
    }

    /**
     * Get how many columns there are in the BlockManager.
     * @return Integer number of column in the BlockManager.
     */
    public int getColumns() {
        return Blocks[0].length;
    }

    /**
     * Get the specific BufferdImage in the specified position
     * @param row of the image
     * @param column of the image
     * @return BufferdImage in the specified position.
     */
    public BufferedImage getImage(int row, int col){
        return Blocks[row][col];
    }

    /**
     * Method to read a 2D char array to BufferedImage array.
     * @param textMap to be read.
     */
    public void readTextMap(char[][] textMap) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                switch (textMap[i][j]) {
                    case '_':
                        addBlank(i, j);
                        break;
                    case 'o':
                        addMarkedBlank(i, j);
                        break;
                    case 'C':
                        addCrate(i, j);
                        break;
                    case 'M':
                        addMarkedCrate(i, j);
                        break;
                    case '@':
                        addPlayer(i, j);
                        break;
                    case 'A':
                        addPlayer(i, j);
                        break;
                    case 'X':
                        addWall(i, j);
                        break;
                }
            }
        }
    }

    /**
     * Method to get BufferedImage array
     * @return 2D Image array
     */
    public BufferedImage[][] getBlockMap() {return Blocks; }
}
