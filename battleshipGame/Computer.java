package battleshipGame;
import java.util.ArrayList;

public class Computer
{
    Boards bds = new Boards();
    ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
    ArrayList<Coordinate> chooseFrom = new ArrayList<Coordinate>();
    
    public Computer()
    {
        bds.initBoardA();
        bds.initBoardB();
    }

    public void compSetUp()
    {
        char direction = randomDirection();
        Coordinate crd = randomCoordinate();
        Ship aircraftCarrier = new Ship('A', direction, crd);
        while(bds.isValidLocation(crd, aircraftCarrier) == false)
        {
            crd = randomCoordinate();
        }

        bds.placeShips(crd, aircraftCarrier);

        direction = randomDirection();
        crd = randomCoordinate();
        Ship battleship = new Ship('B', direction, crd);
        while(bds.isValidLocation(crd, battleship) == false)
        {
            crd = randomCoordinate();
        }

        bds.placeShips(crd, battleship);

        direction = randomDirection();
        crd = randomCoordinate();
        Ship submarine = new Ship('S', direction, crd);
        while(bds.isValidLocation(crd, submarine) == false)
        {
            crd = randomCoordinate();
        }

        bds.placeShips(crd, submarine);

        direction = randomDirection();
        crd = randomCoordinate();
        Ship destroyer = new Ship('D', direction, crd);
        while(bds.isValidLocation(crd, destroyer) == false)
        {
            crd = randomCoordinate();
        }

        bds.placeShips(crd, destroyer);

        direction = randomDirection();
        crd = randomCoordinate();
        Ship patrolBoat = new Ship('P', direction, crd);
        while(bds.isValidLocation(crd, patrolBoat) == false)
        {
            crd = randomCoordinate();
        }

        bds.placeShips(crd, patrolBoat);

        System.out.println("The Computer has finished setting up its board as well. Time to play!");
        System.out.println("'X' represents where the computer has attacked your board.");

    }

    public void fireAndAttackPlayer(Player p, Coordinate attack)
    {
        printResult(bds.resultHitMissPlayer(attack, p));
    }

    public void printResult(char result)
    {
        if(result == 'H')
        {
            System.out.println("The computer has successfully HIT your ship!");           
        }else 
        {
            System.out.println("The computer has MISSED your ship");
        }
    }

    public Coordinate getPredictionCoord(Player p)
    {
        Coordinate c = randomCoordinate();

    // Computer will first predict where player ships are based on coordinates
        if(coords.size() >= 1)
        {
            c = coords.get(coords.size() -1); // Last element of array list
            if(bds.resultHitMissPlayer(c, p) == 'H')
            {
                chooseFrom = chooseAdjacentLocations(c);
                int randNum = (int)(Math.random() * chooseFrom.size()) + 1;
                while(!bds.isValidAttack(chooseFrom.get(randNum-1)) || bds.getBoardB()[chooseFrom.get(randNum -1).getY() +1][chooseFrom.get(randNum -1).getX() +1] != '~')
                {
                    random = (int)(Math.random() * chooseFrom.size()) +1;
                }

                coord.add(chooseFrom.get(randNum -1));
                return chooseFrom.get(randNum -1);
            }
        }
        c = randomCoordinate();
        while(!bds.isValidAttack(c) || bds.getBoardB()[c.getY() +1][c.getX() +1] != '~')
        {
            c = randomCoordinate();
        }
        coords.add(c);
        return c;
    }

    public ArrayList<Coordinate> chooseAdjacentLocations(Coordinate crd)
    {
        ArrayList<Coordinate> adjacentCoords = new ArrayList<Coordinate>();
        if(crd.getX() == 0 && crd.getY() == 0)
        {
            adjacentCoords.add(new Coordinate(crd.getX() +1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() +1));
        }else if(crd.getX() == 0 && crd.getY() == 9)
        {
            adjacentCoords.add(new Coordinate(crd.getX() +1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() -1));

        }else if(crd.getX() == 0)
        {
            adjacentCoords.add(new Coordinate(crd.getX() +1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() +1));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() -1));
        }else if(crd.getX() == 9 && crd.getY() == 0)
        {
            adjacentCoords.add(new Coordinate(crd.getX() -1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() +1));
        }else if(crd.getX() == 9 && crd.getY() == 9)
        {
            adjacentCoords.add(new Coordinate(crd.getX() -1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() -1));
        }else if(crd.getX() == 9)
        {
            adjacentCoords.add(new Coordinate(crd.getX() - 1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() +1));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() -1));
        }else if(crd.getY() == 0)
        {
            adjacentCoords.add(new Coordinate(crd.getX() -1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() +1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() +1));
        }else if(crd.getY() == 9)
        {
            adjacentCoords.add(new Coordinate(crd.getX() -1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() +1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() , crd.getY() -1));
        }else 
        {
            adjacentCoords.add(new Coordinate(crd.getX(), crd.getY() -1));
            adjacentCoords.add(new Coordinate(crd.getX(), crd.getY() +1));
            adjacentCoords.add(new Coordinate(crd.getX() -1, crd.getY()));
            adjacentCoords.add(new Coordinate(crd.getX() +1, crd.getY()));
        }
    }

    public boolean verifyCompW(Player p)
    {
        for(int i = 1; i < p.getBoardA().length; i++)
        {
            for(intj = 0; j < p.getBoardA()[0].length; j++)
            {
                if(p.getBoardA()[i][j] != "~" && bds.getBoardB()[i][j] != 'H' )
                {
                    return false;
                }
            }
        }
        return true;
    }

    public char randomDirection()
    {
        int dirNum = (int)(Math.random() * 4) +1;
        if(dirNum == 1)
        {
            return 'u';
        }else if (dirNum == 2)
        {
            return 'd';
        }else if (dirNum == 3)
        {
            return 'r';
        }

        return 'l';
    }

    public Coordinate randomCoordinate()
    {
        int randNum1 = (int)(Math.random() * 10);
        int randNum2 = (int)(Math.random() * 10);

        return new Coordinate(randNum1, randNum2);
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