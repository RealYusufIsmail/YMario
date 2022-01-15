package io.github.realyusufismail.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import io.github.realyusufismail.YMario;
import org.jetbrains.annotations.NotNull;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject(@NotNull World world, TiledMap map, @NotNull Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef(); // Body definition
        FixtureDef fdef = new FixtureDef(); // Fixture definition
        PolygonShape shape = new PolygonShape(); // Polygon shape

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / YMario.PPM,
                (bounds.getY() + bounds.getHeight() / 2) / YMario.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / YMario.PPM, bounds.getHeight() / 2 / YMario.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
