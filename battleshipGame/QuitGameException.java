package battleshipGame;

/**
 * Thrown whenever the player types "quit" at any input prompt.
 * Caught at the top level in Battleship.main() to exit gracefully.
 */
public class QuitGameException extends RuntimeException
{
    public QuitGameException()
    {
        super("Player chose to quit the game.");
    }
}
