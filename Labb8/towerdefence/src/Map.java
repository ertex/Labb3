
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Map extends Sprite {
// Map and muminstd belongs to David Johansson Te2

    //Map is basicly a big container for things that will be doing stuff, like towers and enemies
    static ArrayList<Enemy> enemies; //Add Motivation here    maby make this static to make it easier for Tower
    private ArrayList<Tower> towers;
    private ArrayList<Integer> waypoints;
    private int collitionMap[][], wave;
    static final int tileSize = 50, mapSize = 9; //tilesize can be modified, but DONT TOUCH mapSize! it tells the size of collitionMap[][]
    static final int mapPixelSize = tileSize * mapSize;

    private BufferedImage bloodImg;

    private BufferedImage towerImg;
    private BufferedImage enemyImg;

    static boolean lost;

    public Map(BufferedImage img) {
        super(0, 0, img);

        try {
            bloodImg = ImageIO.read(new File("src\\Images\\Blood.png"));

            towerImg = ImageIO.read(new File("src\\Images\\My.png"));
            enemyImg = ImageIO.read(new File("src\\Images\\Muminpappa.png"));

        } catch (IOException e) {
            System.out.println("image not found");
        }

        wave = 1;
        enemies = new ArrayList();
        towers = new ArrayList();
        waypoints = new ArrayList();
        lost = false;
        generateMap();//Generates the map sepperatly

    }

    public void enemiesUpdate() {//updates enemies

        for (Enemy u : enemies) {

            if (u.isAlive()) {
                u.update(waypoints);
            }
        }

    }

    public void towersUpdate() {//updates towers

        for (Tower o : towers) {
            o.update(enemies);

        }
    }



    public void draw(Graphics2D g2d) {//draws the whole map
        super.draw(g2d);

        for (Enemy u : enemies) {
            u.draw(g2d);
        }
        for (Tower o : towers) {
            o.draw(g2d);
        }

    }

    public void generateMap() {
        collitionMap = new int[][]{
            {1, 1, 4, 0, 0, 5, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 1},
            {2, 0, 3, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 10, 11},
            {1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 7, 0, 0, 0, 6, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 8, 0, 0, 0, 0, 0, 9, 1}};
        //Numbers that == 1 wil e buildable tiles, where you can place towers
        //Numbers that =/= 1 will be unbuildable tiles
        //Numbers >1 will be waypoint for Enemy to follow (They follow a incremental pattern)
        for (int k = 0; k < 11; k++) { //The k < 11, 11 is the last waypoint of the collitionMap and k=2 is the first
            for (int i = 0; i < mapSize; i++) {
                for (int u = 0; u < mapSize; u++) {

                    if (collitionMap[i][u] == k + 2) {//COMMENT THIS WHEN TIME
                        waypoints.add(i * tileSize);
                        waypoints.add(u * tileSize);
                    }
                }
            }
        }
    }

    public void addTower(int x, int y) { //creates a new tower
                collitionMap[(int) y / tileSize][(int) x / tileSize] = 0;
                    towers.add(new Tower((int) (x / tileSize) * tileSize, (int) (y / tileSize) * tileSize, 5,5,50, towerImg));

        }
    public void addEnemyAtStart(){
    //The Y cordinates of waypoints are on the even numbers and X on odds
        enemies.add(new Enemy(waypoints.get(1),waypoints.get(0),50, enemyImg, bloodImg));
    }



//get clicked tower


}
