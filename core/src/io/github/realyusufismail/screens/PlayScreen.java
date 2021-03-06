package io.github.realyusufismail.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.realyusufismail.YMario;
import io.github.realyusufismail.scenes.Hud;
import io.github.realyusufismail.sprites.Mario;
import io.github.realyusufismail.tools.B2WorldCreator;
import org.jetbrains.annotations.NotNull;

public class PlayScreen implements Screen {
    private final YMario game;
    private TextureAtlas atlas;


    private final OrthographicCamera gameCamera;
    private final Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d Constructor
    private World world;
    private Box2DDebugRenderer b2dr;

    // sprites
    private Mario player;

    public PlayScreen(@NotNull YMario game) {
        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game = game;
        // create camera
        gameCamera = new OrthographicCamera();
        // create viewport
        gamePort = new FitViewport(YMario.V_WIDTH / YMario.PPM, YMario.V_HEIGHT / YMario.PPM,
                gameCamera);
        // create HUD
        hud = new Hud(game.batch);
        // create map
        mapLoader = new TmxMapLoader();
        // load map
        map = mapLoader.load("level1.tmx");
        // create renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1 / YMario.PPM);
        // sets up our game camera
        gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        // create Box2D world
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        // create player
        player = new Mario(world, this);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    private void handleInput(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.b2Body.applyLinearImpulse(new Vector2(0, 4f), player.b2Body.getWorldCenter(),
                    true);
        if (Gdx.input.isKeyPressed(Input.Keys.D) && player.b2Body.getLinearVelocity().x <= 2)
            player.b2Body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2Body.getWorldCenter(),
                    true);
        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.b2Body.getLinearVelocity().x >= -2)
            player.b2Body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2Body.getWorldCenter(),
                    true);
    }

    public void update(float deltaTime) {
        handleInput(deltaTime);
        world.step(1 / 60f, 6, 2);
        player.update(deltaTime);
        gameCamera.position.x = player.b2Body.getPosition().x;
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
        // render Box2DDebugLines
        b2dr.render(world, gameCamera.combined);
        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
