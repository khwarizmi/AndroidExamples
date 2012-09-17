package com.example.testgraphics;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class Sprite {

	private int x,y; //left-bottom (x,y) point.
	private int w,h; //width and height of sprite.
	//Rotation Angle
	private float rotationAngle;
	private double directionAngle = Math.PI; // 180 angle;
	
	private Bitmap sprite;
	
	//Pen Properties
	private boolean penDown;
	private int penSize;
	private int penColor;  //Bit representation of Color(each is 8-bit digit):  [(alpha << 24) | (red << 16) | (green << 8) | blue], Maximum Color Value: 32 bits
	private Paint pen;
	private ArrayList<Point> penHistory; // Holds pendown History.
	
	public Sprite(int _x, int _y, Bitmap _sprite){
		x = _x; 
		y = _y;
		
		w = _sprite.getWidth();
		h = _sprite.getHeight();
		
		pen = new Paint();
		penHistory = new ArrayList<Point>();
		
		sprite = _sprite;
	}
	
	/*  set: sets the value of params to member variables.
	 *  change: adds the value of the param to member variables.
	 *  
	 *  Pen Capabilities(drawing with a pen[size, color]).
	 *  Motion Capabilities(translation, rotation). 
	 * */
	
	/*  Pen Capabilities :
	 *    set _ PenDown[True | False], PenFormat[PenSize, Color PenColor]
	 *    change _ PenSize[PenSize], PenColor[PenColor]
	 * */
	private void addPoint() {
		if(penDown)
			penHistory.add(new Point(x + w/2 , y + h/2));
	}
	
	public void setPenDown(boolean _penDown) {
		
		//set pen down to current location
		if(penDown == false && _penDown == true) {
			penDown = true;
			addPoint();
		}
		penDown = _penDown;
	}

	public void setPenFormat(int _penSize, int _penColor) {
		penSize = _penSize;
		penColor = _penColor;
		pen.setColor(penColor);
		pen.setStrokeWidth(penColor);
	}

	public void setPenColor(int _penColor) {
		penColor = _penColor;
		pen.setColor(penColor);
	}

	public void changePenSizeBy(int _penSize) {
		penSize += _penSize;
		pen.setStrokeWidth(penSize);
	}
	
	public void changePenColorBy(int _penColor) {
		penColor += _penColor;
		pen.setColor(penColor);
	}
	
	public void clearPenDrawings() {
		penHistory.clear();
	}
	/*  Motion Capabilities : 
	 *    set _ Translation[X, Y], PointTo[RotationAngle],  
	 *    change _ X[x], Y[y]
	 *    move
	 * */
	public void changeX(int _x) {
		x += _x;	
		addPoint();
	}
	
	public void changeY(int _y) {
		y += _y;
		addPoint();
	}
	
	public void move(int steps) {
		
		x = x  + (int)(steps * Math.sin(directionAngle));
		y = y  + (int)(steps * Math.cos(directionAngle));
		
		addPoint();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void changeTurnBy(float _rotationAngle) {
		//TODO: reduce angle region to 0 - 360
		rotationAngle += _rotationAngle;
		directionAngle -= (_rotationAngle * Math.PI / 180);
	}
	
	public void setTranslation(int _x, int _y) {
		x += _x;
		y += _y;
		
		addPoint();
	}
	
	public void setPointTo(float _rotationAngle) {
		rotationAngle = _rotationAngle;
		//TODO: Make the Direction Angle change also
	}

	public void goTo(int _x, int _y) {
		int x_diff = Math.abs(x - _x);
		int y_diff = Math.abs(y - _y);
		setTranslation(x_diff, y_diff);
	}
	
	/* Sensing Capabilities */
	public float distanceTo(Sprite another) {
		int newX = Math.abs(x - another.getX());
		int newY = Math.abs(y - another.getY());
		return (float) Math.sqrt(newX * newX + newY * newY);
	}
	
	/* Touched Down the Sprite Checker */
	public boolean touchedDown(int touchX, int touchY) {
		
		Log.d("HIT_1", "beginning hit-test");
		//check if they are inside the bounding box of the sprite
		if( !(touchX >= x  && touchX <= x + w && touchY >= y && touchY <= y + h) ) {
			Log.d("HIT_1", "it's outside bounding rect");
			return false;
		}
		

		Log.d("HIT_2", "here");
		
		int lowestX = Math.max(touchX - 2, x) - x;
		int highestX = Math.min(touchX + 2, x + w-1) - x;
		int lowestY = Math.max(touchY - 2, y) - y;
		int highestY = Math.min(touchY + 2, y + h-1) - y;
		
		for(int i = lowestX; i <= highestX; i++) {
			for(int j = lowestY; j <= highestY; j++) {
				Log.d("HIT", String.format("Testing pixel/ alpha=%s", Color.alpha(sprite.getPixel(i, j))));
				if(Color.alpha(sprite.getPixel(i, j)) == 255) {
					
					Log.d("HIT_1", "found non-transparent pixel");
					return true;
				}
			}
		}
		Log.d("HIT_1", "no hit found");
		

		Log.d("HIT_3", "here");
		
		return false;
	}
	
	/* Self-Drawing Function */
	public void Draw(Canvas canvas) {
		
		// DrawLine if Pen is Down from Last X,Y the pen was down to the New X, New Y.	
		if(penHistory.size() != 0) {
			Point p1, p2;
			for(int i = 1; i < penHistory.size(); i++) {
				p1 = penHistory.get(i-1);
				p2 = penHistory.get(i);
				canvas.drawLine(p1.x, p1.y, p2.x, p2.y, pen);
			}
		}
		
		canvas.save();
		canvas.rotate(rotationAngle, x + w/2, y + h/2);
		canvas.drawBitmap(sprite, x , y, null);
		canvas.restore();
	}
	
}
