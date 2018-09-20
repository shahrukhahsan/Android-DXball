package shahrukh.seventh;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;


public class Level {
	private final int xCount=110;

	public Level(Context context) {
		// TODO Auto-generated constructor stub
	}
	public Brick[] BrickColorBack(Context context, Brick brOne[]) {
		brOne[0] = new Brick(context, Color.BLUE);
		brOne[1] = new Brick(context, Color.BLUE);
		brOne[2] = new Brick(context, Color.BLUE);
		brOne[3] = new Brick(context, Color.BLUE);
		brOne[4] = new Brick(context, Color.BLUE);
		brOne[5] = new Brick(context, Color.BLUE);
		brOne[6] = new Brick(context, Color.BLUE);
		
		brOne[7] = new Brick(context, Color.MAGENTA);
		brOne[8] = new Brick(context, Color.MAGENTA);
		brOne[9] = new Brick(context, Color.MAGENTA);
		brOne[10] = new Brick(context, Color.MAGENTA);
		return brOne;
	}
	public Brick[] AllFlagTrue(Brick brOne[]) {
		brOne[0].SetFlagTrue();
		brOne[1].SetFlagTrue();
		brOne[2].SetFlagTrue();
		brOne[3].SetFlagTrue();
		brOne[4].SetFlagTrue();
		brOne[5].SetFlagTrue();
		brOne[6].SetFlagTrue();
		
		brOne[7].SetFlagTrue();
		brOne[8].SetFlagTrue();
		brOne[9].SetFlagTrue();
		brOne[10].SetFlagTrue();
		return brOne;
	}
	// Level 1
		public void InitializeAllLevelOne(Canvas canvas, Brick brOne[]) {
			
			//Bricks
			brOne[0].Initialize(canvas, 50, 50);
			brOne[1].Initialize(canvas, 50+(xCount*1), 50);
			brOne[2].Initialize(canvas, 50+(xCount*2), 50);
			brOne[3].Initialize(canvas, 50+(xCount*3), 50);
			brOne[4].Initialize(canvas, 50+(xCount*4), 50);
			brOne[5].Initialize(canvas, 50+(xCount*5), 50);
			brOne[6].Initialize(canvas, 50+(xCount*6), 50);
			
		}

		//Level 2
		public void InitializeAllLevelTwo(Canvas canvas, Brick brOne[]) {
		
			brOne[7].Initialize(canvas, 50, 170);
			brOne[8].Initialize(canvas, 50, 230);
			brOne[9].Initialize(canvas, 260+(xCount*4), 170);
			brOne[10].Initialize(canvas, 260+(xCount*4), 230);
		}	
	
	
	
	
}
