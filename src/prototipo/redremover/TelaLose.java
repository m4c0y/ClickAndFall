package prototipo.redremover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TelaLose extends Activity {
	TextView tvTempo;
	TextView tvClicks;
	Button btnTentarNovamente;
	Button btnMenu;
	int faseAtual;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.telalose);
		faseAtual = getIntent().getExtras().getInt("Fase");
		
		btnTentarNovamente = (Button) findViewById(R.id.btnTentarNovamente);
		btnMenu = (Button) findViewById(R.id.btnMenu);

		// botão de ir para a próxima fase 
		btnTentarNovamente.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	        	  startActivity(new Intent(TelaLose.this, Fases.class).putExtra("Fase", faseAtual));
	        	  TelaLose.this.finish();
	          }      	      
		});
		
		// botão de voltar para o menu de seleção de fases
		btnMenu.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	        	  // startActivity(new Intent(TelaLose.this, TelaFases.class));
	        	  TelaLose.this.finish();
	          }      	      
		});
		
		tvTempo = (TextView) findViewById(R.id.tvTempo);
		tvClicks = (TextView) findViewById(R.id.tvClick);
		
		tvTempo.setText("Tempo: " + getIntent().getExtras().getString("Tempo") + "ms");
		tvClicks.setText("Clicks: " + getIntent().getExtras().getInt("Clicks"));
		
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
