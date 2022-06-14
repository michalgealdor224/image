import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import javax.lang.model.util.Elements;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainScene extends JPanel {

    public static void main(String[] args) {

    }
    private JButton search;
    private JTextField chooseAccount;
    private ChromeDriver driver;
    private JButton colorShiftRight;
    private JButton colorShiftLeft;
    private JButton showBorders;
    private JButton EliminateBlue;
    private JButton lighter;
    private JButton darker;
    BufferedImage bufferedImage;




    public MainScene() throws IOException {
        this.setBounds(0, 0, 900, 500);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setBackground(Color.pink);
        this.search = new JButton("search");
        this.search.setBounds(450,20,80,30);
        add(search);
        this.chooseAccount = new JTextField();
        this.chooseAccount.setBounds(350,20,100,30);
        add(chooseAccount);
    //    this.search.addActionListener( (event) -> {
      //      try {
                ;
                paintImage(getProfile(chooseAccount.getText()));

        //    } catch (IOException e) {
          //      e.printStackTrace();
            //}

      //  });
    }

    public String getProfile(String name) throws IOException {
        name = "eden mazig";
        for (int i = 0; i < name.length(); i++) {
            if ((!Character.isLetter(name.charAt(i)))) {
                name = name.substring(0, i) + name.substring(i + 1);
            }
        }
        String url = "https://www.facebook.com/" + name;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement webElement = driver.findElement(By.className("pzggbiyp"));
        WebElement webElement1 = webElement.findElement(By.cssSelector("image[x=\"0\"]"));
        String hrefOfImage = webElement1.getAttribute("xlink:href");
        return hrefOfImage;
    }


    public void paintImage (String hrefOfImage) throws IOException {
        URL url1 = new URL(hrefOfImage);
        InputStream is = url1.openStream();
        OutputStream os = new FileOutputStream("image.jpg");
        byte [] b = new  byte[2048];
        int length;
        while ((length= is.read(b)) != -1) {
            os.write(b,0,length);
        }
        is.close();
        os.close();
        File file = new File("C:\\Users\\USER\\IdeaProjects\\image processing\\image.jpg");
         bufferedImage = ImageIO.read(file);
        JLabel pic1 = new JLabel();
        pic1.setBounds(50, -100, 500, 600);
        ImageIcon image = new ImageIcon((bufferedImage));
        pic1.setIcon(image);
        this.add(pic1);
        repaint();
        JLabel pic2 = new JLabel();
        pic2.setBounds(600, -100, 500, 600);
        pic2.setIcon(image);
        this.add(pic2);
        repaint();
        filterGreyScale(pic1);
    }

    public void filterGreyScale (JLabel picture) {
        for (int x = 0; x < picture.getWidth(); x++) {
            for (int y = 0; y < picture.getHeight(); y++) {
                int currentRGB = bufferedImage.getRGB(x, y);
                Color currentColor = new Color(currentRGB);
//                int currentRed =
//                int currentBlue =
//                int currentGreen =
//                Color newColor = new Color(currentGreen,currentBlue,currentRed);
//                bufferedImage.setRGB(x,y,newColor.getRGB());
                repaint();
            }

        }

    }



}