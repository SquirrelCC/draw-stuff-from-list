package com.scconsulting.listsample;

import android.graphics.Path;

public class Flower {

	public Flower() {
		super();
	}
	
	/**
	 * 
	 * @param path - Path to contain the figure
	 * @param startX - Beginning x coordinate
	 * @param startY - Beginning y coordinate
	 * @param stopX - Ending x coordinate
	 * @param stopY - Ending y coordinate
	 * @return
	 * 
	 * Draw flower into a Path, to be drawn to a Canvas in the calling Activity.
	 * Draw a Polar Graph Rose Curve: r==Cos[p/q*theta]
	 * If p is even, there will be 2p petals. If p is odd, there will be p petals.
	 * q determines the width of the curve, or the width of each petal.
	 * Rose Curve with 7 petals: r = Cos(7/3*theta)
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the flower.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
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
		
		float theta;
		float centerX;
		float centerY;
		
		// Convert Polar coordinates to Cartesian coordinates:
		// double x = Math.cos( angleInRadians ) * radius;
		// double y = Math.sin( angleInRadians ) * radius;
		// Convert theta angle to radians to get "angleInRadians"
		float thetaIncrement = 1.0f;
		float distance = Math.abs(stopY - startY)/2;
		centerX = startX;
		centerY = stopY + Math.abs(stopY - startY)/2;
		
		// Start at Polar due "East", or rightmost x coordinate.
		path.moveTo(centerX+distance, centerY);

		// Increment theta from 0 to 360 degrees for even number, 0 to 540 for odd number.
		float deg = 542f;	// Extra 2 deg to close a small gap
		for (theta=0.0f; theta<deg; theta+=thetaIncrement) {
			
			path.lineTo( centerX + ( xConvert(theta) * distance ),
					centerY - ( yConvert(theta) * distance ) );
		}
			
		return path;
	}
	
	private static float xConvert(float theta) {
		// 7 petals
		return (float)Math.cos( Math.toRadians(theta) ) * (float)Math.cos( 7.0f/3.0f * Math.toRadians(theta) );
	}
	private static float yConvert(float theta) {
		// y = Math.sin(Math.toRadians(theta)) * radius,
		//    where radius = Cos(7f/3f * theta), for 7 petals.
		return (float)Math.sin( Math.toRadians(theta) ) * (float)Math.cos( 7.0f/3.0f * Math.toRadians(theta) );
	}
}
