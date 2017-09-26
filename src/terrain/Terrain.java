package terrain;

import exceptions.LocationOutOfBoundsException;
import gamearea.Area;
import window.WindowProperties;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * <h1>terrain.Terrain Class</h1> The terrain.Terrain class defines behavior for all terrain.
 *
 * @author Dominic Martinez
 * @since 2017-9-15
 */

public abstract class Terrain
        implements Comparable<Terrain> {

    // The max x-y location of the bottom left corner of the object on the game grid
    private static final int MAX_X = WindowProperties.MAX_X;
    private static final int MAX_Y = WindowProperties.MAX_Y;
    private static PriorityQueue<Terrain> drawQueue = new PriorityQueue<>();
    public final TerrainType TERRAIN_TYPE;
    public final int Z_AXIS;
    private final MovementType MOVEMENT_TYPE;
    // The x-y location of the bottom left corner of the object on the game grid
    private int x;
    private int y;
    // The size of the object. This should be the size of the image in pixels.
    private int width;
    private int height;
    // The image of the object.
    private BufferedImage terrainImage;

    /**
     * Abstract constructor for terrain
     *
     * @param x x-location of bottom edge of terrain
     * @param y y-location of bottom edge of terrain
     * @param imageURL URL of terrain image
     *
     * @throws IOException
     * @throws LocationOutOfBoundsException
     */
    public Terrain(int x, int y, MovementType movementType, TerrainType terrainType, int zAxis, URL imageURL)
            throws IOException, LocationOutOfBoundsException {
        //URL("file:tree_1.png")
        terrainImage = ImageIO.read(imageURL);

        if (x < 0 || y < 0 || x > MAX_X || y > MAX_Y) {
            throw new LocationOutOfBoundsException(x, y, MAX_X, MAX_Y);
        }

        width = terrainImage.getWidth();
        height = terrainImage.getHeight();
        this.MOVEMENT_TYPE = movementType;
        this.TERRAIN_TYPE = terrainType;
        this.Z_AXIS = zAxis;
        setLocation(x, y);
    }

    /**
     * Set terrain location
     *
     * @param x x-location on grid
     * @param y y-location on grid
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static void drawAll() {
        while (!drawQueue.isEmpty()) {
            drawQueue.poll()
                     .draw();
        }
    }

    // Draw the terrain at its location in the window.
    public void draw() {
        // Calculate y location in pixels
        int yInPixels = (WindowProperties.GRID_SIZE * (y + 1)) - terrainImage.getHeight();

        // Calculate x location in pixels
        int xInPixels = WindowProperties.GRID_SIZE * x;

        Area.getG2().drawImage(terrainImage, null, xInPixels, yInPixels);
    }

    public void addToDrawQueue() {
        Terrain.drawQueue.add(this);
    }

    @Override
    public int compareTo(Terrain o) {
        return this.Z_AXIS - o.Z_AXIS;
    }

    @Override
    public String toString() {
        return TERRAIN_TYPE.toString();
    }

    abstract public void performInteractionAction();

    // Object movement type
    public enum MovementType {
        NORMAL,
        BLOCKED,
        SLOW
    }

    // Types of terrain
    public enum TerrainType {
        GRASS('G'),
        WATER('W'),
        TREE('T'),
        STONE('S'),
        FIRE('F');

        private static HashMap<Character, TerrainType> characterMap = new HashMap<>();

        static {
            for (TerrainType terrainEnum : TerrainType.values()) {
                characterMap.put(terrainEnum.terrainChar, terrainEnum);
            }
        }

        private final char terrainChar;

        TerrainType(char terrainChar) {
            this.terrainChar = terrainChar;
        }

        public static TerrainType getTerrainType(char terrainChar) {
            return characterMap.get(terrainChar);
        }

        public char getTerrainChar() {
            return terrainChar;
        }
    }

    public static Terrain createTerrainObject(Terrain.TerrainType tileTerrainType, int x, int y)
            throws LocationOutOfBoundsException, IOException {
        Terrain terrainObject;

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
}