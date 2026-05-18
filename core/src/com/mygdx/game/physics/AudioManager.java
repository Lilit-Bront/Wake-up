package com.mygdx.game.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.GameResources;

public class AudioManager {
    public Music backgroundMusic;

    public boolean isSoundOn;
    public boolean isMusicOn;

    public AudioManager(){
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Incantation-chosic.com_ (1).mp3"));
        //shootSound = Gdx.audio.newSound(Gdx.files.internal(GameResources.SHOOT_SOUND_PATH));
        //explosionSound = Gdx.audio.newSound(Gdx.files.internal(GameResources.DESTROY_SOUND_PATH));

        backgroundMusic.setVolume(0.2f);
        backgroundMusic.setLooping(true);

        backgroundMusic.play();

        isMusicOn = true;
        isSoundOn = true;
    }

    public void updateMusicFlag() {
        if (isMusicOn) backgroundMusic.play();
        else backgroundMusic.stop();
    }
}
