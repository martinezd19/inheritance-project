package animation;

import window.WindowProperties;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;


//TODO: Fix keyboard being registered during animation
public class AnimateCharacter {

    public static final double ANIMATION_LENGTH     = .25;
    public static final int    SPRITES_IN_ANIMATION = 4;
    public static final int    TILE_SIZE            = 48;

    private final URL      FILE_URL;
    private final String[] SPRITE_ORDER;
    private       int      currentXGrid;
    private       int      currentYGrid;

    private AnimateSprite spriteAnimator;

    public AnimateCharacter(int currentXGrid, int currentYGrid, URL fileURL, String[] spriteOrder)
            throws MalformedURLException {
        this.currentXGrid = currentXGrid;
        this.currentYGrid = currentYGrid;
        FILE_URL = fileURL;
        SPRITE_ORDER = spriteOrder;

        Sprite characterSprite = new Sprite(TILE_SIZE, fileURL);

        for (int i = 0; i < Movements.values().length; i++) {
            BufferedImage[] spriteImages = new BufferedImage[SPRITES_IN_ANIMATION];
            for (int j = 0; j < SPRITES_IN_ANIMATION; j++) {
                spriteImages[j] = characterSprite.getSprite(i, j);
            }

            Movements.valueOf(spriteOrder[i])
                     .setSpriteImages(spriteImages);
        }
        int gridSize = WindowProperties.GRID_SIZE;
        spriteAnimator = new AnimateSprite(currentXGrid * gridSize, currentYGrid * gridSize);
        spriteAnimator.setAnimationLength(ANIMATION_LENGTH);
    }

    public AnimateSprite getSpriteAnimator() {
        return spriteAnimator;
    }

    public void animate(Movements movement) {
        spriteAnimator.setSpriteImages(movement.getSpriteImages());

        int yMove    = 0;
        int xMove    = 0;
        int gridSize = WindowProperties.GRID_SIZE;
        spriteAnimator.setXPos(currentXGrid * gridSize);
        spriteAnimator.setYPos(currentYGrid * gridSize);

        switch (movement) {
            case DOWN:
                yMove = 1;
                break;
            case UP:
                yMove = -1;
                break;
            case LEFT:
                xMove = -1;
                break;
            case RIGHT:
                xMove = 1;
                break;
        }

        spriteAnimator.animateTo((currentXGrid += xMove) * gridSize, (currentYGrid += yMove) * gridSize);
    }

    public enum Movements {
        DOWN,
        LEFT,
        UP,
        RIGHT;

        private BufferedImage[] spriteImages;

        public BufferedImage[] getSpriteImages() {
            return spriteImages;
        }

        public void setSpriteImages(BufferedImage[] spriteImages) {
            this.spriteImages = spriteImages;
        }
    }
}
