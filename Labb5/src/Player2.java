public class Player2 {

    private String name;
    private DiceCup diceCup;
    private int sum;

    public Player2(String name, DiceCup diceCup){
        this.diceCup = diceCup;
        this.name = name;
        sum = 0;
    }

    public void player2roll(){
        sum += diceCup.rollAllDice();
    }

    public String getName(){
        return name;
    }

    public int accumulatedSum (){
        return sum;
    }
}
