package selfish;
/**
 * A custom exception to handle errors in the game logic
 * @author Dasharn Dennis
 * @version 1
 */
public class GameException extends Exception {
/** Constructs a new GameException with the specified detail message and cause.
 * @param msg The detail message of the exception.
 * @param e The cause of the exception.
 */
    public GameException(String msg, Throwable e){
        super(msg, e);
    }
    
    
}
