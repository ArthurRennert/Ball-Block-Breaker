package utilities;

/**
 * A simple utility class that represents a counter for keeping score or tracking values.
 */
public class Counter {
   private int value;

   /**
    * Increases the current count by the specified number.
    *
    * @param number the amount to increase.
    */
   public void increase(int number) {
      value = value + number;
   }

   /**
    * Decreases the current count by the specified number.
    *
    * @param number the amount to decrease.
    */
   public void decrease(int number) {
      value = value - number;
   }

   /**
    * Returns the current count value.
    *
    * @return the current count.
    */
   public int getValue() {
      return value;
   }

   /**
    * Sets the counter to the specified value.
    *
    * @param val the value to set.
    */
   public void setValue(int val) {
      value = val;
   }

   /**
    * Returns a string representation of the counter.
    *
    * @return a string in the format "Score: X".
    */
   @Override
   public String toString() {
      return "Score: " + value;
   }
}