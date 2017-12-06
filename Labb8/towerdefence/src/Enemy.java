
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Sprite {


    private int hp, maxHp, speed, moveDelay, nextY, nextX, tileSize;
    private boolean alive, canMove;
    private String deathZero, deathOne, deathTwo;
    private int nextWaypoint;
    private Random rnd;

    public Enemy(int x, int y,int hp, BufferedImage img) {
        super(x, y, img); //Img gets declared further down, this is ugly I know


        this.deathZero = deathZero;
        this.deathOne = deathOne;
        this.deathTwo = deathTwo;

        tileSize = Map.tileSize;
        rnd = new Random();
        this.hp = hp;
        maxHp = hp;
        speed = 2;

        alive = true;
        nextWaypoint = 0;

    }

    public void draw(Graphics2D g2d) {
        if (alive) {
            super.draw(g2d);
            g2d.setColor(Color.RED);//The following 4 lines is a hp-bar for the enemy
            g2d.fillRect(x, y + 45, 50, 5);//background color
            g2d.setColor(Color.GREEN);
            g2d.fillRect(x, y + 45, (hp * 50 / maxHp), 5);
            g2d.setColor(Color.BLACK);
        }

    }

    public void update(ArrayList<Integer> waypoints) {

        if (hp <= 0 && alive == true) {
            alive = false;
            Map.won=true; //This needs to be changed if used in a future implementation
        }

        if (alive) {
            if (waypoints.size() == nextWaypoint) {

                Map.lost = true;

            }

            if (canMove) {
                move();//-----------------------------------
                canMove = false; //This is to regulte speed, see movedelay and speed
                if (x < nextX + tileSize / 2 & x > nextX - tileSize / 2 & y < nextY + tileSize / 2 & y > nextY - tileSize / 2) {

                    checkNextWaypoint(waypoints);
                    nextWaypoint += 2; //Moves 2 steps due to the waypoints is stacked in a one dimentional array on odds and evens
                }
                moveDelay = speed;
            }

            if (moveDelay < 1) { //This is to regulate how often enemy can move
                canMove = true;
            } else {
                moveDelay--;
            }
        }
    }

    public void move() {//This homes the Enemy to the Next X/Y value

        if (nextY > y) {
            y += 4;

        }
        if (nextY < y) {
            y -= 4;

        }
        if (nextX > x) {
            x += 4;

        }
        if (nextX < x) {
            x -= 4;

        }

    }

    public void checkNextWaypoint(ArrayList<Integer> waypoints) {//This looks in the the waypoints and finds the next appropriate waypoint

        nextY = waypoints.get(nextWaypoint); //The Y cordinates are on the even numbers and X on odds
        nextX = waypoints.get(nextWaypoint + 1);

    }

    public void getDamaged(int hp) {
        this.hp = this.hp - hp;
        if (hp < 1) {
            alive = false;
        }

    }

    public boolean isAlive() {
        return alive;
    }


    public int gethp() {
        return hp;
    }


}
