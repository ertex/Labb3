import java.util.Random;

public class Dice {

    private int sides;
    private int lastRoll;
    private Random random = new Random();

    public Dice(int numsides){
        sides = numsides;
        lastRoll = 0;
    }

    public int roll(){
        int roll = random.nextInt(sides) + 1;
        lastRoll = roll;
        return roll;
    }

    public int getLastRoll(){ // om du inte rullat något returnerar den 0
        return lastRoll;
    }

    public String toString(){
        return ("Dice{result =" + lastRoll + "}");
    }
}
