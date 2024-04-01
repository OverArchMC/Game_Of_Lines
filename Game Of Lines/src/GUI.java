import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI {
    private final Canvas canvas;
    private Graphics2D g;
    public GUI(){
        JFrame jFrame = new JFrame("Game Of Lines");
        canvas = new Canvas();
        jFrame.add(canvas);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
        jFrame.requestFocus();
        canvas.createBufferStrategy(2);
        g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, 500, 500);
        g.setColor(Color.gray);
        g.fillRect(0,0,500,500);
    }
    public void update() throws IOException {

        canvas.getBufferStrategy().show();
        canvas.update(g);
        g.dispose();
        g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, 500, 500);
        g.setBackground(new Color(0,0,0,0));

        Image image = ImageIO.read(new File("C:\\Users\\josep\\Downloads\\Game Of Lines\\Game Of Lines\\pictures\\pic.jpg"));
        image = image.getScaledInstance(500, 500, 0);
        g.drawImage(image, 0, 0, null);

        g.setColor(Color.gray);

//        g.fillRect(0,0,500,500);
    }
    public void putPixel(int x, int y, Color color){
        g.setColor(color);
        g.clearRect(8*x+246,-8*y+246,8,8);
    }
    public void drawLine(int[] a, int[] b){
        g.setColor(Color.RED);
        g.drawLine(8*a[0]+250, -8*a[1]+250, 8*b[0]+250, -8*b[1]+250);
    }
}