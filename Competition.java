import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds.
 * It also keeps track of the number of victories of each player.
 */
public class Competition {

    Player player1;
    Player player2;
    boolean displayMessage;
    int[] gameWins = new int[2];

    /**
     * The class constructor, which receives two Player objects
     * representing the two competing opponents and a flag indicating
     * whether game play messages should be printed to screen.
     */
    private Competition(Player player1, Player player2, boolean displayMessage) {
        this.player1 = player1;
        this.player2 = player2;
        this.displayMessage = displayMessage;
    }


    /*
     * Returns the integer representing the type of player 1; returns -1 on bad
     * input.
     */
    private static int parsePlayer1Type(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception E) {
            return -1;
        }
    }

    /*
     * Returns the integer representing the type of player 2; returns -1 on bad
     * input.
     */
    private static int parsePlayer2Type(String[] args) {
        try {
            return Integer.parseInt(args[1]);
        } catch (Exception E) {
            return -1;
        }
    }

    /*
     * Returns the integer representing the type of player 2; returns -1 on bad
     * input.
     */
    private static int parseNumberOfGames(String[] args) {
        try {
            return Integer.parseInt(args[2]);
        } catch (Exception E) {
            return -1;
        }
    }

    /**
     * The method runs a Nim competition between two players according to the three user-specified arguments.
     * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
     * player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
     * (2) The type of the second player, which is a positive integer between 1 and 4.
     * (3) The number of rounds to be played in the competition.
     *
     * @param args an array of string representations of the three input arguments, as detailed above.
     */

    /**
     * A helper function - plays one round of the nims games.
     */
    private void playOneRound() {
        Board board = new Board();
        if (displayMessage) {
            System.out.println("Welcome to the sticks game!");
        }
        Player currentPlayer = player1;
        while (board.getNumberOfUnmarkedSticks() > 0) {
            if (displayMessage) {
                System.out.println("Player" + " " + currentPlayer.getPlayerId() + ", it is now your turn!");
            }
            Move playerMove = currentPlayer.produceMove(board);
            while (board.markStickSequence(playerMove) != 0) {
                if (displayMessage) {
                    System.out.println("Invalid move. Enter another:");
                }
                playerMove = currentPlayer.produceMove(board);
            }
            if (displayMessage) {
                System.out.println("Player" + " " + currentPlayer.getPlayerId() + " " + "made the move: " + playerMove);
            }
            if (currentPlayer == player2) {
                currentPlayer = player1;
            } else {
                currentPlayer = player2;
            }
        }
            if (displayMessage) {
                System.out.println("Player" + " " + currentPlayer.getPlayerId() + " " + "won!");
            }
            if (currentPlayer == player1) {
                gameWins[0]++;
            } else {
                gameWins[1]++;
            }
    }

    /**
     * According the the amount of rounds, calculates the amount of wins each player has.
     * (This was implemented by initializing an array of integers where the 2 indexes
     * represent each player as their amount of wins.
     * @param playerPosition- position of player in the game.
     * @return player's score - according to position.

     */
    public int getPlayerScore(int playerPosition) {
        int player1Score = gameWins[0];
        int player2Score = gameWins[1];
        if (playerPosition == 1) {
            return player1Score;
        } else if (playerPosition == 2) {
            return player2Score;
        }
        return -1;
    }

    /**
     * Based on the amount of rounds given, this function takes the helper
     * function - play one round and applies it for that amount of times.
     * @param numberOfRounds- the amount of rounds the game will be played.
     */
    public void playMultipleRounds(int numberOfRounds) {
        String player1Type = player1.getTypeName();
        String player2Type = player2.getTypeName();
        System.out.println("Starting a Nim competition of" +" "+ numberOfRounds+ " " +
                "rounds between a"+ " " + player1Type + "player and a"+ " " + player2Type + "player.");
        for (int i = 1; i <= numberOfRounds; i++) {
            playOneRound();
        }

    }

    /**
     * The main initializes the games by taking the input arguments and applying it
     * to the games key functions.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int p1Type = parsePlayer1Type(args);
        int p2Type = parsePlayer2Type(args);
        int numGames = parseNumberOfGames(args);
        Player player1 = new Player(p1Type, 1, scanner);
        Player player2 = new Player(p2Type, 2, scanner);
        boolean displayMessage;
        if ((player1.getPlayerType() == Player.HUMAN) || (player2.getPlayerType() == Player.HUMAN)) {
            displayMessage = true;
        } else {
            displayMessage = false;
        }
        Competition competition = new Competition(player1, player2, displayMessage);
        competition.playMultipleRounds(numGames);
        System.out.println("The results are " + competition.getPlayerScore(1) + ":" + competition.getPlayerScore(2));
        scanner.close();

    }
}
