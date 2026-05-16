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

    public void PlayerSetUp()
    {
        System.out.println("Now it is time to set your ships!");

        // --- Aircraft Carrier (size 5) ---
        System.out.println("The first ship is an aircraft carrier of size 5");
        System.out.println("What direction would you like to place this ship? Enter 'u' for up, 'd' for down, 'r' for right or 'l' for left");
        char dir = scn.next().charAt(0);
        System.out.println("Now enter the X-coordinate (column 0-9) of where the location is: ");
        int xCord = scn.nextInt();
        System.out.println("Now enter the Y-coordinate (row 0-9) of where the location is: ");
        int yCord = scn.nextInt();

        Coordinate crd = new Coordinate(xCord, yCord);
        Ship aircraftCarrier = new Ship('A', dir, crd);
        while (!bds.isValidLocation(crd, aircraftCarrier))
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-coordinate: ");
            xCord = scn.nextInt();
            System.out.println("Enter the Y-coordinate: ");
            yCord = scn.nextInt();
            crd = new Coordinate(xCord, yCord);
            aircraftCarrier = new Ship('A', dir, crd);
        }
        bds.placeShips(crd, aircraftCarrier);
        bds.printBoardA();

        // --- Battleship (size 4) ---
        System.out.println("The second ship is a battleship of size 4");
        System.out.println("What direction would you like to place this ship? Enter 'u' for up, 'd' for down, 'r' for right or 'l' for left");
        dir = scn.next().charAt(0);
        System.out.println("Now enter the X-coordinate: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-coordinate: ");
        yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);
        Ship battleship = new Ship('B', dir, crd);
        while (!bds.isValidLocation(crd, battleship))
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-coordinate: ");
            xCord = scn.nextInt();
            System.out.println("Enter the Y-coordinate: ");
            yCord = scn.nextInt();
            crd = new Coordinate(xCord, yCord);
            battleship = new Ship('B', dir, crd);
        }
        bds.placeShips(crd, battleship);
        bds.printBoardA();

        // --- Submarine (size 3) ---
        System.out.println("The third ship is a submarine of size 3");
        System.out.println("What direction would you like to place this ship? Enter 'u' for up, 'd' for down, 'r' for right or 'l' for left");
        dir = scn.next().charAt(0);
        System.out.println("Now enter the X-coordinate: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-coordinate: ");
        yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);
        Ship submarine = new Ship('S', dir, crd);
        while (!bds.isValidLocation(crd, submarine))
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-coordinate: ");
            xCord = scn.nextInt();
            System.out.println("Enter the Y-coordinate: ");
            yCord = scn.nextInt();
            crd = new Coordinate(xCord, yCord);
            submarine = new Ship('S', dir, crd);
        }
        bds.placeShips(crd, submarine);
        bds.printBoardA();

        // --- Destroyer (size 3) ---
        System.out.println("The fourth ship is a destroyer of size 3");
        System.out.println("What direction would you like to place this ship? Enter 'u' for up, 'd' for down, 'r' for right or 'l' for left");
        dir = scn.next().charAt(0);
        System.out.println("Now enter the X-coordinate: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-coordinate: ");
        yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);
        Ship destroyer = new Ship('D', dir, crd);
        while (!bds.isValidLocation(crd, destroyer))
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-coordinate: ");
            xCord = scn.nextInt();
            System.out.println("Enter the Y-coordinate: ");
            yCord = scn.nextInt();
            crd = new Coordinate(xCord, yCord);
            destroyer = new Ship('D', dir, crd);
        }
        bds.placeShips(crd, destroyer);
        bds.printBoardA();

        // --- Patrol Boat (size 2) ---
        System.out.println("The fifth and final ship is a patrol boat of size 2");
        System.out.println("What direction would you like to place this ship? Enter 'u' for up, 'd' for down, 'r' for right or 'l' for left");
        dir = scn.next().charAt(0);
        System.out.println("Now enter the X-coordinate: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-coordinate: ");
        yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);
        Ship patrolBoat = new Ship('P', dir, crd);
        while (!bds.isValidLocation(crd, patrolBoat))
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-coordinate: ");
            xCord = scn.nextInt();
            System.out.println("Enter the Y-coordinate: ");
            yCord = scn.nextInt();
            crd = new Coordinate(xCord, yCord);
            patrolBoat = new Ship('P', dir, crd);
        }
        bds.placeShips(crd, patrolBoat);
        bds.printBoardA();

        System.out.println("You have finished setting up your board!");
    }

    public Coordinate playerGuessAttack()
    {
        System.out.println("Enter the X-coordinate of where you want to attack the computer's board: ");
        int attackX = scn.nextInt();
        System.out.println("Enter the Y-coordinate of where you want to attack the computer's board: ");
        int attackY = scn.nextInt();

        Coordinate attack = new Coordinate(attackX, attackY);
        while (!bds.isValidAttack(attack))
        {
            System.out.println("That is an invalid attack location. Please enter again.");
            System.out.println("Enter the X-coordinate: ");
            attackX = scn.nextInt();
            System.out.println("Enter the Y-coordinate: ");
            attackY = scn.nextInt();
            attack = new Coordinate(attackX, attackY);
        }
        return attack;
    }

    public void fireAndAttackComp(Computer comp, Coordinate attack)
    {
        bds.printResult(bds.resultHitMissComp(attack, comp));
    }

    public void printBoard(char[][] board)
    {
        bds.printBoard(board);
    }

    public boolean verifyPlayerWin(Computer c)
    {
        // Player wins when every non-~ cell on computer's boardA has been hit (marked X)
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (c.getBoardA()[i][j] != '~' && c.getBoardA()[i][j] != 'X')
                    return false;
        return true;
    }

    public char[][] getBoardA() { return bds.getBoardA(); }
    public char[][] getBoardB() { return bds.getBoardB(); }
}
