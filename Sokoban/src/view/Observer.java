package view;

/**
 * Inteface for the Observer
 */
public interface Observer {

    /**
     * Updates the Observer
     * @param mapState is the 2D char array to update the Observer
     */
    void update(char[][] mapState);
}
