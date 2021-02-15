package Main;

import javax.swing.*;

public class Main extends JFrame {
    JFrame jFrame;
    AppPanel appPanel;


    public static void main(String arg[]) {
        new Main();
    }

    public Main() {
        jFrame = new JFrame("Atari Pong");
        appPanel = new AppPanel();
        jFrame.add(appPanel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }
}
