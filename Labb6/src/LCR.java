public class LCR {
    private Player[] players;
    private Dice dice;
    private int lastPlayer;
    private int currentPlayer = 0;
    private int nextPlayer = 1;

    public LCR(Player[] players, Dice dice){
        this.dice = dice;
        this.players = players;
        this.lastPlayer = players.length-1;
    }

    public void nextTurn(){
        for(int i = 0; i < players[currentPlayer].getNRolls; i++){
            char roll = dice.roll();
            if(roll == 'L'){
                players[currentPlayer].takeChip();
                players[nextPlayer].giveChip();
            }else if(roll == 'C'){
                players[currentPlayer].takeChip();
            }else if(roll == 'R'){
                players[currentPlayer].takeChip();
                players[lastPlayer].giveChip();
            }
        }
        lastPlayer = currentPlayer;
        currentPlayer = nextPlayer;
        if(nextPlayer == players.length-1){
            nextPlayer = 0;
        }else{
            nextPlayer += 1;
        }
    }

    public boolean checkWin(){
        int playersWithChips = 0;

        for(int i = 0; i < players.length-1; i++){
            if(players[i].getNRolls > 0)
                playersWithChips++;
        }

        if(playersWithChips == 1){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        String string = "";
        for(int i = 0;i < players.length-1; i++ ){
            string += players[i].toString();
        }
        return string;
    }
}
