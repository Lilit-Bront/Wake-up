package com.mygdx.game.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.List;

public class Life {
    Texture life1;
    Texture life2;
    Texture life3;
    Texture life4;
    Texture life5;

    public Life() {
        life1 = new Texture("Textures/life/life1.png");
        life2 = new Texture("Textures/life/life2.png");
        life3 = new Texture("Textures/life/life3.png");
        life4 = new Texture("Textures/life/life4.png");
        life5 = new Texture("Textures/life/life5.png");
    }

    public void draw(Batch batch, int leftLife, float cameraX) {

        if (leftLife == 5) {
            batch.draw(life5, cameraX - 600, 650, 79 * 2, 12 * 2);
        } else if (leftLife == 4) {
            batch.draw(life4, cameraX - 600, 650, 79 * 2, 12 * 2);
        } else if (leftLife == 3) {
            batch.draw(life3, cameraX - 600, 650, 79 * 2, 12 * 2);
        } else if (leftLife == 2) {
            batch.draw(life2, cameraX - 600, 650, 79 * 2, 12 * 2);
        } else if (leftLife == 1) {
            batch.draw(life1, cameraX - 600, 650, 79 * 2, 12 * 2);
        }
    }
}
