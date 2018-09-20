package shahrukh.seventh;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class GameCanvas extends View {

	int xCount = 110;	//For Brick Position Counts
	Paint textP;
	int countDown=3;
	int playerHealth=3;
	int ballSpeedX, ballSpeedY;
	
	private Brick[] brOne = new Brick[11];
	private Bar bar;
	private Ball ball;
	
	private int xBall, yBall;
	private int xPlayer, yPlayer, playerSize;
	private boolean xBallFlag=true, yBallFlag=false;
	private boolean firstTime=true;
	
	private boolean touchFlag = false;
	private int screenX;
	
	private int scoreCount;
	private int levelCount;
	
	private Level level;
	
	public GameCanvas(Context context) {
		super(context);
		
		//All bricks
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
		//Bar
		bar = new Bar(context, Color.WHITE);
		
		//Ball
		ball = new Ball(context, Color.YELLOW);
		
		scoreCount=0;
		levelCount=1;
		
		level = new Level(context);
		
		textP = new Paint();
		textP.setColor(Color.WHITE); 
		textP.setTextSize(50); 
		}
	
	
protected void onDraw(final Canvas canvas) {

		
		canvas.drawRGB(0, 0, 0);
		if(firstTime)
		{
			//playerHealth = 3;
			firstTime=false;
			yBallFlag = false;
			xBall=canvas.getWidth() / 2;
			yBall=canvas.getHeight();
			
			xPlayer = (canvas.getWidth()/2)-125;
			yPlayer = canvas.getHeight()-50;
			playerSize = 250;
			
			ballSpeedX = 13;
			ballSpeedY = 7;
		}
		
		Drawable d = getResources().getDrawable(R.drawable.background);
		d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		d.draw(canvas);

		// Check end of level
		if(scoreCount == 7 && levelCount == 1) {

			levelCount++;
			//scoreCount = 0;
			level.AllFlagTrue(brOne);
			level.BrickColorBack(getContext(), brOne);
			firstTime = true;
			xBall=canvas.getWidth() / 2;
			yBall=canvas.getHeight();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(scoreCount == 11 && levelCount == 2) {
			levelCount++;
		}
		//Level Change
				if(levelCount == 1) {
					level.InitializeAllLevelOne(canvas, brOne);
					//level.InitializeAllLevelTwo(canvas, brOne);
					textP.setColor(Color.GREEN);
					textP.setTextSize(40);
					canvas.drawText("Level " +levelCount, canvas.getWidth()/2-75, 50, textP);
					canvas.drawText("Score " +scoreCount, 10, 50, textP);
					canvas.drawText("Life " +playerHealth, canvas.getWidth()-110, 50, textP);
				}
				else if (levelCount == 2) {
					level.InitializeAllLevelTwo(canvas, brOne);
					textP.setColor(Color.GREEN); 
					textP.setTextSize(40);
					canvas.drawText("Level " +levelCount, canvas.getWidth()/2-75, 50, textP);
					canvas.drawText("Score " +scoreCount, 10, 50, textP);
					canvas.drawText("Life " +playerHealth, canvas.getWidth()-110, 50, textP);
				} 
				else if(levelCount == 3){
					ballSpeedX = 0;
					ballSpeedY = 0;
					xPlayer = -1000;
					xBall = -1000;
					yBall = 0;
					textP.setColor(Color.YELLOW); 
					textP.setTextSize(100);
					canvas.drawText("You Win", canvas.getWidth()/2-170, canvas.getHeight()/2+50, textP);
				}
				
				//Player
				if(playerHealth == 3) {
					ball.Initialize(canvas, xBall, yBall, 22, Color.YELLOW);
					bar.Initialize(canvas, xPlayer, yPlayer, Color.WHITE, playerSize); //Player xlength = 250, yLength = 50
				}
				else if(playerHealth == 2) {
					ball.Initialize(canvas, xBall, yBall, 22, Color.YELLOW);
					bar.Initialize(canvas, xPlayer, yPlayer, Color.WHITE, playerSize); //Player xlength = 250, yLength = 50
				} 
				else if(playerHealth == 1) {
					ball.Initialize(canvas, xBall, yBall, 22, Color.YELLOW);
					bar.Initialize(canvas, xPlayer, yPlayer, Color.WHITE, playerSize); //Player xlength = 250, yLength = 50
				} else{
					ballSpeedX = 0;
					ballSpeedY = 0;
					xPlayer = 0;
					textP.setColor(Color.YELLOW); 
					textP.setTextSize(100);
					canvas.drawText("Game Over", canvas.getWidth()/2-250, canvas.getHeight()/2+50, textP);
				}
				// Screen Tap checking x position on screen
				this.setOnTouchListener(new OnTouchListener() {
					
					public boolean onTouch(View v, MotionEvent event) {
						screenX = (int)event.getX();
						switch(event.getAction()) {
							case MotionEvent.ACTION_DOWN:
								touchFlag = true;
								break;
							case MotionEvent.ACTION_UP:
								touchFlag = false;
								break;
						}
						return true;
					}
				});
				Collision(canvas);
				
				// Player Movement According to Touch
				if(touchFlag) {
					if(screenX > canvas.getWidth()/2) {
						xPlayer += 15;
						if(xPlayer >= canvas.getWidth()-playerSize)
							xPlayer = canvas.getWidth()-playerSize;
					} else {
						xPlayer -= 15;
						if(xPlayer <= 0)
							xPlayer = 0;
					}
				} else {
					xPlayer = xPlayer;
				}
				
				invalidate();			
		}

protected void Collision(Canvas canvas){
	xBall++;
	
	// Collision between Player and Ball
	if(yBall-20 > canvas.getHeight()-100 && (xBall-20 >= bar.getX() && xBall-20 <= bar.getX()+playerSize)) {
		yBallFlag = true;
		if(xBall-20 >= bar.getX() && xBall-20 <= bar.getX()+(playerSize/5))
			xBallFlag = true;
		else if(xBall-20 >= bar.getX()+((playerSize/5)*4) && xBall-20 <= bar.getX()+playerSize)
			xBallFlag = false;
	}
	
	
	// Collision between Bricks and Ball
			//Ball 1
			if((yBall-20 > brOne[0].getY()-80 && yBall-20 < brOne[0].getY()+60) && (xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100)  && brOne[0].GetFlag() == true) {
			
				if((xBall-20 > brOne[0].getX()+87 && xBall-20 < brOne[0].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
					
				brOne[0].Erase(Color.TRANSPARENT);
				brOne[0].NoBrick();
				scoreCount++;
			}
			//Ball 2
			if((yBall-20 > brOne[1].getY()-80 && yBall-20 < brOne[1].getY()+60) && (xBall-20 > brOne[1].getX()-50 && xBall-20 < brOne[1].getX()+100) && brOne[1].GetFlag() == true) {
				
				if((xBall-20 > brOne[1].getX()+87 && xBall-20 < brOne[1].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[1].getX()-50 && xBall-20 < brOne[1].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
											
				brOne[1].Erase(Color.TRANSPARENT);
				brOne[1].NoBrick();
				scoreCount++;
			
			}
			
			//Ball 3
			if((yBall-20 > brOne[2].getY()-80 && yBall-20 < brOne[2].getY()+60) && (xBall-20 > brOne[2].getX()-50 && xBall-20 < brOne[2].getX()+100)  && brOne[2].GetFlag() == true) {
				
				if((xBall-20 > brOne[2].getX()+87 && xBall-20 < brOne[2].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[2].getX()-50 && xBall-20 < brOne[2].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
					
				brOne[2].Erase(Color.TRANSPARENT);
				brOne[2].NoBrick();
				scoreCount++;
			}		
			
			//Ball 4
			if((yBall-20 > brOne[3].getY()-80 && yBall-20 < brOne[3].getY()+60) && (xBall-20 > brOne[3].getX()-50 && xBall-20 < brOne[3].getX()+100)  && brOne[3].GetFlag() == true) {
				
				if((xBall-20 > brOne[3].getX()+87 && xBall-20 < brOne[3].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[3].getX()-50 && xBall-20 < brOne[3].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
					
				brOne[3].Erase(Color.TRANSPARENT);
				brOne[3].NoBrick();
				scoreCount++;
			}		
			
			//Ball 5
			if((yBall-20 > brOne[4].getY()-80 && yBall-20 < brOne[4].getY()+60) && (xBall-20 > brOne[4].getX()-50 && xBall-20 < brOne[4].getX()+100)  && brOne[4].GetFlag() == true) {
				
				if((xBall-20 > brOne[4].getX()+87 && xBall-20 < brOne[4].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[4].getX()-50 && xBall-20 < brOne[4].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
					
				brOne[4].Erase(Color.TRANSPARENT);
				brOne[4].NoBrick();
				scoreCount++;
				}
			//Ball 6
			if((yBall-20 > brOne[5].getY()-80 && yBall-20 < brOne[5].getY()+60) && (xBall-20 > brOne[5].getX()-50 && xBall-20 < brOne[5].getX()+100)  && brOne[5].GetFlag() == true) {
				
				if((xBall-20 > brOne[5].getX()+87 && xBall-20 < brOne[5].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[5].getX()-50 && xBall-20 < brOne[5].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
				
					
				brOne[5].Erase(Color.TRANSPARENT);
				brOne[5].NoBrick();
				scoreCount++;
				
			}		
			
			//Ball 7
			if((yBall-20 > brOne[6].getY()-80 && yBall-20 < brOne[6].getY()+60) && (xBall-20 > brOne[6].getX()-50 && xBall-20 < brOne[6].getX()+100)  && brOne[6].GetFlag() == true) {
				
				if((xBall-20 > brOne[6].getX()+87 && xBall-20 < brOne[6].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[6].getX()-50 && xBall-20 < brOne[6].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
			
				brOne[6].Erase(Color.TRANSPARENT);
				brOne[6].NoBrick();
				scoreCount++;
			}	

			//Ball 8
			if((yBall-20 > brOne[7].getY()-80 && yBall-20 < brOne[7].getY()+60) && (xBall-20 > brOne[7].getX()-50 && xBall-20 < brOne[7].getX()+100)  && brOne[7].GetFlag() == true) {
				
				if((xBall-20 > brOne[7].getX()+87 && xBall-20 < brOne[7].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[7].getX()-50 && xBall-20 < brOne[7].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
				
								
				brOne[7].Erase(Color.TRANSPARENT);
				brOne[7].NoBrick();
				scoreCount++;
				
			}	
			
			//Ball 9
			if((yBall-20 > brOne[8].getY()-80 && yBall-20 < brOne[8].getY()+60) && (xBall-20 > brOne[8].getX()-50 && xBall-20 < brOne[8].getX()+100)  && brOne[8].GetFlag() == true) {
				
				if((xBall-20 > brOne[8].getX()+87 && xBall-20 < brOne[8].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[8].getX()-50 && xBall-20 < brOne[8].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
				
				brOne[8].Erase(Color.TRANSPARENT);
				brOne[8].NoBrick();
				scoreCount++;
				
			}		
			
			//Ball 10
			if((yBall-20 > brOne[9].getY()-80 && yBall-20 < brOne[9].getY()+60) && (xBall-20 > brOne[9].getX()-50 && xBall-20 < brOne[9].getX()+100) && brOne[9].GetFlag() == true) {
				
				if((xBall-20 > brOne[9].getX()+87 && xBall-20 < brOne[9].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[9].getX()-50 && xBall-20 < brOne[9].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
				
						
				brOne[9].Erase(Color.TRANSPARENT);
				brOne[9].NoBrick();
				scoreCount++;
				
			}	
			
			//Ball 11
			if((yBall-20 > brOne[10].getY()-80 && yBall-20 < brOne[10].getY()+60) && (xBall-20 > brOne[10].getX()-50 && xBall-20 < brOne[10].getX()+100) && brOne[10].GetFlag() == true) {
				
				if((xBall-20 > brOne[10].getX()+87 && xBall-20 < brOne[10].getX()+100))
					xBallFlag = false;
				else if((xBall-20 > brOne[10].getX()-50 && xBall-20 < brOne[10].getX()-37))
					xBallFlag = true;
				else if((xBall-20 > brOne[0].getX()-50 && xBall-20 < brOne[0].getX()+100) && (yBall-20 > brOne[0].getY()+57 && yBall-20 < brOne[0].getY()+60)){
					yBallFlag = true;
				} else {
					yBallFlag = false;
				}
			
				brOne[10].Erase(Color.TRANSPARENT);
				brOne[10].NoBrick();
				scoreCount++;
				
			}
			
			

			if(xBallFlag == true) {
				xBall -= ballSpeedX;
			}

			if(xBallFlag == false) {
				xBall += ballSpeedX;
			}
				
			if(yBallFlag == false)
				yBall += ballSpeedY;

			if(yBallFlag == true)
				yBall -= ballSpeedY;
			
			if(xBall < 40)
				xBallFlag = false;

			if(xBall > canvas.getWidth()-40)
				xBallFlag = true;
			
			if(yBall < 40)
				yBallFlag = false;

			if(yBall >= canvas.getHeight()) {
				yBallFlag = true;
				playerHealth--;
			}
			
	}
	
}
