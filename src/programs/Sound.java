package programs;

import java.net.URL;

public class Sound {
    URL sound;

    public Sound(String path) {
        sound = getClass().getResource(path);
    }

    public URL getSound() {
        return sound;
    }
}
