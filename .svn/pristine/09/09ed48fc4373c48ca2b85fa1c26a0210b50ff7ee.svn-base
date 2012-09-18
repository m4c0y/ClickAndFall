package prototipo.bean.shapes;

import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import prototipo.bean.shapes.interfaces.NotClickable;
import prototipo.bean.shapes.interfaces.Vermelho;

import android.content.Context;

public class CuboVermelhoNotClickable extends Cubo implements NotClickable, Vermelho {
	
	private static TiledTextureRegion tiledTextureRegion;
	private static String image = "red2_face_box_tiled.png";

	public CuboVermelhoNotClickable(float positionX, float positionY) {
		super(positionX, positionY, CuboVermelhoNotClickable.tiledTextureRegion);
	}

	public static TiledTextureRegion getTiledTextureRegion() {
		return tiledTextureRegion;
	}
	
	public static void setTiledTextureRegion(Context context, int regionY) {
		CuboVermelhoNotClickable.tiledTextureRegion = TextureRegionFactory.createTiledFromAsset(Cubo.TEXTURE, context, CuboVermelhoNotClickable.image, 0, regionY, 2, 1);
	}
}
