package prototipo.bean.shapes;

import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import prototipo.bean.shapes.interfaces.Azul;

import android.content.Context;

public class CuboAzul extends Cubo implements Azul {
	
	public static TiledTextureRegion tiledTextureRegion;
	private static String image = "blue_face_box_tiled.png";

	public CuboAzul(float positionX, float positionY) {
		super(positionX, positionY, CuboAzul.tiledTextureRegion);
	}

	public static TiledTextureRegion getTiledTextureRegion() {
		return tiledTextureRegion;
	}

	public static void setTiledTextureRegion(Context context, int regionY) {
		CuboAzul.tiledTextureRegion = TextureRegionFactory.createTiledFromAsset(Cubo.TEXTURE, context, CuboAzul.image, 0, regionY, 2, 1);
	}
}
