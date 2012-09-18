package prototipo.redremover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TelaPrincipal extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.telaprincipal);
		
		Button btnJogar;
		Button btnSobre;
		Button btnSair;

		btnJogar = (Button) findViewById(R.id.btnJogar);
		btnSobre = (Button) findViewById(R.id.btnSobre);
		btnSair = (Button) findViewById(R.id.btnSair);

		btnJogar.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	              startActivity(new Intent(TelaPrincipal.this, TelaFases.class));
	          }      	      
		});
		
		btnSobre.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	              startActivity(new Intent(TelaPrincipal.this, TelaSobre.class));
	          }      	      
		});
		
		btnSair.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	        	  if (SplashScreen.mMediaPlayer != null) {
	        		  SplashScreen.mMediaPlayer.release();
	        		  SplashScreen.mMediaPlayer = null;
	              }
	        	  System.exit(0);
	          }      	      
		});
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
