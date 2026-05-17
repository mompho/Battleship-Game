package battleshipGame;
import java.util.Scanner;

public class Battleship
{
    public static void main(String[] args)
    {
        printWelcomeBanner();

        // Outer loop: restarting the game brings us back here
        // QuitGameException breaks out of it entirely
        while (true)
        {
            try
            {
                runGame();

                // Game ended naturally (win or loss) — ask to play again
                if (!askPlayAgain())
                    break;
            }
            catch (RestartGameException e)
            {
                System.out.println("\n==========================================");
                System.out.println("  RESTARTING GAME...");
                System.out.println("==========================================\n");
                // Loop continues - fresh Player and Computer are created in runGame()
            }
            catch (QuitGameException e)
            {
                // Player typed "quit" somewhere — exit the outer loop
                break;
            }
            catch (Exception e)
            {
                // Safety net for any unexpected error — print and restart cleanly
                System.out.println("\n  Unexpected error: " + e.getMessage());
                System.out.println("  Restarting the game...\n");
            }
        }

        System.out.println("\n==========================================");
        System.out.println("  Thanks for playing Battleship! Goodbye.");
        System.out.println("==========================================\n");
    }

    // One full game from ship placement to win/loss 
    // Throws RestartGameException or QuitGameException if player requests 
    private static void runGame()
    {
        Player   p = new Player();
        Computer c = new Computer();

        p.PlayerSetUp();
        c.compSetUp();

        int round = 1;

        while (!p.verifyPlayerWin(c) && !c.verifyCompW(p))
        {
            System.out.println("\n##########################################");
            System.out.println("  ROUND " + round);
            System.out.println("##########################################\n");

            // Player's turn
            System.out.println(">>> YOUR TURN <<<");
            Coordinate attackOnComputer = p.playerGuessAttack();
            p.fireAndAttackComp(c, attackOnComputer);

            if (p.verifyPlayerWin(c)) break;

            // Computer's turn
            System.out.println("\n>>> COMPUTER'S TURN <<<");
            Coordinate attackOnPlayer = c.getPredictedCoord(p);
            System.out.println("  Computer attacks " + attackOnPlayer + "...");
            c.fireAndAttackPlayer(p, attackOnPlayer);

            // Show boards
            System.out.println("\n--- YOUR BOARD (Board A: your ships) ---");
            p.printBoard(p.getBoardA());

            System.out.println("\n--- YOUR ATTACK BOARD (Board B: hits on enemy) ---");
            p.printBoard(p.getBoardB());

            round++;
        }

        // Print outcome
        System.out.println("\n==========================================");
        if (p.verifyPlayerWin(c))
            System.out.println("  GAME OVER! CONGRATULATIONS, YOU WON!");
        else
            System.out.println("  GAME OVER! YOU LOST. BETTER LUCK NEXT TIME!");
        System.out.println("==========================================\n");
    }

    // Ask the player whether they want to play again after a natural end
    // Returns true = play again, false = quit.
    // Also listens for "quit" and "restart" commands

    private static boolean askPlayAgain()
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Would you like to play again? (yes / no): ");

        while (true)
        {
            try
            {
                String input = scn.next().trim().toLowerCase();

                if (input.equals("quit"))    throw new QuitGameException();
                if (input.equals("restart")) throw new RestartGameException();
                if (input.equals("yes") || input.equals("y")) return true;
                if (input.equals("no")  || input.equals("n")) return false;

                System.out.println("  Please type 'yes' or 'no': ");
            }
            catch (QuitGameException | RestartGameException e)
            {
                throw e;
            }
            catch (Exception e)
            {
                System.out.println("  Invalid input. Please type 'yes' or 'no': ");
                scn.nextLine();
            }
        }
    }

    private static void printWelcomeBanner()
    {
        System.out.println("==========================================");
        System.out.println("  WELCOME TO BATTLESHIP!");
        System.out.println("  You are playing against the computer.");
        System.out.println("  Type 'quit' to exit or 'restart' to");
        System.out.println("  start over at ANY input prompt.");
        System.out.println("==========================================\n");
    }
}
