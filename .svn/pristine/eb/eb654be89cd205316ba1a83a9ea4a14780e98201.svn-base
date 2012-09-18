package prototipo.bean.shapes;

import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import prototipo.bean.shapes.interfaces.Vermelho;

import android.content.Context;

public class CuboVermelho extends Cubo implements Vermelho {
	
	private static TiledTextureRegion tiledTextureRegion;
	private static String image = "red_face_box_tiled.png";

	public CuboVermelho(float positionX, float positionY) {
		super(positionX, positionY, CuboVermelho.tiledTextureRegion);
	}

	public static TiledTextureRegion getTiledTextureRegion() {
		return tiledTextureRegion;
	}

	public static void setTiledTextureRegion(Context context, int regionY) {
		CuboVermelho.tiledTextureRegion = TextureRegionFactory.createTiledFromAsset(Cubo.TEXTURE, context, CuboVermelho.image, 0, regionY, 2, 1);
	}
}
