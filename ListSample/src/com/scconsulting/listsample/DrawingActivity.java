package com.scconsulting.listsample;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class DrawingActivity extends Activity {
	
	View mView;   
	private static Paint mPaint;
	private int shapeId;
	static float startX = 100f;
	static float startY = 100f;
	static float stopX = 300f;
	static float stopY = 300f;
	private static boolean blue = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.draw);
		
		// Set up a custom view to draw into
		LinearLayout layout = (LinearLayout) findViewById(R.id.myDrawing);  
		mView = new DrawingView(this);  
		layout.addView(mView, new LayoutParams(  
				LinearLayout.LayoutParams.MATCH_PARENT,  
				LinearLayout.LayoutParams.MATCH_PARENT));  
		init();
		
		Intent intent = this.getIntent();
		shapeId = intent.getIntExtra("shape", 0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_sample, menu);
		return true;
	}
	
	@Override
	public void onResume(){
    	super.onResume();

 	}
	
	private void init() {
		mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(0xFFFFFF00);  // Yellow
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(3);
	}
	
	class DrawingView extends View {
		private Path path;
		private Bitmap mBitmap;
		private Canvas mCanvas;
		  
		public DrawingView(Context context) {
			super(context);
			path = new Path(); 
			mBitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBitmap);
			this.setBackgroundColor(Color.BLACK);
		}
		
		@Override  
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			canvas.drawPath(path, mPaint);
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				// Set the beginning of the touch "event".
				startX = event.getX();
				startY= event.getY();
				
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				
				// We could use the ACTION_MOVE event to do finger painting,
				//   following the motion of the user's touch.
				
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				
				// Draw all shapes at the ACTION_UP event,
				//    when user picks up finger.
				
				stopX = event.getX();
				stopY= event.getY();
				//Log.i("DrawingActivity", "Action_Up");
				
				// Draw all shapes any size,
				//    as indicated by the user's swipe motion on the screen
				// Pass the x, y coordinates of the user's swiped line to the shape
				//    drawing methods, to make these methods more portable.
				switch(shapeId) {
	            case 0: // Line
	            	drawLine(path, startX, startY, stopX, stopY);
	            	break;
	            case 1: // Arrow
	            	drawArrow(path, startX, startY, stopX, stopY);
	            	break;
	            case 2: // Circle
	            	drawCircle(path, startX, startY, stopX, stopY);
	            	break;
	            case 3: // Oval
	            	drawOval(path, startX, startY, stopX, stopY);
	            	break;
	            case 4: // Rectangle
	            	drawRectangle(path, startX, startY, stopX, stopY);
	            	break;
	            case 5: // Star
	            	drawStar(path, startX, startY, stopX, stopY);
	            	break;
	            case 6: // Star of David
	            	drawStar6(path, startX, startY, stopX, stopY);
	            	break;
	            case 7: // Heart
	            	drawHeart(path, startX, startY, stopX, stopY);
	            	break;
	            case 8: // Shamrock
	            	drawShamrock(path, startX, startY, stopX, stopY);
	            	break;
	            case 9: // Tree
	            	drawTree(path, startX, startY, stopX, stopY);
	            	break;
	            case 10: // Flower
	            	drawFlower(path, startX, startY, stopY);
	            	break;
	            case 11: // Happy Face
	            	drawHappy(path, startX, startY, stopX, stopY);
	            	break;
				case 12: // Teddy Bear
	            	drawBear(path, startX, startY, stopX, stopY);
	            	break;
				}
				
				mCanvas.drawPath(path, mPaint);
				
			}
			
			// Force the the canvas to redraw.
			invalidate();
		   
			return true;
		}
	}
	
	public static Path drawLine(Path path, float startX, float startY, float stopX, float stopY) {
		mPaint.setColor(Color.WHITE);
		mPaint.setStyle(Paint.Style.STROKE);
		
    	path.moveTo(startX, startY);
    	path.lineTo(stopX, stopY);
    	
		return path;
	}
	
	public static Path drawArrow(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.YELLOW);
		mPaint.setStyle(Paint.Style.STROKE);
		
		Arrow.drawPath(path, startX, startY, stopX, stopY);
    	
		return path;
	}
	
	public static Path drawCircle(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.CYAN);
		mPaint.setStyle(Paint.Style.STROKE);
		
    	double temp = Math.pow((startX-stopX),2) + Math.pow((startY-stopY),2);
    	float radius = (float)Math.sqrt(temp)/2;
    	path.addCircle((startX + stopX)/2,(startY + stopY)/2, radius, Path.Direction.CW);
    	
		return path;
	}
	
	public static Path drawOval(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.rgb(255, 153, 0));  // Orange
		mPaint.setStyle(Paint.Style.STROKE);
		
		if (stopY > startY) {
			// User swiped from top to bottom.
    		// Switch start and stop.
    		float tempStopX = stopX;
    		float tempStopY = stopY;
   			stopX = startX;
			stopY = startY;
				
   			startX = tempStopX;
   			startY = tempStopY;
		}
		
    	RectF rectF = new RectF();
    	rectF.set(startX, stopY, stopX, startY);
		path.addOval(rectF, Path.Direction.CW);
		
		return path;
	}
	
	public static Path drawRectangle(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Paint.Style.STROKE);
		
		if (stopY > startY) {
			// User swiped from top to bottom.
    		// Switch start and stop.
    		float tempStopY = stopY;
			stopY = startY;
   			startY = tempStopY;
		}
		if (stopX < startX) {
			float tempStopX = stopX;
			stopX = startX;
			startX = tempStopX;
		}
		
		// Use new Path to set up rectangle,
		//   then add it to the main Path.
		// This is to prevent some odd extra lines from appearing
		//   when multiple rectangles are drawn.
		Path rectPath = new Path();
		rectPath.addRect(startX, stopY, stopX, startY, Path.Direction.CW);
		path.addPath(rectPath);
		
		return path;
	}
	
	public static Path drawStar(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.YELLOW);
		mPaint.setStyle(Paint.Style.STROKE);
		
		// Draw a 5-pointed star any size, any position, into a Path.
		Star.drawPath(path, startX, startY, stopX, stopY);
		
		return path;
	}
	
	public static Path drawStar6(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.YELLOW);
		mPaint.setStyle(Paint.Style.STROKE);
		
		// Draw a 6-pointed star, known as "Star of David", any size, any position, into a Path.
		Star6.drawPath(path, startX, startY, stopX, stopY);
		
		return path;
	}
	
	public static Path drawHeart(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.RED);
    	mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    	
    	// Draw a filled heart any size, any position, into a Path.
    	Heart.drawPath(path, startX, startY, stopX, stopY);
   		
	    return path;
	}
	
	public static Path drawShamrock(Path path, float startX, float startY, float stopX, float stopY) {
    	
    	mPaint.setColor(Color.GREEN);
    	mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    	
    	// Draw a green shamrock any size, any position, into a Path.
    	// The shamrock is drawn as 3 hearts, rotating the Path as each is drawn.
    	Shamrock.drawPath(path, startX, startY, stopX, stopY);
    	
    	return path;
    }
	
	public static Path drawTree(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		
		// Draw a green coniferous tree.
		// The tree will be upside down if the user swipes top to bottom on the screen.
		Tree.drawPath(path, startX, startY, stopX, stopY);
		
		return path;
	}
	
	public static Path drawFlower(Path path, float startX, float startY, float stopY) {
		
		mPaint.setColor(Color.MAGENTA);
		mPaint.setStyle(Paint.Style.STROKE);
		
		// Draw a flower as a Polar Rose Curve
		Flower.drawPath(path, startX, startY, stopY, stopY);
		
		return path;
	}
	
	public static Path drawHappy(Path path, float startX, float startY, float stopX, float stopY) {
		
		mPaint.setColor(Color.rgb(255, 206, 180)); // Light skin tone
		mPaint.setStyle(Paint.Style.STROKE);
		
		// Draw a happy face, any size, any position, into a Path.
		Happy.drawPath(path, startX, startY, stopX, stopY);
		
		return path;
	}
	
	public static Path drawBear(Path path, float startX, float startY, float stopX, float stopY) {
		
		if (blue) {
			mPaint.setColor(Color.BLUE);
		}
		else {
			mPaint.setColor(Color.MAGENTA);
		}
		blue = !blue;
		//mPaint.setColor(Color.rgb(102, 51, 0)); // Brown
		mPaint.setStyle(Paint.Style.STROKE);
		
		// Draw a teddy bear, any size, any position, into a Path.
		// Draw again, and watch the colors change.
		Bear.drawPath(path, startX, startY, stopX, stopY);
    	
		return path;
	}

}