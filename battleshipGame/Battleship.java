package battleshipGame;

public class Battleship
{
    public static void main(String[] args)
    {
        Player p   = new Player();
        Computer c = new Computer();

        System.out.println("Welcome to the BattleShip Game! You will be playing against the computer.");
        p.PlayerSetUp();
        c.compSetUp();

        while (!p.verifyPlayerWin(c) && !c.verifyCompW(p))
        {
            // Player attacks computer
            Coordinate attackOnComputer = p.playerGuessAttack();
            p.fireAndAttackComp(c, attackOnComputer);

            // Computer attacks player
            Coordinate attackOnPlayer = c.getPredictedCoord(p);
            c.fireAndAttackPlayer(p, attackOnPlayer);

            // Show boards after each round
            System.out.println("Your Board A:");
            p.printBoard(p.getBoardA());

            System.out.println("Your Board B:");
            p.printBoard(p.getBoardB());
        }

        if (p.verifyPlayerWin(c))
            System.out.println("GAME OVER! CONGRATS YOU WON!");
        else if (c.verifyCompW(p))
            System.out.println("GAME OVER! YOU LOST, BETTER LUCK NEXT TIME!");
    }
}
