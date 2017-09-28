package action;

import animation.AnimateCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInteraction implements KeyListener {

    private AnimateCharacter character;

    public KeyboardInteraction(AnimateCharacter character) {
        this.character = character;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        character.animate(AnimateCharacter.Movements.DOWN);
        System.out.println("Key Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
