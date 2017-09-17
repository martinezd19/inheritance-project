package terrain;

import exceptions.LocationOutOfBoundsException;
import window.WindowProperties;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * <h1>terrain.TerrainAbstract Class</h1> The terrain.TerrainAbstract class defines behavior for all terrain.
 *
 * @author Dominic Martinez
 * @since 2017-9-15
 */

public abstract class TerrainAbstract {

    // The x-y location of the bottom left corner of the object on the game grid
    private int x;
    private int y;

    // The max x-y location of the bottom left corner of the object on the game grid
    private static final int MAX_X = WindowProperties.MAX_X;
    private static final int MAX_Y = WindowProperties.MAX_Y;

    // The size of the object. This should be the size of the image in pixels.
    private int width;
    private int height;

    // The image of the object.
    private BufferedImage terrainImage;

    // Object movement type
    public enum MovementType {
        NORMAL,
        BLOCKED,
        SLOW
    }

    private final MovementType movementType;

    // Types of terrain
    public enum TerrainType {
        GRASS('G'),
        WATER('W'),
        TREE('T'),
        STONE('S'),
        FIRE('F');

        private final char terrainChar;
        private static HashMap<Character, TerrainType> characterMap = new HashMap<>();

        static {
            for (TerrainType terrainEnum : TerrainType.values()) {
                characterMap.put(terrainEnum.terrainChar, terrainEnum);
            }
        }

        TerrainType(char terrainChar) {
            this.terrainChar = terrainChar;
        }

        public char getTerrainChar() {
            return terrainChar;
        }

        public static TerrainType getTerrainType(char terrainChar) {
            return characterMap.get(terrainChar);
        }
    }

    private final TerrainType terrainType;

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
    public TerrainAbstract(int x, int y, MovementType movementType, TerrainType terrainType, URL imageURL)
            throws IOException, LocationOutOfBoundsException {
        //URL("file:tree_1.png")
        terrainImage = ImageIO.read(imageURL);

        if (x < 0 || y < 0 || x > MAX_X || y > MAX_Y) {
            throw new LocationOutOfBoundsException(x, y, MAX_X, MAX_Y);
        }

        width = terrainImage.getWidth();
        height = terrainImage.getHeight();
        this.movementType = movementType;
        this.terrainType = terrainType;
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

    // Draw the terrain at its location in the window.
    public void draw(Graphics2D g2) {
        // Calculate y location in pixels
        int yInPixels = WindowProperties.GRID_SIZE * (y+1);

        // Calculate x location in pixels
        int xInPixels = WindowProperties.GRID_SIZE * x;

        g2.drawImage(terrainImage, null, xInPixels, yInPixels);
    }

    @Override
    public String toString() {
        return getTerrainType().toString();
    }

    abstract public void performInteractionAction();

    public MovementType getMovementType() {
        return movementType;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }
}