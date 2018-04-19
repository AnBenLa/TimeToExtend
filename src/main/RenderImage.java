package main;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RenderImage {

    public static void createImage() {
        int width = 1920;
        int height = 1080;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        renderImage(image);
        File imageFile = new File("tmp\\Output.jpg");
        try {
            ImageIO.write(image, "jpg", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renderImage(BufferedImage image) {
        for (int i = 0; i < 1920; i++) {
            for (int c = 0; c < 1080; c++) {
                Camera camera = new Camera(new Vector(new int[]{2,2,2}), new Vector(new int[]{-2,-2,-2}), 90f);
                ArrayList<Object> objects = new ArrayList<Object>();
                objects.add(
                        new Object(
                            new Polygon[]{
                                new Polygon(
                                        new Vector(
                                                new int[]{0, 0, 0}
                                                ),
                                        new Vector(
                                                new int[]{1,0,0}
                                            ),
                                        new Vector(
                                                new int[]{0,-1,0}
                                            )
                                        )
                                }
                            )
                        );

                image.setRGB(i, c, RenderImage.renderPixelRGB(i, c, camera, objects));
            }
        }
    }

    public static int renderPixelRGB(int x, int y, Camera camera, ArrayList<Object> objects) {
        Color color = new Color(255, 255, 255);
        for(Object object : objects){
            if(object.inView(camera,x,y)){
                color = new Color(0,0,0);
            }
        }
        return color.getRGB();
    }

    public static void openImage() {
        try {
            BufferedImage img = ImageIO.read(new File("tmp\\Output.jpg"));
            ImageIcon icon = new ImageIcon(img);
            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(1920, 1080);
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
