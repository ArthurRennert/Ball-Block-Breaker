package music;

import sprites.Block;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.List;

/**
 * The MusicPlayer class is responsible for managing and playing sound effects and background music
 * in the game. It supports setting sounds for specific block types, volume control,
 * continuous background music looping, and pause/unpause functionality.
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
    * Constructs a MusicPlayer and plays a default silent sound to initialize audio systems.
    */
   public MusicPlayer() {
      silence = new Sound("/Silence.wav");
      playMusic(silence.getSound());
   }

   /**
    * Sets the background music sound clip.
    *
    * @param backgroundMusic the background music sound
    */
   public void setBackgroundMusic(Sound backgroundMusic) {
         this.backgroundMusic = backgroundMusic;
   }

   /**
    * Sets the paddle hit sound.
    *
    * @param paddleHit the paddle hit sound
    */
   public void setPaddleHit(Sound paddleHit) {
      this.paddleHit = paddleHit;
   }


   /**
    * Sets the frame block hit sound.
    *
    * @param frameBlockHit the frame block hit sound
    */
   public void setFrameBlockHit(Sound frameBlockHit) {
      this.frameBlockHit = frameBlockHit;
   }

   /**
    * Sets the pit block hit sound.
    *
    * @param pitBlockHit the pit block hit sound
    */
   public void setPitBlockHit(Sound pitBlockHit) {
      this.pitBlockHit = pitBlockHit;
   }

   /**
    * Sets the game block hit sounds.
    *
    * @param gameBlockHit a list of game block hit sounds
    * @param isSingleGameBlockSound true if only one sound should be used for all blocks
    */
   public void setGameBlockHit(List<Sound> gameBlockHit, boolean isSingleGameBlockSound) {
      this.isSingleGameBlockSound = isSingleGameBlockSound;
      this.gameBlockHit = gameBlockHit;
   }

   /**
    * Plays the sound corresponding to the type of the block that was hit.
    *
    * @param beingHit the block that was hit
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
    * Plays a sound from a given file path (URL).
    *
    * @param filepath the URL to the sound file
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
    * Plays the background music in a loop with the specified volume.
    *
    * @param volume the volume level (0.0 to 1.0)
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
    * Pauses the currently playing background music and stores the position for later resume.
    */
   public void pauseBackgroundMusic() {
      backgroundClipTime = backgroundClip.getMicrosecondPosition();
      if (backgroundClipTime >= backgroundClip.getMicrosecondLength()) {
         backgroundClipTime = backgroundClipTime - ((int) (backgroundClipTime / backgroundClip.getMicrosecondLength()) * backgroundClip.getMicrosecondLength());
      }
      backgroundClip.stop();
   }

   /**
    * Resumes playing the background music from the last paused position.
    */
   public void unpauseBackgroundMusic() {
      backgroundClip.setMicrosecondPosition(backgroundClipTime);
      backgroundClip.start();
      backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   /**
    * Stops the background music playback.
    */
   public void stopBackgroundMusic() {
      backgroundClip.stop();
   }

   /**
    * Sets the volume for the specified audio clip.
    *
    * @param volume the volume level (0.0 to 1.0)
    * @param clip the audio clip
    */
   public void setVolume(float volume, Clip clip) {
      if (volume < 0f || volume > 1f)
         throw new IllegalArgumentException("Volume not valid: " + volume);
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(20f * (float) Math.log10(volume));
   }

   /**
    * Gets the current volume of the specified audio clip.
    *
    * @param clip the audio clip
    * @return the current volume level (0.0 to 1.0)
    */
   public float getVolume(Clip clip) {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      return (float) Math.pow(10f, gainControl.getValue() / 20f);
   }
}
