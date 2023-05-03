package selfish.deck;
import java.io.Serializable;
/**
 * The Card class represents a single card in a deck.
 * @author Dasharn Dennis
 * @version 1
 */

public class Card implements Comparable, Serializable{
    /**
     * The name of the card.
     */
    private String name;
    /**
     * The description of the card.
     */
    private String description;
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new Card object with the given name and description.
     *
     * @param name the name of the card.
     * @param description the description of the card.
     */
    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }
    /**
     * Gets the description of the card.
     *
     * @return the description of the card.
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * Returns the name of the card as a string.
     *
     * @return the name of the card.
     */
    public String toString(){
        return this.name;
    }
    /**
     * The main method for the Card class. This method is not used in production and is only
     * provided for testing purposes.
     * @param args command line arguments.
     */
    public static void main(String[] args)  {
    }
    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}


