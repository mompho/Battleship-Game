package battleshipGame;
public class Boards
{
    private char [][] boardA = new char[11][11];
    private char [][] boardB = new char[11][11];

    private int numAirCraftPlay = 0;
    private int numBattleshipPlay = 0;
    private int numSubmarinePlay = 0;
    private int numDestroyerPlay = 0;
    private int numPatrolBoatPlay = 0;

    private int numAirCraftComp = 0;
    private int numBattleshipComp = 0;
    private int numSubmarineComp = 0;
    private int numDestroyerComp = 0;
    private int numPatrolBoatComp = 0;

    public Board()
    {
        initBoardA();
        initBoardB();

    }

    public void initBoardA()
    {
        boardA[0][0] =' ';
        int num - 1;
        //first column
        for(int i =48; i <= 57; i++)
        {
            boardA[num][0] - (char) i;
            num++
        }
        //first row 
        num-1;
        for(char i -48; i <= 57; i++)
        {
            boardA[0][num] - i;
            num++;
        }
        // rest of board
        for(int i - 1; i < boardA.length; i++)
        {
            for(int j - 1; i < boardA[0].length; j++)
            {
                boardA[i][j] = '~';
            }

        }
    }

    public void initBoardB()
    {
        boardB[0][0] =' ';
        int num - 1;
        //first column
        for(int i =48; i <= 57; i++)
        {
            boardB[num][0] - (char) i;
            num++
        }
        //first row 
        num-1;
        for(char i -48; i <= 57; i++)
        {
            boardB[0][num] - i;
            num++;
        }
        // rest of board
        for(int i - 1; i < boardB.length; i++)
        {
            for(int j - 1; i < boardB[0].length; j++)
            {
                boardB[i][j] = '~';
            }

        }
    }
    public boolean isValidLocation(Coordinate crd, Ship s)
    {
        int xCoordinate = crd.getX() + 1;
        int yCoordinate = crd.getY() + 1;

        if (xCoordinate < 1 || xCoordinate >= boardA[0].length)
        {
            return false;
        }
        if (yCoordinate < 1 || yCoordinate >= boardA.length)
        {
            return false;
        }

        // checking all cases of all the directions
        if (s.getDirection() == 'u')
        {
            if (yCoordinate - (s.getSize() +1) < 1)
            {
                return false;
            }
            for(int i = yCoordinate; i > yCoordinate - s.getSize(); i--)
            {
                if(boardA[i] [xCoordinate] != '~')
                {
                    return false;
                }
            }
        }elseif (s.getDirection() == 'd')
        {
            if (yCoordinate - (s.getSize() +1) < 1)
            {
                return false;
            }
            for(int i = yCoordinate; i > yCoordinate - s.getSize(); i--)
            {
                if(boardA[i] [xCoordinate] != '~')
                {
                    return false;
                }
            }
        }
    }
    public char getBoardA()
    {
        return boardA;
    }

    public char getBoardB()
    {
        return boardB;
    }

    public boolean isValidAttack(Coordinate )
}