public class DiceCup {

    private Dice[] dices = new Dice[5];

    public DiceCup(Dice[] dices){
        this.dices = dices;
    }

    public int rollAllDice(){
        int sum = 0;
        for(int i = 0; i <5; i++){
            dices[i].roll();
            sum += dices[i].getLastRoll();
        }
        return sum;
    }
}
