package io.github.realyusufismail.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.realyusufismail.YMario;
import io.github.realyusufismail.scenes.Hud;
import org.jetbrains.annotations.NotNull;

public class PlayScreen implements Screen {
    private final YMario game;

    private final OrthographicCamera gameCamera;
    private final Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    //Box2d Constructor
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(@NotNull YMario game) {
        this.game = game;
        // create camera
        gameCamera = new OrthographicCamera();
        // create viewport
        gamePort = new FitViewport(YMario.V_WIDTH, YMario.V_HEIGHT, gameCamera);
        // create HUD
        hud = new Hud(game.batch);
        // create map
        mapLoader = new TmxMapLoader();
        // load map
        map = mapLoader.load("level1.tmx");
        // create renderer
        renderer = new OrthogonalTiledMapRenderer(map);
        //sets up our game camera
        gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        // create Box2D world
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef  = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef  = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
        }
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    private void handleInput(float deltaTime) {
        if (Gdx.input.isTouched()) {
            gameCamera.position.x += 100 * deltaTime;
        }
    }

    public void update(float deltaTime) {
        handleInput(deltaTime);
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        update(delta);
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // render map
        renderer.render();
        // render HUD
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    /**
     * @param width the new width
     * @param height the new height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height); // update viewport size
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
