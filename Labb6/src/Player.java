public class Player {
    private String name;
    private int chips = 3;

    public Player (String name){
        this.name = name;
    }

    public int getNRolls{
        return chips;
    }

    public void takeChip(){
        chips--;
    }

    public void giveChip(){
        chips++;
    }

    public String toString() {
        return "{" + name + "," + chips + "}";
    }
}
