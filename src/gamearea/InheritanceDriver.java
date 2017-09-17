package gamearea;

import exceptions.LocationOutOfBoundsException;
import window.Window;

import java.io.IOException;

public class InheritanceDriver {

    // The main driver method.
    public static void main(String args[]) throws IOException, LocationOutOfBoundsException {
        // Use our area, and show the window.
        Window window = new Window("Inheritance Lecture");
        GameArea area = new GameArea(window.getGraphics());
        area.paint();
        window.add(area);
        window.setVisible(true);
    }
}
