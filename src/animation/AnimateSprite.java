package animation;

import gamearea.Area;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class AnimateSprite extends JPanel implements ActionListener {
    private static final int FRAMERATE = 15;
    private static boolean isAnimating;
    private double animationLength;
    private BufferedImage[] spriteImages;
    private double x;
    private double y;
    private BufferedImage currentImage;

    int numImages;
    int frameCounter;
    int totalFrameCounter;
    int framesToDo;
    int imageCounter;
    int framesToChange;
    double stepTiming;
    double xStep;
    double yStep;

    Graphics2D g2;

    Timer animationTimer;

    public AnimateSprite() {
        resetVars();
        x = 0;
        y = 0;
        //setBackground(new Color(0,0,0,0));
        setPreferredSize(new Dimension(AnimateCharacter.TILE_SIZE, AnimateCharacter.TILE_SIZE));
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g2 = (Graphics2D) g;

        System.out.println("Painting");

        Area.drawChar(currentImage, (int)Math.round(x), (int)Math.round(y));

        // Sync for cross-platform smooth rendering.
        Toolkit.getDefaultToolkit()
               .sync();
    }

    /* public void paint() {
        super.paint((Graphics)g2);

        g2.drawImage(currentImage, null, (int)Math.round(x), (int)Math.round(y));
    } */

    public void resetVars() {
        numImages = 0;
        frameCounter = 0;
        totalFrameCounter = 0;
        framesToDo = 0;
        stepTiming = 0;
        xStep = 0;
        yStep = 0;
        framesToChange = 1;
        imageCounter = 0;
        isAnimating = false;
    }

    public double getAnimationLength() {
        return animationLength;
    }

    public void setAnimationLength(double animationLength) {
        this.animationLength = animationLength;
    }

    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    public void setSpriteImages(BufferedImage[] spriteImages) {
        this.spriteImages = spriteImages;
    }

    public void animateTo(int xNew, int yNew) {
        if(isAnimating) {
            return;
        }

        isAnimating = true;

        numImages = spriteImages.length;
        stepTiming = ((double)numImages)/animationLength;
        xStep = (x-xNew)/(FRAMERATE*animationLength);
        yStep = (y-yNew)/(FRAMERATE*animationLength);
        framesToDo = (int)Math.round(FRAMERATE*animationLength);
        framesToChange = (int)Math.round((((double)FRAMERATE)*animationLength)/numImages);
        currentImage = spriteImages[0];

        animationTimer = new Timer(1000/FRAMERATE, this);
        animationTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += xStep;
        y += yStep;

        if(totalFrameCounter++ >= framesToDo) {
            resetVars();
            animationTimer.stop();
            return;
        }

        if(++frameCounter >= framesToChange) {
            imageCounter++;
            frameCounter = 0;
            currentImage = (imageCounter < spriteImages.length) ? spriteImages[imageCounter] : spriteImages[spriteImages.length-1];
        }

        SwingUtilities.invokeLater(new DrawRunnable(currentImage, x, y));
    }
}

class DrawRunnable implements Runnable {

    private final BufferedImage currentImage;
    private final double x;
    private final double y;

    DrawRunnable(BufferedImage currentImage, double x, double y) {
        this.currentImage = currentImage;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        Area.getG2().drawImage(currentImage, null, (int)x, (int)y);
        System.out.println("Painting 2");
    }
}

