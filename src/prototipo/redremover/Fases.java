package prototipo.redremover;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import prototipo.bean.fases.Fase;
import prototipo.bean.records.Record;
import prototipo.bean.shapes.Cubo;
import prototipo.bean.shapes.CuboAzul;
import prototipo.bean.shapes.CuboVerde;
import prototipo.bean.shapes.CuboVermelho;
import prototipo.bean.shapes.CuboVermelhoNotClickable;
import prototipo.bean.shapes.Forma;
import prototipo.bean.shapes.interfaces.NotClickable;
import prototipo.dao.FaseDAO;
import prototipo.dao.RecordesDAO;
import prototipo.dao.TutorialDAO;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Fases extends BaseGameActivity implements IOnAreaTouchListener {

	// ===========================================================
	// Fields
	// ===========================================================

	private PhysicsWorld mPhysicsWorld;
	private Fase fase;
	private int clicks = 0;
	private long startTime;
	private TextureRegion backgroundTexture;
	private Texture texture;
	private Texture mFontTexture;
	private Font mFont;
	private Text tutorial;

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Engine onLoadEngine() {

		final Camera camera = new Camera(0, 0, Contants.CAMERA_WIDTH,
				Contants.CAMERA_HEIGHT);

		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						Contants.CAMERA_WIDTH, Contants.CAMERA_HEIGHT), camera);
		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		return new Engine(engineOptions);
	}

	@Override
	public void onLoadResources() {

		this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);

		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
		this.mEngine.getFontManager().loadFont(this.mFont);

		TextureRegionFactory.setAssetBasePath("gfx/");

		CuboAzul.setTiledTextureRegion(this, 0);
		CuboVerde.setTiledTextureRegion(this, 64);
		CuboVermelho.setTiledTextureRegion(this, 128);
		CuboVermelhoNotClickable.setTiledTextureRegion(this, 192);

