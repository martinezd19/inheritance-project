package gamearea;

import exceptions.LocationOutOfBoundsException;

import java.awt.*;
import java.io.IOException;

public class GameArea
        extends Area {

    private Graphics g;

    // The constructor for gamearea.GameArea. It takes in a parameter for the
    // number of trees to place in the area.
    public GameArea(Graphics g) throws IOException, LocationOutOfBoundsException {
        super();
        this.g = g;
    }

    public void paint() {
        super.paint(g);
    }
}
