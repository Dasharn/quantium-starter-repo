/**
 * The GameDriver class is responsible for driving the execution of the Moonbase Alpha game
 * It contains a helper function to centre text in a longer string
 * @author Dasharn Dennis
 * @version 1
 */

public class GameDriver {

    /**
     * A helper function to centre text in a longer String.
     * @param width The length of the return String.
     * @param s The text to centre.
     * @return A longer string with the specified text centred.
     * @author Dasharn Dennis
     * @version 1
     */
    public static String centreString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    /**
     * a public constructor for the GameDriver
     */
    public GameDriver() {}

    public static void main(String[] args)  {
        
    }

}