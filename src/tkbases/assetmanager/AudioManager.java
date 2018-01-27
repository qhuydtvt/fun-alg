package tkbases.assetmanager;

import tkbases.utils.AudioUtils;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.Clip;

/**
 * Created by huynq on 5/14/17.
 */
public class AudioManager {
    public static Clip loadSFXFromAsset(String audioUrl) {
        return AudioUtils.loadSound(String.format(String.format("assets/music/sfx/%s", audioUrl)));
    }

    public static MediaPlayer playBGMFromAsset(String url) {
        return AudioUtils.playMedia(String.format("assets/music/bgm/%s", url));
    }
}
