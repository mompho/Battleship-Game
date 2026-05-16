package battleshipGame;

public class Boards
{
    private char[][] boardA = new char[10][10];
    private char[][] boardB = new char[10][10];

    private int numAirCraftPlay   = 0;
    private int numBattleshipPlay = 0;
    private int numSubmarinePlay  = 0;
    private int numDestroyerPlay  = 0;
    private int numPatrolBoatPlay = 0;

    private int numAirCraftComp   = 0;
    private int numBattleshipComp = 0;
    private int numSubmarineComp  = 0;
    private int numDestroyerComp  = 0;
    private int numPatrolBoatComp = 0;

    public Boards()
    {
        initBoardA();
        initBoardB();
    }

    public void initBoardA()
    {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                boardA[i][j] = '~';
    }

    public void initBoardB()
    {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                boardB[i][j] = '~';
    }

    public boolean isValidLocation(Coordinate crd, Ship s)
    {
        int x = crd.getX(); // column
        int y = crd.getY(); // row

        if (x < 0 || x >= 10) return false;
        if (y < 0 || y >= 10) return false;

        if (s.getDirection() == 'u')
        {
            if (y - (s.getSize() - 1) < 0) return false;
            for (int i = y; i > y - s.getSize(); i--)
                if (boardA[i][x] != '~') return false;
        }
        else if (s.getDirection() == 'd')
        {
            if (y + (s.getSize() - 1) >= 10) return false;
            for (int i = y; i < y + s.getSize(); i++)
                if (boardA[i][x] != '~') return false;
        }
        else if (s.getDirection() == 'r')
        {
            if (x + (s.getSize() - 1) >= 10) return false;
            for (int i = x; i < x + s.getSize(); i++)
                if (boardA[y][i] != '~') return false;
        }
        else if (s.getDirection() == 'l')
        {
            if (x - (s.getSize() - 1) < 0) return false;
            for (int i = x; i > x - s.getSize(); i--)
                if (boardA[y][i] != '~') return false;
        }
        return true;
    }

    public boolean isValidAttack(Coordinate crd)
    {
        int x = crd.getX();
        int y = crd.getY();
        return (x >= 0 && x < 10 && y >= 0 && y < 10);
    }

    public char resultHitMissComp(Coordinate crd, Computer opposition)
    {
        int y = crd.getY();
        int x = crd.getX();
        char cell = opposition.getBoardA()[y][x];

        if      (cell == 'A') { numAirCraftComp++;   if (numAirCraftComp   == 5) System.out.println("You have sunk your opponent's aircraft carrier!"); }
        else if (cell == 'B') { numBattleshipComp++; if (numBattleshipComp == 4) System.out.println("You have sunk your opponent's battleship!"); }
        else if (cell == 'S') { numSubmarineComp++;  if (numSubmarineComp  == 3) System.out.println("You have sunk your opponent's submarine!"); }
        else if (cell == 'D') { numDestroyerComp++;  if (numDestroyerComp  == 3) System.out.println("You have sunk your opponent's destroyer!"); }
        else if (cell == 'P') { numPatrolBoatComp++; if (numPatrolBoatComp == 2) System.out.println("You have sunk your opponent's patrol boat!"); }

        if (cell != '~')
        {
            boardB[y][x] = 'H';
            opposition.getBoardA()[y][x] = 'X';
            return 'H';
        }
        boardB[y][x] = 'M';
        return 'M';
    }

    public char resultHitMissPlayer(Coordinate crd, Player opposition)
    {
        int y = crd.getY();
        int x = crd.getX();
        char cell = opposition.getBoardA()[y][x];

        if      (cell == 'A') { numAirCraftPlay++;   if (numAirCraftPlay   == 5) System.out.println("Your opponent sunk your aircraft carrier!"); }
        else if (cell == 'B') { numBattleshipPlay++; if (numBattleshipPlay == 4) System.out.println("Your opponent sunk your battleship!"); }
        else if (cell == 'S') { numSubmarinePlay++;  if (numSubmarinePlay  == 3) System.out.println("Your opponent sunk your submarine!"); }
        else if (cell == 'D') { numDestroyerPlay++;  if (numDestroyerPlay  == 3) System.out.println("Your opponent sunk your destroyer!"); }
        else if (cell == 'P') { numPatrolBoatPlay++; if (numPatrolBoatPlay == 2) System.out.println("Your opponent sunk your patrol boat!"); }

        if (cell != '~')
        {
            opposition.getBoardA()[y][x] = 'X';
            return 'H';
        }
        boardB[y][x] = 'M';
        return 'M';
    }

    public void printResult(char result)
    {
        if (result == 'M')
            System.out.println("Tough luck soldier! You MISSED!");
        else
            System.out.println("Great strike soldier! You successfully HIT the enemy ship!");
    }

    public void placeShips(Coordinate crd, Ship s)
    {
        int x = crd.getX();
        int y = crd.getY();

        if (s.getDirection() == 'u')
            for (int i = y; i > y - s.getSize(); i--)
                boardA[i][x] = s.getLetter();
        else if (s.getDirection() == 'd')
            for (int i = y; i < y + s.getSize(); i++)
                boardA[i][x] = s.getLetter();
        else if (s.getDirection() == 'r')
            for (int i = x; i < x + s.getSize(); i++)
                boardA[y][i] = s.getLetter();
        else if (s.getDirection() == 'l')
            for (int i = x; i > x - s.getSize(); i--)
                boardA[y][i] = s.getLetter();
    }

    public char[][] getBoardA() { return boardA; }
    public char[][] getBoardB() { return boardB; }

    // Prints board with column header row (0-9) and row number on each line
    public void printBoard(char[][] board)
    {
        System.out.print("        ");
        for (int j = 0; j < 10; j++)
            System.out.printf("%-8d", j);
        System.out.println();

        for (int i = 0; i < 10; i++)
        {
            System.out.printf("%-8d", i);
            for (int j = 0; j < 10; j++)
                System.out.printf("%-8c", board[i][j]);
            System.out.println();
        }
    }

    public void printBoardA() { printBoard(boardA); }
    public void printBoardB() { printBoard(boardB); }
}
