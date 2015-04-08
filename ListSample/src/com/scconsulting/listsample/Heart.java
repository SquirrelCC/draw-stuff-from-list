package com.scconsulting.listsample;

import android.graphics.Path;
import android.graphics.RectF;

public class Heart {

	public Heart() {
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
	 * Draw a heart into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the heart.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		if (stopY > startY) {
			// User swiped from top to bottom.
    		// Switch start and stop. (Draw right side up.)
    		float tempStopX = stopX;
    		float tempStopY = stopY;
   			stopX = startX;
			stopY = startY;
				
   			startX = tempStopX;
   			startY = tempStopY;
		}
    	
    	// Wide, 90 degree angle at base
		float diameter = ( (startY-stopY) / ((float)Math.sqrt(2)) );
	    float centerX = startX;
	    float centerY = startY - ((startY-stopY) / 2.0f);
	    float z = diameter / 6.0f;
   		
	    // Move to first position, no line drawn
	    path.moveTo((centerX - diameter) + (2.0f*z), centerY);
   		
	    float left = (centerX+z) - diameter;
   		float top = stopY - z;
   		float right = left + diameter;
   		float bottom = top + diameter;

   		//  Draw first arc, on top left.
   		path.arcTo(new RectF(left, top, right, bottom), -225f, 177f, false);
   		
   		left = centerX - z;
   		right = left + diameter;
   		
   		// Draw right arc.
   		path.arcTo(new RectF(left, top, right, bottom), -132f, 180f, false);
   		
   		//  Lower lines in V-shape.
   		path.lineTo(centerX, startY);
   		path.lineTo((centerX - diameter) + (2.0f*z), centerY);
   		
	    return path;
	}
}
