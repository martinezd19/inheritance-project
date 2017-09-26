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

    Timer animationTimer;

    public AnimateSprite() {
        resetVars();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(currentImage, null, (int)Math.round(x), (int)Math.round(y));
    }

    public void resetVars() {
        x = 0;
        y = 0;
        numImages = 0;
        frameCounter = 0;
        totalFrameCounter = 0;
        framesToDo = 0;
        stepTiming = 0;
        xStep = 0;
        yStep = 0;
        framesToChange = 1;
        imageCounter = 0;
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

    public void animateTo(int xOld, int yOld, int xNew, int yNew) {
        if(isAnimating) {
            return;
        }

        isAnimating = true;

        numImages = spriteImages.length;
        stepTiming = ((double)numImages)/animationLength;
        xStep = ((double)xOld-xNew)/(FRAMERATE*animationLength);
        yStep = ((double)yOld-yNew)/(FRAMERATE*animationLength);
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
            animationTimer.stop();
            isAnimating = false;
            return;
        }

        if(++frameCounter >= framesToChange) {
            imageCounter++;
            frameCounter = 0;
            currentImage = (imageCounter < spriteImages.length) ? spriteImages[imageCounter] : spriteImages[spriteImages.length-1];
        }

        this.repaint();
    }
}
