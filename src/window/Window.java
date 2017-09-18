package window;

import javax.swing.JFrame;

public class Window
        extends JFrame {

    // The constructor for the window.Window class.
    public Window(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(WindowProperties.WIDTH, WindowProperties.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}