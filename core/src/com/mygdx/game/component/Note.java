package com.mygdx.game.component;

import static com.mygdx.game.GameSettings.SCREEN_HEIGHT;
import static com.mygdx.game.GameSettings.SCREEN_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Note extends Component{
    PhysicsObject physicsObject;
    Texture noteTexture;
    public boolean isTouchPlayer;
    public boolean wasCollected;


    public Note(Texture texture, int width, int height, int x, int y, World world, String openNoteTexture) {
        super(texture, width, height, x, y);
        physicsObject = new PhysicsObject.PhysicalObjectBuilder(world, BodyDef.BodyType.StaticBody)
                .addCircularFixture(width/2,(short) 4)
                .setInitialPosition(x, y)
                .build(this);
        noteTexture = new Texture(openNoteTexture);
    }
    public void onTouchPlayer(){
        System.out.printf("Hello");
        isTouchPlayer = true;
        wasCollected = true;
    }
    public void drawNote(Batch batch, int cameraX) {
        if (isTouchPlayer == true) {
            batch.draw(noteTexture, cameraX - SCREEN_WIDTH / 2, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        }
    }

    @Override
    public void draw(Batch batch) {
        if (wasCollected == false) {
            batch.draw(
                    texture,
                    physicsObject.getX() - width / 2,
                    physicsObject.getY() - height / 2,
                    width,
                    height
            );
        }
        if (isTouchPlayer){
            physicsObject.destroy();
        }
    }
}
