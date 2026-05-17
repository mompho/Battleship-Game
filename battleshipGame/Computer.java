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
        System.out.println("'X' represents where the computer has attacked your board.\n");
    }

    private void placeShipRandomly(char type)
    {
        char dir   = randomDirection();
        Coordinate crd  = randomCoordinate();
        Ship ship  = new Ship(type, dir, crd);

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
        if (!coords.isEmpty())
        {
            Coordinate last = coords.get(coords.size() - 1);
            if (p.getBoardA()[last.getY()][last.getX()] == 'X')
            {
                ArrayList<Coordinate> adjacent = chooseAdjacentLocations(last);
                for (Coordinate adj : adjacent)
                {
                    if (bds.isValidAttack(adj) && !isAlreadyAttackedOnPlayer(adj, p))
                    {
                        coords.add(adj);
                        return adj;
                    }
                }
            }
        }

        Coordinate c = randomCoordinate();
        int attempts = 0;
        while (isAlreadyAttackedOnPlayer(c, p) || !bds.isValidAttack(c))
        {
            c = randomCoordinate();
            attempts++;
            if (attempts > 200)
            {
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 10; j++)
                    {
                        Coordinate seq = new Coordinate(j, i);
                        if (!isAlreadyAttackedOnPlayer(seq, p)) return seq;
                    }
            }
        }
        coords.add(c);
        return c;
    }

    private boolean isAlreadyAttackedOnPlayer(Coordinate crd, Player p)
    {
        int y = crd.getY();
        int x = crd.getX();
        char cell = p.getBoardA()[y][x];
        return (cell == 'X' || bds.getBoardB()[y][x] == 'M');
    }

    public ArrayList<Coordinate> chooseAdjacentLocations(Coordinate crd)
    {
        ArrayList<Coordinate> adj = new ArrayList<Coordinate>();
        int x = crd.getX();
        int y = crd.getY();

        if (y > 0) adj.add(new Coordinate(x, y - 1));
        if (y < 9) adj.add(new Coordinate(x, y + 1));
        if (x > 0) adj.add(new Coordinate(x - 1, y));
        if (x < 9) adj.add(new Coordinate(x + 1, y));

        return adj;
    }

    public boolean verifyCompW(Player p)
    {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (p.getBoardA()[i][j] != '~' && p.getBoardA()[i][j] != 'X')
                    return false;
        return true;
    }

    public void fireAndAttackPlayer(Player p, Coordinate attack)
    {
        char result = bds.resultHitMissPlayer(attack, p);
        bds.printComputerResult(result);
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
