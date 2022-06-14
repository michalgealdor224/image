import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends JFrame {
    public static void main(String[] args) throws IOException {

        new Main();

    }
    public Main () throws IOException {
        this.setSize(900,500);
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
