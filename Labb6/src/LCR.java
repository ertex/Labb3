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
        int rolls = players[currentPlayer].getNRolls();
        for(int i = 0; i < rolls; i++){
            char roll = dice.roll();
            System.out.print(roll + " ");
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
        System.out.println();
        lastPlayer = currentPlayer;
        currentPlayer = nextPlayer;
        if(nextPlayer == players.length-1){
            nextPlayer = 0;
        }else{
            nextPlayer ++;
        }
    }

    public String getCurrentPlayer(){
        return players[currentPlayer].toString();
    }

    public String getPlayer(int pos){
        return players[pos].toString();
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
            return win;
        }else if(playersWithChips<1){
            System.out.println("everybody loses!");
            return win;
        }else{
            return -1;
        }
    }

    public String toString(){
        String string = "";
        for(int i = 0;i < players.length; i++ ){
            string += players[i].toString();
        }
        return string;
    }
}
