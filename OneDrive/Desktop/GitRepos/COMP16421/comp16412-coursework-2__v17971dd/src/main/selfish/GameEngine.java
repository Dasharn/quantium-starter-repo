
package selfish;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import selfish.deck.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * The GameEngine class represents the engine of the game. It is responsible for managing the game
 * state and handling player actions.
 * The engine initializes and manages the game decks, creates and manages the astronaut players, 
 * and controls the flow of the game.
 * Once the game has been initialized, it can be started with the {@code startGame()} method, which 
 * will begin the first turn of the game. The engine will then alternate between turns until the
 * game ends, which can be checked with the {@code gameOver()} method. The winner of the game can
 * be obtained with the {@code getWinner()} method.</p>
 * The engine can be saved and loaded using the {@code saveState()} and {@code loadState()} methods 
respectively, allowing players to save their progress and resume at a later time
* @author Dasharn Dennis
* @version 1
 */
public class GameEngine implements Serializable{
    //Write javadoc below for all of these private attributes
   
    /**
     * activePlayers is a collection of Astronaut players
     */
    private Collection<Astronaut> activePlayers;
    /**
     * corpses is a list of dead astronaut players
     */
    private List<Astronaut> corpses;
    /**
     * currentPlayer is the the player whose turn is in progress
     */
    private Astronaut currentPlayer;
    /**
     * hasStarted is a flag for whether the game has started
     */
    private boolean hasStarted;
    /**
     * random is a random number generator
     */
    private Random random;
    /**
     * gameDeck is the deck of action cards
     */
    private GameDeck gameDeck;
    /**
     * gameDiscard is the deck of discarded actiosn cards
     */
    private GameDeck gameDiscard;
    /**
     * spaceDeck is the deck of space cards
     */
    private SpaceDeck spaceDeck;
    /**
     * spaceDiscard is the deck of discarded space cards
     */
    private SpaceDeck spaceDiscard;
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;
    /**
     * Private GameEngine constructor method
     */
    private GameEngine(){}
    /**
    * Constructs a new GameEngine object with the specified seed and decks.
    * @param seed the seed used to initialize the random number generator
    * @param gameDeck the file path of the game deck to use
    * @param spaceDeck the file path of the space deck to use
    * @throws Exception if there is an error reading or parsing the game or space decks
    */
    public GameEngine(long seed, String gameDeck, String spaceDeck) throws Exception{
        random = new Random(seed);
        
        // Initialize gameDeck and spaceDeck
        try {
            this.gameDeck = new GameDeck(gameDeck);
            this.spaceDeck = new SpaceDeck(spaceDeck);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find one or more input files.");
            throw e;
        }
        
        this.gameDiscard = new GameDeck();
        this.spaceDiscard = new SpaceDeck();
        
        // Shuffle the decks
        this.gameDeck.shuffle(random);
        this.spaceDeck.shuffle(random);
        
        // Initialize other variables
        this.activePlayers = new ArrayList<Astronaut>();
        this.corpses = new ArrayList<Astronaut>();
        this.currentPlayer = null;
        this.hasStarted = false;
    }
    /**Adds a new player to the game.
     * @param player The name of the player to add.
     * @return The total number of players in the game, including the new player.
     * */
        
    
    public int addPlayer(String player){
       
        Astronaut newPlayer = new Astronaut(player, this);
        activePlayers.add(newPlayer);
        
        return activePlayers.size();

        };
    /**
     * Ends the current turn and advances to the next player's turn
     * @return The number of the player who just ended their turn
     */
    public int endTurn(){
       
        return 0;
        

    }
    /**
     * Checks whether the game is over.
     * @return {@code true} if the game is over, {@code false} otherwise.
*/

    public boolean gameOver(){
        return false;
    }
    /**
     * Gets a list of all players in the game, including dead players and the current player
     * @return A list of all players in the game
     */
    public List<Astronaut> getAllPlayers(){
        
        List<Astronaut> allPlayers = new ArrayList<Astronaut>();
        allPlayers.addAll(activePlayers);
        allPlayers.addAll(corpses);
        allPlayers.add(currentPlayer);
        return allPlayers;}
    
    /**Gets the current player of the game.
     * @return The current player of the game.
     */


    public Astronaut getCurrentPlayer(){
        return currentPlayer;};
    /**Gets the total number of players (active, dead and current player).
     * @return The total number of players in the game.
     * */

    public int getFullPlayerCount(){
        
        return activePlayers.size() + corpses.size() + 1; //+1 for currentPlayer;
        }
    /**
     * Gets the game deck.
     * @return The game deck.
     */
    
    public GameDeck getGameDeck(){
        return gameDeck;}
    /**Gets the game discard deck.
     * @return The game discard deck.
     * */
    
    public GameDeck getGameDiscard(){
        return gameDiscard;}
    /**
     * Gets the space deck.
     * @return The space deck.
     * */

    
    public SpaceDeck getSpaceDeck(){
            return spaceDeck;}
    /**Gets the space discard deck.
     * @return The space discard deck.
    */
        
    public SpaceDeck getSpaceDiscard(){
        return spaceDiscard;}
    /**Gets the winner of the game.
     * @return The astronaut that won the game.
     */

    public Astronaut getWinner(){
        return currentPlayer;}
    
    /**Removes the given player from the active player list and adds it to the corpses list.
     * @param corpse The astronaut to be killed.
    */
    public void killPlayer(Astronaut corpse){
        
        corpses.add(corpse);
        activePlayers.remove(corpse);

    }
    /**Loads a saved game state from a file.
     * @param corpse the name of the file containing the saved game state
     * @return the GameEngine object containing the loaded game state
     * @throws GameException if there was an error loading the file or deserializing the object
     */
    public static GameEngine loadState(String corpse) throws GameException{
        
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(corpse));
            GameEngine game = (GameEngine) in.readObject();
            in.close();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            String msg = "Error loading game state file: " + e.getMessage();
            throw new GameException(msg, e);
        }
    }
    /**
     * Merges the two given decks.
     * @param deck1 The first deck to be merged.
     * @param deck2 The second deck to be merged.
     */
    public void mergeDecks(Deck deck1, Deck deck2){
    

    }
    /**Saves the current game state to a file.
     * @param path the path and name of the file to save the game state to
     * @throws GameException if there was an error saving the file
    */

    public void saveState(String path) throws GameException{
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
            out.close();
            System.out.println("Game state saved to " + path);
        } catch (IOException e) {
            String msg = "Error saving game state file: " + e.getMessage();
            throw new GameException(msg, e);
            
        }
        
    }
    /**
     * Splits the given oxygen value into two values.
     * @param dbl The oxygen value to be split.
     * @return An array containing the two split oxygen values.
     */
    public Oxygen[] splitOxygen(Oxygen dbl){
       
        return null;}
    /**
     * Starts the game.
     */
    public void startGame(){
        hasStarted = true;
        //use startGame method to deal out cards to each player and set hasStarted
        //First deal out the Oxygen cards (value 2 cards first, then value 1), then deal four cards to each player by cycling through the players




    }
    /**Starts a new turn by setting the current player to the next active player in the game and resetting their oxygen level.
     * If there are no more active players in the game, the game ends and the winner is the player with the most oxygen.
     * If the game has not yet started, this method has no effect.
*/
    public void startTurn(){
       }
    /**
     * Moves the specified astronaut to a new location on the game board and returns the card drawn as a result of the travel action.
     * @param traveller the astronaut who will be moving to a new location
     * @return the card drawn as a result of the travel action
*/
    public Card travel(Astronaut traveller){
      

        return null;}
    
    
}
