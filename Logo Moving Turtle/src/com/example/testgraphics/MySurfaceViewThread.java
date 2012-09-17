package com.example.testgraphics;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MySurfaceViewThread extends Thread {
		
		private boolean running = false;
		private DrawingCanvas dcObj;
		SurfaceHolder surfaceHolder;
		    
		MySurfaceViewThread(DrawingCanvas obj) {	
			dcObj = obj;
			surfaceHolder = dcObj.getHolder();
		}
		
		@Override 
		public void run() {
			//Repeat drawing loop until the thread is stopped.
			while(running) {
				//Lock the canvas surface and return the canvas to draw on it.
				 Canvas canvas = surfaceHolder.lockCanvas();
				 //Call DrawingCanvas with the Canvas Object to Draw on it.
				 synchronized (surfaceHolder) {
					 // draws the canvas on the panel
					 dcObj.onDraw(canvas);
				} 
				//Unlock the Canvas and render the current image.
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		
		public void setRunning(boolean runState) {
			running = runState;
		}
	}