package prototipo.redremover;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity implements Runnable {

	public static MediaPlayer mMediaPlayer;
	public static MediaPlayer mMediaPlayerKnock;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		Handler h = new Handler();
		h.postDelayed(this, 3000);
		
		SplashScreen.mMediaPlayerKnock = MediaPlayer.create(this, R.raw.knock);
		SplashScreen.mMediaPlayerKnock.setLooping(false);
		
		SplashScreen.mMediaPlayer = MediaPlayer.create(this, R.raw.fase);
		SplashScreen.mMediaPlayer.setLooping(true);
		SplashScreen.mMediaPlayer.start();
	}

	public void run() {
		startActivity(new Intent(this, TelaPrincipal.class));
		finish();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		SplashScreen.mMediaPlayer.pause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		SplashScreen.mMediaPlayer.start();
	}
}