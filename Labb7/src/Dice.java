import java.util.Random;

public class Dice {

    public Dice(){

    }

    public char roll(){
        Random random = new Random();
        int roll = random.nextInt(5);
        if(roll == 1){
            return 'L';
        }else if(roll == 2){
            return 'C';
        }else if(roll == 3){
            return 'R';
        }else{
            return '.';
        }
    }
}
