package models;


public class Level {

    private final String name;
    private final int rows;
    private final int columns;
    private char[][] map;

    /**
     * Level constuctor reads in the Level.
     * @param name of the Level
     * @param map, 2D char array to be stored as the base of the Level.
     */
    public Level(String name, char[][] map){
        this.name = name;
        this.rows = map.length;
        this.columns = map[0].length;
        this.map = map;
    }

    /**
     * Get the Level name
     * @return the Level name
     */
    public String name(){
        return this.name;
    }

    /**
     * Getter mehtod for the 2D char array
     * @return the 2D char array
     */
    public char[][] map(){
        return map;
    }

    /**
     * 
     * @return Rows of the map
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * 
     * @return Columns of the map
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * 
     * @return The Row that the Player is in.
     */
    public int getPlayerRow(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                if(map[i][j] == '@') return i;
            }
        }
        return -1;
    }

    /**
     * 
     * @return The Column that the Player is in.
     */
    public int getPlayerColumn(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                if(map[i][j] == '@') return j;
            }
        }
        return -1;
    }

    /**
     * Get the amount of the targets currently visible on the mapp.
     * If there is a Marked Crate it will not be read as a target.
     * @return Amount of visible Targets
     */
    public int getAmountOfTargets(){
        int targets = 0;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++)
                if(map[i][j] == 'o') targets++;
        }
        return targets;
    }

    /**
     * @return Amount of Marked Crates on currently on the map.
     */
    public int getAmountofMarkedCrates(){
        int markedCrateCount = 0;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++)
                if(map[i][j] == 'M') markedCrateCount++;
        }
        return markedCrateCount;
    }

    /**
     * Get the amount of Crates on currently on the map,
     * Marked Crates will not be counted.
     * @return Amount of Crates on the map
     */
    public int getAmountofCrates(){
        int crates = 0;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++)
                if(map[i][j] == 'C') crates++;
        }
        return crates;
    }
}
