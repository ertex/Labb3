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
    /* Dice dice = new Dice();
    System.out.println(dice.roll());
    Player Em = new Player("Emi");
    System.out.println(Em.getNRolls());
    Em.giveChip();
    Em.giveChip();
    System.out.println(Em.getNRolls());
    Em.takeChip();
    System.out.println(Em.getNRolls());
    */

    new CommandLineLCR().run();
  }

  public void run() {
    boolean done = false;
    buildLCRGame();
    System.out.println("LCR started");
    System.out.print("Players are ");
    Scanner s = new Scanner(System.in);
    render();
    while (!done) {
      render();
      String cmd = s.next();
      switch (cmd) {
        case "r":
          game.nextTurn();
          break;
        case "q":
          System.out.printf(game.toString() + "\nGame aborted");
          done = true;
          break;
        default:
          System.out.println("?");
      }
      if(game.checkWin()>= 0){
        System.out.println("Game over! Winner is " + game.getPlayer(game.checkWin()));
        done = true;
      }
    }

  }

    public CommandLineLCR buildLCRGame() {


    // Set up number of players and player names
      System.out.println("Enter number of players");
        int numbPlayers = s.nextInt();
        Player[] players = new Player[numbPlayers];
        for(int i = 0; i < numbPlayers; i++){
          System.out.println("Name of player " + (i+1) + ": ");
          players[i] = new Player(s.next());
        }

        //Create game dice
      Dice dice = new Dice(6);

        //Create new game
      this.game = new LCR(players, dice);
      return new CommandLineLCR();
    }

    private void render() {
      System.out.println(game.toString());
      System.out.println("Current player is " + game.getCurrentPlayer());
      System.out.print("> ");
    }

}
