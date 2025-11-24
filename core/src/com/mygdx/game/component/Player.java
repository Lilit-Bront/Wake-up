package com.mygdx.game.component;

import static com.mygdx.game.GameSettings.COUNT_OF_LIVES;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public class Player extends Component {

    enum State {IDLE, RUNNING, ATTACK}
    MyGdxGame game;

    private Animation<TextureRegion> idelAnimation;
    private Animation<TextureRegion> runAnimation;
    private Animation<TextureRegion> attackAnimation;
    private State state;
    public int countOfLives;
    private boolean isTouchingFlor;

    public PhysicsObject physicsObject;
    private long animationStartTime;
    private boolean shouldFlip = false;



    public Player(Texture texture, int width, int height, int x, int y, World world) {
        super(texture, width, height, x, y);

        physicsObject = new PhysicsObject.PhysicalObjectBuilder(world, BodyDef.BodyType.DynamicBody)
                .addRectangularFixture(width, height, (short) 1)
                .setInitialPosition(x, y)
                .build(this);
        createAnimation();
        animationStartTime = System.currentTimeMillis();
        state = State.IDLE;
        isTouchingFlor = true;

        countOfLives = COUNT_OF_LIVES;

    }

    private void createAnimation() {
        Texture textureIdle = new Texture("Textures/player/IDLE.png");
        Texture textureRunning = new Texture("Textures/player/run1.png");
        Array<TextureRegion> frames = new Array<>();

        for (int i = 0; i < 7; i++) {
            frames.add(
                    new TextureRegion(textureIdle, i * 63, 0, 64, 96)
            );
        }
        idelAnimation = new Animation<TextureRegion>(2f, frames, Animation.PlayMode.LOOP);
        frames.clear();

        for (int i = 0; i < 6; i++) {
            frames.add(
                    new TextureRegion(textureRunning, i * 64, 0, 64, 96)
            );
        }
        runAnimation = new Animation<>(1f, frames, Animation.PlayMode.LOOP);
        frames.clear();

        for (int i = 0; i < 5; i++) {
            frames.add(
                    new TextureRegion(textureRunning, i * 63, 0, 63, 96)
            );
        }
        attackAnimation = new Animation<>(1.2f, frames, Animation.PlayMode.NORMAL);
        frames.clear();
    }


    public void moveLeft() {
        physicsObject.getBody().applyForceToCenter(
                new Vector2(-10, 0), true
        );
        state = State.RUNNING;
        shouldFlip = false;
    }

    public void moveRight() {
        physicsObject.getBody().applyForceToCenter(
                new Vector2(10, 0), true
        );
        state = State.RUNNING;
        shouldFlip = true;
    }

    public void moveUp() {
        if (isTouchingFlor == false) {
            return;
        }

        physicsObject.getBody().setLinearVelocity(new Vector2(
                physicsObject.getBody().getLinearVelocity().x, 10)
        );

        isTouchingFlor = false;
    }

    public void moveAttackRight() {
        physicsObject.getBody().applyForceToCenter(
                new Vector2(0, 0), true
        );
        animationStartTime = System.currentTimeMillis();
        state = State.ATTACK;
        shouldFlip = true;
    }

    public void moveAttackLeft() {
        physicsObject.getBody().applyForceToCenter(
                new Vector2(0, 0), true
        );
        animationStartTime = System.currentTimeMillis();
        state = State.ATTACK;
        shouldFlip = false;
    }


    @Override
    public void draw(Batch batch) {
        x = physicsObject.getX() - width / 2;
        y = physicsObject.getY() - height / 2;

        long time = (System.currentTimeMillis() - animationStartTime) / 100;
        TextureRegion region;
        if (state == State.RUNNING) {
            region = runAnimation.getKeyFrame(time);
        } else if (state == State.ATTACK) {
            region = attackAnimation.getKeyFrame(time);
        } else {
            region = idelAnimation.getKeyFrame(time);
        }
        if (shouldFlip == region.isFlipX()) {
            region.flip(true, false);
        }
        batch.draw(region, x, y, width, height);
        if (attackAnimation.isAnimationFinished(time)) {
            state = State.IDLE;
        }




    }

    public void onTouchFlour() {
        System.out.println("Player has touched flor");
        isTouchingFlor = true;
    }

    public void onTouchEnemy() {
        if (state != State.ATTACK) {
            countOfLives -= 1;
            System.out.println("Left count of lives: " + countOfLives);
        }

    }

}
