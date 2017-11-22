import java.util.Random;

public class Dice {
    private int faces;
    private char lastResult;

    public Dice(int faces){
        this.faces = faces;
    }

    public char roll(){
        Random random = new Random();
        int roll = random.nextInt(faces-1);
        if(roll == 0){
            lastResult = 'L';
            return 'L';
        }else if(roll == 1){
            lastResult = 'C';
            return 'C';
        }else if(roll == 2){
            lastResult = 'R';
            return 'R';
        }else{
            lastResult = '.';
            return '.';
        }
    }
    public char getLastResult(){
        return lastResult;
    }

}
