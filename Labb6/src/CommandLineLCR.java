import java.util.Scanner;

/**
 * This class is a skeleton, don't change the over all
 * structure just uncomment and add code where needed (TODOs)
 */
public class CommandLineLCR {
  private LCR game;
  private Scanner s = new Scanner(System.in);

  public CommandLineLCR(){}

  public static void main(String[] args) {
    buildLCRGame();


  }

  public void run() {
    boolean done = false;
    // ... = buildLCRGame();
    System.out.println("LCR started");
    System.out.print("Players are ");
    Scanner s = new Scanner(System.in);
    //render( ... );
    while (!done) {
      //System.out.println("Player is " + ...);
      System.out.print("> ");
      String cmd = s.nextLine();
      switch (cmd) {
        case "r":
          // What to do here?
          break;
        case "q":
          done = true;
          break;
        default:
          System.out.println("?");
      }
    }

        /* TODO
        if ( ... ) {
            System.out.println("Game over! Winner is " + lcr.getWinner());
        } else {
            render(lcr);
            System.out.println("Game aborted");
        }*/
  }

    public void buildLCRGame() {


    // Set up number of players and player names
      System.out.println("Enter number of players");
        int numbPlayers = s.nextInt();
        Player[] players = new Player[numbPlayers];
        for(int i = 0; i < numbPlayers; i++){
          System.out.println("Name of player " + i+1 + ": ");
          players[i] = new Player(s.next());
        }

        //Create game dice
      Dice dice = new Dice();

        //Create new game
      this.game = new LCR(players, dice);
      return new CommandLineLCR();
    }


    private void render() {
        // This needs overridden toString method to work!
      /*
        for (String s : LCR.getResult()) {
            System.out.print(s + "  ");
        }
        System.out.println();
        for (Player p : lcr.getPlayers()) {
            System.out.print(p + " ");
        }
        System.out.println();
        */
      System.out.println("" + game.toString);
    }

}
