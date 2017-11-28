public class Player {
    private String name;
    private int chips = 3;

    public Player (String name){
        this.name = name;
    }

    public int getNRolls(){
        if(chips<1){
            return 0;
        }
        if(chips>3){
            return 3;
        }
        return chips;
    }

    public void takeChip(){
        if(chips>0){
            chips--;
        }
    }

    public void giveChip(){

        chips++;
    }

    public int getChips(){
        return chips;

    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "{" + name + "," + chips + "}";
    }
}
