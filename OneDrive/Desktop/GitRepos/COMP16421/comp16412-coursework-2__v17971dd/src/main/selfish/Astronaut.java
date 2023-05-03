package selfish;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import selfish.deck.Card;
import selfish.deck.Oxygen;
import java.io.Serializable;
/**
 * The Astronaut class represents a player in the game.
 * @author Dasharn Dennis
 * @version 1
*/
public class Astronaut implements Serializable{
    //Write javadoc for all private attributes (game, actions,oxygens,name,track) in the Astronaut class
    /**
     * game refers to the current Game being played
     */
    private GameEngine game;
    /**
     * actions is the list of action cards that a player has the can be played
     */
    private List<Card> actions;
    /**
     * oxygens is the list of oxygen cards that a player can play
     */
    private List<Oxygen> oxygens;
    /**
     * name is the name of the player
     */
    private String name;
    /**
     * track is the collection of cards that a player can play
     */
    private Collection<Card> track;
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;
    /**Constructs an Astronaut object with the given name and GameEngine object.
     * @param name The name of the player.
     * @param game The GameEngine object that the player belongs to.
     * */
    public Astronaut(String name, GameEngine game) {
        this.game = game;
        this.name = name;
        this.actions = new ArrayList<>();
        this.track = new ArrayList<>();
        this.oxygens = new ArrayList<>();
    }
    /**Adds the given card to the player's hand.
     * @param card The card to be added.
    */
    public void addToHand(Card card){
        //add card to actions
        if (card instanceof Oxygen){
            oxygens.add((Oxygen) card);
        }
        else {
            actions.add(card);
        }
        
    }
    /**Adds the given card to the player's track.
     * @param card The card to be added.
     * */
    public void addToTrack(Card card){
        track.add(card);
        
    }
    /**Removes a single-value Oxygen but does not return it -- instead it adds the card to the game engine's gameDsicard pile and 
     * @return The remaining oxygen of the player (the toal value of all Oxygen cards, also accessible through a call to oxygenRemaining)
     * */
    public int breathe(){
        //remove a single value oxygen from the player's hand and add it to the game's discard pile
        for (Card card : oxygens) {
            if (((Oxygen) card).getValue() == 1) {
                oxygens.remove(card);
                game.getGameDiscard().add(card);
                break;
            }
        }
        return oxygenRemaining();
        
    }
    /**Returns the distance of the player from the ship.
     * @return The distance of the player from the ship.
     * */
    public int distanceFromShip(){
        //the ship is 6 cards away from a player when they start, calculate the distance and return it
        return 6 - track.size();
        
    }

    /**Returns the list of actions that the player can take.
     * @return The list of actions that the player can take.
     * */
    public List<Card> getActions() {
        // Create a custom comparator that sorts by name
        Comparator<Card> nameComparator = Comparator.comparing(Card:: toString);
        
        // Sort the actions list using the custom comparator
        Collections.sort(actions, nameComparator);
    
        // Return the sorted list
        return actions;
    }
    