//		texture = new Texture(1024, 1024, TextureOptions.DEFAULT);
//		backgroundTexture = TextureRegionFactory.createFromAsset(texture, this, "cenario3.png", 0, 0);
//		this.mEngine.getTextureManager().loadTexture(texture);
		this.mEngine.getTextureManager().loadTexture(Cubo.TEXTURE);

		Intent it = getIntent();
		try {
			fase = new Fase(FaseDAO.parseFaseXML(getAssets().open("Fases/" + (getIntent().getIntExtra("Fase", 1) + ".xml"))));
			tutorial = new Text(0, 0, mFont, TutorialDAO.parseFaseXML(getAssets().open("Tutorial/" + (getIntent().getIntExtra("Fase", 0) + ".xml"))));
		} catch (Exception e) {
			Log.e(Contants.TAG, e.getMessage());
		}
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene(2);
//		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
//		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, 0, this.backgroundTexture)));
//		
//		scene.setBackground(autoParallaxBackground);

		//scene.setBackground(new ColorBackground(100, 149, 237));
		this.mPhysicsWorld = new PhysicsWorld(new Vector2(0,
				SensorManager.GRAVITY_EARTH), false) {

			@Override
			public void onUpdate(float pSecondsElapsed) {

				super.onUpdate(pSecondsElapsed);

				for (int i = 0; i < fase.getGreenShapes().size(); i++) {

					float x = fase.getGreenShapes().get(i).getX();
					float y = fase.getGreenShapes().get(i).getY();
					PointF point = fase.getPoints().get(i);

					if (y > Contants.CAMERA_HEIGHT) {
						Log.i(Contants.TAG, "LOSE");

						alert("LOSE");
						return;
					}

					if (x != point.x || y != point.y) {
						point.set(x, y);
						return;
					}
				}

				for (IShape shape : fase.getRedShapes()) {

					if (shape.getY() < Contants.CAMERA_HEIGHT) {
						return;
					}
				}

				Log.i(Contants.TAG, "WIN");
				alert("WIN");
				
				Record record = new Record("ABC", clicks, System.currentTimeMillis() - startTime);
				
				try {
					RecordesDAO.saveRecord(getAssets().open("Records/" + (getIntent().getIntExtra("Fase", 1) + ".xml")), openFileOutput("Records/" + (getIntent().getIntExtra("Fase", 1) + ".xml"), MODE_WORLD_WRITEABLE) , record);
				} catch (Exception e) {
					Log.e(Contants.TAG, e.getMessage());
				}
			}
		};

		scene.registerUpdateHandler(this.mPhysicsWorld);

		scene.setOnAreaTouchListener(this);
		
		if(tutorial != null)
			scene.getLastChild().attachChild(tutorial);
		
		return scene;
	}

	@Override
	public void onLoadComplete() {

		for (IShape shape : fase.getShapes()) {
			configureShape(shape);
		}
		SplashScreen.mMediaPlayer.start();

		startTime = System.currentTimeMillis();
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final ITouchArea pTouchArea, final float pTouchAreaLocalX,
			final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			this.removeFace((IShape) pTouchArea);
			clicks++;
			return true;
		}

		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void configureShape(IShape shape) {

		final Scene scene = this.mEngine.getScene();
		FixtureDef fixtureDef;
		BodyType type;

		if (shape instanceof Cubo) {
			fixtureDef = PhysicsFactory.createFixtureDef(1, 0.3f, 0.5f);
			type = ((Cubo) shape).getType();
		} else {
			fixtureDef = PhysicsFactory.createFixtureDef(0, 0.3f, 0.5f);
			type = ((Forma) shape).getType();
		}

		Body body = PhysicsFactory.createBoxBody(this.mPhysicsWorld, shape,
				type, fixtureDef);

		if (!(shape instanceof NotClickable)) {
			scene.registerTouchArea(shape);
		}
		scene.getLastChild().attachChild(shape);
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(shape,
				body, true, true));
	}

	private void removeFace(final IShape shape) {
		final Scene scene = this.mEngine.getScene();

		final PhysicsConnector facePhysicsConnector = this.mPhysicsWorld
				.getPhysicsConnectorManager()
				.findPhysicsConnectorByShape(shape);

		this.mPhysicsWorld.unregisterPhysicsConnector(facePhysicsConnector);
		this.mPhysicsWorld.destroyBody(facePhysicsConnector.getBody());

		scene.unregisterTouchArea(shape);
		scene.getLastChild().detachChild(shape);

		if (fase.getBlueShapes().contains(shape))
			fase.getBlueShapes().remove(shape);
		else if (fase.getRedShapes().contains(shape))
			fase.getRedShapes().remove(shape);
	}

	private void alert(final String text) {

		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				try {
					Intent it;
					if (text == "WIN") {
						it = new Intent(Fases.this, TelaWin.class);
					} else {
						it = new Intent(Fases.this, TelaLose.class);
					}
					
					it.putExtra("Fase", getIntent().getExtras().getInt("Fase"));
					it.putExtra("Tempo", String.format("%s",
							(System.currentTimeMillis() - startTime)));
					it.putExtra("Clicks", clicks);
					startActivity(it);
					Fases.this.finish();
				} catch (Exception ex) {
					Log.e(Contants.TAG, ex.getMessage());
				}
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();

		SplashScreen.mMediaPlayer.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();

		SplashScreen.mMediaPlayer.start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
	   MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.telamenu, menu);
	      return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  // Captura o menu selecionado
	   switch (item.getItemId()) {
	      case R.id.refresh:
	         Fases.this.finish();
	         startActivity(new Intent(Fases.this, Fases.class).putExtra("Fase", getIntent().getExtras().getInt("Fase")));
	         return true;
	      case R.id.recorde:
	         // capturar evento de recorde
	         return true;
	      case R.id.revert:
	    	 Fases.this.finish();
	    	 return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}
}
