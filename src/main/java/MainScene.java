import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class MainScene extends JPanel {
    public static final int WINDOW_WIDTH = 900 , WINDOW_HEIGHT =500;


    public static void main(String[] args) throws IOException {

    }

    private JButton search;
    private JButton negative;
    private JTextField chooseAccount;
    private JButton colorShiftRight;
    private JButton colorShiftLeft;
    private JButton eliminateRed;
    private JButton eliminateBlue;
    private JButton eliminateGreen;
    private JButton darker;
    private JButton lighter;
    private JButton grayScale;
    private JLabel eliminate;
    BufferedImage bufferedImage;
    JLabel pic1;
    JLabel pic2;


    public MainScene()  {
        this.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setBackground(Color.pink);
        this.search = new JButton("search");
        this.search.setBounds(450, 20, 80, 30);
        add(search);
        this.chooseAccount = new JTextField();
        this.chooseAccount.setBounds(350, 20, 100, 30);
        add(chooseAccount);
        this.negative = new JButton("negative");
        this.negative.setBounds(350, 50, 100, 30);
        add(negative);
        this.negative.addActionListener((event) -> {
            try {
                negative();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.colorShiftRight = new JButton("color shift right");
        this.colorShiftRight.setBounds(350, 80, 200, 30);
        add(colorShiftRight);
        this.colorShiftRight.addActionListener((event) -> {
            try {
                colorShiftRight();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.colorShiftLeft = new JButton("color shift left");
        this.colorShiftLeft.setBounds(350, 110, 200, 30);
        add(colorShiftLeft);
        this.colorShiftLeft.addActionListener((event) -> {
            try {
                colorShiftLeft();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.eliminate = new JLabel("eliminate:");
        this.eliminate.setBounds(290, 140, 100, 30);
        add(eliminate);
        this.eliminateRed = new JButton("red");
        this.eliminateRed.setBounds(350, 140, 70, 30);
        add(eliminateRed);
        this.eliminateRed.addActionListener((event) -> {
            try {
                eliminateRed();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.eliminateBlue = new JButton("blue");
        this.eliminateBlue.setBounds(420, 140, 70, 30);
        add(eliminateBlue);
        this.eliminateBlue.addActionListener((event) -> {
            try {
                eliminateBlue();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.eliminateGreen = new JButton("green");
        this.eliminateGreen.setBounds(490, 140, 70, 30);
        add(eliminateGreen);
        this.eliminateGreen.addActionListener((event) -> {
            try {
                eliminateGreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });

        this.darker = new JButton("darker");
        this.darker.setBounds(350, 200, 200, 30);
        add(darker);
        this.darker.addActionListener((event) -> {
            try {
                darker();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.grayScale = new JButton("grayscale");
        this.grayScale.setBounds(350, 170, 200, 30);
        add(grayScale);
        this.grayScale.addActionListener((event) -> {
            try {
                grayScale();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });
        this.lighter = new JButton("lighter");
        this.lighter.setBounds(350, 230, 200, 30);
        add(lighter);
        this.lighter.addActionListener((event) -> {
            try {
                lighter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        });

        this.search.addActionListener( (event) -> {
            try {
                if (pic2 != null) {
                    remove(pic2);

                }
                paintImage(getProfile(chooseAccount.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public String getProfile(String name) throws IOException {
        String nameOfAccount = validName(smallLetters(name));
        System.out.println(nameOfAccount);
        for (int i = 0; i < name.length(); i++) {
            if ((!Character.isLetter(name.charAt(i)))) {
                name = name.substring(0, i) + name.substring(i + 1);
            }
        }
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=C:\\Users\\USER\\AppData\\Local\\Temp\\scoped_dir12424_1911673665\\Default\n");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        String url = "https://www.facebook.com/" + name;
        driver.get(url);
        driver.manage().window().maximize();
        String hrefOfImage;
        WebElement imageElement = driver.findElement(By.cssSelector("a[aria-label=\""+nameOfAccount+"\"]"));
        WebElement webElement1 = imageElement.findElement(By.cssSelector("image[x=\"0\"]"));
        hrefOfImage = webElement1.getAttribute("xlink:href");
        return hrefOfImage;
    }


    public void paintImage(String hrefOfImage) throws IOException {
        URL url1 = new URL(hrefOfImage);
        InputStream is = url1.openStream();
        OutputStream os = new FileOutputStream("image.jpg");
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();

        removeAfterFilter();
        File file2 = new File("C:\\Users\\USER\\IdeaProjects\\image processing\\image.jpg");
        BufferedImage bufferedImage2 = ImageIO.read(file2);
         pic2 = new JLabel();
        pic2.setBounds(30, 150, 300, 300);
        ImageIcon image2 = new ImageIcon((bufferedImage2));
        pic2.setIcon(image2);
        this.add(pic2);
        repaint();
    }

    public String validName(String name) {
        String newName = "";
        capitalLetters(name);
        String[] arr = name.split(" ");
        for(int i = 0; i<arr.length;i++) {
            capitalLetters(arr[i]);
            newName = newName + " " + capitalLetters(arr[i]);

        }
        System.out.println(newName.charAt(0) + "0");
        System.out.println(newName.charAt(1) + "1");
        newName = newName.substring(1);
        return newName;
    }

    public String capitalLetters (String name) {
        char first = name.charAt(0);
        char x =Character.toUpperCase(first);
        name = x + name.substring(1);
        return name;
    }
    public String smallLetters (String name) {
        String newName="";
        for (int i = 0; i <name.length() ; i++) {
            char x = Character.toLowerCase(name.charAt(i));
            newName = newName + x;
        }
        return newName;
    }
    public void lighter() throws IOException {
        removeAfterFilter();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int current = bufferedImage.getRGB(x, y);
                Color color = new Color(current);
                int red = color.getRed()+ (color.getRed()/3);
                if (red > 255) {
                    red=255;
                }
                int green = color.getGreen() +(color.getGreen()/3);
                if (green > 255) {
                    green=255;
                }
                int blue = color.getBlue()+ (color.getBlue()/3);
                if (blue > 255) {
                    blue=255;
                }
                Color newColor = new Color(red,green,blue );
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        repaint();
    }





    public void darker() throws IOException {
        removeAfterFilter();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int current = bufferedImage.getRGB(x, y);
                Color color = new Color(current);
                int red = color.getRed()/3;
                int green = color.getGreen()/3;
                int blue = color.getBlue()/3;
                Color newColor = new Color(red,green,blue );
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        repaint();
    }

    public void grayScale() throws IOException {
        removeAfterFilter();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int current = bufferedImage.getRGB(x, y);
                Color color = new Color(current);
                int red = (int) (color.getRed()*(0.299));
                int green = (int) (color.getGreen() * (0.587));
                int blue = (int) (color.getBlue()*(0.114));
                Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        repaint();
    }
    public void removeAfterFilter () throws IOException {
        if (pic1 != null) {
            remove(pic1);
        }
        File file = new File("C:\\Users\\USER\\IdeaProjects\\image processing\\image.jpg");
         bufferedImage = ImageIO.read(file);
         pic1 = new JLabel();
        pic1.setBounds(570, 150, 300, 300);
        ImageIcon image = new ImageIcon((bufferedImage));
        pic1.setIcon(image);
        this.add(pic1);
    }

    public void negative() throws IOException {
        removeAfterFilter();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int current = bufferedImage.getRGB(x, y);
                Color color = new Color(current);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(255 - red, 255 - green, 255 - blue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }
    public void eliminateRed() throws IOException {
        removeAfterFilter();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int current = bufferedImage.getRGB(x, y);
                Color color = new Color(current);
                int red = 0;
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }
    public void eliminateGreen() throws IOException {
        removeAfterFilter();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int current = bufferedImage.getRGB(x, y);
                Color color = new Color(current);
                int red = color.getRed();
                int green =0;
                int blue = color.getBlue();
                Color newColor = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

        public void eliminateBlue() throws IOException {
        removeAfterFilter();
            int width=bufferedImage.getWidth();
            int height=bufferedImage.getHeight();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y <height ; y++) {
                    int current=bufferedImage.getRGB(x,y);
                    Color color=new Color(current);
                    int red=color.getRed();
                    int green=color.getGreen();
                    int blue=0;
                    Color newColor=new Color(red,green,blue);
                    bufferedImage.setRGB(x,y,newColor.getRGB());
                }
            }
}
public void colorShiftRight() throws IOException {
    removeAfterFilter();
    int width=bufferedImage.getWidth();
    int height=bufferedImage.getHeight();
    for (int x = 0; x < width; x++) {
        for (int y = 0; y <height ; y++) {
            int current=bufferedImage.getRGB(x,y);
            Color color=new Color(current);
            int red=color.getGreen();
            int green=color.getBlue();
            int blue=color.getRed();
            Color newColor=new Color(red,green,blue);
            bufferedImage.setRGB(x,y,newColor.getRGB());
        }
    }

}


public void colorShiftLeft () throws IOException {
    removeAfterFilter();
    int width=bufferedImage.getWidth();
    int height=bufferedImage.getHeight();
    for (int x = 0; x < width; x++) {
        for (int y = 0; y <height ; y++) {
            int current=bufferedImage.getRGB(x,y);
            Color color=new Color(current);
            int red=color.getBlue();
            int green=color.getRed();
            int blue=color.getGreen();
            Color newColor=new Color(red,green,blue);
            bufferedImage.setRGB(x,y,newColor.getRGB());
        }
    }

}





}