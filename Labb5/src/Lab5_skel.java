import java.util.Scanner;

public class Lab5_skel {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // -- Del 1 --

        doCommandLine();
        //System.out.println(toRobber("Hej på dig"));
        //System.out.println(toPigLatin("Hej på dig"));


        // -- Del 2 --

        //rollADice();
        //letPlayerRoll();
        //letTwoPlayersRollSameDice();
        //letPlayerUseDiceCup();
        //findPlayerWithMax();
    }

    // ---------- Del 1 ---------------

    // 1 and 4
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
                    System.out.println(lastReceived);
                    break;

                case "q":
                    looping = false;
                    break;
            }

        } while (looping);
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
    public static void rollADice() {
    }

    // 6
    public static void letPlayerRoll() {
    }

    // 7
    public static void letTwoPlayersRollSameDice() {
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
