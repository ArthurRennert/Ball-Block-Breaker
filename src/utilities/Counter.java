package utilities;

/**
 *
 */
public class Counter {

   private int value;

//   public Counter(int val) {
//      value = val;
//   }

   /**
    * @param number
    */
   // add number to current count.
   public void increase(int number) {
      value = value + number;
   }

   /**
    * @param number
    */
   // subtract number from current count.
   public void decrease(int number) {
      value = value - number;
   }

   /**
    * @return
    */
   // get current count.
   public int getValue() {
      return value;
   }

   /**
    * @param val
    */
   public void setValue(int val) {
      value = val;
   }

   @Override
   public String toString() {
      return "Score: " + value;
   }
}