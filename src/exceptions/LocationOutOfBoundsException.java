package exceptions;

public class LocationOutOfBoundsException
        extends Exception {

    public LocationOutOfBoundsException(int x, int y, int max_x, int max_y) {
        super("The grid location (" + x + ", " + y + ") is out of bounds. Max grid size = (" + max_x + ", " + max_y +
                ")");
    }

}
