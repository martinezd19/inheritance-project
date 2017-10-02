package action;

import animation.AnimateCharacter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInteraction
        implements KeyListener {

    private AnimateCharacter character;

    public KeyboardInteraction(AnimateCharacter character) {
        this.character = character;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                character.animate(AnimateCharacter.Movements.UP);
                break;
            case KeyEvent.VK_S:
                character.animate(AnimateCharacter.Movements.DOWN);
                break;
            case KeyEvent.VK_A:
                character.animate(AnimateCharacter.Movements.LEFT);
                break;
            case KeyEvent.VK_D:
                character.animate(AnimateCharacter.Movements.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
