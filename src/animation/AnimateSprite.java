package animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AnimateSprite
        extends JLabel
        implements ActionListener {

    private static final int FRAMERATE = 30;
    private static boolean         isAnimating;
    int    numImages;
    int    frameCounter;
    int    totalFrameCounter;
    int    framesToDo;
    int    imageCounter;
    int    framesToChange;
    double stepTiming;
    double xStep;
    double yStep;
    Timer animationTimer;
    private        double          animationLength;
    private        BufferedImage[] spriteImages;
    private        double          x;
    private        double          y;
    private        BufferedImage   currentImage;

    public AnimateSprite(double x, double y) {
        resetVars();
        this.x = x;
        this.y = y;
        setBackground(new Color(0, 0, 0, 0));
        setBounds((int) Math.round(x), (int) Math.round(y), AnimateCharacter.TILE_SIZE, AnimateCharacter.TILE_SIZE);
        setLayout(null);
    }

    public void setXPos(double x) {
        this.x = x;
    }

    public void setYPos(double y) {
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Sync for cross-platform smooth rendering.
        Toolkit.getDefaultToolkit()
               .sync();
    }

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

    public void setAnimationLength(double animationLength) {
        this.animationLength = animationLength;
    }

    public void setSpriteImages(BufferedImage[] spriteImages) {
        this.spriteImages = spriteImages;
    }

    public void animateTo(int xNew, int yNew) {
        if (isAnimating) {
            return;
        }

        isAnimating = true;

        numImages = spriteImages.length;
        stepTiming = ((double) numImages) / animationLength;
        xStep = (xNew - x) / (FRAMERATE * animationLength);
        yStep = (yNew - y) / (FRAMERATE * animationLength);
        framesToDo = (int) Math.round(FRAMERATE * animationLength);
        framesToChange = (int) Math.round((((double) FRAMERATE) * animationLength) / numImages);
        currentImage = spriteImages[0];

        animationTimer = new Timer(1000 / FRAMERATE, this);
        animationTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += xStep;
        y += yStep;

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

        SwingUtilities.invokeLater(new DrawRunnable(currentImage, x, y, this));
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

