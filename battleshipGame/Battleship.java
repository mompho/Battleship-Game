package battleshipGame;

public class Battleship
{
    public static void main(String[] args)
    {
        Player p = new Player();
        Computer c = new Computer();

        System.out.println("Welcome to the Battleships Game! You will be playing against the computer.");
        p.PlayerSetUp();
        c.compSetUp();

        while(!p.verifyPlayerWin(c) && !c.verifyCompW(p))
        {
            // Player attacking the computer
            Coordinate attackOnComputer = p.playerGuessAttack();
            p.fireAndAttackComp(c, attackOnComputer);

            // Computer attacking player
            Coordinate attackOnPlayer = c.getPredictedCoord(p);
            c.fireAndAttackPlayer(c, attackOnPlayer);

            // Print out Player's Boards
            System.out.println("Your Board A: ");
            p.printBoard(p.getBoardA());

            System.out.println("Your Board B: ");
            p.printBoard(p.getBoardB());
        }
        if(verifyPlayerWin(c))
                {
                    System.out.println("GAME OVER! CONGRATS YOU WON!");
                } else if(verifyCompW(p))
                {
                    System.out.println("GAME OVER! YOU LOST, BETTER LUCK NEXT TIME!");
                }
            }
        
            private static boolean verifyPlayerWin(Computer c) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'verifyPlayerWin'");
            }
}