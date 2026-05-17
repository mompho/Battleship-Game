package battleshipGame;

/**
 * Thrown whenever the player types "restart" at any input prompt.
 * Caught at the top level in Battleship.main() to restart the game loop.
 */
public class RestartGameException extends RuntimeException
{
    public RestartGameException()
    {
        super("Player chose to restart the game.");
    }
}
