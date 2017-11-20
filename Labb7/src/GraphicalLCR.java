import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alex on 2015-11-20.
 */
public class GraphicalLCR extends JFrame {
  private JPanel rootPanel;
  private JButton rollButton;
  private JButton quitButton;
  private JPanel playerPanel;
  private JLabel resultLabel;
  private JPanel cmdPanel;
  private JPanel resultPanel;

  public static void main(String[] args) {
    GraphicalLCR game = new GraphicalLCR();
    game.run();
  }

  public GraphicalLCR() {
    super("LCR game");

    setContentPane(rootPanel);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    rollButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    quitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    pack();
    setVisible(true);
  }

  public void run() {
  }

  private void buildLCRGame() {
  }

  private void render() {
  }
}
