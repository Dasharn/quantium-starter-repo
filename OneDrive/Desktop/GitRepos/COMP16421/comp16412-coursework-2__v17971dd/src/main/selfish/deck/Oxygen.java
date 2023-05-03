package selfish.deck;

import java.io.Serializable;

/**
 * A class representing a Oxygen card that is a subclass of the Card class
 * @author Dasharn Dennis
 * @version 1
 */
public class Oxygen extends Card implements Comparable, Serializable{
    /**
     * The value of the oxygen level
     */
    private int value;
    /**
     * The version of UID of the class for serialization purposes
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructs an Oxygen object with a specified value
     * @param value the value of the oxygen level
     */
    public Oxygen(int value) {
        super("Oxygen Card", "Oxygen");
        this.value = value;
    }
    /**
     * Returns the current value of the oxygen level
     * @return the current value of the oxygen level
     */
    public int getValue(){
        return this.value;
    }
    /**
     * Returns a string representatino of the oxygen level object
     * @return a string representation fo the oxygen level object
     */
    public String toString(){
        return "Oxygen(" + this.value + ")";
    }
    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
