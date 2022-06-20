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

    public static void main(String[] args) throws IOException {

    }
    private JButton search;
    private JButton negative;
    private JTextField chooseAccount;
    private ChromeDriver driver;
    private JButton colorShiftRight;
    private JButton colorShiftLeft;
    private JButton showBorders;
    private JButton eliminateRed;
    private JButton eliminateBlue;
    private JButton eliminateGreen;
    private JButton lighter;
    private JButton darker;
    private JLabel eliminate;
    BufferedImage bufferedImage;
    JLabel pic1;





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
        this.negative = new JButton("negative");
        this.negative.setBounds(350,50,100,30);
        add(negative);
        this.negative.addActionListener( (event) -> {
            negative();
            repaint();
        });
        this.colorShiftRight = new JButton("color shift right");
        this.colorShiftRight.setBounds(350,80,200,30);
        add(colorShiftRight);
        this.colorShiftRight.addActionListener( (event) -> {
            colorShiftRight();
            repaint();
        });
        this.colorShiftLeft = new JButton("color shift left");
        this.colorShiftLeft.setBounds(350,110,200,30);
        add(colorShiftLeft);
        this.colorShiftLeft.addActionListener( (event) -> {
            colorShiftLeft();
            repaint();
        });
        this.eliminate = new JLabel("eliminate:");
        this.eliminate.setBounds(270,140,100,30);
        add(eliminate);
        this.eliminateRed = new JButton("red");
        this.eliminateRed.setBounds(350,140,70,30);
        add(eliminateRed);
        this.eliminateRed.addActionListener( (event) -> {
            eliminateRed();
            repaint();
        });
        this.eliminateBlue = new JButton("blue");
        this.eliminateBlue.setBounds(420,140,70,30);
        add(eliminateBlue);
        this.eliminateBlue.addActionListener( (event) -> {
            eliminateBlue();
            repaint();
        });
        this.eliminateGreen = new JButton("green");
        this.eliminateGreen.setBounds(490,140,70,30);
        add(eliminateGreen);
        this.eliminateGreen.addActionListener( (event) -> {
            eliminateGreen();
            repaint();
        });
        this.showBorders = new JButton("show borders");
        this.showBorders.setBounds(350,170,200,30);
        add(showBorders);
        this.showBorders.addActionListener( (event) -> {
            shoeBorders();
            repaint();
        });
        this.darker = new JButton("darker");
        this.darker.setBounds(350,200,200,30);
        add(darker);
        this.darker.addActionListener( (event) -> {
            darker();
            repaint();
        });
        paintImage(getProfile(chooseAccount.getText()));


//        this.search.addActionListener( (event) -> {
//            try {
//                getProfile(chooseAccount.getText());
//                paintImage();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        });
    }

    public String getProfile(String name) throws IOException {
        name = "eden mazig";
        for (int i = 0; i < name.length(); i++) {
            if ((!Character.isLetter(name.charAt(i)))) {
                name = name.substring(0, i) + name.substring(i + 1);
            }
        }
        System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("C:\\Users\\USER\\AppData\\Local\\Temp\\scoped_dir12424_1911673665\\Default\n");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        String url = "https://www.facebook.com/" + name;
        driver.get(url);
        driver.manage().window().maximize();
        boolean ifExist = false;
        String hrefOfImage= " ";
        WebElement webElement;
        do {
             webElement = driver.findElement(By.className("bp9cbjyn"));
            if (webElement.isDisplayed()){
                ifExist =true;
            }
        }while (!ifExist);
        boolean ifHave =false;
            do {
                WebElement webElement1 = webElement.findElement(By.cssSelector("image[x=\"0\"]"));
                if (webElement1.isDisplayed()) {
                    ifHave = true;
                    hrefOfImage = webElement1.getAttribute("xlink:href");
                }
            } while (!ifHave);



        return hrefOfImage;

    }


    public void paintImage (String hrefOfImage) throws IOException {
        System.out.println(hrefOfImage);
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
        File file = new File("C:\\Users\\USER\\IdeaProjects\\image processing\\jet.jpg");
         bufferedImage = ImageIO.read(file);
         pic1 = new JLabel();
        pic1.setBounds(30, 200, 200, 200);
        ImageIcon image = new ImageIcon((bufferedImage));
        pic1.setIcon(image);
        this.add(pic1);
        repaint();
        File file2 = new File("C:\\Users\\USER\\IdeaProjects\\image processing\\jet.jpg");
        BufferedImage bufferedImage2 = ImageIO.read(file2);
        JLabel pic2 = new JLabel();
        pic2.setBounds(600, 200, 200, 200);
        ImageIcon image2 = new ImageIcon((bufferedImage2));
        pic2.setIcon(image2);
        this.add(pic2);
        repaint();
    }

//    public void mirror(){
//        int width = bufferedImage.getWidth();
//        int height = bufferedImage.getHeight();
//        for (int i = 0; i <height; i++) {
//            for (int j = 0; j < width; j++) {
//                photoToCopy[i][j]=new Pixel(photo[i][j].getX(),photo[i][j].getY(),photo[i][j].getColor());
//            }
//        }
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                try{
//                    Color pixelColor = photoToCopy[i][width-j].getColor();
//                    photo[i][j].setColor(pixelColor);
//                }catch (Exception e){}
//            }
//        }
//        repaint();
//        mirror=true;
//    }

    public void darker() {
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
    }

    public static boolean isSimilarColor(Color color1,Color color2){
        boolean similar=false;
        int redDiff=Math.abs(color1.getRed()-color2.getRed());
        int blueDiff=Math.abs(color1.getBlue()-color2.getBlue());
        int greenDiff=Math.abs(color1.getGreen()-color2.getGreen());
        if (redDiff+greenDiff+blueDiff<90){
            similar=true;
        }
        return similar;
    }
    public void negative() {
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
    public void eliminateRed() {
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
    public void eliminateGreen() {
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

        public void eliminateBlue() {
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
public void colorShiftRight() {
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


public void colorShiftLeft () {

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
public void shoeBorders() {
    int rgb=bufferedImage.getRGB(0,0);
    Color pixelColor=new Color(rgb);
    int width=bufferedImage.getWidth();
    int height=bufferedImage.getHeight();
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            int current = bufferedImage.getRGB(x, y);
            Color color = new Color(current);
            if (isSimilarColor(pixelColor, color)) {
                bufferedImage.setRGB(x, y, Color.black.getRGB());
            }
        }
    }
}
    public void blackLine() {
            int rgb=bufferedImage.getRGB(0,0);
            Color pixelColor=new Color(rgb);
            int width=bufferedImage.getWidth();
            int height=bufferedImage.getHeight();
        Color previousPixel=null;
        for (int x = 0; x < width; x++) {
                for (int y = 0; y <height; y++) {
                    int current=bufferedImage.getRGB(x,y);
                   Color color=new Color(current);
                   if (previousPixel!=null&&!isSimilarColor(previousPixel,color)){
                        bufferedImage.setRGB(x,y,Color.black.getRGB());
                   }
                    previousPixel=color;
                }

        }

    }



}