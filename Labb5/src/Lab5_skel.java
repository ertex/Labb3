public class Lab5_skel {

  public static void main(String[] args) {
    // -- Del 1 --

    doCommandLine();
    //System.out.println(toRobber("Hej på dig"));
    //System.out.println(toPigLatin("Hej på dig"));


    // -- Del 2 --

    //rollADice();

    //letPlayerRoll();
    Dice d = new Dice(6);
    for(int i = 0; i <100; i++){
      System.out.print(d.roll());
    }
    //letTwoPlayersRollSameDice();
    //letPlayerUseDiceCup();
    //findPlayerWithMax();
  }

  // ---------- Del 1 ---------------

  // 1 and 4
  public static void doCommandLine() {
  }

  // 2
  public static String toRobber(String text) {
    return null; // Just for now
  }

  // 3
  public static boolean isVowel(char ch) {
    return false;  // Just for now
  }

  public static String toPigLatin(String text) {
    return null;// Just for now
  }


  // ---------- Del 2 ---------------

  // 5
  public static void rollADice(Dice dice) {
    for(int i = 0; i <100; i++){
      System.out.print(dice.roll();
    }
  }

  // 6
  public static void letPlayerRoll(Player1 player, int numRolls) {
    player.rollDice(numRolls);
  }

  // 7
  public static void letTwoPlayersRollSameDice(Dice dice, String name1, String name2) {
    Player1 player1 = new Player1(name1, dice);
    Player1 player2 = new Player1(name2, dice);
    letPlayerRoll(player1);
    letPlayerRoll(player2);
  }

  // 8
  public static void letPlayerUseDiceCup() {
  }

  // 9
  // Same as above but toString overridden.
  
  // 10
  public static void findPlayerWithMax() {
  }
}
