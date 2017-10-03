package terrain;

import exceptions.LocationOutOfBoundsException;
import gamearea.Area;
import window.WindowProperties;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * <h1>terrain.Terrain Class</h1> The terrain.Terrain class defines behavior for all terrain.
 *
 * @author Dominic Martinez
 * @since 2017-9-15
 */

public abstract class Terrain
        implements Comparable<Terrain> {

    // The max x-y location of the bottom left corner of the object on the game grid
    private static final int                    MAX_X     = WindowProperties.MAX_X;
    private static final int                    MAX_Y     = WindowProperties.MAX_Y;
    private static       PriorityQueue<Terrain> drawQueue = new PriorityQueue<>();
    public final  TerrainType   TERRAIN_TYPE;
    public final  int           Z_AXIS;
    private final MovementType  MOVEMENT_TYPE;
    // The x-y location of the bottom left corner of the object on the game grid
    private       int           x;
    private       int           y;
    // The size of the object. This should be the size of the image in pixels.
    private       int           width;
    private       int           height;
    public final int           G_WIDTH;
    public final int           G_HEIGHT;
    // The image of the object.
    private       BufferedImage terrainImage;

    private static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Terrain(int x, int y, MovementType movementType, TerrainType terrainType, int zAxis, int G_HEIGHT, int G_WIDTH)
            throws IOException, LocationOutOfBoundsException {
        this.G_HEIGHT = G_HEIGHT;
        this.G_WIDTH = G_WIDTH;

        if (WindowProperties.outOfGrid(x, y)) {
            throw new LocationOutOfBoundsException(x, y, MAX_X, MAX_Y);
        }
        this.MOVEMENT_TYPE = movementType;
        this.TERRAIN_TYPE = terrainType;
        this.Z_AXIS = zAxis;
        setLocation(x, y);
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

        Area.getG2()
            .drawImage(terrainImage, null, xInPixels, yInPixels);
    }

    public void setTerrainImage(BufferedImage terrainImage) {
        this.terrainImage = terrainImage;
        width = terrainImage.getWidth();
        height = terrainImage.getHeight();
    }

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

    abstract public boolean canMoveHere();

    // Object movement type
    public enum MovementType {
        NORMAL,
        BLOCKED,
        SLOW
    }

    /* *** ADD NEW TERRAIN OBJECTS TO THIS ENUM *** */
    public enum TerrainType {
        ACID('A'),
        BRICK('B'),
        COBBLE('C'),
        DEMONIC_STONE('D'),
        LAVA('L'),
        STONE('S'),
        WATER('W');

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

    /* *** ADD NEW TERRAIN OBJECTS TO THIS SWITCH *** */
    public static Terrain createTerrainObject(Terrain.TerrainType tileTerrainType, int x, int y)
            throws LocationOutOfBoundsException, IOException {
        Terrain terrainObject;

        switch (tileTerrainType) {
            case ACID:
                terrainObject = new Acid(x, y);
                break;
            case BRICK:
                terrainObject = new Brick(x, y);
                break;
            case COBBLE:
                terrainObject = new Cobble(x, y);
                break;
            case DEMONIC_STONE:
                terrainObject = new DemonicStone(x, y);
                break;
            case LAVA:
                terrainObject = new Lava(x, y);
                break;
            case STONE:
                terrainObject = new Stone(x,y);
                break;
            case WATER:
                terrainObject = new Water(x, y);
                break;
            default:
                terrainObject = new Cobble(x, y);
                break;
        }

        return terrainObject;
    }

    protected BufferedImage pickRandomImageInDir(String directory) throws IOException {
        File   dir      = new File(directory);
        File[] images   = dir.listFiles();
        Random rand     = new Random();
        File   randFile = images[rand.nextInt(images.length)];
        return ImageIO.read(randFile);
    }

    protected static void playSound(File soundFile) {
        if(clip.isRunning()) {
            return;
        }

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip.close();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}