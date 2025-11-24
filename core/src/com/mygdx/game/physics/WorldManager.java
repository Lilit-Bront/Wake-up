package com.mygdx.game.physics;

import static com.mygdx.game.GameSettings.POSITION_ITERATIONS;
import static com.mygdx.game.GameSettings.STEP_TIME;
import static com.mygdx.game.GameSettings.VELOCITY_ITERATIONS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.component.Enemy;
import com.mygdx.game.component.Note;
import com.mygdx.game.component.Player;
import com.mygdx.game.component.StaticBlock;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {

    public OrthoCachedTiledMapRenderer mapRenderer;
    public World world;

    public Player player;
    public float tileScale;
    MapManager mapManager;
    public List<Enemy> enemyList;

    public List<Note> noteList;

    float accumulator;

    public WorldManager() {

        world = new World(new Vector2(0, -15), true);
        world.setContactListener(new ContactManager());

        mapManager = new MapManager("levels/WakeUpLevel1.0.tmx");
        mapRenderer = new OrthoCachedTiledMapRenderer(mapManager.getMap(), mapManager.getTileScale());
        mapRenderer.setBlending(true);
        enemyList = new ArrayList<>();
        noteList = new ArrayList<>();
        tileScale = mapManager.getTileScale();

        buildWorld(mapManager);
    }

    public float getWorldWidth() {
        return mapManager.getCountOfTilesHorizontal() * mapManager.getTileSize() * mapManager.getTileScale();
    }

    public void buildWorld(MapManager mapManager) {
        float tileScale = mapManager.getTileScale();


        for (
                RectangleMapObject object :
                mapManager.getMap().getLayers().get("Слой объектов 1").getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = object.getRectangle();
            new StaticBlock(
                    (int) (rect.width * tileScale),
                    (int) (rect.height * tileScale),
                    (int) (rect.x * tileScale),
                    (int) (rect.y * tileScale),
                    world
            );
        }


        for (
                RectangleMapObject object :
                mapManager.getMap().getLayers().get("Actor").getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = object.getRectangle();

            if (object.getName().equals("Player")) {
                player = new Player(new Texture("Bird.png"),
                        (int) (rect.width * tileScale),
                        (int) (96),
                        (int) (rect.x * tileScale),
                        (int) (rect.y * tileScale),
                        world
                );
            }

            if (object.getName().equals("Enemy")) {
                enemyList.add(
                        new Enemy(
                                rect.width * tileScale,
                                rect.height * tileScale,
                                rect.x * tileScale,
                                rect.y * tileScale,
                                world
                        )
                );
            }
            if (object.getName().equals("Note")) {
                noteList.add(
                        new Note(
                                new Texture("Textures/note1.png"),
                                (int) (rect.width * tileScale),
                                (int) (rect.height * tileScale),
                                (int) ((rect.x + rect.width/2) * tileScale),
                                (int) ((rect.y + rect.height/2)* tileScale),
                                world,
                                (String) object.getProperties().get("texture")
                        )
                );
            }
        }
        /*
        for (
            RectangleMapObject object :
            mapManager.getMap().getLayers().get(INTERACTIVE_OBJECTS.getName()).getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = object.getRectangle();

            if (object.getName().equals(MapObjects.PIT.getName())) {
                new PitBlock(world, rect, onLoseListener);
            } else if (object.getName().equals(MapObjects.FINISH.getName())) {
                FinishLine finishLine = new FinishLine(world, rect, onWinListener, tileScale);
                actorsList.add(finishLine);
                disposablesList.add(finishLine);
            } else if (object.getName().equals(MapObjects.COIN.getName())) {
                Coin coin = new Coin(world, rect, onScoreEarnedListener, onRemoveBodyListener, tileScale);
                actorsList.add(coin);
                disposablesList.add(coin);
            } else if (object.getName().equals(MapObjects.LADDER.getName())) {
                new Ladder(world, rect);
            } else if (object.getName().equals(MapObjects.BONUS.getName())) {
                BonusBlock bonusBlock = new BonusBlock(world, rect, onRemoveBodyListener, onScoreEarnedListener, tileScale);
                actorsList.add(bonusBlock);
                disposablesList.add(bonusBlock);
            }

        }*/
    }

    public void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

}