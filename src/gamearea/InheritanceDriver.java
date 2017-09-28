package gamearea;

import action.KeyboardInteraction;
import animation.AnimateCharacter;
import exceptions.LocationOutOfBoundsException;
import window.Window;
import window.WindowProperties;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class InheritanceDriver {

    private static JLayeredPane lpane = new JLayeredPane();

    // The main driver method.
    public static void main(String args[])
            throws IOException, LocationOutOfBoundsException {
        // Use our area, and show the window.
        Window frame = new Window();

        GameArea area = new GameArea();

        BufferedReader mapReader = new BufferedReader(new FileReader("map.txt"));
        area.setTerrainTiles(area.loadMap(mapReader));

        AnimateCharacter character = new AnimateCharacter(3, 3, new URL("file:CharacterSpriteSheet.png"), new String[]{"DOWN", "LEFT", "UP", "RIGHT"});
        frame.addKeyListener(new KeyboardInteraction(character));

        frame.setPreferredSize(new Dimension(WindowProperties.WIDTH, WindowProperties.HEIGHT));
        frame.setResizable(false);
        frame.add(lpane);
        lpane.setBounds(0, 0, 600, 400);
        lpane.add(area, new Integer(0));
        lpane.add(character.getSpriteAnimator(), new Integer(1000));
        //frame.add(character.getSpriteAnimator());
        frame.pack();
        frame.setVisible(true);
        area.paint(frame.getGraphics());
        //character.getSpriteAnimator().paint(frame.getGraphics());

        //character.animate(AnimateCharacter.Movements.DOWN);
    }
}
