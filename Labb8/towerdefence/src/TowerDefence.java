import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferStrategy;

/**
 * Created by davjoa on 2017-12-06.
 */


public class TowerDefence {

    private BufferedImage mapImg;

    private Graphics2D g2d;
    private Canvas canvas;
    private BufferStrategy bs;
    private Map map;

    public TowerDefence() {
        try {
            mapImg = ImageIO.read(new File("src\\Images\\Map.png"));
        } catch (IOException e) {
            System.out.println("map img not found");
        }
        canvas = new Canvas();
        //mainFrame.add(canvas);


        map = new Map(mapImg);
        map.addEnemyAtStart();

    }

    public void draw() {
        //the main method of drawing all other things, this is called from GUITowerDefence
        //it uses g2d since it is superior to Graphics since it eliminates flickering

            g2d = (Graphics2D) bs.getDrawGraphics();

            map.draw(g2d);

            if (!bs.contentsLost()) {
                bs.show();
            }

            g2d.clearRect(0, 0, 1000, 1000);


    }



    public void update(){
        map.towersUpdate();
        map.enemiesUpdate();

    }

public Canvas getCanvas(){
        return canvas;

}
public void initCanvas(){
    //This creates the bufferstrategy of the canvas, this must be called after the canvas has been added to a frame
    canvas.createBufferStrategy(2);
    bs = canvas.getBufferStrategy();

}
public Map getMap(){
    return map;

}

}
