import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by alex on 2015-11-20.
 * Modified by David and Emelie 2017-11-20
 */
public class GraphicalLCR extends JFrame {
    private JPanel rootPanel;
    private JButton rollButton;
    private JButton quitButton;
    private JPanel playerPanel;
    private JLabel resultLabel;
    private JPanel cmdPanel;
    private JPanel resultPanel;
    private JFrame frame;
    private LCR game;
    private ActionHandler actionHandler;
    private int xSize, ySize;
    private HashMap<String, PlayerPanel> map;


    public static void main(String[] args) {
        GraphicalLCR game = new GraphicalLCR(400, 400);

    }

    public GraphicalLCR(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        map = new HashMap<>();
        buildLCRGame(); //This needs to be before createAndShowGUI()
        actionHandler = new ActionHandler();
        createAndShowGUI();
        run();
    }

    public void run() {


        resultLabel.setText(game.getLastResults());
        for (PlayerPanel p : map.values()) {
            if(game.getCurrentPlayer().equals(p.getPlayer().getName())){
                p.getPanel().setBackground(Color.RED);
            }
            else{
                p.getPanel().setBackground(Color.white);
            }
            p.update();
        }
    }

    private void buildLCRGame() {
        // Set up number of players and player names
        int numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players"));
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {

            players[i] = new Player(JOptionPane.showInputDialog("Name of player " + (i + 1) + " :"));
        }
        //Create new game
        this.game = new LCR(players);
    }


    public void createAndShowGUI() {
        //Init of the main frame
        JFrame frame = new JFrame("GraphicalLCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));
        frame.setVisible(true);
        frame.setSize(xSize, ySize);
        //All of the main sub-frames
        playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Players"));
        cmdPanel = new JPanel();
        cmdPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
        resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        //adds the main sub frames to the main frame
        frame.add(playerPanel);
        frame.add(resultPanel);
        frame.add(cmdPanel);


        for (Player p : game.getPlayers()) {
            map.put(p.getName(), new PlayerPanel(p)); //puts new playerPanels into the hashmap at the place of their name
            playerPanel.add(map.get(p.getName()).panel);

        }

        resultLabel = new JLabel();
        resultLabel.setText("");
        resultPanel.add(resultLabel);

        //Creates a new button, sets the name and adds a actionhandler to he button, then adds it to cmdPanel
        quitButton = new JButton("quit");
        quitButton.setVisible(true);
        quitButton.setText("quit");
        quitButton.addActionListener(actionHandler);
        cmdPanel.add(quitButton);

        //Creates a new button, sets the name and adds a actionhandler to he button, then adds it to cmdPanel
        rollButton = new JButton("roll");
        rollButton.setVisible(true);
        rollButton.setText("roll");
        rollButton.addActionListener(actionHandler);
        cmdPanel.add(rollButton);


        frame.pack(); //optimised the size of the frame

    }

    private class PlayerPanel {
        private JPanel panel = new JPanel();
        private JLabel points, name;
        private Player player;

        public PlayerPanel(Player player) {
            this.player = player;
            panel.setBorder(BorderFactory.createLineBorder(Color.blue));
            panel.setVisible(true);
            points = new JLabel();
            name = new JLabel();
            name.setText(player.getName());
            panel.add(name);
            panel.add(points);
            panel.setLayout(new GridLayout(0, 1));
            update();
        }

        public Player getPlayer() {
            return player;
        }

        public void update() {
            points.setText("Points: " + player.getChips());
        }


        public JPanel getPanel() {
            return panel;

        }
    }

    private class ActionHandler implements ActionListener{
            //this listens if a action is performed and exceutes the linked action
        public void actionPerformed(ActionEvent e) {

            try {

                String cmd = e.getActionCommand();
                switch (cmd) {

                    case "quit":


                        break;

                    case "roll":
                        game.nextTurn();
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }
}