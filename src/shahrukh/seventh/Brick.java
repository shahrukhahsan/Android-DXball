package shahrukh.seventh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;

public class Brick extends View{
	private Paint p;
	private int X, Y;
	boolean presenceFlag = true;
	
	public Brick(Context context, int color) {
		super(context);
		p = new Paint();
		p.setColor(color);
		p.setStyle(Style.FILL);
		p.setStrokeWidth(10);
	}
	
	public void Initialize(Canvas canvas, int x, int y) {
		X = x;
		Y = y;
		Rect rect = new Rect();
		rect.set(x, y, x+100, y+50);
		canvas.drawRect(rect, p);
	}
	
	//Erase a brick
	public void Erase(int color) {
		p.setColor(color);
	}


	public float getX() {
		return X;
	}
	
	public float getY() {
		return Y;
	}
	
	public boolean GetFlag() {
		return presenceFlag;
	}
	
	public void SetFlagTrue() {
		presenceFlag = true;
	}
	
	// Erase Location of Brick
	public void NoBrick() {
		presenceFlag = false;
	}
}
