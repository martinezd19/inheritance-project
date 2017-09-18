package gamearea;

import exceptions.LocationOutOfBoundsException;
import window.Window;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InheritanceDriver {

    // The main driver method.
    public static void main(String args[]) throws IOException, LocationOutOfBoundsException {
        // Use our area, and show the window.
        Window window = new Window("Inheritance Lecture");

        GameArea area = new GameArea();

        BufferedReader mapReader = new BufferedReader(new FileReader("map.txt"));
        area.setTerrainTiles(area.loadMap(mapReader));

        window.add(area);

        window.setVisible(true);
    }
}
