public class Player1 {

    private String name;
    private Dice dice;

    public Player1(String name, Dice dice){
        this.dice = dice;
        this.name = name;
    }

    public void rollDice(int numRolls){
        System.out.println("Player is " + name);
        for(int i = 0; i < numRolls; i++){
            System.out.print(dice.roll());
        }
    }
}
