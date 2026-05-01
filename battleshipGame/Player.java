package battleshipGame;
import java.util.Scanner;

public class Player 
{
    Board bds = new Boards();
    Scanner scn = new Scanner(Systen.in);

    public Player()
    {
        bds.initBoardA();
        bds.initBoardB();
    }

    public void PlayerSetUp()
    {
        
    }

}