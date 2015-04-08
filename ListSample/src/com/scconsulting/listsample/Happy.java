package com.scconsulting.listsample;

import android.graphics.Path;
import android.graphics.RectF;

public class Happy {

	public Happy() {
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
	 * Draw a happy face into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the happy face.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		float radius = Math.max(Math.abs(stopX - startX), Math.abs(startY - stopY)) / 2;
    	float centerX = startX + ((stopX - startX)/2);
    	float centerY = startY - ((startY - stopY)/2);

    	path.addCircle(centerX, centerY, radius, Path.Direction.CW);
	
    	// Left eye
    	Float centerEye = centerX - (radius / 3);
    	Float left = (float) (centerEye - (radius * 0.167));
    	Float top = (float) (centerY - (radius * 0.2));
    	Float right = (float) (centerEye + (radius * 0.167));
    	Float bottom = (float) (centerY + (radius * 0.0));

    	path.addOval(new RectF(left, top, right, bottom), Path.Direction.CW);
	
    	// Right eye - same top, bottom
    	centerEye = centerX + (radius / 3);
    	left = (float) (centerEye - (radius * 0.167));
    	right = (float) (centerEye + (radius * 0.167));

    	path.addOval(new RectF(left, top, right, bottom), Path.Direction.CW);
	
    	// Smile!
    	left = (float) (centerX - (radius * 0.5));
    	top = (float) (centerY - radius);
    	right = (float) (centerX + (radius * 0.5));
    	bottom = (float) (centerY + (radius * 0.67));

    	path.moveTo(centerX, bottom);
    	path.arcTo(new RectF(left, top, right, bottom), 90, 45);
    	path.moveTo(centerX, bottom);
    	path.arcTo(new RectF(left, top, right, bottom), 90, -45);
		
		return path;
	}
}
