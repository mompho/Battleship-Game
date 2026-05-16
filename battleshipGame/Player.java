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

        // Placing aircraft carrier
        System.out.println("The first ship is an aircraft of size 5");
        System.out.println("What direction would you like to place this ship on? Enter 'u' for up, 'd' for down, 'r' for right and 'l' for left");
        char dir = scn.next().charAt(0);

        System.out.println("Now enter the X-Coordinate of where the location is: ");
        int xCord = scn.nextInt();
        System.out.println("Now enter the Y-Coordinate of where the location is: ");
        int yCord = scn.nextInt();

        Coordinate crd = new Coordinate(xCord, yCord);

        Ship aircraftCarrier = new Ship('A', dir, crd);
        while(bds.isValidLocation(crd, aircraftCarrier) == false)
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-Coordinate of where the location is: ");
            int newX = scn.nextInt();
            System.out.println("Enter the Y-Coordinate of where the location is: ");
            int newY = scn.nextInt();
            crd = new Coordinate(newX, newY);

        }
        bds.placeShips(crd, aircraftCarrier);
        bds.printBoardA();

        // Placing Battleship
        System.out.println("The second ship is a battleship of size 4");
        System.out.println("What direction would you like to place this ship on? Enter 'u' for up, 'd' for down, 'r' for right and 'l' for left");
        dir = scn.next().charAt(0);

        System.out.println("Now enter the X-Coordinate of where the location is: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-Coordinate of where the location is: ");
        yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);

        Ship battleship = new Ship('B', dir, crd);
        while(bds.isValidLocation(crd, battleship) == false)
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-Coordinate of where the location is: ");
            int newX = scn.nextInt();
            System.out.println("Enter the Y-Coordinate of where the location is: ");
            int newY = scn.nextInt();
            crd = new Coordinate(newX, newY);

        }
        bds.placeShips(crd, battleship);
        bds.printBoardA();

        // Placing the submarine
        System.out.println("The third ship is a submarine of size 3");
        System.out.println("What direction would you like to place this ship on? Enter 'u' for up, 'd' for down, 'r' for right and 'l' for left");
        dir = scn.next().charAt(0);

        System.out.println("Now enter the X-Coordinate of where the location is: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-Coordinate of where the location is: ");
         yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);

        Ship submarine = new Ship('S', dir, crd);
        while(bds.isValidLocation(crd, submarine) == false)
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-Coordinate of where the location is: ");
            int newX = scn.nextInt();
            System.out.println("Enter the Y-Coordinate of where the location is: ");
            int newY = scn.nextInt();
            crd = new Coordinate(newX, newY);

        }
        bds.placeShips(crd, submarine);
        bds.printBoardA();

        // Place destroyer
        System.out.println("The fourth ship is a destroyer of size 3");
        System.out.println("What direction would you like to place this ship on? Enter 'u' for up, 'd' for down, 'r' for right and 'l' for left");
        dir = scn.next().charAt(0);

        System.out.println("Now enter the X-Coordinate of where the location is: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-Coordinate of where the location is: ");
         yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);

        Ship destroyer = new Ship('D', dir, crd);
        while(bds.isValidLocation(crd, destroyer) == false)
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-Coordinate of where the location is: ");
            int newX = scn.nextInt();
            System.out.println("Enter the Y-Coordinate of where the location is: ");
            int newY = scn.nextInt();
            crd = new Coordinate(newX, newY);

        }
        bds.placeShips(crd, destroyer);
        bds.printBoardA();

        // Placing patrol boat 
        System.out.println("The fifth and final  ship is a patrol boat of size 2");
        System.out.println("What direction would you like to place this ship on? Enter 'u' for up, 'd' for down, 'r' for right and 'l' for left");
        dir = scn.next().charAt(0);

        System.out.println("Now enter the X-Coordinate of where the location is: ");
        xCord = scn.nextInt();
        System.out.println("Now enter the Y-Coordinate of where the location is: ");
         yCord = scn.nextInt();

        crd = new Coordinate(xCord, yCord);

        Ship patrolBoat = new Ship('P', dir, crd);
        while(bds.isValidLocation(crd, patrolBoat) == false)
        {
            System.out.println("That is an invalid location. Please enter again.");
            System.out.println("Enter the X-Coordinate of where the location is: ");
            int newX = scn.nextInt();
            System.out.println("Enter the Y-Coordinate of where the location is: ");
            int newY = scn.nextInt();
            crd = new Coordinate(newX, newY);

        }
        bds.placeShips(crd, patrolBoat);
        bds.printBoardA();

        System.out.println("You have finished setting up your board!");
    }

    public Coordinate playerGuessAttack()
    {
        System.out.println("Enter the X-Coordinate of where you want to attack the computer's board: ");
        int attackX = scn.nextInt();
        System.out.println("Enter the Y-Coordinate of where you want to attack the computer's board: ");
        int attackY = scn.nextInt();

        Coordinate attack = new Coordinate(attackX, attackY);

        while (bds.isValidAttack(attack)== false)
        {
            System.out.println("That is an invalid attack location. Please enter again: ");
            System.out.println("Enter the X-Coordinate of where you want to attack the computer's board: ");
            int newXattack = scn.nextInt();
            System.out.println("Enter the Y-Coordinate of where you want to attack the computer's board: ");
            int newYattack = scn.nextInt();
            
            attack = new Coordinate(newXattack, newYattack);
        }

        return attack;
    }

    public void fireAndAttackComp(Computer comp, Coordinate attack)
    {
        bds.printResult(bds.resultHitMissComp(attack, comp));
    }

    public void printBoard(char[][] board)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                System.out.print(board[i][j] + "        ");
            }
            System.out.println();
        }
    }

    public boolean verifyPlayerWin(Computer c)
    {
        for(int i = 1; i < c.getBoardA().length; i++)
        {
            for(int j = 1; j < c.getBoardA()[0].length; j++)
            {
                if(c.getBoardA()[i][j] != '~' && bds.getBoardB()[i][j] != 'H')
                {
                    return false;
                }
            } 
        }
        return true;
    }

    public char[][] getBoardA()
    {
        return bds.getBoardA();
    }

    public char[][] getBoardB()
    {
        return bds.getBoardB();
    }
}