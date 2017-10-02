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

//TODO: Have program exit on window close

public class InheritanceDriver {

    private static JLayeredPane lpane = new JLayeredPane();

    // The main driver method.
    public static void main(String args[])
            throws IOException, LocationOutOfBoundsException {
        //Create window, top-level JPanel, and GameArea
        Window frame  = new Window();
        JPanel jPanel = new JPanel();
        GameArea area = new GameArea();

        //Initialize area and character
        BufferedReader mapReader = new BufferedReader(new FileReader("map.txt"));
        area.setTerrainTiles(area.loadMap(mapReader));

        AnimateCharacter character = new AnimateCharacter(3, 3, new URL("file:CharacterSpriteSheet.png"),
                new String[]{"DOWN", "LEFT", "UP", "RIGHT"});

        //Initialize frame, top-level JPanel, and LayeredPane
        jPanel.setLayout(null);

        lpane.setBounds(0, 0, WindowProperties.WIDTH, WindowProperties.HEIGHT);

        frame.addKeyListener(new KeyboardInteraction(character));
        frame.setPreferredSize(new Dimension(WindowProperties.WIDTH, WindowProperties.HEIGHT));
        frame.setResizable(false);
        frame.add(jPanel);

        jPanel.add(lpane);

        lpane.add(character.getSpriteAnimator(), new Integer(1000));
        lpane.add(area, new Integer(0));

        //Open window
        frame.pack();
        frame.setVisible(true);

        //Paint terrain
        area.addAllToQueue();
    }
}
