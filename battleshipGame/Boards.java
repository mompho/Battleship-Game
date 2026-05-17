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

    // Returns true if boardB cell was already attacked (player's tracking board)
    public boolean isAlreadyAttackedB(Coordinate crd)
    {
        int y = crd.getY();
        int x = crd.getX();
        return (boardB[y][x] == 'H' || boardB[y][x] == 'M');
    }

    public boolean isValidLocation(Coordinate crd, Ship s)
    {
        int x = crd.getX();
        int y = crd.getY();

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

    // Player attacks computer
    public char resultHitMissComp(Coordinate crd, Computer opposition)
    {
        int y = crd.getY();
        int x = crd.getX();

        if (boardB[y][x] == 'H' || boardB[y][x] == 'M') return 'A';

        char cell = opposition.getBoardA()[y][x];
        boolean sunk = false;
        String sunkMsg = "";

        if      (cell == 'A') { numAirCraftComp++;   if (numAirCraftComp   == 5) { sunk = true; sunkMsg = "YOU SUNK THE ENEMY'S AIRCRAFT CARRIER!"; } }
        else if (cell == 'B') { numBattleshipComp++; if (numBattleshipComp == 4) { sunk = true; sunkMsg = "YOU SUNK THE ENEMY'S BATTLESHIP!"; } }
        else if (cell == 'S') { numSubmarineComp++;  if (numSubmarineComp  == 3) { sunk = true; sunkMsg = "YOU SUNK THE ENEMY'S SUBMARINE!"; } }
        else if (cell == 'D') { numDestroyerComp++;  if (numDestroyerComp  == 3) { sunk = true; sunkMsg = "YOU SUNK THE ENEMY'S DESTROYER!"; } }
        else if (cell == 'P') { numPatrolBoatComp++; if (numPatrolBoatComp == 2) { sunk = true; sunkMsg = "YOU SUNK THE ENEMY'S PATROL BOAT!"; } }

        if (cell != '~')
        {
            boardB[y][x] = 'H';
            opposition.getBoardA()[y][x] = 'X';
            if (sunk) printSunkBanner(sunkMsg);
            return 'H';
        }
        boardB[y][x] = 'M';
        return 'M';
    }

    // Computer attacks player
    public char resultHitMissPlayer(Coordinate crd, Player opposition)
    {
        int y = crd.getY();
        int x = crd.getX();

        if (opposition.getBoardA()[y][x] == 'X' || boardB[y][x] == 'M') return 'A';

        char cell = opposition.getBoardA()[y][x];
        boolean sunk = false;
        String sunkMsg = "";

        if      (cell == 'A') { numAirCraftPlay++;   if (numAirCraftPlay   == 5) { sunk = true; sunkMsg = "THE ENEMY SUNK YOUR AIRCRAFT CARRIER!"; } }
        else if (cell == 'B') { numBattleshipPlay++; if (numBattleshipPlay == 4) { sunk = true; sunkMsg = "THE ENEMY SUNK YOUR BATTLESHIP!"; } }
        else if (cell == 'S') { numSubmarinePlay++;  if (numSubmarinePlay  == 3) { sunk = true; sunkMsg = "THE ENEMY SUNK YOUR SUBMARINE!"; } }
        else if (cell == 'D') { numDestroyerPlay++;  if (numDestroyerPlay  == 3) { sunk = true; sunkMsg = "THE ENEMY SUNK YOUR DESTROYER!"; } }
        else if (cell == 'P') { numPatrolBoatPlay++; if (numPatrolBoatPlay == 2) { sunk = true; sunkMsg = "THE ENEMY SUNK YOUR PATROL BOAT!"; } }

        if (cell != '~')
        {
            opposition.getBoardA()[y][x] = 'X';
            if (sunk) printSunkBanner(sunkMsg);
            return 'H';
        }
        boardB[y][x] = 'M';
        return 'M';
    }

    private void printSunkBanner(String message)
    {
        String border = "=".repeat(message.length() + 8);
        System.out.println("\n" + border);
        System.out.println("*** " + message + " ***");
        System.out.println(border + "\n");
    }

    public void printPlayerResult(char result)
    {
        System.out.println("--------------------------------------------------");
        if      (result == 'M') System.out.println("  YOUR ATTACK: MISS! Better luck next shot.");
        else if (result == 'H') System.out.println("  YOUR ATTACK: HIT!  Great shot, soldier!");
        else                    System.out.println("  You've already attacked that location!");
        System.out.println("--------------------------------------------------");
    }

    public void printComputerResult(char result)
    {
        System.out.println("--------------------------------------------------");
        if      (result == 'M') System.out.println("  COMPUTER ATTACK: MISS! Your ships are safe.");
        else if (result == 'H') System.out.println("  COMPUTER ATTACK: HIT!  The enemy struck your ship!");
        else                    System.out.println("  Computer already attacked that location (skipped).");
        System.out.println("--------------------------------------------------");
    }

    public void placeShips(Coordinate crd, Ship s)
    {
        int x = crd.getX();
        int y = crd.getY();

        if      (s.getDirection() == 'u') for (int i = y; i > y - s.getSize(); i--) boardA[i][x] = s.getLetter();
        else if (s.getDirection() == 'd') for (int i = y; i < y + s.getSize(); i++) boardA[i][x] = s.getLetter();
        else if (s.getDirection() == 'r') for (int i = x; i < x + s.getSize(); i++) boardA[y][i] = s.getLetter();
        else if (s.getDirection() == 'l') for (int i = x; i > x - s.getSize(); i--) boardA[y][i] = s.getLetter();
    }

    public char[][] getBoardA() { return boardA; }
    public char[][] getBoardB() { return boardB; }

    public void printBoard(char[][] board)
    {
        System.out.print("        ");
        for (int j = 0; j < 10; j++) System.out.printf("%-8d", j);
        System.out.println();
        for (int i = 0; i < 10; i++)
        {
            System.out.printf("%-8d", i);
            for (int j = 0; j < 10; j++) System.out.printf("%-8c", board[i][j]);
            System.out.println();
        }
    }

    public void printBoardA() { printBoard(boardA); }
    public void printBoardB() { printBoard(boardB); }
}
