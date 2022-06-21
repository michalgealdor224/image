import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {

    public static final int WINDOW_WIDTH = 900 , WINDOW_HEIGHT =500;
    public static void main(String[] args) throws IOException {

        new Main();

    }
    public Main () throws IOException {
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setResizable(false);
        this.setBackground(Color.cyan);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        MainScene mainScene = new MainScene();
        add(mainScene);

    }
}
