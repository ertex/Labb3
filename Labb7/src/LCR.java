import javax.swing.*;
import java.awt.*;

public class LCR {
    private Player[] players;
    private Dice dice;
    private int lastPlayer;
    private int currentPlayer = 0;
    private int nextPlayer = 1;

    private JLabel resultLabel;
    public boolean running;


    public LCR(Player[] players, Dice dice){
        this.dice = dice;
        this.players = players;
        this.lastPlayer = players.length-1;
        resultLabel = new JLabel();
        players[currentPlayer].setColor(Color.GREEN);
        running = true;
    }

    public void nextTurn(){
        int rolls = players[currentPlayer].getNRolls();
        resultLabel.setText("");//clears the label
        for(int i = 0; i < rolls; i++){
            char roll = dice.roll();
            resultLabel.setText(resultLabel.getText()+" "+roll);
            if(roll == 'L'){
                players[currentPlayer].takeChip();
                players[nextPlayer].giveChip();
                players[nextPlayer].updatePointsLabel();
            }else if(roll == 'C'){
                players[currentPlayer].takeChip();
            }else if(roll == 'R'){
                players[currentPlayer].takeChip();
                players[lastPlayer].giveChip();
                players[lastPlayer].updatePointsLabel();
            }
            players[currentPlayer].updatePointsLabel();
        }
        System.out.println();
        lastPlayer = currentPlayer;
        currentPlayer = nextPlayer;
        if(nextPlayer == players.length-1){
            nextPlayer = 0;
        }else{
            nextPlayer ++;
        }
        players[lastPlayer].setColor(Color.WHITE);
        players[currentPlayer].setColor(Color.GREEN);
        checkWin();
    }

    public String getCurrentPlayer(){
        return players[currentPlayer].toString();
    }

    public Player getPlayer(int pos){
        return players[pos];
    }

    public Player[] getPlayers() {
        return players;
    }

    public int checkWin(){
        int playersWithChips = 0;
        int win = 0;

        for(int i = 0; i < players.length; i++){
            if(players[i].getNRolls() > 0){
                playersWithChips++;
                win = 1;
            }

        }

        if(playersWithChips == 1){
            running = false;
            return win;

        }else{

            return -1;
        }
    }

    public JLabel getResultLabel(){
        return resultLabel;
    }

    public String toString(){
        String string = "";
        for(int i = 0;i < players.length; i++ ){
            string += players[i].toString();
        }
        return string;
    }
}
