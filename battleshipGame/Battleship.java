package battleshipGame;

public class Battleship
{
    public static void main(String[] args)
    {
        Player   p = new Player();
        Computer c = new Computer();

        System.out.println("==========================================");
        System.out.println("  WELCOME TO BATTLESHIP!");
        System.out.println("  You are playing against the computer.");
        System.out.println("==========================================\n");

        p.PlayerSetUp();
        c.compSetUp();

        int round = 1;

        while (!p.verifyPlayerWin(c) && !c.verifyCompW(p))
        {
            System.out.println("\n##########################################");
            System.out.println("  ROUND " + round);
            System.out.println("##########################################\n");

            // --- Player's turn ---
            System.out.println(">>> YOUR TURN <<<");
            Coordinate attackOnComputer = p.playerGuessAttack();
            p.fireAndAttackComp(c, attackOnComputer);

            if (p.verifyPlayerWin(c)) break;

            // --- Computer's turn ---
            System.out.println("\n>>> COMPUTER'S TURN <<<");
            Coordinate attackOnPlayer = c.getPredictedCoord(p);
            System.out.println("  Computer attacks " + attackOnPlayer + "...");
            c.fireAndAttackPlayer(p, attackOnPlayer);

            // --- Show boards ---
            System.out.println("\n--- YOUR BOARD (Board A: your ships) ---");
            p.printBoard(p.getBoardA());

            System.out.println("\n--- YOUR ATTACK BOARD (Board B: hits on enemy) ---");
            p.printBoard(p.getBoardB());

            round++;
        }

        System.out.println("\n==========================================");
        if (p.verifyPlayerWin(c))
            System.out.println("  GAME OVER! CONGRATULATIONS, YOU WON!");
        else
            System.out.println("  GAME OVER! YOU LOST. BETTER LUCK NEXT TIME!");
        System.out.println("==========================================\n");
    }
}
