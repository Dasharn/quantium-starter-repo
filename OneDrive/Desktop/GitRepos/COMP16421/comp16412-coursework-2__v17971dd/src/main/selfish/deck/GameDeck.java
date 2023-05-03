
package selfish.deck;
/**
 * The GameDeck class extends the Deck class and represents a deck of cards specific to the game
 * It contains constants representing the different tyeps of cards and methods for drawing and splitting oxygen cards
 * @author Dasharn Dennis
 * @version 1
 * 
 */

public class GameDeck extends Deck{
    /**
     * Constant representing the "Hack suit"
     */
    public static final String HACK_SUIT = "Hack suit";
    /**
     * Constant representing the "Hole in suit" card
     */
    public static final String HOLE_IN_SUIT = "Hole in suit";
    /**
     * Constant representing the "Laser blast" card
     */
    public static final String LASER_BLAST = "Laser blast";
    /**
     * Constant representing the "Oxygen" card
     */
    public static final String OXYGEN = "Oxygen";
    /**
     * Constant representing the "Oxygen(1)" card.
     */
    public static final String OXYGEN_1 = "Oxygen(1)";
    /**
     * Constant representing the "Oxygen(2)" card.
     */
    public static final String OXYGEN_2 = "Oxygen(2)";
    /**
     * Constant representing the "Oxygen siphon" card
     */
    public static final String OXYGEN_SIPHON = "Oxygen siphon";
    /**
     * Constant representing the "Rocket booster" card
     */
    public static final String ROCKET_BOOSTER = "Rocket booster";
    /**
     * Constant representing the "Shield" card
     */
    public static final String SHIELD = "Shield";
    /**
     * Constant representing the "Tether" card
     */
    public static final String TETHER = "Tether";
    /**
     * Constant representing the "Tractor beam" card
     */
    public static final String TRACTOR_BEAM = "Tractor beam";
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;
    //subclass of Deck

    public GameDeck(){
    
    }
        
    /**
     * Constructs a new GameDeck instance by loading cards from the specified file path
     * 
     * @param path the file path to load cards from.
     * @throws Exception
     */
    public GameDeck(String path) throws Exception {
        /*
         * Create instance of Deck class
         */
        loadCards(path);
    
    }
    /**
     * Draws an Oxygen cards from the deck with the specified value
     * 
     * @param value The value of the Oxygen card to draw
     * @return An Oxygen object representing the drawn card
     */
    public Oxygen drawOxygen(int value) {
        
        return new Oxygen(0);
        
    }
    /**
     * Splits the specified Oxygen card into two Oxygen cards of equal or near equal value
     * @param dbl The Oxygen card to split
     * @return An array of two Oxygen objects representing the split cards
     */
    public Oxygen[] splitOxygen(Oxygen dbl){
        return new Oxygen[0];
        
    }
    
}
