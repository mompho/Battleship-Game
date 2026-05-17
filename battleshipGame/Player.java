package battleshipGame;

import java.util.InputMismatchException;
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

    // ---------------------------------------------------------------
    // Helper: read a valid direction character ('u','d','r','l')
    // Re-prompts on anything invalid
    // ---------------------------------------------------------------
    private char readDirection()
    {
        while (true)
        {
            try
            {
                char dir = scn.next().toLowerCase().charAt(0);
                if (dir == 'u' || dir == 'd' || dir == 'r' || dir == 'l')
                    return dir;
                System.out.println("  Invalid direction! Please enter 'u', 'd', 'r' or 'l': ");
            }
            catch (Exception e)
            {
                System.out.println("  Invalid input. Please enter 'u', 'd', 'r' or 'l': ");
                scn.nextLine(); // clear bad input
            }
        }
    }

    // ---------------------------------------------------------------
    // Helper: read a valid integer coordinate (0-9)
    // Re-prompts on non-integer or out-of-range input
    // ---------------------------------------------------------------
    private int readCoordinate(String axis)
    {
        while (true)
        {
            try
            {
                int val = scn.nextInt();
                if (val >= 0 && val <= 9)
                    return val;
                System.out.println("  Out of range! " + axis + "-coordinate must be between 0 and 9. Try again: ");
            }
            catch (InputMismatchException e)
            {
                System.out.println("  Invalid input! Please enter a whole number between 0 and 9 for the " + axis + "-coordinate: ");
                scn.nextLine(); // clear bad token
            }
        }
    }

    // ---------------------------------------------------------------
    // Helper: place one ship with full validation loop
    // ---------------------------------------------------------------
    private void placeOneShip(String name, char type)
    {
        System.out.println("\nPlacing your " + name + "...");
        System.out.println("Enter direction ('u' up, 'd' down, 'r' right, 'l' left): ");
        char dir = readDirection();

        System.out.println("Enter X-coordinate (column 0-9): ");
        int x = readCoordinate("X");
        System.out.println("Enter Y-coordinate (row 0-9): ");
        int y = readCoordinate("Y");

        Coordinate crd = new Coordinate(x, y);
        Ship ship = new Ship(type, dir, crd);

        while (!bds.isValidLocation(crd, ship))
        {
            System.out.println("  That location is invalid (out of bounds or overlaps another ship).");
            System.out.println("  Enter direction again: ");
            dir = readDirection();
            System.out.println("  Enter X-coordinate (0-9): ");
            x = readCoordinate("X");
            System.out.println("  Enter Y-coordinate (0-9): ");
            y = readCoordinate("Y");
            crd  = new Coordinate(x, y);
            ship = new Ship(type, dir, crd);
        }

        bds.placeShips(crd, ship);
        System.out.println("  " + name + " placed successfully!");
        bds.printBoardA();
    }

    // ---------------------------------------------------------------
    // Set up all 5 ships
    // ---------------------------------------------------------------
    public void PlayerSetUp()
    {
        System.out.println("\n==========================================");
        System.out.println("  TIME TO PLACE YOUR SHIPS!");
        System.out.println("==========================================\n");
        System.out.println("Your board:");
        bds.printBoardA();

        placeOneShip("Aircraft Carrier (size 5)", 'A');
        placeOneShip("Battleship (size 4)",       'B');
        placeOneShip("Submarine (size 3)",         'S');
        placeOneShip("Destroyer (size 3)",         'D');
        placeOneShip("Patrol Boat (size 2)",       'P');

        System.out.println("\n==========================================");
        System.out.println("  ALL SHIPS PLACED! READY FOR BATTLE.");
        System.out.println("==========================================\n");
    }

    // ---------------------------------------------------------------
    // Player chooses where to attack
    // Re-prompts if out of bounds OR already attacked
    // ---------------------------------------------------------------
    public Coordinate playerGuessAttack()
    {
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

            // Check if already attacked (H or M on boardB)
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
