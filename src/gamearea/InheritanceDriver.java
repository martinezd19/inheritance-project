package gamearea;

import animation.AnimateCharacter;
import exceptions.LocationOutOfBoundsException;
import window.Window;
import window.WindowProperties;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class InheritanceDriver {

    // The main driver method.
    public static void main(String args[])
            throws IOException, LocationOutOfBoundsException {
        // Use our area, and show the window.
        Window window = new Window("Inheritance Lecture");

        GameArea area = new GameArea();

        BufferedReader mapReader = new BufferedReader(new FileReader("map.txt"));
        area.setTerrainTiles(area.loadMap(mapReader));

        AnimateCharacter character = new AnimateCharacter(3, 3, new URL("file:CharacterSpriteSheet.png"), new String[]{"DOWN", "LEFT", "UP", "RIGHT"});

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(area, new Integer(0));
        layeredPane.add(character.getSpriteAnimator(), new Integer(1));
        layeredPane.setVisible(true);

        window.add(layeredPane);

        window.setVisible(true);

        character.animate(AnimateCharacter.Movements.DOWN);
    }
}
