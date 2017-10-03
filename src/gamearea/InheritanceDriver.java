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
    public static void main(String args[]) {
        //Create window, top-level JPanel, and GameArea
        Window frame  = new Window();
        JPanel jPanel = new JPanel();

        try {
            GameArea area = new GameArea();

            //Initialize area and character
            BufferedReader mapReader = new BufferedReader(new FileReader("map.txt"));
            area.setTerrainTiles(area.loadMap(mapReader));

            AnimateCharacter character = new AnimateCharacter(10, 10, new URL("file:assets/CharacterSpriteSheet.png"),
                    new String[]{"DOWN", "LEFT", "UP", "RIGHT"});

            //Initialize frame, top-level JPanel, and LayeredPane
            jPanel.setLayout(null);

            lpane.setBounds(0, 0, WindowProperties.WIDTH, WindowProperties.HEIGHT);

            frame.addKeyListener(new KeyboardInteraction(character));
            frame.setPreferredSize(new Dimension(WindowProperties.WIDTH, WindowProperties.HEIGHT));
            frame.setResizable(false);
            frame.add(jPanel);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            jPanel.add(lpane);

            lpane.add(character.getSpriteAnimator(), Integer.valueOf(1000));
            lpane.add(area, Integer.valueOf(0));

            //Open window
            frame.pack();
            frame.setVisible(true);

            //Paint terrain
            area.addAllToQueue();
        } catch (LocationOutOfBoundsException e) {
            System.out.println("Error with bounds; check that map size matches with window grid size");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("File exception; check that all file names are valid");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
