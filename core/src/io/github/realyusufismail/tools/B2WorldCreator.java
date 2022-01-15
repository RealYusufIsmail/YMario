package io.github.realyusufismail.tools;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import io.github.realyusufismail.YMario;
import io.github.realyusufismail.sprites.Brick;
import io.github.realyusufismail.sprites.Coin;
import org.jetbrains.annotations.NotNull;

public class B2WorldCreator {
    public B2WorldCreator(World world, @NotNull TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (RectangleMapObject object : map.getLayers()
            .get(2)
            .getObjects()
            .getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / YMario.PPM,
                    (rect.getY() + rect.getHeight() / 2) / YMario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / YMario.PPM, rect.getHeight() / 2 / YMario.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for (RectangleMapObject object : map.getLayers()
            .get(3)
            .getObjects()
            .getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / YMario.PPM,
                    (rect.getY() + rect.getHeight() / 2) / YMario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / YMario.PPM, rect.getHeight() / 2 / YMario.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // creates brick bodies/fixtures
        for (RectangleMapObject object : map.getLayers()
            .get(5)
            .getObjects()
            .getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();

            new Brick(world, map, rect);
        }

        // creates coin bodies/fixtures
        for (RectangleMapObject object : map.getLayers()
            .get(4)
            .getObjects()
            .getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();

            new Coin(world, map, rect);
        }
    }
}
