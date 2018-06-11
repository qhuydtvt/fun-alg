package tkbases.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by huynq on 5/12/17.
 */
@SuppressWarnings("deprecation")
public class AudioUtils {
    private static ClassLoader classLoader = SpriteUtils.class.getClassLoader();

    /**
     * For playing sound effect: wav
     * @param audioUrl
     * @return
     */
    public static Clip loadSound(String audioUrl) {
        try {
            URL url = classLoader.getResource(audioUrl);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void initialize() {
        new javafx.embed.swing.JFXPanel();
    }

    /**
     * For playing background music, must calll intialize first: mp3
     * @param audioUrl
     * @return
     */
    public static MediaPlayer playMedia(String audioUrl) {
        try {
            URI uri = classLoader.getResource(audioUrl).toURI();
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(uri.toString()));
            mediaPlayer.play();
            return mediaPlayer;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
