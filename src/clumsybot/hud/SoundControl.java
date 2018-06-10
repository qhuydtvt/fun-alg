package clumsybot.hud;

import clumsybot.Settings;
import tkbases.GameObject;
import tkbases.actions.Action;
import tkbases.actions.ActionSequence;
import tkbases.actions.ActionWait;
import tkbases.inputs.InputManager;
import tkbases.renderers.ImageRenderer;

/**
 * Created by huynq on 2/4/18.
 */
public class SoundControl extends GameObject {
    public boolean disabled;

    public SoundControl() {
        super();
        this.renderer = new ImageRenderer("images/sound_on.png");
        disabled = false;
    }

    @Override
    public void update(GameObject parent) {
        super.update(parent);
        if (InputManager.instance.leftMouseInfo.pressed && !disabled) {
            if(InputManager.instance.leftMouseInfo.inside(this)) {
                Settings.soundEffectEnabled = !Settings.soundEffectEnabled;
                disabled = true;
                this.addAction(new ActionSequence(
                        new ActionWait(30),
                        new Action() {
                            @Override
                            public boolean execute(GameObject owner) {
                                SoundControl soundControl = (SoundControl) owner;
                                soundControl.disabled = false;
                                return true;
                            }

                            @Override
                            public void reset() {

                            }
                        }
                ));
            }
        }
    }
}
