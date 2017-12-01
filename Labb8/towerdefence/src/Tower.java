import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

public class Tower extends Sprite {

    private int firerate;
    private int damage;
    private int range;
    private int lineDelay;
    private int firedelay;
    private String sound;
    private Enemy target;
    private boolean canFire;


    public Tower(int x, int y,int firerate,int damage,int range, BufferedImage img) {
        super(x, y, img); //  could not use "-img.getHeight()" in order to fix issues with location issues, so I did some hardcoding

        this.sound = sound;
        this.damage = damage;
        this.range = range;
        lineDelay = 0;
        this.firerate = firerate;
        firedelay=0;
      }

    public void update(ArrayList<Enemy> enemies) {

        if (canFire) {

            for (Enemy u : enemies) {
                if (u.isAlive()) {
                    if (checkInRange(u)) { //checks if targeted enemy is in range checkInRange returns a boolean, the first enemy will be targeted and fired at
                        shoot(target); //You can never guess what this does...
                        canFire = false; // if it was in range, a shot was fired and the tower can't shoot until it's cooled down
                        firedelay = firerate;
                        break;
                    }
                }

            }
        }

        if (firedelay < 1) { //cools the tower down and allows it to shoot again
            canFire = true;
        } else {
            firedelay--;
        }

    }

    public boolean checkInRange(Enemy target) {

        if (sqrt(Math.pow((((x + width / 2) - (target.getX() + target.width / 2))), 2) + Math.pow((((y + height / 2) - (target.getY() + target.height / 2))), 2)) < range) { //does some math magic with distance, but Fredrik said "matte är min kryptonit" so this is not very detailed, sorry.

            this.target = target; //Saves target so it can be fetched from other methods
            return true;

        } else {
            return false;
        }

    }

    public void shoot(Enemy enemy) {
        enemy.getDamaged(damage);
        //GameManager.playSound(sound); //-----------

        lineDelay = 5; //The amount of time the line will stay up

    }

    public void draw(Graphics g2d) {

        g2d.drawRect(x, y, width, height);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.black);
        super.draw(g2d);

        if (target != null) {
            if (checkInRange(target)) {
                if (lineDelay > 1) { //makes the bulletline stay a while so it's visible

                    g2d.drawLine(this.getX() + width / 2, this.getY() + height / 2, target.getX() + target.getWidth() / 2, target.getY() + target.getHeight() / 2); // this.x / y is protected and in sprite
                    //"bULLET"
                }
            }
        }
        lineDelay--;

    }


    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return the firerate
     */
    public int getFirerate() {
        return firerate;
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @return the firedelay
     */
    public int getFiredelay() {
        return firedelay;
    }


}
