package Listeners;

/**
 * A class that is used to count things.
 * @author Shlomo Shatz   */
public class Counter {

    //The value of the counter
    private int value;

    /**
     * The constructor of the class.
     * @param i The integer that the counter is being set to
     */
    public Counter(int i) {
        this.value = i;
    }

    /**
     * Increases the value based on a given number.
     * @param number The number being added to the counter
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the value based on a given number.
     * @param number The number decreased from the counter
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * A getter for the value of the class.
     * @return The value of the counter
     */
    public int getValue() {
        return this.value;
    }
}
