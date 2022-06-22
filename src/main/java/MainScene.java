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
    public static final int WINDOW_WIDTH = 900 , WINDOW_HEIGHT =500, WINDOW_X = 0 ,WINDOW_Y= 0 , X_SEARCH = 450, Y_SEARCH =20,
     WIDTH_SEARCH=80 , HEIGHT_BUTTON = 30, X_OF_BUTTON =350, WIDTH_OF_SEARCH =100,WIDTH_OF_BUTTON =200,
    WIDTH_OF_ELIMINATE= 70,Y_OF_CHOOSE_ACCOUNT =20, Y_OF_NEGATIVE =50,Y_OF_COLOR_SHIFT_RIGHT =80,Y_OF_COLOR_SHIFT_LEFT =110,
            Y_OF_ELIMINATE= 140, X_OF_ELIMINATE= 290, X_OF_ELIMINATE_BLUE =420,X_OF_ELIMINATE_GREEN =490,
    Y_OF_DARKER=200,Y_OF_GRAYSCALE =170, Y_OF_LIGHTER = 230, START_WORD=0, SECOND_CHAR =1, MAX_OF_SIZE=2048, FINISH=-1,
    ZERO =0, X_OF_PIC2 = 30, Y_OF_PIC = 150, WIDTH_OF_PIC = 300, HEIGHT_OF_PIC = 300,
            X_OF_PIC1 = 570, FIRST_CHAR=0, MAX_OF_RGB=255, NUMBER_DIVISION=3, NO_HAVE=0;
    public static final double GRAYSCALE_RED =0.299,GRAYSCALE_GREEN =0.587,
    GRAYSCALE_BLUE = 0.114;



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
        this.setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setBackground(Color.pink);
        this.search = new JButton("search");
        this.search.setBounds(X_SEARCH, Y_SEARCH, WIDTH_SEARCH, HEIGHT_BUTTON);
        add(search);
        this.chooseAccount = new JTextField();
        this.chooseAccount.setBounds(X_OF_BUTTON, Y_OF_CHOOSE_ACCOUNT, WIDTH_OF_SEARCH, HEIGHT_BUTTON);
        add(chooseAccount);
        this.negative = new JButton("negative");
        this.negative.setBounds(X_OF_BUTTON, Y_OF_NEGATIVE, WIDTH_OF_BUTTON, HEIGHT_BUTTON);
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
        this.colorShiftRight.setBounds(X_OF_BUTTON, Y_OF_COLOR_SHIFT_RIGHT, WIDTH_OF_BUTTON, HEIGHT_BUTTON);
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
        this.colorShiftLeft.setBounds(X_OF_BUTTON, Y_OF_COLOR_SHIFT_LEFT, WIDTH_OF_BUTTON, HEIGHT_BUTTON);
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
        this.eliminate.setBounds(X_OF_ELIMINATE, Y_OF_ELIMINATE, WIDTH_OF_SEARCH, HEIGHT_BUTTON);
        add(eliminate);
        this.eliminateRed = new JButton("red");
        this.eliminateRed.setBounds(X_OF_BUTTON, Y_OF_ELIMINATE, WIDTH_OF_ELIMINATE, HEIGHT_BUTTON);
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
        this.eliminateBlue.setBounds(X_OF_ELIMINATE_BLUE, Y_OF_ELIMINATE, WIDTH_OF_ELIMINATE, HEIGHT_BUTTON);
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
        this.eliminateGreen.setBounds(X_OF_ELIMINATE_GREEN, Y_OF_ELIMINATE, WIDTH_OF_ELIMINATE, HEIGHT_BUTTON);
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
        this.darker.setBounds(X_OF_BUTTON, Y_OF_DARKER, WIDTH_OF_BUTTON, HEIGHT_BUTTON);
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
        this.grayScale.setBounds(X_OF_BUTTON, Y_OF_GRAYSCALE, WIDTH_OF_BUTTON, HEIGHT_BUTTON);
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
        this.lighter.setBounds(X_OF_BUTTON, Y_OF_LIGHTER, WIDTH_OF_BUTTON, HEIGHT_BUTTON);
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
                name = name.substring(START_WORD, i) + name.substring(i + SECOND_CHAR);
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
        byte[] b = new byte[MAX_OF_SIZE];
        int length;
        while ((length = is.read(b)) != FINISH) {
            os.write(b, ZERO, length);
        }
        is.close();
        os.close();

        removeAfterFilter();
        File file2 = new File("C:\\Users\\USER\\IdeaProjects\\image processing\\image.jpg");
        BufferedImage bufferedImage2 = ImageIO.read(file2);
         pic2 = new JLabel();
        pic2.setBounds(X_OF_PIC2, Y_OF_PIC, WIDTH_OF_PIC, HEIGHT_OF_PIC);
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
        newName = newName.substring(SECOND_CHAR);
        return newName;
    }

    public String capitalLetters (String name) {
        char first = name.charAt(FIRST_CHAR);
        char x =Character.toUpperCase(first);
        name = x + name.substring(SECOND_CHAR);
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
                int red = color.getRed()+ (color.getRed()/NUMBER_DIVISION);
                if (red > MAX_OF_RGB) {
                    red=MAX_OF_RGB;
                }
                int green = color.getGreen() +(color.getGreen()/NUMBER_DIVISION);
                if (green > MAX_OF_RGB) {
                    green=MAX_OF_RGB;
                }
                int blue = color.getBlue()+ (color.getBlue()/NUMBER_DIVISION);
                if (blue > MAX_OF_RGB) {
                    blue=MAX_OF_RGB;
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
                int red = color.getRed()/NUMBER_DIVISION;
                int green = color.getGreen()/NUMBER_DIVISION;
                int blue = color.getBlue()/NUMBER_DIVISION;
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
                int red = (int) (color.getRed()*(GRAYSCALE_RED));
                int green = (int) (color.getGreen() * (GRAYSCALE_GREEN));
                int blue = (int) (color.getBlue()*(GRAYSCALE_BLUE));
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
        pic1.setBounds(X_OF_PIC1, Y_OF_PIC, WIDTH_OF_PIC, HEIGHT_OF_PIC);
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
                Color newColor = new Color(MAX_OF_RGB - red, MAX_OF_RGB - green, MAX_OF_RGB - blue);
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
                int red = NO_HAVE;
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
                int green =NO_HAVE;
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
                    int blue=NO_HAVE;
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