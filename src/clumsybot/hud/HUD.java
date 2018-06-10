package clumsybot.hud;

import clumsybot.Settings;
import tkbases.GameObject;
import tkbases.Vector2D;

/**
 * Created by huynq on 2/4/18.
 */
public class HUD extends GameObject {
    public HUD() {
        super();
        addSoundControls();
    }

    private void addSoundControls() {
        SoundControl soundControl = new SoundControl();
        soundControl.getPosition().y = 20;
        soundControl.getPosition().x = Settings.SCREEN_WIDTH - 20;
        soundControl.transform.anchor = new Vector2D(1, 0);
        this.children.add(soundControl);
    }
}
