import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int chips = 3;
    private JPanel panel;
    private JLabel points;

    public Player (String name){
        this.name = name;
        panel  = new JPanel();
    }

    public int getNRolls(){
        return chips;
    }

    public void takeChip(){
        chips--;
    }

    public void giveChip(){
        chips++;
    }

    public JPanel getPanel(){
        //Creates a Panel containing all relevant information
        panel.setLayout(new GridLayout(0,1));
        panel.setBackground(Color.WHITE);
        panel.setSize(40,40);
        panel.setName(this.name);//so that it will be able to be identified later on
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JLabel name   = new JLabel(this.name);
        points = new JLabel("Points : " + this.chips);

        panel.add(name);
        panel.add(points);
        return panel;

    }

    public void setColor(Color C){
        panel.setBackground(C);

    }

    public void updatePointsLabel(){

         points.setText("Points : " + this.chips);
    }

    public String toString() {
        return "{" + name + "," + chips + "}";
    }
}
