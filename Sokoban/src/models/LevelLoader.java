package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LevelLoader {
    private List<String> levels;
    private String path;
    private Level currentLevel;
    private int index;

    /**
     * Constructs the LevelLoader and will load
     * the First Level
     */
    public LevelLoader() {
        this.levels = new ArrayList<String>();
        this.path = "src/levels/";
        File folder = new File(path);
        File[] files = folder.listFiles();
        for(File file : files)
            addLevel(file.getName());
        this.index = 0;
        this.currentLevel = new Level(levels.get(index), readFile(levels.get(index)));
    }

    /*
     * Method to add the Level to the LevelLoader.
     */
    private void addLevel(String name){
        name = name.replace(".txt", "");
        levels.add(name);
    }

    /**
     * Re reads current Level
     */
    private void reReadLevel(){
        currentLevel = new Level(currentLevel.name(), readFile(currentLevel.name()));
    }

    /**
     * Will initilize 
     * @param name Of the level to be initialized
     * @return 2D charr array
     */
    private char[][] readFile(String name){
        name += ".txt";
        try (Scanner scan = new Scanner(new File(path + name))) {
            ArrayList<char[]> listOfArrays = new ArrayList<char[]>();
            String s;
            int rows = 0;
            int cols = 0;
            while(scan.hasNext()){
                s = scan.nextLine();
                rows++;
                if(cols > s.toCharArray().length)
                    cols = s.toCharArray().length;
                listOfArrays.add(s.toCharArray());
            }
            scan.close();
            char[][] map = new char[rows][cols]; 
            for(int i = 0; i < rows; i++){
                map[i] = listOfArrays.get(i);
            }
            return transponente(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Will convert the matrix to the corresponding transponete
     * @param given matrix to convert
     * @return transponeted matrix.
     */
    private char[][] transponente(char[][] map){
        char[][] transponente = new char[map[0].length][map.length];
        for(int i = 0; i < transponente.length; i++){
            for(int j = 0; j < transponente[0].length; j++){
                transponente[i][j] = map[j][i];
            }
        }
        return transponente;
    }

    /**
     * Checks if there is a next Level
     * @return
     */
    public boolean hasNext(){
        if(index < levels.size() - 1 && index >= 0)
            return true;
        return false;
    }

    /**
     * Will try to get the next Level of Loader
     * @return
     */
    public Level nextLevel(){
        if(hasNext()){
            index++;
            currentLevel = new Level(levels.get(index), readFile(levels.get(index)));
            return currentLevel;
        }
        return null;
    }

    /**
     * 
     * @return current Level
     */
    public Level getLevel(){
        return currentLevel;
    }

    /**
     * 
     * @return will Call for the Current Level to be initilized again
     * and return it.
     */
    public Level reset(){
        reReadLevel();
        return currentLevel;
    }

    /**
     * 
     * @return String Array of the Names of the Levels.
     */
    public String[] getLevelNames(){
        return (String[]) levels.toArray(new String[levels.size()]);
    }
}
