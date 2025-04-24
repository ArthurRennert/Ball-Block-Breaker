package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * A timer utility that tracks and formats time in hours, minutes, and seconds.
 * Supports both regular counting and countdown modes.
 */
public class Timer {
   private static DecimalFormat dFormat = new DecimalFormat("00");
   private javax.swing.Timer timer;
   private int second;
   private int minute;
   private int hour;

   /**
    * Constructs a new Timer with the specified initial time.
    * @param hour initial hours
    * @param minute initial minutes
    * @param second initial seconds
    */
   public Timer(int hour, int minute, int second) {
      this.second = second;
      this.minute = minute;
      this.hour = hour;
   }

   /**
    * Sets the timer to a new time.
    * @param hour new hour
    * @param minute new minute
    * @param second new second
    */
   public void setTimer(int hour, int minute, int second) {
      this.second = second;
      this.minute = minute;
      this.hour = hour;
   }

   /**
    * Returns the total time in seconds.
    * @return total time in seconds
    */
   public int getTimeInSeconds () {
      return hour * 60 * 60 + minute * 60 + second;
   }

   /**
    * Initializes and starts a timer that counts forward every second.
    */
   public void timerInit() {
      timer = new javax.swing.Timer(1000, e -> {
         second++;
         if (second == 60) {
            second = 0;
            minute++;
         }
         if (minute == 60) {
            minute = 0;
            hour++;
         }
      });
      timer.start();
   }

   /**
    * Stops the timer if it's currently running.
    */
   public void stopTimer() {
      timer.stop();
   }

   /**
    * Restarts the timer from its current time.
    */
   public void restartTimer() {
      timer.restart();
   }

   /**
    * Initializes and starts a countdown timer.
    * When the time reaches 00:00:00, the timer stops.
    */
   public void countdownTimerInit() {
      timer = new javax.swing.Timer(1000, e -> {
         second--;
         if (second == -1) {
            second = 59;
            minute--;
         }
         if (minute == -1) {
            minute = 59;
            hour--;
         }
         if (hour == 0 && minute == 0 && second == -1) {
            timer.stop();
         }
      });
      timer.start();
   }

   /**
    * Returns a formatted string representing the current time.
    * @return formatted time string (MM:SS or HH:MM:SS)
    */
   public String showTimer() {
      String ddSecond = dFormat.format(second);
      String ddMinute = dFormat.format(minute);
      String ddHour = dFormat.format(hour);

      String resWithoutHour = ddMinute + ":" + ddSecond;
      String resWithHour = ddHour + ":" + ddMinute + ":" + ddSecond;

      String res = hour > 0 ? resWithHour : resWithoutHour;
      return res;
   }

   /**
    * @return current hour
    */
   public int getHour() {
      return hour;
   }

   /**
    * @return current minute
    */
   public int getMinute() {
      return minute;
   }

   /**
    * @return current second
    */
   public int getSecond() {
      return second;
   }
}
