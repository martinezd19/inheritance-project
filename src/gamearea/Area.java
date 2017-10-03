package gamearea;

import exceptions.LocationOutOfBoundsException;
import terrain.Terrain;
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

abstract public class Area
        extends JLabel {

    private static BufferedImage background;
    // The area tile map.
    private static ArrayList<Terrain>[][] terrainTiles;

    // To hide this parameter from being passed around.

    // The constructor for the gamearea.Area class.
    public Area()
            throws IOException, LocationOutOfBoundsException {

        background = new BufferedImage(WindowProperties.WIDTH, WindowProperties.HEIGHT, BufferedImage.TYPE_INT_ARGB);

        setBounds(0, 0, WindowProperties.WIDTH, WindowProperties.HEIGHT);
    }

    public static Graphics2D getG2() {
        return (Graphics2D) background.getGraphics();
    }

    public ArrayList<Terrain>[][] getTerrainTiles() {
        return terrainTiles;
    }

    public static void setTerrainTiles(ArrayList<Terrain>[][] terrainTiles) {
        Area.terrainTiles = terrainTiles;
    }

    public static boolean interactWithTile(int x, int y) {
        ArrayList<Terrain> terrain = terrainTiles[y][x];
        boolean canMove = true;

        for(Terrain tile : terrain) {
            tile.performInteractionAction();
            if(!tile.canMoveHere()) {
                canMove = false;
            }
        }

        return canMove;
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
                    int height = terrainObject.G_HEIGHT;
                    int width = terrainObject.G_WIDTH;

                    for(int i = row; i >= Math.max(0, row-height+1); i--) {
                        for(int j = col; j <= Math.min(mapTiles[0].length-1, col+width-1); j++) {
                            mapTiles[i][j].add(terrainObject);
                        }
                    }

                    //mapTiles[row][col].add(terrainObject);
                }
            }
        }

        return mapTiles;
    }

    public void addAllToQueue() {
        for (ArrayList<Terrain>[] a : terrainTiles) {
            for (ArrayList<Terrain> b : a) {
                for (Terrain terrain : b) {
                    terrain.addToDrawQueue();
                }
            }
        }

        Terrain.drawAll();

        setIcon(new ImageIcon(background));
    }

    // Overridden function from JPanel, which allows us to
    // write our own paint method which draws our area.
    @Override
    public void paint(Graphics g) {
        // This calls JPanel's paintComponent method to handle
        // the lower-level details of drawing in a window.
        super.paint(g);

        // Sync for cross-platform smooth rendering.
        Toolkit.getDefaultToolkit()
               .sync();
    }

}