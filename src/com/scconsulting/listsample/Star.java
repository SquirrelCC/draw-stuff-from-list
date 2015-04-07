package com.scconsulting.listsample;

import android.graphics.Path;

public class Star {

	public Star() {
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
	 * Draw 5 pointed star into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the star.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		if (startY < stopY) {
    		// Star would be upside down. Switch start and stop.
    		float tempY = startY;
    		startY = stopY;
    		stopY = tempY;
    	}
   		
   		float angle = 36;
   		float starLineLen = (float) ((startY-stopY)/Math.cos(Math.toRadians(angle))-4);
   		
   		// startX is at the horizontal center of the star.
   		// Shift it to the left to get the first start point.
   		// A little trigonometry here..
   		startX = (float) (startX - (starLineLen * Math.cos(Math.toRadians(72))) );
   		float saveX = startX;
   		float saveY = startY;

   		// Calculate change in X,Y to move to next star point.
   		float deltaX = (float) (starLineLen * Math.cos(Math.toRadians(angle)));
   		float deltaY = (float) (starLineLen * Math.sin(Math.toRadians(angle)));
   		stopX = startX + deltaX;
   		stopY = startY - deltaY;
   		
   		path.moveTo(startX, startY);
   		path.lineTo(stopX, stopY);
   		
   		startX = stopX;
   		startY = stopY;
   		angle = 0;

   		// Calculate change in X,Y to move to next star point.
   		deltaX = (float) (starLineLen * Math.cos(Math.toRadians(angle)));
   		deltaY = (float) (starLineLen * Math.sin(Math.toRadians(angle)));
   		stopX = startX - deltaX;
   		stopY = startY - deltaY;
   		
   		path.lineTo(stopX, stopY);
   		
   		startX = stopX;
   		startY = stopY;
   		angle = 36;

   		// Calculate change in X,Y to move to next star point.
   		deltaX = (float) (starLineLen * Math.cos(Math.toRadians(angle)));
   		deltaY = (float) (starLineLen * Math.sin(Math.toRadians(angle)));
   		stopX = startX + deltaX;
   		stopY = startY + deltaY;
   		
   		path.lineTo(stopX, stopY);
   		
   		startX = stopX;
   		startY = stopY;
   		angle = 72;

   		// Calculate change in X,Y to move to next star point.
   		deltaX = (float) (starLineLen * Math.cos(Math.toRadians(angle)));
   		deltaY = (float) (starLineLen * Math.sin(Math.toRadians(angle)));
   		stopX = startX - deltaX;
   		stopY = startY - deltaY;
   		
   		path.lineTo(stopX, stopY);
   		
   		startX = stopX;
   		startY = stopY;
   		
   		stopX = saveX;
   		stopY = saveY;
   		
   		path.lineTo(stopX, stopY);
   		path.close();
		
		return path;
	}
}
