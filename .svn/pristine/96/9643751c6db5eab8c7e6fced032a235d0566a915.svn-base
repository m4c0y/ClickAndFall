package prototipo.redremover;

import prototipo.redremover.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TelaWin extends Activity {
	TextView tvTempo;
	TextView tvClicks;
	Button btnProxima;
	Button btnMenu;
	int faseAtual;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.telawin);
		faseAtual = getIntent().getExtras().getInt("Fase");
		
		btnProxima = (Button) findViewById(R.id.btnProxima);
		btnMenu = (Button) findViewById(R.id.btnMenu);

		// botão de ir para a próxima fase 
		btnProxima.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	        	  TelaWin.this.finish();
	        	  startActivity(new Intent(TelaWin.this, Fases.class).putExtra("Fase", faseAtual + 1));
	          }      	      
		});
		
		// botão de voltar para o menu de seleção de fases
		btnMenu.setOnClickListener(new OnClickListener() {
	          public void onClick(View view) {
	        	  // startActivity(new Intent(TelaWin.this, TelaFases.class));
	        	  TelaWin.this.finish();
	          }      	      
		});
		
		tvTempo = (TextView) findViewById(R.id.tvTempo);
		tvClicks = (TextView) findViewById(R.id.tvClick);
		
		tvTempo.setText("Tempo: " + getIntent().getExtras().getString("Tempo") + "ms");
		tvClicks.setText("Clicks: " + getIntent().getExtras().getInt("Clicks"));
		
		// se a fase que estiver for a última, ele esconde o botão de "Próxima"
		if (faseAtual == Contants.UltimaFase) {
			btnProxima.setVisibility(8);
		}
		
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
