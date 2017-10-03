package animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

//TODO: Fix character not being drawn on game start
public class AnimateSprite
        extends JLabel
        implements ActionListener {

    private static final int FRAMERATE = 30;
    private static boolean isAnimating;
    int    numImages;
    int    frameCounter;
    int    totalFrameCounter;
    int    framesToDo;
    int    imageCounter;
    int    framesToChange;
    double stepTiming;
    double xStep;
    double yStep;
    Timer  animationTimer;
    private double          animationLength;
    private BufferedImage[] spriteImages;
    private double          xPos;
    private double          yPos;
    private BufferedImage   currentImage;

    public AnimateSprite(double xPos, double yPos, BufferedImage startingImage) {
        resetVars();
        this.xPos = xPos;
        this.yPos = yPos;
        setBounds((int) Math.round(xPos), (int) Math.round(yPos), AnimateCharacter.TILE_SIZE, AnimateCharacter.TILE_SIZE);
        setLocation((int) Math.round(xPos), (int) Math.round(yPos));
        setIcon(new ImageIcon(startingImage));
        setBackground(new Color(0, 0, 0, 0));
    }

    public void setXPos(double x) {
        this.xPos = x;
    }

    public void setYPos(double y) {
        this.yPos = y;
    }

    public void setAnimationLength(double animationLength) {
        this.animationLength = animationLength;
    }

    public void setSpriteImages(BufferedImage[] spriteImages) {
        this.spriteImages = spriteImages;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Sync for cross-platform smooth rendering.
        Toolkit.getDefaultToolkit()
               .sync();
    }

    private void resetVars() {
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

    protected void animateTo(int xNew, int yNew) {
        if (isAnimating) {
            return;
        }

        isAnimating = true;

        numImages = spriteImages.length;
        stepTiming = ((double) numImages) / animationLength;
        xStep = (xNew - xPos) / (FRAMERATE * animationLength);
        yStep = (yNew - yPos) / (FRAMERATE * animationLength);
        framesToDo = (int) Math.round(FRAMERATE * animationLength);
        framesToChange = (int) Math.round((((double) FRAMERATE) * animationLength) / numImages);
        currentImage = spriteImages[0];

        animationTimer = new Timer(1000 / FRAMERATE, this);
        animationTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xPos += xStep;
        yPos += yStep;

        if (totalFrameCounter++ >= framesToDo) {
            resetVars();
            animationTimer.stop();
            return;
        }

        if (++frameCounter >= framesToChange) {
            imageCounter++;
            frameCounter = 0;
            currentImage = (imageCounter < spriteImages.length) ? spriteImages[imageCounter] :
                    spriteImages[spriteImages.length - 1];
        }

        SwingUtilities.invokeLater(new DrawRunnable(currentImage, xPos, yPos, this));
    }
}

class DrawRunnable
        implements Runnable {

    private final BufferedImage currentImage;
    private final double        x;
    private final double        y;
    private       JLabel        jLabel;

    DrawRunnable(BufferedImage currentImage, double x, double y, JLabel jLabel) {
        this.currentImage = currentImage;
        this.x = x;
        this.y = y;
        this.jLabel = jLabel;
    }

    @Override
    public void run() {
        jLabel.setLocation((int) Math.round(x), (int) Math.round(y));
        jLabel.setIcon(new ImageIcon(currentImage));
        jLabel.setBackground(new Color(0, 0, 0, 0));
    }
}

