package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 *
 */
public class Timer {
   private static DecimalFormat dFormat = new DecimalFormat("00");
   private javax.swing.Timer timer;
   private int second;
   private int minute;
   private int hour;

   /**
    * @param hour
    * @param minute
    * @param second
    */
   public Timer(int hour, int minute, int second) {
      this.second = second;
      this.minute = minute;
      this.hour = hour;
   }

   /**
    * @param hour
    * @param minute
    * @param second
    */
   public void setTimer(int hour, int minute, int second) {
      this.second = second;
      this.minute = minute;
      this.hour = hour;
   }

   /**
    * @return
    */
   public int getTimeInSeconds () {
      return hour * 60 * 60 + minute * 60 + second;
   }
   /**
    *
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
    *
    */
   public void stopTimer() {
      timer.stop();
   }

   /**
    *
    */
   public void restartTimer() {
      timer.restart();
   }

   /**
    *
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
    * @return
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
    * @return
    */
   public int getHour() {
      return hour;
   }

   /**
    * @return
    */
   public int getMinute() {
      return minute;
   }

   /**
    * @return
    */
   public int getSecond() {
      return second;
   }
}
