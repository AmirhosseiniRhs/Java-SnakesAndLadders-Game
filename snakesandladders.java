import java.util.Random;
import java.util.Scanner;


public class snakesandladders {


    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    private static final int[] LADDER_STARTS = {6, 12, 75, 51};
    private static final int[] LADDER_ENDS = {18, 46, 92, 67};

    private static final int[] SNAKE_STARTS = {98, 79, 54, 38};
    private static final int[] SNAKE_ENDS = {35, 42, 13, 2};


    public static void main(String[] args) {

        System.out.println("Welcome to Snakes and Ladders!");
        System.out.println("Player 1, enter your name:");
        String player1Name = scanner.nextLine();
        System.out.println("Player 2, enter your name:");
        String player2Name = scanner.nextLine();


        int player1Position = 0;
        int player2Position = 0;


        printBoardAndRules();

        System.out.println("\nYou win by landing on square 100.");
        System.out.println("When it's your turn, press R to roll the dice.");

        boolean isPlayer1Turn = true;


        while (player1Position < 100 && player2Position < 100) {


            String currentPlayerName;
            int currentPlayerPosition;
            int otherPlayerPosition;

            if (isPlayer1Turn) {
                currentPlayerName = player1Name;
                currentPlayerPosition = player1Position;
                otherPlayerPosition = player2Position;
            } else {
                currentPlayerName = player2Name;
                currentPlayerPosition = player2Position;
                otherPlayerPosition = player1Position;
            }

            String entry;
            while (true) {
                System.out.println("\nIt's " + currentPlayerName + "'s Turn.");
                System.out.println(currentPlayerName + ", Press R to roll the dice.");
                entry = scanner.nextLine();

                if (entry.equalsIgnoreCase("R")) {
                    break;
                } else {
                    System.out.println("Remember to press R to take your turn. Try again!");
                }
            }


            int diceRoll = rollDice();
            System.out.println(currentPlayerName + " rolled a " + diceRoll + ".");

            int potentialNewPosition = currentPlayerPosition + diceRoll;


            if (potentialNewPosition > 100) {
                System.out.println(currentPlayerName + "! You need to land exactly on 100.");
                System.out.println("You'll stay on square " + currentPlayerPosition + " for this turn.");
                potentialNewPosition = currentPlayerPosition;
            } else {
                System.out.println(currentPlayerName + " is moving from " + currentPlayerPosition + " to " + potentialNewPosition + ".");


                potentialNewPosition = applySnakeLadder(potentialNewPosition, currentPlayerName);
            }

            if (potentialNewPosition == otherPlayerPosition && potentialNewPosition != 0) {
                System.out.println("That means " + getOtherPlayerName(currentPlayerName, player1Name, player2Name) + " is pushed all the way back to the Starting point (square 0)!");
                if (isPlayer1Turn) {
                    player2Position = 0;
                } else {
                    player1Position = 0;
                }
            }


            if (isPlayer1Turn) {
                player1Position = potentialNewPosition;
            } else {
                player2Position = potentialNewPosition;
            }

            printCurrentPositions(player1Name, player1Position, player2Name, player2Position);

            if (player1Position == 100) {
                System.out.println("\n"+ player1Name + "has won the game.");
                break;
            } else if (player2Position == 100) {
                System.out.println("\n"+ player2Name + "has won the game.");
                break;
            }


            isPlayer1Turn = !isPlayer1Turn;
        }

        scanner.close();

    }


    public static int rollDice() {
        return random.nextInt(6) + 1;
    }


    public static int applySnakeLadder(int currentPosition, String playerName) {

        for (int i = 0; i < LADDER_STARTS.length; i++) {
            if (currentPosition == LADDER_STARTS[i]) {
                int newPosition = LADDER_ENDS[i];
                System.out.println(playerName + " has found a ladder! going up from " + currentPosition + " to " + newPosition + ".");
                return newPosition;
            }
        }


        for (int i = 0; i < SNAKE_STARTS.length; i++) {
            if (currentPosition == SNAKE_STARTS[i]) {
                int newPosition = SNAKE_ENDS[i];
                System.out.println(playerName + " is bitten by a snake! Sliding down from " + currentPosition + " to " + newPosition + ".");
                return newPosition;
            }
        }

        return currentPosition;
    }


    public static void printCurrentPositions(String p1Name, int p1Pos, String p2Name, int p2Pos) {
        System.out.println("\nCurrent Positions");
        System.out.print(p1Name + ": ");
        if (p1Pos == 0) {
            System.out.println("At the very beginning (Start)");
        } else {
            System.out.println("On square " + p1Pos);
        }

        System.out.print(p2Name + ": ");
        if (p2Pos == 0) {
            System.out.println("At the very beginning (Start)");
        } else {
            System.out.println("On square " + p2Pos);
        }
        System.out.println("\n");
    }


    public static String getOtherPlayerName(String currentPlayerName, String player1Name, String player2Name) {
        if (currentPlayerName.equals(player1Name)) {
            return player2Name;
        } else {
            return player1Name;
        }
    }




    public static void printBoardAndRules() {
        System.out.println("\n===============================");
        System.out.println(" The Snakes and Ladders Board  ");
        System.out.println("===============================");
        System.out.println("100 99 98 97 96 95 94 93 92 91");
        System.out.println("81 82 83 84 85 86 87 88 89 90");
        System.out.println("80 79 78 77 76 75 74 73 72 71");
        System.out.println("61 62 63 64 65 66 67 68 69 70");
        System.out.println("60 59 58 57 56 55 54 53 52 51");
        System.out.println("41 42 43 44 45 46 47 48 49 50");
        System.out.println("40 39 38 37 36 35 34 33 32 31");
        System.out.println("21 22 23 24 25 26 27 28 29 30");
        System.out.println("20 19 18 17 16 15 14 13 12 11");
        System.out.println("1 2 3 4 5 6 7 8 9 10");
        System.out.println("===============================");
        System.out.println("\nThe Ladders:");
        System.out.println("- From 6 to 18");
        System.out.println("- From 12 to 46");
        System.out.println("- From 75 to 92");
        System.out.println("- From 51 to 67");
        System.out.println("\nThe Snakes:");
        System.out.println("- From 98 to 35");
        System.out.println("- From 79 to 42");
        System.out.println("- From 54 to 13");
        System.out.println("- From 38 to 2");
        System.out.println("================");
    }
}
