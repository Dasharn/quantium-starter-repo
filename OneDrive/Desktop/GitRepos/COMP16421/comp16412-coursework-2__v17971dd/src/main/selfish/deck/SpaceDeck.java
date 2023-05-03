package selfish.deck;
/**
 * SpaceDeck is a subclass of Deck that represents a deck of cards in a space-themed card game.
 * The deck contains cards that represent various space-related events, such as asteroid fields,
 * cosmic radiation, gravitational anomalies, hyperspace travel, meteoroids, nebulae, solar flares,
 * wormholes, and useful junk that can be salvaged.
 * This class provides a default constructor that creates an instance of the Deck class, and
 * a constructor that takes a file path as a parameter to create an instance of the Deck class.
 * The class also includes public fields that represent the names of the cards in the deck, and
 * a serialVersionUID field for serialization purposes.
 *@author Dasharn Dennis
 *@version 1
*/
public class SpaceDeck extends Deck{
    /**
     *  ASTEROID_FIELD is a type of Space Card
     */
    public static final String ASTEROID_FIELD = "Asteroid Field";
    /**
     *  BLANK_SPACE is a type of Space Card
     */
    public static final String BLANK_SPACE = "Blank Space";
    /**
     * COSMIC_RADIATION is a type of Space Card
     */
    public static final String COSMIC_RADIATION = "Cosmic Radiation";
    /**
     *  GRAVITATIONAL_ANOMALY is a type of Space Card
     */
    public static final String GRAVITATIONAL_ANOMALY = "Gravitational Anomaly";
    /**
     *  HYPERSPCACE is a type of Space Card
     */
    public static final String HYPERSPACE = "Hyperspace";
    /**
     *  METEOROID is a type of Space Card
     */
    public static final String METEOROID = "Meteroid";
    /**
     *  MYSTERIOUS_NEBULA is a type of Space Card
     */
    public static final String MYSTERIOUS_NEBULA = "Mysterious Nebula";
    /**
     *  Solar Flare is a type of Space Card
     */
    public static final String SOLAR_FLARE = "Solar Flare";
    /**
     *  USEFUL_JUNK is a type of Space Card
     */
    public static final String USEFUL_JUNK = "Useful Junk";
    /**
     *  Wormhole is a type of Space Card
     */
    public static final String WORMHOLE = "Wormhole";
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructs a new SpaceDeck object with no parameters
     * Createes an instance of the Deck class
     */

    public SpaceDeck() {}
        
    /**
     * Create instance of Deck Class
     * Creates an instance of the Deck class with the specified path
     * @param path the path to the file containing the deck of cards
     * @throws Exception
     */
    public SpaceDeck(String path) throws Exception {


        loadCards(path);
        

    }
    
}
