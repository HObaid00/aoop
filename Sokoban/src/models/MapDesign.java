package models;

public class MapDesign {
    private Level level;
    private char[][] map;
    private int playerRow;
    private int playerColumn;

    /***
     * Constructor for the MapDesign
     * @param level
     */
    public MapDesign(Level level) {
        this.level = level;
        initLevel(level);
    }

    /**
     * Intitlizes the Level.
     * @param level
     */
    private void initLevel(Level level) {
        this.map = level.map();
        this.playerRow = level.getPlayerRow();
        this.playerColumn = level.getPlayerColumn();

    }

    /**
     * 
     * @return the Row of the Player
     */
    public int getPlayerRow() {
        return this.playerRow;
    }

    /**
     * 
     * @return the Column of the Player
     */
    public int getPlayerColumn() {
        return this.playerColumn;
    }

    /**
     * 
     * @return Amount of Rows in the map
     */
    public int mapRows(){
        return map.length;
    }

    /**
     * 
     * @return Amount of Columns in the map
     */
    public int mapColumns(){
        return map[0].length;
    }

    /**
     * Inserts the coridnates to put the Player one position up.
     */
    public void moveUp() {
        movePlayer(getPlayerRow(), getPlayerColumn() - 1);

    }

    /**
     * Inserts the coridnates to put the Player one position left.
     */
    public void moveLeft() {
        movePlayer(getPlayerRow() - 1, getPlayerColumn());

    }

    /*
     * Inserts the coridnates to put the Player one position right.
     */
    public void moveRight() {
        movePlayer(getPlayerRow() + 1, getPlayerColumn());

    }

    /**
     * Inserts the coridnates to put the Player one position down.
     */
    public void moveDown() {
        movePlayer(getPlayerRow(), getPlayerColumn() + 1);

    }

    /**
     * Checks if the position is empty
     * @param char to be checkout out
     * @return bolean based on if it is empty
     */
    private boolean isEmpty(char ch) {
        switch (ch) {
            case 'o' -> {
                return true;
            }
            case '_' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Check first if position is empty
     * Function looks if the given type is movable
     * @param char to be checked
     * @return boolean if it is movable
     */
    private boolean movable(char ch) {
        switch (ch) {
            case 'M' -> {
                return true;
            }
            case 'C' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Substitutes position that was used in action
     * @param row to be replaced
     * @param col to be replaced
     */
    private void replace(int row, int col) {
        switch (map[row][col]) {
            case '@' -> {
                map[row][col] = '_';
            }
            case 'A' -> {
                map[row][col] = 'o';
            }
            case 'C' -> {
                map[row][col] = '_';
            }
            case 'M' -> {
                map[row][col] = 'o';
            }
            case '_' -> {
                map[row][col] = '_';
            }
            case 'o' -> {
                map[row][col] = 'o';
            }
            default -> {}
        }
    }

    /**
     * Adds A crate to the given position in the map
     * @param row to be pushed to
     * @param column to be pushed to
     */
    private void pushCrate(int row, int column) {
        if (map[row][column] == 'o') {
            map[row][column] = 'M';
        } else if (map[row][column] == '_') {
            map[row][column] = 'C';
        }
    }

    /**
     * Checks the relation between given row and playerRow
     * @param row to check against
     * @return relation between given row and playerRow
     */
    private int rowRelation(int row) {
        return playerRow - row;
    }

    /**
     * Checks the relation between given column and playerColomn
     * @param column to check against
     * @return relation between given column and playerColumn
     */
    private int columnRelation(int column) {
        return playerColumn - column;
    }

    /**
     * Will correctly place the Player in the given row and column if its possible.
     * @param row to be placed in
     * @param column to be placed in.
     */
    private void setPlayerPosition(int row, int column) {
            replace(playerRow, playerColumn);
            if (map[row][column] == 'o')
            map[row][column] = 'A';
            else
            map[row][column] = '@';
            playerRow = row;
            playerColumn = column;
    }

    private void movePlayer(int row, int column) {
        if(row >= 0 && row < level.getRows() && column >= 0 && column < level.getColumns()){
            if (!winCondition()) {
                if (isEmpty(map[row][column])) {
                    setPlayerPosition(row, column);
                    
                } else if (movable(map[row][column])) {
                    int nextRow = row - rowRelation(row);
                    int nextColumn = column - columnRelation(column);
                    if (isEmpty(map[nextRow][nextColumn])) {
                        pushCrate(nextRow, nextColumn);
                        replace(row, column);
                        setPlayerPosition(row, column);
                    }
                }
            }   
        }
    }

    /**
     * The Wincondition to win the mao. I.E. if all of the normal Crates has
     * become Marked Crates.
     * @return boolean based on if the map is won.
     */
    public boolean winCondition() {
        return level.getAmountofCrates() == 0;
    }

    /**
     * Method to print the stats of the Crates & Targets
     */
    public void printStats() {
        System.out.println("The current amount of crate Marked: " + level.getAmountofMarkedCrates());
        System.out.println("The current amount of targets: " + level.getAmountOfTargets());
        System.out.println("The current amount of crates: " + level.getAmountofCrates() + "\n");
    }

    /**
     * 
     * @return the 2D char map representation
     */
    public char[][] getMap(){
        return this.map;
    }
}



