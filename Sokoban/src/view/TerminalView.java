package view;

public class TerminalView implements Observer{

    private char[][] map;

    /**
     * Constructor
     */
    public TerminalView(){}

    /**
     * Method prints the map to the Terminal.
     */
    public void printMap() {
        String s = "";
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                s += map[j][i] + " ";

            }
            s += "\n";
        }
        System.out.println(s);
    }

    /**
     * Updates the TerminalViews Map and will call for it to be printed.
     */
    @Override
    public void update(char[][] mapState) {
        this.map = mapState;
        printMap();
    }   
}