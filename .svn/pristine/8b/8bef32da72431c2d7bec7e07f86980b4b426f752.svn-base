package prototipo.bean.shapes;

import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import prototipo.bean.shapes.interfaces.NotClickable;
import prototipo.bean.shapes.interfaces.Verde;

import android.content.Context;

public class CuboVerde extends Cubo implements NotClickable, Verde {
	
	private static TiledTextureRegion tiledTextureRegion;
	private static String image = "green_face_box_tiled.png";

	public CuboVerde(float positionX, float positionY) {
		super(positionX, positionY, CuboVerde.tiledTextureRegion);
	}

	public static TiledTextureRegion getTiledTextureRegion() {
		return tiledTextureRegion;
	}

	public static void setTiledTextureRegion(Context context, int regionY) {
		CuboVerde.tiledTextureRegion = TextureRegionFactory.createTiledFromAsset(Cubo.TEXTURE, context, CuboVerde.image, 0, regionY, 2, 1);
	}
}
