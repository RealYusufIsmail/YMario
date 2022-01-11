package io.github.realyusufismail.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import io.github.realyusufismail.YMario;

public class Mario extends Sprite {
    public World world;
    public Body b2Body;

    public Mario(World world) {
        this.world = world;
        defineMario();
    }

    private void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / YMario.PPM, 32 / YMario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / YMario.PPM);

        fdef.shape = shape;
        b2Body.createFixture(fdef);
    }
}
