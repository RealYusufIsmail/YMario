package io.github.realyusufismail.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import org.jetbrains.annotations.NotNull;

public class Brick extends InteractiveTileObject {
    /**
     * Constructor for Brick class
     * 
     * @param world Box2D world
     * @param map TiledMap
     * @param bounds Rectangle
     */
    public Brick(@NotNull World world, TiledMap map, @NotNull Rectangle bounds) {
        super(world, map, bounds);
    }
}
