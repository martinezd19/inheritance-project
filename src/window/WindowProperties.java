package window;

public interface WindowProperties {

    // The width and height of the window (in pixels).
    int WIDTH  = 640;
    int HEIGHT = 640;

    // The size of each square on the game grid
    int GRID_SIZE = 40;

    //The max x-y grid locations
    int MAX_X = (WIDTH / GRID_SIZE) - 1;
    int MAX_Y = (HEIGHT / GRID_SIZE) - 1;
}
