package io.github.realyusufismail.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import io.github.realyusufismail.YMario;
import io.github.realyusufismail.screens.PlayScreen;
import org.jetbrains.annotations.NotNull;

public class Mario extends Sprite {
    public World world;
    public Body b2Body;
    private TextureRegion motionlessMario;

    public Mario(World world, @NotNull PlayScreen screen) {
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        motionlessMario = new TextureRegion(getTexture(), 0, 0, 16, 16);
        setBounds(0, 0, 16 / YMario.PPM, 16 / YMario.PPM);
        setRegion(motionlessMario);
    }

    public void update(float dt) {
        setPosition(b2Body.getPosition().x - getWidth() / 2,
                b2Body.getPosition().y - getHeight() / 2);
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
