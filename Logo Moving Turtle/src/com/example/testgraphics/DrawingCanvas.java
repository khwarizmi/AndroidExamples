package com.example.testgraphics;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingCanvas extends SurfaceView implements SurfaceHolder.Callback {

	private MySurfaceViewThread mySurfaceViewThread ;
	private SurfaceHolder holder;
	private Sprite turtle;
	
	private Paint mPaint = new Paint();
	// Variables to test Logo Turtle loop
	int ii = 0,j = 0,k = 0;
	
	public DrawingCanvas(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		holder = getHolder();
		holder.addCallback(this);
		mySurfaceViewThread = new MySurfaceViewThread(this);
		Bitmap Turtle = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		turtle = new Sprite(100, 100, Turtle);
		turtle.setPenDown(true);

		mPaint.setColor(Color.BLACK);
	}
	
	public void onDraw(Canvas canvas) {
		
		//Flush current canvas drawings
		canvas.drawColor(Color.WHITE);
		
	    // Call Draw Turtle Function
	    turtle.Draw(canvas);
	    canvas.drawText("Feras", 100, 100, mPaint);
	    
	    // Lags the render view to simulate frame-rates
		int i = 1000000;
		while(i > 0)
			i--;
		
		turtle.setPenColor(Color.CYAN);
		
		if(ii < 2) {
			if(j < 4) {
				if(k < 4) {
					turtle.move(60);
					turtle.changeTurnBy(90);
					k++;
				} else {
					k = 0;
					turtle.changeTurnBy(90);
					j++;
					if(j == 4) {
						j = 0;
						ii++;
						turtle.changeTurnBy(45);				
						}
				}
			}
		}
		
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceCreated(SurfaceHolder holder) {
		if(!mySurfaceViewThread.isAlive())
		{
			mySurfaceViewThread = new MySurfaceViewThread(this);
			mySurfaceViewThread.setRunning(true);
			mySurfaceViewThread.start();	
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		 // simply copied from sample application LunarLander:
    	// we have to tell thread to shut down & wait for it to finish, or else
    	// it might touch the Surface after we return and explode
    	boolean retry = true;
    	mySurfaceViewThread.setRunning(false);
    	while (retry) {
        try {
            mySurfaceViewThread.join();
            retry = false;
        	} catch (InterruptedException e) {
            // we will try it again and again...
        	}
    	} 
		 
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){

		/*
		int action = event.getAction();
		int touch_x = (int) event.getX();
		int touch_y = (int) event.getY();
		 
		 Log.d("HIT_0", Integer.toString(action));
		 
		switch(action) {
		case MotionEvent.ACTION_DOWN :
			 Log.d("HIT_0", "pre");
			 if(turtle.touchedDown(touch_x, touch_y)) {
					turtle.move(100);
			}
			 Log.d("HIT_0", "post");
			return true;
		case MotionEvent.ACTION_UP : 
			break;
			
		case MotionEvent.ACTION_CANCEL : ; break;
		case MotionEvent.ACTION_MOVE : 
			 int historySize = event.getHistorySize();
			 int x,y;
			 for(int i = 0; i < historySize; i++)
			 {
				 x = (int)event.getHistoricalX(i);
				 y = (int)event.getHistoricalY(i);

			 }
			 x = (int) event.getX();
			 y = (int) event.getY();

			 return true;
		}
		*/
		return super.onTouchEvent(event);
		
	}
	
	
	
}
