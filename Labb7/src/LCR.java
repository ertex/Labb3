public class LCR {
    private Player[] players;
    private Dice dice;
    private int lastPlayer;
    private int currentPlayer = 0;
    private int nextPlayer = 1;
    private String lastResults;
    private Dice die[] = new Dice[3];

    public LCR(Player[] players){
        for(int i = 0;i<3;i++){
            die[i] =new Dice(6);
        }
        this.players = players;
        this.lastPlayer = players.length-1;
    }
    //This method is a bit different from Labb6, due to recived feedback,but it has the same function
    public void nextTurn(){
        String rolls = rollNDice(players[currentPlayer].getNRolls());
        for(int i =0; i< rolls.getBytes().length;i++) {
            char roll = rolls.charAt(i);
            if (roll == 'L') {
                players[currentPlayer].takeChip();
                players[nextPlayer].giveChip();
            } else if (roll == 'C') {
                players[currentPlayer].takeChip();
            } else if (roll == 'R') {
                players[currentPlayer].takeChip();
                players[lastPlayer].giveChip();
            }
        }
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

    public String rollNDice(int n){
        String s = "";
        for(int i=0;i<n;i++){
            s=s+ die[i].roll();

        }
        lastResults = s;
return s;
    }


    public String getLastResults(){
        return lastResults;

    }
    public String getPlayer(int pos){
        return players[pos].toString();
    }
    public Player[] getPlayers(){
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
