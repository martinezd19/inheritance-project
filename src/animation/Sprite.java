package animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sprite {

    private static final String defaultSprite = "CharacterSpriteSheet";
    private static BufferedImage spriteSheet;
    private final int TILE_SIZE;
    private final URL FILE;

    public Sprite(int tileSize, URL file) {
        TILE_SIZE = tileSize;
        FILE = file;

        spriteSheet = loadSprite();
    }

    public BufferedImage loadSprite() {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public BufferedImage getSprite(int xGrid, int yGrid)
            throws MalformedURLException {

        if (spriteSheet == null) {
            spriteSheet = loadSprite();
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
