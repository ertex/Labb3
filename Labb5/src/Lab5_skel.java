import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab5_skel {

    public static void main(String[] args) {
        // -- Del 1 --

        doCommandLine();
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
        for (int i = 0; i < 5; i++) {
            players[i] = new Player2("" + i, dicecup);
            letPlayerUseDiceCup(players[i]);
            System.out.println(dicecup.toString());
        }
        for (int i = 0; i < 5; i++) {
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
            lastReceived = scanner.nextLine();

            switch (lastReceived) {
                case "p":
                    System.out.println("Input text");
                    lastReceived = scanner.nextLine();
                    System.out.println(toPigLatin(lastReceived));
                    break;
                case "r":
                    System.out.println("Input text");
                    lastReceived = scanner.nextLine();

                    System.out.println(toRobber(lastReceived));
                    break;

                case "q":
                    looping = false;
                    break;
            }

        } while (looping);
    }

    // 2
    public static String toRobber(String text) {
        ArrayList<Byte> newString = new ArrayList();
        // byte[] newString = new byte[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);//no matter what outcome isvowel() will have ch is allways added
            newString.add((byte) ch);
            if (!isVowel(ch)) { //if the character is !vowel a "RobberTwist" will be added to the char
                newString.add((byte) ("o".charAt(0)));
                newString.add((byte) ch);
            }
        }
        byte[] byteArray = new byte[newString.size()];
        for (int i = 0; i < newString.size(); i++) {
            byteArray[i] = newString.get(i);
        }
        return new String(byteArray);
    }

    // 3

    public static boolean isVowel(char ch) {
        return "AOUÅEIYÄÖaouåeiyäö".indexOf(ch) != -1;
    }

    public static String toPigLatin(String text) {
        String[] words = text.split(" ");
        String[] pigLatinWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
              String word = words[i];
            if (isVowel(word.charAt(0))) {
                pigLatinWords[i] = word + "way"; //the first letter is a vowel, just add -way
            } else {
                int n = word.length();
                do {
                      // this loop moves the enire array(String) one char to the left looping back to the end
                    //until the first letter is an vowel

                    char[] newString = new char[n];
                    newString[n - 1] = word.charAt(0);

                    for (int j = 0; j < n - 1; j++) {
                        newString[j] = word.charAt(j + 1);
                    }
                    word = new String(newString); //Note original data is not overwritten since word in not part of words[]
                } while (!isVowel(word.charAt(0)));
                pigLatinWords[i] = word + "ay";//adds -ay after the entire word
            }

        }


        return String.join(" ", pigLatinWords);


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
        for (int i = 1; i < players.length; i++) {
            if (players[i].accumulatedSum() > value) {
                value = players[i].accumulatedSum();
                player = i;
            }
        }
        return player;

    }
}