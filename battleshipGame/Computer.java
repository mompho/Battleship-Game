package battleshipGame;

import java.util.ArrayList;

public class Computer
{
    Boards bds = new Boards();
    ArrayList<Coordinate> coords     = new ArrayList<Coordinate>();
    ArrayList<Coordinate> chooseFrom = new ArrayList<Coordinate>();

    public Computer()
    {
        bds.initBoardA();
        bds.initBoardB();
    }

    public void compSetUp()
    {
        placeShipRandomly('A');
        placeShipRandomly('B');
        placeShipRandomly('S');
        placeShipRandomly('D');
        placeShipRandomly('P');

        System.out.println("The Computer has finished setting up its board as well. Time to play!");
        System.out.println("'X' represents where the computer has attacked your board.");
    }

    private void placeShipRandomly(char type)
    {
        char dir = randomDirection();
        Coordinate crd = randomCoordinate();
        Ship ship = new Ship(type, dir, crd);
        while (!bds.isValidLocation(crd, ship))
        {
            dir  = randomDirection();
            crd  = randomCoordinate();
            ship = new Ship(type, dir, crd);
        }
        bds.placeShips(crd, ship);
    }

    public Coordinate getPredictedCoord(Player p)
    {
        // If we have a previous hit, try adjacent cells
        if (!coords.isEmpty())
        {
            Coordinate last = coords.get(coords.size() - 1);
            // Only pursue adjacency if last coord was a hit
            if (p.getBoardA()[last.getY()][last.getX()] == 'X')
            {
                chooseFrom = chooseAdjacentLocations(last);
                for (Coordinate adj : chooseFrom)
                {
                    if (bds.isValidAttack(adj) &&
                        bds.getBoardB()[adj.getY()][adj.getX()] == '~')
                    {
                        coords.add(adj);
                        return adj;
                    }
                }
            }
        }

        // Random attack on an un-attacked cell
        Coordinate c = randomCoordinate();
        while (!bds.isValidAttack(c) || bds.getBoardB()[c.getY()][c.getX()] != '~')
            c = randomCoordinate();

        coords.add(c);
        return c;
    }

    public ArrayList<Coordinate> chooseAdjacentLocations(Coordinate crd)
    {
        ArrayList<Coordinate> adj = new ArrayList<Coordinate>();
        int x = crd.getX();
        int y = crd.getY();

        if (y > 0) adj.add(new Coordinate(x, y - 1)); // up
        if (y < 9) adj.add(new Coordinate(x, y + 1)); // down
        if (x > 0) adj.add(new Coordinate(x - 1, y)); // left
        if (x < 9) adj.add(new Coordinate(x + 1, y)); // right

        return adj;
    }

    public boolean verifyCompW(Player p)
    {
        // Computer wins when every non-~ cell on player's boardA has been hit (marked X)
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (p.getBoardA()[i][j] != '~' && p.getBoardA()[i][j] != 'X')
                    return false;
        return true;
    }

    public void fireAndAttackPlayer(Player p, Coordinate attack)
    {
        char result = bds.resultHitMissPlayer(attack, p);
        printResult(result);
    }

    public void printResult(char result)
    {
        if (result == 'H')
            System.out.println("The computer has successfully HIT your ship!");
        else
            System.out.println("The computer MISSED your ship.");
    }

    public char randomDirection()
    {
        int d = (int)(Math.random() * 4);
        if (d == 0) return 'u';
        if (d == 1) return 'd';
        if (d == 2) return 'r';
        return 'l';
    }

    public Coordinate randomCoordinate()
    {
        int x = (int)(Math.random() * 10);
        int y = (int)(Math.random() * 10);
        return new Coordinate(x, y);
    }

    public char[][] getBoardA() { return bds.getBoardA(); }
    public char[][] getBoardB() { return bds.getBoardB(); }
}
