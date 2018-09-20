package shahrukh.seventh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;


public class Ball extends View{
	Paint p;
	
	public Ball(Context context, int color) {
		super(context);
		// TODO Auto-generated constructor stub
		p = new Paint();
		p.setColor(color);
		p.setStyle(Style.FILL);
		p.setStrokeWidth(10);
	}
	
	public void Initialize(Canvas canvas, int x, int y, int r, int color) {
		canvas.drawCircle(x, y, r, p);
		p.setColor(color);
		p.setStyle(Style.FILL);
		p.setStrokeWidth(10);
	}
}