    /**Returns the string representation of the list of actions that the player can take.
     * @param enumerated A boolean value indicating whether to enumerate the actions.
     * @param excludeShields A boolean value indicating whether to exclude shield cards.
     * @return The string representation of the list of actions that the player can take.
     * */
    public String getActionsStr(boolean enumerated, boolean excludeShields){
        List<String> actions = new ArrayList<String>();
        List<String> repeatedCards = new ArrayList<String>();
        List<String> cards = new ArrayList<String>();
        
        // get all non-shield cards in hand
        for (Card card : getActions()) {
            if (!excludeShields || !(card.toString().equals("Shield"))) {
                cards.add(card.toString());
            }
        }
        // sort cards alphabetically
        Collections.sort(cards);
    
        // group repeated cards
        for (String card : cards) {
            int count = Collections.frequency(cards, card);
            if (count > 1 && !repeatedCards.contains(card)) {
                repeatedCards.add(card);
                actions.add(count + "x " + card);
            } else if (!repeatedCards.contains(card)) {
                actions.add(card);
            }
        }
        // sort repeated cards alphabetically
        Collections.sort(repeatedCards);
    
        // add enumerated prefix to cards if needed
        if (enumerated) {
            for (int i = 0; i < actions.size(); i++) {
                String prefix = "[" + (char) (i + 65) + "] ";
                if (actions.get(i).contains("x")) {
                    actions.set(i, prefix + actions.get(i));
                } else {
                    actions.set(i, prefix + actions.get(i));
                }
            }
        }
        // join cards and repeated cards with ", "
        String actionsStr = String.join(", ", actions);
        // add repeated cards to end of string
        if (!repeatedCards.isEmpty()) {
            actionsStr += ", " + String.join(", ", repeatedCards);
        }
        // add semicolon to end of string if needed
        return actionsStr;
    }
    /**Returns the list of cards in the player's hand.
     * @return The list of cards in the player's hand.
     * */
    public List<Card> getHand(){
        List<Card> hand = new ArrayList<>();
        hand.addAll(actions);
        hand.addAll(oxygens);
        return hand;

        
    }
    /**Returns the string representation of the list of cards in the player's hand.
     * @return The string representation of the list of cards in the player's hand.
     * */
    public String getHandStr() {
        List<Card> hand = getHand();
        if (hand.isEmpty()) {
            return "Dead astronauts hold nothing in their hands";
        }
        StringBuilder handStr = new StringBuilder();
        Map<String, Integer> cardCount = new TreeMap<>();
        for (Card card : hand) {
            String cardName = card.toString();
            if (!cardCount.containsKey(cardName)) {
                cardCount.put(cardName, 1);
            } else {
                cardCount.put(cardName, cardCount.get(cardName) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : cardCount.entrySet()) {
            String cardName = entry.getKey();
            int count = entry.getValue();
            if (!(count == 1)){
            String cardStr = count + "x " + cardName;
            handStr.append(cardStr).append(", ");}
            else{
                String cardStr = cardName;
                handStr.append(cardStr).append(", ");
            }
        }
        handStr.deleteCharAt(handStr.length() - 1); // remove last comma
        handStr.deleteCharAt(handStr.length() - 1); // remove extra space
        return handStr.toString();
    }
    
    /**Returns the track of cards that the player has played.
     * @return The track of cards that the player has played.
     * */

    public Collection<Card> getTrack(){
        return track;
        
    }
    /**Hacks the given card and adds it to the player's hand.
     * @param card The card to be hacked.
     * */
    public void hack(Card card){
        //remove specified card from an astronaut's hand at it to to gameDiscard pile or spaceDiscard pile
        //Tether, Oxygen, Shield, Hack suit,Tractor beam, Rocket booster, Laser blast, Hole in suit cards are put in gameDiscard pile
        //Blank space, Useful junk, Mysterious nebula, Hyperspace, Meteoroid, Cosmic radiation, Asteroid field, Gravitational anomaly, Wormhole,Solar flare are put in spaceDiscard pile

        if (card.toString().equals("Tether") || card.toString().equals("Oxygen") || card.toString().equals("Shield") || card.toString().equals("Hack suit") || card.toString().equals("Tractor beam") || card.toString().equals("Rocket booster") || card.toString().equals("Laser blast") || card.toString().equals("Hole in suit")){
            //remove card from hand
            getActions().remove(card);
            game.getGameDiscard().add(card);
        }
        else{
            if (card instanceof Oxygen){
                oxygens.remove(card);
                game.getSpaceDiscard().add(card);
            }
            else{
                actions.remove(card);
                game.getSpaceDiscard().add(card);
            }
        }

    }
    /**
     * * Hacks the card with the given name and adds it to the player's hand.
     * @param card The name of the card to be hacked.
     * @return The hacked card.
     *  */
    public Card hack(String card){
        //remove specified card from an astronaut's hand at it to to gameDiscard pile or spaceDiscard pile
        //Tether, Oxygen, Shield, Hack suit,Tractor beam, Rocket booster, Laser blast, Hole in suit cards are put in gameDiscard pile
        //Blank space, Useful junk, Mysterious nebula, Hyperspace, Meteoroid, Cosmic radiation, Asteroid field, Gravitational anomaly, Wormhole,Solar flare are put in spaceDiscard pile
        for (Card cardsCard : getHand()){
            if (cardsCard.toString().equals(card)){
                hack(cardsCard);
                return cardsCard;
            }
        }

        return null;
        
    }
    /**
     * Checks whether the player has the card with the given name.
    *@param card The name of the card to check.
     * @return The number of copies of the card that the player has.
     * */
    public int hasCard(String card){
        int count = 0;
        for (Card cardsCard : getHand()){
            if (cardsCard.toString().equals(card)){
                count++;
            }
        }
        return count;
    }
    /**
     * * Checks whether the player has the Melted Eyeballs card.
    * @return true if the player has the Melted Eyeballs card, false otherwise.
     */
    public boolean hasMeltedEyeballs(){
        //returns true if there is a Solar flare directlly behind the astronaut
        for (Card card : track){
            if (card.toString().equals("Solar flare")){
                return true;
            }
        }
        return false;
    }
    /**
    * Checks whether the player has won the game.
    * @return true if the player has won the game, false otherwise.
    */
    public boolean hasWon(){
        if (((6 - track.size())== 0) && isAlive()){
            return true;
        }
        else{
            return false;}
    
    }
    /**
    * Checks whether the player is still alive.
    * @return true if the player is still alive, false otherwise.
    */
    public boolean isAlive(){
        if (oxygens.size() > 0){
            return true;
        }
        else{ 
            toString();
            return false;

        }
        
    }
    /**
     * Uses the player's Laser Blast card to attack another player.
     * @return The attacked player's card that was destroyed by the Laser Blast, or null if no player was attacked.
     */

    public Card laserBlast() {
        // Check if the track is empty
        if (track.isEmpty()) {
            return null;
        }
    
        // Remove and return the topmost card from the track
        Card removedCard = ((List<Card>) track).get(track.size() - 1);
        track.remove(removedCard);
        return removedCard;
    }
    /**
    * Returns the amount of oxygen remaining for the player.
    * @return The amount of oxygen remaining for the player.
    */
    public int oxygenRemaining(){
        //loop through all of oxygen cards remaining in Astronaut's hand
        //calculate total value of all oxygen cards 
        //return total oxygen available

        int totalOxygen = 0;
        for (Oxygen oxygen : oxygens){
            totalOxygen += oxygen.getValue();
        }
        return totalOxygen;

    }
    /**
    * Peeks at the top card on the player's track without removing it.
    * @return The top card on the player's track.
    */
    public Card peekAtTrack(){
        if (track.size() > 0){
        Card behindCard = ((List<Card>) track).get(track.size() - 2);
        return behindCard;}
        else{
            return null;
        }
        
    }
    /**
    * Siphons oxygen from the ship's supply and adds it to the player's remaining oxygen.
    * @return The amount of oxygen siphoned.
    */
    public Oxygen siphon(){
       
        return new Oxygen(0);
        
    }
    /**
    * Steals a random card from another player's hand and adds it to the player's hand.
    * @return The stolen card.
    */
    public Card steal(){
        //removes and returns a random Card from an astronaut's hand
        //if the astronaut has no cards in their hand, return null

        if (getHand().size() == 0){
            return null;
        }
        else{
            Random rand = new Random();
            int randomIndex = rand.nextInt(getHand().size());
            Card randomCard = ((List<Card>) getHand()).get(randomIndex);
            getHand().remove(randomCard);
            return randomCard;
        }
        
    }
    /**
    * Swaps the track of the astronaut with another astronaut.
    * @param swapee the astronaut whose track will be swapped with another astronaut's track; must not be {@code null}
    */
    public void swapTrack(Astronaut swapee){
        //swaps the track of the astronaut with another astronaut
        //if the astronaut is null, throw an IllegalArgumentException
        //if the astronaut is the same as the astronaut who is swapping, throw an IllegalArgumentException
        //if the astronaut is dead, throw an IllegalStateException
        //if the astronaut is not dead, swap the tracks of the two astronauts

        if (swapee == null){
            throw new IllegalArgumentException("Astronaut cannot be null");
        }
        else if (swapee == this){
            throw new IllegalArgumentException("Astronaut cannot be the same as the astronaut who is swapping");
        }
        else if (swapee.isAlive() == false){
            throw new IllegalStateException("Astronaut is dead");
        }
        else{
            List<Card> temp = new ArrayList<Card>();
            temp.addAll(track);
            track.clear();
            track.addAll(swapee.getTrack());
            swapee.getTrack().clear();
            swapee.getTrack().addAll(temp);
        }

    }
    /** Returns a string representation of the player.
     * @return A string representation of the player.
     */
    public String toString(){
        //if oxgen is greater than zero return players name in this format "<name>"
        if (oxygens.size() > 0){

            String message = String.format("%s", this.name);
            return message;

            
        }
        else{
            String message = String.format("%s (is dead)", this.name);
            return message;
        }
        
    
    }
    
    
}
