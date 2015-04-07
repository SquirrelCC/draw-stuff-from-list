package com.scconsulting.listsample;

import android.graphics.Path;

public class Arrow {

	public Arrow() {
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
	 * Draw an arrow into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting and ending x,y coordinates of the user's swiped line
	 * to determine the length and direction of the arrow.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		// Make arrowhead sides 1/6 of line length.
		// Line length = squareroot((|delta X|^2) + (|delta Y|^2))
		int lineLen = (int) Math.round( Math.sqrt( 
			Math.pow(Math.abs(stopX - startX), 2) + 
			Math.pow(Math.abs(stopY - startY), 2)
		));
		int arrowheadLen = Math.round(lineLen / 6);

		// 30 60 90 triangle side ratio: 1, sqrt(3), 2 (sides opposite angles)
		int centerLineLen  = (int) Math.round((arrowheadLen / 2) * Math.sqrt(3));
		
		// Calculate slope of the line.
		float lineSlope = (0 - (stopY - startY)) / (stopX - startX);
			
		// The angle of the line, relative to the X axis,
		//    is the arctangent of the slope of the line.
		// Math.atan returns radians, so convert to degrees.
		double angleLine = Math.toDegrees((Math.atan(lineSlope)));

		if (angleLine < 0) {
			angleLine = angleLine + 180;
		}
    			
		// Calculate the angle of the arrowhead,
		//    mapped to an angle from the X axis.
		double angleArrow1 = angleLine + 30;
		double angleArrow2 = angleLine - 30;
    			
		if (angleArrow2 < 0) {
			angleArrow2 = angleArrow2 + 360;
		}
    			
		double darrowEndX = stopX;
		double darrowEndY = stopY;
    			
		// Calculate change in X,Y to move to each the 2 arrowhead points.
		double deltaX1 = arrowheadLen * Math.cos(Math.toRadians(angleArrow1));
		double deltaY1 = arrowheadLen * Math.sin(Math.toRadians(angleArrow1));
		double deltaX2 = arrowheadLen * Math.cos(Math.toRadians(angleArrow2));
		double deltaY2 = arrowheadLen * Math.sin(Math.toRadians(angleArrow2));
		
		double deltaX3 = centerLineLen * Math.cos(Math.toRadians(angleLine));
		double deltaY3 = centerLineLen * Math.sin(Math.toRadians(angleLine));
    			
		if (stopY > startY) {
			deltaX1 = 0 - deltaX1;
			deltaY1 = 0 - deltaY1;
			deltaX2 = 0 - deltaX2;
			deltaY2 = 0 - deltaY2;
			deltaX3 = 0 - deltaX3;
			deltaY3 = 0 - deltaY3;
		}
    			
		// Calculate the 2 arrowhead points.
		float arrowHeadPoint1X = (float) (darrowEndX - deltaX1);
		float arrowHeadPoint1Y = (float) (darrowEndY + deltaY1);
		float arrowHeadPoint2X = (float) (darrowEndX - deltaX2);
		float arrowHeadPoint2Y = (float) (darrowEndY + deltaY2);
		
		float arrowHeadBaseX = (float) (darrowEndX - deltaX3);
		float arrowHeadBaseY = (float) (darrowEndY + deltaY3);
    			
		// Draw first line, to base of arrowhead.
		path.moveTo(startX, startY);
		path.lineTo(arrowHeadBaseX, arrowHeadBaseY);

		// Draw a line to 1st arrowhead point.
		path.lineTo(arrowHeadPoint1X, arrowHeadPoint1Y);
		
		// Draw a line to 2nd point.
		path.lineTo(arrowHeadPoint2X, arrowHeadPoint2Y);
		
		// Draw a line to point of arrow.
		path.lineTo(stopX, stopY);
		
		// Redraw side of arrowhead to 1st arrowhead point, to make the top rounded,
		//    because "Paint.Cap" is set to "BUTT"  in onDraw() to eliminate
		//    round cap of 1st line extending into arrowhead.
		path.lineTo(arrowHeadPoint1X, arrowHeadPoint1Y);
		
		return path;
	}
}
