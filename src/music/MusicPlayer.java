package music;

import sprites.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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

   private static Clip backgroundClip;

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
   public void playMusic(URL filepath) {
      try {
         AudioInputStream audioInput = AudioSystem.getAudioInputStream(filepath);
         Clip clip = AudioSystem.getClip();
         clip.open(audioInput);
         clip.start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void playBackgroundMusic() {
      try {
         AudioInputStream audioInput = AudioSystem.getAudioInputStream(backgroundMusic.getSound());
         backgroundClip = AudioSystem.getClip();
         backgroundClip.open(audioInput);
         backgroundClip.start();
         backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void stopBackgroundMusic() {
      backgroundClip.stop();
   }

   public void setVolume(float volume, Clip clip) {
      if (volume < 0f || volume > 1f)
         throw new IllegalArgumentException("Volume not valid: " + volume);
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(20f * (float) Math.log10(volume));
   }

   public float getVolume(Clip clip) {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      return (float) Math.pow(10f, gainControl.getValue() / 20f);
   }
}
