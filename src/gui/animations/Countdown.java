package gui.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.animations.infrastructure.Animation;
import gui.animations.infrastructure.AnimationRunner;
import music.MusicPlayer;
import sprites.infrastructure.SpriteCollection;
import java.awt.Color;
import utilities.Timer;

/**
 *
 */
public class Countdown implements Animation {
   private static final Color DARK_YELLOW = new Color(255, 204, 0);

   private int countFrom;
   private SpriteCollection gameScreen;
   private KeyboardSensor keyboardSensor;
   private AnimationRunner animationRunner;
   private boolean stop;
   private Timer timer;
   private MusicPlayer musicPlayer;

   /**
    * @param countFrom
    * @param gameScreen
    */
   public Countdown(int countFrom, SpriteCollection gameScreen, AnimationRunner ar, KeyboardSensor ks, MusicPlayer musicPlayer) {
      this.countFrom = countFrom;
      this.gameScreen = gameScreen;
      animationRunner = ar;
      keyboardSensor = ks;
      this.stop = false;
      timer = new Timer(0, 0, countFrom);
      timer.countdownTimerInit();
      this.musicPlayer = musicPlayer;
   }

   /**
    * @param d
    */
   @Override
   public void doOneFrame(DrawSurface d) {
      gameScreen.drawAllOn(d);
      if (keyboardSensor.isPressed("enter")) {
         timer = new Timer(0, 0, timer.getTimeInSeconds());
         musicPlayer.pauseBackgroundMusic();
         animationRunner.run(new KeyPressStoppable(keyboardSensor, "space", new PauseScreen(keyboardSensor, gameScreen)));
         musicPlayer.unpauseBackgroundMusic();
         timer.countdownTimerInit();
      }
      d.setColor(DARK_YELLOW);
      if (timer.getTimeInSeconds() > countFrom) {
         return;
      } else if (timer.getTimeInSeconds() > 0 && timer.getTimeInSeconds() <= countFrom) {
         d.drawText((int) (d.getWidth() / 2.4), d.getHeight() / 5, "Game starts in " + timer.getTimeInSeconds(), 30);
      } else if (timer.getTimeInSeconds() <= 0) {
         this.stop = true;
      }
   }

   /**
    * @return
    */
   @Override
   public boolean shouldStop() {
      return this.stop;
   }
}
