import java.util.Scanner;

public class Lab5_skel {

    public static void main(String[] args) {
        // -- Del 1 --

        //doCommandLine();
        //System.out.println(toRobber("Hej på dig"));
        //System.out.println(toPigLatin("Hej på dig"));


        // -- Del 2 --
        //rollADice();
        Dice dice = new Dice(6);

        for (int i = 0; i < 100; i++) {
            System.out.print(rollADice(dice));
        }

        //letPlayerRoll();
        Player1 pelle = new Player1("Pelle", dice);

        letPlayerRoll(pelle, 5);

        //letTwoPlayersRollSameDice();
        letTwoPlayersRollSameDice(dice, "Anna", "Bella", 5);

        //letPlayerUseDiceCup();
        Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            Dice d = new Dice(6);
            dices[i] = d;
        }

        DiceCup dicecup = new DiceCup(dices);
        Player2 laurencia = new Player2("Laurencia", dicecup);
        letPlayerUseDiceCup(laurencia);
        System.out.println();
        System.out.println(dicecup.toString());
        System.out.println("Laurencia: " + laurencia.accumulatedSum());
        letPlayerUseDiceCup(laurencia);
        System.out.println(dicecup.toString());
        System.out.println("Laurencia: " + laurencia.accumulatedSum());


        //findPlayerWithMax();
        Player2[] players = new Player2[5];
        for(int i = 0; i <5; i++){
            players[i] = new Player2("" + i,dicecup);
            letPlayerUseDiceCup(players[i]);
            System.out.println(dicecup.toString());
        }
        for(int i = 0; i <5; i++){
            System.out.println(players[i].getName() + ":" + players[i].accumulatedSum());
        }
        int winner = findPlayerWithMax(players);
        System.out.println("Max: " + players[winner].getName() + ":" + players[winner].accumulatedSum());
        System.out.print("\t(spelare " + players[winner].getName() + " vann med " + players[winner].accumulatedSum() + " poäng)");
    }

    // ---------- Del 1 ---------------

    // 1 and 4
    static Scanner scanner = new Scanner(System.in);

    public static void doCommandLine() {
        String lastReceived;
        boolean looping = true;
        do {
            lastReceived = scanner.next();
            switch (lastReceived) {
                case "p":
                    System.out.println("Input text");
                    lastReceived = scanner.next();
                    System.out.println(lastReceived);
                    break;
                case "r":
                    System.out.println("Input text");
                    lastReceived = scanner.next();
                    // System.out.println(lastReceived);
                    toRobber(lastReceived);
                    break;

                case "q":
                    looping = false;
                    break;
            }

        } while (looping);
    }

    // 2
    public static String toRobber(String text) {
        for (int i : text.getBytes()) {
            char ch = text.charAt(i);
            if (isVowel(ch)) { //if the char at index i is a wovel, print the char
                System.out.print(ch);
            } else {        //else if if if its !vowel (konsonant?) surround a "o" with the char
                System.out.print(ch + "o" + ch);
            }

        }


        return null; // Just for now
    }

    // 3
    public static boolean isVowel(char ch) {


        return "AEIOUÅÄÖaeiouåäö".indexOf(ch) != -1;
    }

    public static String toPigLatin(String text) {
        return null;// Just for now
    }

    // ---------- Del 2 ---------------

    // 5
    public static int rollADice(Dice dice) {
        int num = dice.roll();
        return num;
    }

    // 6
    public static void letPlayerRoll(Player1 player, int numRolls) {
        player.rollDice(numRolls);
    }

    // 7
    public static void letTwoPlayersRollSameDice(Dice dice, String name1, String name2, int numRolls) {
        Player1 player1 = new Player1(name1, dice);
        Player1 player2 = new Player1(name2, dice);
        letPlayerRoll(player1, numRolls);
        letPlayerRoll(player2, numRolls);
    }

    // 8
    public static void letPlayerUseDiceCup(Player2 player) {
        player.player2roll();
    }

    //10
    public static int findPlayerWithMax(Player2[] players) {
        int player = 0;
        int value = players[0].accumulatedSum();
        for(int i = 1; i < players.length; i++){
            if(players[i].accumulatedSum()> value){
                value = players[i].accumulatedSum();
                player = i;
            }
        }
        return player;

    }
}