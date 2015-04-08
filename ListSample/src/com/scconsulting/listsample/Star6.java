package com.scconsulting.listsample;

import android.graphics.Path;

public class Star6 {

	public Star6() {
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
	 * Draw 6 pointed star into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the star.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		float lineLen = (startY - stopY);
    	stopX = startX; // No slant. x doesn't change, no matter where path was swiped.
		
    	// 30-60-90 triangles from starting points, with side ratios 1, 2, sqrt3.
    	// Height of each triangle is 3/4 height of the star.
    	double deltaX = (.75 * lineLen) / Math.sqrt(3);
    	double deltaY = .75 * lineLen;

    	// Start at bottom of star.
    	path.moveTo(startX, startY);
		path.lineTo( (float) (startX + deltaX), (float) (startY - deltaY));
		path.lineTo( (float) (startX - deltaX), (float) (startY - deltaY));
		path.lineTo(startX, startY);
		path.close();
		
		// Move to top of star to draw 2nd triangle.
		path.moveTo(stopX, stopY);
		path.lineTo( (float) (stopX + deltaX), (float) (stopY + deltaY));
		path.lineTo( (float) (stopX - deltaX), (float) (stopY + deltaY));
		path.lineTo(stopX, stopY);
		path.close();
		
		return path;
	}
}
