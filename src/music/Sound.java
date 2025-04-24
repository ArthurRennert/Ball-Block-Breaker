package music;

import java.net.URL;

/**
 * The Sound class represents an audio resource in the game.
 * It encapsulates the file path of a sound file and provides access to its URL.
 */
public class Sound {
    private URL sound;

    /**
     * Constructs a Sound object with a given resource path.
     * The path should be a valid path to a sound file located in the resources directory.
     *
     * @param path the relative path to the sound file (e.g., "/Sounds/effect.wav")
     */
    public Sound(String path) {
        sound = getClass().getResource(path);
    }

    /**
     * Returns the URL of the sound file.
     *
     * @return the URL pointing to the sound resource
     */
    public URL getSound() {
        return sound;
    }
}
