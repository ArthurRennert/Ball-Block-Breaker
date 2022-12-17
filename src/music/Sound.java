package music;

import java.net.URL;

/**
 *
 */
public class Sound {
    private URL sound;

    /**
     * @param path
     */
    public Sound(String path) {
        sound = getClass().getResource(path);
    }

    /**
     * @return
     */
    public URL getSound() {
        return sound;
    }
}
