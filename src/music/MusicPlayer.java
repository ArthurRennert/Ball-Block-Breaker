package music;

import sprites.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.List;

/**
 *
 */
public class MusicPlayer {
   private Sound backgroundMusic;
   private Sound paddleHit;
   private Sound frameBlockHit;
   private Sound pitBlockHit;
   private List<Sound> gameBlockHit;
   private boolean isSingleGameBlockSound;
   private Sound silence;

   private static Clip backgroundClip;
   private static long backgroundClipTime;

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
   public void setGameBlockHit(List<Sound> gameBlockHit, boolean isSingleGameBlockSound) {
      this.isSingleGameBlockSound = isSingleGameBlockSound;
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
         if (isSingleGameBlockSound) {
            playMusic(gameBlockHit.get(0).getSound());
         } else {
            playMusic(gameBlockHit.get((int) (Math.random() * gameBlockHit.size())).getSound());
         }
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

   /**
    * @param volume
    */
   public void playBackgroundMusic(float volume) {
      try {
         AudioInputStream audioInput = AudioSystem.getAudioInputStream(backgroundMusic.getSound());
         backgroundClip = AudioSystem.getClip();
         backgroundClip.open(audioInput);
         setVolume(volume, backgroundClip);
         backgroundClip.start();
         backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   /**
    *
    */
   public void pauseBackgroundMusic() {
      backgroundClipTime = backgroundClip.getMicrosecondPosition();
      backgroundClip.stop();
   }

   /**
    *
    */
   public void unpauseBackgroundMusic() {
      backgroundClip.setMicrosecondPosition(backgroundClipTime);
      backgroundClip.start();
      backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   /**
    *
    */
   public void stopBackgroundMusic() {
      backgroundClip.stop();
   }

   /**
    * @param volume
    * @param clip
    */
   public void setVolume(float volume, Clip clip) {
      if (volume < 0f || volume > 1f)
         throw new IllegalArgumentException("Volume not valid: " + volume);
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(20f * (float) Math.log10(volume));
   }

   /**
    * @param clip
    * @return
    */
   public float getVolume(Clip clip) {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      return (float) Math.pow(10f, gainControl.getValue() / 20f);
   }
}
