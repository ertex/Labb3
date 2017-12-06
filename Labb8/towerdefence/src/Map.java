
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Map extends Sprite {


    //Map is basicly a big container for things that will be doing stuff, like towers and enemies
    static ArrayList<Enemy> enemies; //Add Motivation here    maby make this static to make it easier for Tower
    private ArrayList<Tower> towers;
    private ArrayList<Integer> waypoints;
    private int collitionMap[][], wave;
    static final int tileSize = 50, mapSize = 9; //tilesize can be modified, but DONT TOUCH mapSize! it tells the size of collitionMap[][]

    private BufferedImage bloodImg;

    private BufferedImage towerImg;
    private BufferedImage enemyImg;

    static boolean lost,won;

    public Map(BufferedImage img) {
        super(0, 0, img);

        try {
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
        won = false;
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



    public void draw(Graphics2D g2d) {//draws the whole map and everything inside it
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
            {2, 1, 5, 0, 0, 6, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 1},
            {3, 0, 4, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 11, 12},
            {1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 8, 0, 0, 0, 7, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 9, 0, 0, 0, 0, 0, 10, 1}};
        //Numbers that == 1 are buildable tiles, where you can place towers
        //Numbers that =/= 1 will be unbuildable tiles
        //Numbers >1 will be waypoint for Enemy to follow (They follow a incremental pattern)
        for (int k = 0; k < 11; k++) { //The k < 11, 11 is the last waypoint of the collitionMap and k=2 is the first
            for (int i = 0; i < mapSize; i++) {
                for (int u = 0; u < mapSize; u++) {

                    if (collitionMap[i][u] == k + 2) {
                        //adds the XY cordinates to the arraylist
                        //The Y cordinates of waypoints are on the even numbers and X on odds
                        //A better implementation would be to use Point to make it clearer when reading/coding
                        waypoints.add(i * tileSize);
                        waypoints.add(u * tileSize);
                    }
                }
            }
        }
    }

    public void addTower(int x, int y) { //creates a new tower, x,y is cordinates in the collitionMap
        if(collitionMap[x][y]==1) {
            collitionMap[y][x] = 0;
            towers.add(new Tower((int) x * tileSize, (int) y * tileSize, 20, 5, 150, towerImg));
        }
        else{
            System.out.println("You can not add a tower to X:"+x+" Y:"+y);
        }
        }

    public void addTower(int x, int y,int firerate,int damage,int range) {
        //creates a new tower, x,y is cordinates in the collitionMap, with added customizability
        if(collitionMap[x][y]==1) {
            collitionMap[y][x] = 0;
            towers.add(new Tower((int) x * tileSize, (int) y * tileSize, firerate, damage, range, towerImg));
        }
        else{
            System.out.println("You can not add a tower to X:"+x+" Y:"+y);
        }
    }

    public void addEnemyAtStart(){
    //The Y cordinates of waypoints are on the even numbers and X on odds
        enemies.add(new Enemy(waypoints.get(1),waypoints.get(0),200, enemyImg));
    }

}
