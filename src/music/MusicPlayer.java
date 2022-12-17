package music;

import sprites.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 *
 */
public class MusicPlayer {
   private Sound backgroundMusic;
   private Sound paddleHit;
   private Sound frameBlockHit;
   private Sound pitBlockHit;
   private Sound gameBlockHit;
   private Sound silence;
   private Sound terminator;


   /**
    *
    */
   public MusicPlayer() {
      silence = new Sound("/Silence.wav");
      playMusic(silence.getSound());
   }

   /**
    * @param backgroundMusic
    */
   public void setBackgroundMusic(Sound backgroundMusic) {
         this.backgroundMusic = backgroundMusic;
   }

   /**
    * @param paddleHit
    */
   public void setPaddleHit(Sound paddleHit) {
      this.paddleHit = paddleHit;
   }


   /**
    * @param frameBlockHit
    */
   public void setFrameBlockHit(Sound frameBlockHit) {
      this.frameBlockHit = frameBlockHit;
   }

   /**
    * @param pitBlockHit
    */
   public void setPitBlockHit(Sound pitBlockHit) {
      this.pitBlockHit = pitBlockHit;
   }

   /**
    * @param gameBlockHit
    */
   public void setGameBlockHit(Sound gameBlockHit) {
      this.gameBlockHit = gameBlockHit;
   }

   /**
    * @param beingHit
    */
   public void playMusic(Block beingHit) {
      if (beingHit.getName().equals("Paddle")) {
         playMusic(paddleHit.getSound());
      } else if (beingHit.getName().equals("FrameBlock")) {
         playMusic(frameBlockHit.getSound());
      } else if (beingHit.getName().equals("PitBlock")) {
         playMusic(pitBlockHit.getSound());
      } else if (beingHit.getName().equals("GameBlock")) {
         playMusic(gameBlockHit.getSound());
      }
   }

   /**
    * @param filepath
    */
   private void playMusic(URL filepath) {
      try {
         AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
         Clip clip = AudioSystem.getClip();
         clip.open(audioInput);
         clip.start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    *
    */
   public void playBackgroundMusic() {
      playMusic(backgroundMusic.getSound());
   }
}
