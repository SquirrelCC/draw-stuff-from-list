package com.scconsulting.listsample;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

public class Shamrock {

	public Shamrock() {
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
	 * Draw shamrock into a Path, to be drawn to a Canvas in the calling Activity.
	 * This Class uses the Heart Class.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the shamrock.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		int reps = 3;
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
    	
    	float centerX = startX;
    	float centerY = startY - ((startY-stopY)/2);
    	
    	Matrix mMatrix = new Matrix();
    	mMatrix.postRotate(360f/reps, centerX, centerY);
    	
    	for (int i = 0; i < reps; i++){
	    	
    		// Draw a Heart for each leaf.
    		Heart.drawPath(path, centerX, centerY, stopX, stopY);
	    
    		// Rotate the path (360/number of repetitions) degrees,
    		//    then draw the next leaf.
    		// Rotation is around the center of the path.
    		if (i < reps) {
    			path.transform(mMatrix);
    		}
	    }
    	
    	// Draw a stem
    	float left = centerX - ((startY - stopY)*3/2);
    	float top = stopY - ((startY - stopY));
    	float right = centerX;
    	float bottom = startY + ((startY - stopY));
    	
    	path.moveTo(startX, startY - ((startY - stopY)/2f));
    	path.arcTo(new RectF(left, top, right, bottom), 0f, 20f, true);
		
		return path;
	}
}
