package com.mygdx.game.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextButton {
    private BitmapFont font;
    String text;
    public Texture texture;
    Texture normalTexture;
    Texture pressedTexture;
    int x, y;
    int width;
    int height;
    int textX, textY;
    public int buttonWidth, buttonHeight;
    int textWidth, textHeight;

    public TextButton(int x, int y, String normalTexturePath, String pressedTexturePath, int buttonWidth, int buttonHeight) {
        this.x = x;
        this.y = y;

        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;
        normalTexture = new Texture(normalTexturePath);
        pressedTexture = new Texture(pressedTexturePath);

    /*textX = x + (buttonWidth - textWidth) / 2;
    textY = y + (buttonHeight + textHeight) / 2;*/
    }

    public void draw(Batch batch) {
        batch.draw(normalTexture, x, y, buttonWidth, buttonHeight);
    }

    public boolean isTouched(int touchX, int touchY) {
        if (!Gdx.input.isTouched()){
            return false;
        }
        return touchX >= x && touchX <= x + buttonWidth &&
                touchY >= y && touchY <= y + buttonHeight;
    }

    public void dispose() {
        texture.dispose();
        font.dispose();
    }
}
