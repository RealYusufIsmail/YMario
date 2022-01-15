package io.github.realyusufismail.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Coin extends InteractiveTileObject {
    /**
     * Constructor for Coin class
     * 
     * @param world Box2D world
     * @param map TiledMap
     * @param bounds Rectangle
     */
    public Coin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

    }
}
