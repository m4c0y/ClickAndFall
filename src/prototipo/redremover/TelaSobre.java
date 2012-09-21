package prototipo.redremover;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class TelaSobre extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.telasobre);
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
}
