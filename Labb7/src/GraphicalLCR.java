import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * Created by alex on 2015-11-20.
 * Modified by David and Emelie 2017-11-20
 */
public class GraphicalLCR extends JFrame implements ActionListener {
    private JPanel rootPanel;
    private JButton rollButton;
    private JButton quitButton;
    private JPanel playerPanel;
    private JLabel resultLabel;
    private JPanel cmdPanel;
    private JPanel resultPanel;
    private LCR game;
    private ActionHandler actionHandler;
    private int xSize, ySize;


    public static void main(String[] args) {
        GraphicalLCR game = new GraphicalLCR(400, 400);

    }

    public GraphicalLCR(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        buildLCRGame(); //This needs to be before createAndShowGUI()
        actionHandler = new ActionHandler();
        createAndShowGUI();
        run();
    }

    public void run() {


    }

    private void buildLCRGame() {
        // Set up number of players and player names
        int numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players"));
        System.out.println("Enter number of players");
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {

            players[i] = new Player(JOptionPane.showInputDialog("Name of player " + (i + 1) + " :"));
        }

        //Create game dice
        Dice dice = new Dice();

        //Create new game
        this.game = new LCR(players, dice);
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
        cmdPanel = new JPanel();
        resultPanel = new JPanel();
        //adds the main sub framess to the main frame
        frame.add(playerPanel);
        frame.add(resultPanel);
        frame.add(cmdPanel);

        for (Player p : game.getPlayers()) { //adds all of the players panels to the main subframe playerpanel
            playerPanel.add(p.getPanel());
        }

        //The resultlabel is recived from game
        resultPanel.add(game.getResultLabel());

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


    public void actionPerformed(ActionEvent e) {
    }

    private class ActionHandler implements ActionListener
            //this listens if a action is performed and exceutes the linked action
    {

        public void actionPerformed(ActionEvent e) {

            try {

                String cmd = e.getActionCommand();
                switch (cmd) {

                    case "quit":
                        game.running = false;
                        break;

                    case "roll":
                        if (game.running) {
                            game.nextTurn();
                        }
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }
}