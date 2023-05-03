
package selfish.deck;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Serializable;
/**
 * The Deck class represents a deck of cards.
 * @author Dasharn Dennis
 * @version 1
 */
public abstract class Deck implements Serializable{
    /**
     * a list of cards
     */
    private Collection<Card> cards;
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;
     /**
     * Constructs a new, empty deck.
     */
    protected Deck(){
        cards = new ArrayList<Card>();

    }
    /**
     * Loads a list of Cards from a specified file path using a Last-In-First-Out (LIFO) logic.
     *
     * @param path the file path of the Cards to load
     * @return a List of Card objects in the order they were added to the list (LIFO)
     * @throws Exception if there is an error reading or parsing the file
     */
    protected static List<Card> loadCards(String path) throws Exception{
        //loadCards methods has a LIFO logic
        List<Card> listCards = new ArrayList<Card>();
        Card[] typeCards = stringToCards(path);
        for (int i = 0; i < typeCards.length; i++) {
            listCards.add(typeCards[i]);
        }
        return listCards;
       }
     /**
     * Converts a string representation of cards into an array of Card objects.
     *
     * @param str the string represenation of the cards
     * @return an array of Card objects.
     */
    protected static Card[] stringToCards(String str){
        List<Card> typeCards = new ArrayList<>();
        try (Scanner myReader = new Scanner(new File(str))) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(";");
                int numberOfCards = Integer.parseInt(parts[2]);
                for (int i = 0; i < numberOfCards; i++) {
                    Card card = new Card(parts[0], parts[1]);
                    typeCards.add(card);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return typeCards.toArray(new Card[0]);
        
    }
    /**
     * Adds a card to the deck
     * 
     * @param card the card to be added
     * @return the number of cards in the deck after the operation is complete
     */
     
    public int add(Card card){
        //Follows LIFO logic
        //use the add method to add the card to the deck
        //return the number of cards in the deck after the operation is complete
        cards.add(card);
        return cards.size();
    }
    /**
     * Adds a list of cards to the deck
     * 
     * @param cards the list of cards to be added
     * @return the number of cards in the deck after the operation is complete
     */
    protected int add(List<Card> cards){
        //Follows LIFO logic
        //use the add method to add each card 
        //in the list to the deck
        //return the number of cards in the deck after the operation is complete
        for (int i = 0; i < cards.size(); i++) {
            add(cards.get(i));
        }

        return cards.size();

        }
    /**
     * Draws a card from the deck
     * 
     * @return the card that was drawn
     */
    public Card draw(){
        //Follows LIFO logic
        //use the remove method to remove the first card from the deck
        //return the card that was removed
        Card card = cards.iterator().next();
        remove(card);
        return card;
    }
    /**
     * Removes a card from the deck
     * 
     * @param card the card to be removed
     */
    public void remove(Card card){
        //use the remove method to remove the card from top of the deck
        cards.remove(card);
    }
    
    /** Shuffles the deck of cards using the specified random number generator.
    * @param random the random number generator to use for shuffling the deck of cards; must not be {@code null}
    * 
    */
    public void shuffle(Random random){
        //use java.util.Collections and java.util.Random to shuffle the deck

        Collections.shuffle((List<?>) cards, random);

        

    }
    /**
     * Returns the number of cards currently in the deck
     * 
     * @return the number of cards currently in the deck
     */
    public int size(){
        //return the number of cards currently in the deck

        return cards.size();};
    
}

