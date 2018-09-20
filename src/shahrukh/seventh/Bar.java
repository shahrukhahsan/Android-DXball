package shahrukh.seventh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;

public class Bar extends View{
	Paint p;
	private int X, Y;
	
	public Bar(Context context, int color) {
		super(context);
		p = new Paint();
		p.setColor(color);
		p.setStyle(Style.FILL);
		p.setStrokeWidth(10);
	}
	
	public void Initialize(Canvas canvas, int x, int y, int color, int size) {
		X = x;
		Y = y;
		Rect rect = new Rect();
		rect.set(x, y, x+size, y+50);
		canvas.drawRect(rect, p);
		p.setColor(color);
		p.setStyle(Style.FILL);
		p.setStrokeWidth(10);
	}

	public float getX() {
		return X;
	}
	
	public float getY() {
		return Y;
	}
}
