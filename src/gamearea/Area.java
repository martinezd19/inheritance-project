package gamearea;

import exceptions.LocationOutOfBoundsException;
import terrain.*;
import window.Window;
import window.WindowProperties;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Provides initialization behavior to a game area
 */
abstract public class Area
        extends JPanel {

    // The area tile map.
    protected ArrayList<TerrainAbstract>[][] terrainTiles;

    // To hide this parameter from being passed around.
    private Graphics2D g2;

    // The constructor for the gamearea.Area class.
    public Area() throws IOException, LocationOutOfBoundsException {
        // Read map and create terrain.terrain objects
        BufferedReader mapReader = new BufferedReader(new FileReader("map.txt"));

        terrainTiles = loadMap(mapReader);

        g2 = null;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
    }

    protected ArrayList<TerrainAbstract>[][] loadMap(BufferedReader mapReader) throws IOException, LocationOutOfBoundsException {
        ArrayList<TerrainAbstract>[][] mapTiles = new ArrayList[WindowProperties.MAX_Y][WindowProperties.MAX_X];

        for()

        for (int row = 0; row < mapTiles.length; row++) {
            // Get tile array from row in map.txt
            String[] mapLine = mapReader.readLine().split(" ");

            for (int col = 0; col < mapTiles[0].length; col++) {
                // Get different terrain in tile
                String[] terrainInTile = mapLine[col].split("/");
                // Add each terrain type to tile map
                for (String terrainType : terrainInTile) {
                    // Convert character to TerrainType
                    TerrainAbstract.TerrainType tileTerrainType = TerrainAbstract.TerrainType.getTerrainType(terrainType.charAt(0));
                    // Create terrain object corresponding to TerrainType
                    TerrainAbstract terrainObject = createTerrainObject(tileTerrainType, col, row);
                    // Add terrain object to map
                    System.out.println(terrainObject+", "+row+"/"+col);
                    mapTiles[row][col].add(terrainObject);
                }
            }
        }

        return mapTiles;
    }

    protected TerrainAbstract createTerrainObject(TerrainAbstract.TerrainType tileTerrainType, int x, int y) throws LocationOutOfBoundsException, IOException {
        TerrainAbstract terrainObject;

        switch (tileTerrainType) {
            case GRASS:
                terrainObject = new Grass(x, y);
                break;
            case WATER:
                terrainObject = new Water(x, y);
                break;
            case TREE:
                terrainObject = new Tree(x, y);
                break;
            case STONE:
                terrainObject = new Stone(x, y);
                break;
            case FIRE:
                terrainObject = new Fire(x, y);
                break;
            default:
                terrainObject = new Grass(x, y);
                break;
        }

        return terrainObject;
    }

    // Overridden function from JPanel, which allows us to
    // write our own paint method which draws our area.
    @Override
    public void paint(Graphics g) {
        // This calls JPanel's paintComponent method to handle
        // the lower-level details of drawing in a window.
        super.paint(g);

        g2 = (Graphics2D) g;

        for (ArrayList<TerrainAbstract>[] a : terrainTiles) {
            for(ArrayList<TerrainAbstract> b : a) {
                for(TerrainAbstract terrain : b) {
                    terrain.draw(g2);
                }
            }
        }

        // Sync for cross-platform smooth rendering.
        Toolkit.getDefaultToolkit()
               .sync();
    }
}