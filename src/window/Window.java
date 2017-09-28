package window;

import javax.swing.*;

public class Window
        extends JFrame {

    private final JPanel       panelBlue = new JPanel();
    private       JFrame       frame     = new JFrame();
    private       JLayeredPane lpane     = new JLayeredPane();

    public Window() {
        /* frame.setPreferredSize(new Dimension(WindowProperties.WIDTH, WindowProperties.HEIGHT));
        //frame.setLayout(new BorderLayout());
        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(0, 0, 600, 400);
        panelBlue.setOpaque(true);
        lpane.add(panelBlue, new Integer(0), 0); */

        //frame.setVisible(true);
    }

    public JLayeredPane getLpane() {
        return lpane;
    }
}