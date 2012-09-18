package prototipo.bean.shapes;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Cubo extends AnimatedSprite {

	public static final Texture TEXTURE = new Texture(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);;

	public Cubo(float positionX, float positionY, TiledTextureRegion tiledTextureRegion) {
		super(positionX, positionY, tiledTextureRegion);
	}

	public BodyType getType() {
		return BodyType.DynamicBody;
	}
}
