package window;

public interface WindowProperties {

    // The width and height of the window (in pixels).
    int WIDTH  = 1280;
    int HEIGHT = 1280;

    // The size of each square on the game grid
    int GRID_SIZE = 64;

    //The max x-y grid locations
    int MAX_X = (WIDTH / GRID_SIZE) - 1;
    int MAX_Y = (HEIGHT / GRID_SIZE) - 1;

    static boolean outOfGrid(int x, int y) {
        return (x < 0 || y < 0 || x > MAX_X || y > MAX_Y);
    }
}
