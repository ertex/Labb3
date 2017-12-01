import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Very, very basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame implements ActionListener {


  private final Timer timer;
  private static final int SPEED = 1000;
  private static final int PAUSE = 1000;
  private BufferedImage mapImg;

  public static void main(String[] args) {
    new GUITowerDefence("Tower Defence").setVisible(true);
  }

  public GUITowerDefence(String title) {
    super(title);

    try {
      mapImg = ImageIO.read(new File("src\\Images\\Map-debug.png.png"));
    } catch (IOException e) {
      System.out.println("map img not found");
    }
    Map map = new Map(mapImg);
    //---------------------------------------do stufff

      timer = new Timer(SPEED, this);
    timer.setInitialDelay(PAUSE);
    // Will generate ActionEvents
    timer.start();
  }



  public void createAndShowGui(){
    //import shit from muminSTD

  }

  // ---------- Event handling --------------------

  @Override
  public void actionPerformed(ActionEvent e) {


  }
}