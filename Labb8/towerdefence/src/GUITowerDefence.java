import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/**
 * Very, very basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame implements ActionListener {


  private TowerDefence towerDefence;
  private final Timer timer;
  private static final int SPEED = 10;
  private static final int PAUSE = 10;

public void towersCreate(){ // A visible method for were you can add towers
  towerDefence.getMap().addTower(1,1);
  towerDefence.getMap().addTower(3,1);
  towerDefence.getMap().addTower(3,2);

  }


  public static void main(String[] args) {
    new GUITowerDefence("Tower Defence").setVisible(true);
  }

  public GUITowerDefence(String title) {
    super(title);
    towerDefence = new TowerDefence();
    createAndShowGui();
    towersCreate();
      timer = new Timer(SPEED, this);
      timer.setInitialDelay(PAUSE);
    // Will generate ActionEvents
    timer.start();
  }



  public void createAndShowGui(){
    //import shit from muminSTD


    this.pack();
    this.setSize(700, 650);
    this.setResizable(false);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(towerDefence.getCanvas());
    towerDefence.initCanvas(); //canvas must be part of a frame before bufferstategy can be created

  }

  // ---------- Event handling --------------------

  @Override
  public void actionPerformed(ActionEvent e) {

      //updates and draws the map
    towerDefence.update();
    towerDefence.draw();

    //State checks for the game, wether you won or lost
    if(Map.lost) {
timer.stop();
      JOptionPane.showMessageDialog(null, "oh no, you lost!");
      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }else if(Map.won){
timer.stop();
      JOptionPane.showMessageDialog(null, "YOU WON! YEY");
      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }



}

  }
