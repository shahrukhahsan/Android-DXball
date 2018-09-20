package shahrukh.seventh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class DXballzActivity extends Activity {
	private Button playButton, exitButton,scoreButton;
	
	
	/** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		//setContentView(new GameCanvas(this)); 
		
		playButton = (Button) findViewById(R.id.buttonPlay);
		scoreButton = (Button) findViewById(R.id.buttonScore);
		exitButton = (Button) findViewById(R.id.buttonExit);
		
		
	
    playButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
        	playButton.getBackground().setAlpha(255);
			Intent intent = new Intent(DXballzActivity.this, GameActivity.class);
			startActivity(intent);
        }
    });
    
    exitButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
        	//exitButton.getBackground().setAlpha(255);
        	finish();
			System.exit(0);
        }
    });
    scoreButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
        	
        }
    });
    }
}
