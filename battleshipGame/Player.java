package battleshipGame;
import java.util.Scanner;

public class Player
{
    Boards bds = new Boards();
    Scanner scn = new Scanner(System.in);

    public Player()
    {
        bds.initBoardA();
        bds.initBoardB();
    }

    // Prints the quit/restart reminder shown at every prompt
    private void printMenuHint()
    {
        System.out.println("  (Type 'quit' to exit or 'restart' to start over at any time)");
    }

    // Central command checker — called on every raw string the player types.
    // Throws QuitGameException or RestartGameException if recognised.
    private void checkForCommand(String input)
    {
        String trimmed = input.trim().toLowerCase();
        if (trimmed.equals("quit"))    throw new QuitGameException();
        if (trimmed.equals("restart")) throw new RestartGameException();
    }
    // Helper: read a valid direction character ('u','d','r','l')
    // Supports "quit" and "restart" at any point.
    private char readDirection()
    {
        while (true)
        {
            try
            {
                String raw = scn.next();
                checkForCommand(raw);                        // quit / restart check
                char dir = raw.toLowerCase().charAt(0);
                if (dir == 'u' || dir == 'd' || dir == 'r' || dir == 'l')
                    return dir;
                System.out.println("  Invalid direction! Please enter 'u', 'd', 'r' or 'l'.");
                printMenuHint();
            }
            catch (QuitGameException | RestartGameException e)
            {
                throw e;   // let these propagate up unchanged
            }
            catch (Exception e)
            {
                System.out.println("  Invalid input. Please enter 'u', 'd', 'r' or 'l'.");
                printMenuHint();
                scn.nextLine();
            }
        }
    }

    // Helper: read a valid integer coordinate (0–9).
    // Supports "quit" and "restart" at any point.
    private int readCoordinate(String axis)
    {
        while (true)
        {
            try
            {
                // Peek as a string first so we can detect commands
                String raw = scn.next();
                checkForCommand(raw);                        // quit / restart check

                int val = Integer.parseInt(raw);
                if (val >= 0 && val <= 9)
                    return val;
                System.out.println("  Out of range! " + axis + "-coordinate must be 0–9. Try again: ");
                printMenuHint();
            }
            catch (QuitGameException | RestartGameException e)
            {
                throw e;   // propagate unchanged
            }
            catch (NumberFormatException e)
            {
                System.out.println("  Invalid input! Please enter a whole number 0–9 for the " + axis + "-coordinate.");
                printMenuHint();
            }
            catch (Exception e)
            {
                System.out.println("  Unexpected error reading input. Please try again.");
                printMenuHint();
                scn.nextLine();
            }
        }
    }
    // Helper: place one ship with full direction + coordinate validation
    private void placeOneShip(String name, char type)
    {
        System.out.println("\nPlacing your " + name + "...");
        printMenuHint();

        while (true)
        {
            System.out.println("Enter direction ('u' up, 'd' down, 'r' right, 'l' left): ");
            char dir = readDirection();

            System.out.println("Enter X-coordinate (column 0-9): ");
            int x = readCoordinate("X");
            System.out.println("Enter Y-coordinate (row 0-9): ");
            int y = readCoordinate("Y");

            Coordinate crd  = new Coordinate(x, y);
            Ship       ship = new Ship(type, dir, crd);

            if (bds.isValidLocation(crd, ship))
            {
                bds.placeShips(crd, ship);
                System.out.println("  " + name + " placed successfully!");
                bds.printBoardA();
                return;
            }
            System.out.println("  Invalid location — out of bounds or overlaps another ship. Try again.");
        }
    }

    // Set up all 5 ships
    public void PlayerSetUp()
    {
        System.out.println("\n==========================================");
        System.out.println("  TIME TO PLACE YOUR SHIPS!");
        System.out.println("==========================================");
        printMenuHint();
        System.out.println();
        System.out.println("Your board:");
        bds.printBoardA();

        placeOneShip("Aircraft Carrier (size 5)", 'A');
        placeOneShip("Battleship (size 4)",       'B');
        placeOneShip("Submarine (size 3)",        'S');
        placeOneShip("Destroyer (size 3)",        'D');
        placeOneShip("Patrol Boat (size 2)",      'P');

        System.out.println("\n==========================================");
        System.out.println("  ALL SHIPS PLACED! READY FOR BATTLE.");
        System.out.println("==========================================\n");
    }

    // Player chooses where to attack.
    // Re-prompts on out-of-bounds or already-attacked cells.
    public Coordinate playerGuessAttack()
    {
        printMenuHint();
        while (true)
        {
            System.out.println("Enter X-coordinate to attack (column 0-9): ");
            int attackX = readCoordinate("X");
            System.out.println("Enter Y-coordinate to attack (row 0-9): ");
            int attackY = readCoordinate("Y");

            Coordinate attack = new Coordinate(attackX, attackY);

            if (!bds.isValidAttack(attack))
            {
                System.out.println("  That coordinate is out of bounds. Try again.");
                continue;
            }
            if (bds.isAlreadyAttackedB(attack))
            {
                System.out.println("  You've already attacked (" + attackX + ", " + attackY + ")! Choose a different location.");
                continue;
            }
            return attack;
        }
    }

    public void fireAndAttackComp(Computer comp, Coordinate attack)
    {
        char result = bds.resultHitMissComp(attack, comp);
        bds.printPlayerResult(result);
    }

    public void printBoard(char[][] board)
    {
        bds.printBoard(board);
    }

    public boolean verifyPlayerWin(Computer c)
    {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (c.getBoardA()[i][j] != '~' && c.getBoardA()[i][j] != 'X')
                    return false;
        return true;
    }

    public char[][] getBoardA() { return bds.getBoardA(); }
    public char[][] getBoardB() { return bds.getBoardB(); }
}
