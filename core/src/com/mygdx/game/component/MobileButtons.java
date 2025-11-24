package com.mygdx.game.component;

import static com.mygdx.game.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.screen.GameScreen;

public class MobileButtons {
    public TextButton buttonForwardTexture;
    public TextButton buttonBackwardTexture;
    public TextButton buttonUpTexture;
    public TextButton buttonAttackTexture;
    public TextButton buttonStop;

    public MobileButtons() {
        buttonForwardTexture = new TextButton(200, 50, "Textures/buttons/buttonForwardTexture.png", "Textures/buttons/buttonForwardTexture.png", 80, 80);
        buttonBackwardTexture = new TextButton(100, 50, "Textures/buttons/buttonBackwardTexture.png", "Textures/buttons/buttonBackwardTexture.png", 80, 80);
        buttonUpTexture = new TextButton(1100, 50, "Textures/buttons/buttonUpTexture.png", "Textures/buttons/buttonUpTexture.png", 80, 80);
        buttonAttackTexture = new TextButton(1000, 50, "Textures/buttons/buttonAttackTexture.png", "Textures/buttons/buttonAttackTexture.png", 80, 80);
        buttonStop = new TextButton(1130, 600, "Textures/buttons/Stop.png", "Textures/buttons/Stop.png", 80, 80);
    }

    public void draw(Batch batch, float cameraX) {
        buttonStop.x = (int) (cameraX - SCREEN_WIDTH / 2 + 1130);
        buttonForwardTexture.x = (int) (cameraX - SCREEN_WIDTH / 2 + 200);
        buttonBackwardTexture.x = (int) (cameraX - SCREEN_WIDTH / 2 + 100);
        buttonUpTexture.x = (int) (cameraX - SCREEN_WIDTH / 2 + 1100);
        buttonAttackTexture.x = (int) (cameraX - SCREEN_WIDTH / 2 + 1000);
        buttonForwardTexture.draw(batch);
        buttonStop.draw(batch);
        buttonBackwardTexture.draw(batch);
        buttonUpTexture.draw(batch);
        buttonAttackTexture.draw(batch);
    }
}
