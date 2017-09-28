package gamearea;

import exceptions.LocationOutOfBoundsException;
import terrain.*;
import window.Window;
import window.WindowProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides initialization behavior to a game area
 */

/*
TODO: Implement KeyListener (window.addKeyListener())
 */
abstract public class Area
        extends JPanel {

    // The area tile map.
    private ArrayList<Terrain>[][] terrainTiles;

    // To hide this parameter from being passed around.
    private static Graphics2D g2;

    private int zAxis;

    // The constructor for the gamearea.Area class.
    public Area()
            throws IOException, LocationOutOfBoundsException {
        setG2(null);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
    }

    public static Graphics2D getG2() {
        return g2;
    }

    public  static void setG2(Graphics2D g2) {
        Area.g2 = g2;
    }

    public ArrayList<Terrain>[][] getTerrainTiles() {
        return terrainTiles;
    }

    public void setTerrainTiles(ArrayList<Terrain>[][] terrainTiles) {
        this.terrainTiles = terrainTiles;
    }

    protected ArrayList<Terrain>[][] loadMap(BufferedReader mapReader)
            throws IOException, LocationOutOfBoundsException {
        ArrayList<Terrain>[][] mapTiles = new ArrayList[WindowProperties.MAX_Y + 1][WindowProperties.MAX_X + 1];

        // Initialize array
        for (ArrayList<Terrain>[] a : mapTiles) {
            for (int b = 0; b < a.length; b++) {
                a[b] = new ArrayList<>();
            }
        }

        for (int row = 0; row < mapTiles.length; row++) {
            // Get tile array from row in map.txt
            String[] mapLine = mapReader.readLine()
                                        .split(" ");

            for (int col = 0; col < mapTiles[0].length; col++) {
                // Get different terrain in tile
                String[] terrainInTile = mapLine[col].split("/");
                // Add each terrain type to tile map
                for (String terrainType : terrainInTile) {
                    // Convert character to TerrainType
                    Terrain.TerrainType tileTerrainType = Terrain.TerrainType.getTerrainType(terrainType.charAt(0));
                    // Create terrain object corresponding to TerrainType
                    Terrain terrainObject = Terrain.createTerrainObject(tileTerrainType, col, row);
                    // Add terrain object to map
                    mapTiles[row][col].add(terrainObject);
                }
            }
        }

        return mapTiles;
    }

    // Overridden function from JPanel, which allows us to
    // write our own paint method which draws our area.
    @Override
    public void paint(Graphics g) {
        // This calls JPanel's paintComponent method to handle
        // the lower-level details of drawing in a window.
        super.paint(g);

        setG2((Graphics2D)g);

        for (ArrayList<Terrain>[] a : terrainTiles) {
            for (ArrayList<Terrain> b : a) {
                for (Terrain terrain : b) {
                    terrain.addToDrawQueue();
                }
            }
        }

        Terrain.drawAll();

        // Sync for cross-platform smooth rendering.
        Toolkit.getDefaultToolkit()
               .sync();
    }

    public static void drawChar(BufferedImage img, int x, int y) {
        getG2().drawImage(img, null, x, y);
    }
}