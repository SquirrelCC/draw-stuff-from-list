package com.scconsulting.listsample;

import android.annotation.SuppressLint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Path.Op;

public class Bear {

	public Bear() {
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
	 * Draw teddy bear into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the bear.
	 */
	@SuppressLint("NewApi")
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		if (stopY > startY) {
			// User swiped from top to bottom.
    		// Switch start and stop.
    		float tempStopY = stopY;
			stopY = startY;
   			startY = tempStopY;
		}
		
		Path bearPath = new Path();
		float lineLen = (startY - stopY);
    	stopX = startX; // No slant. x doesn't change, no matter where path was swiped.

		float height = lineLen * 0.667f;
    	float width = lineLen * 0.667f;
    	
    	// Left side of body, from bottom
    	RectF rectF = new RectF();
    	rectF.set(startX - (width/2), startY - height, startX + width/2, startY);

    	bearPath.moveTo(startX, startY);
    	bearPath.arcTo(rectF, 90, 146);
		
		// Save end point
    	PathMeasure pm = new PathMeasure(bearPath, false);
		float coordinates1[] = {0f, 0f};
		// get last point
		pm.getPosTan(pm.getLength(), coordinates1, null);
		
		// Head
		RectF headRectF = new RectF();
    	headRectF.set(startX - (width * 0.333f), stopY, startX + (width * 0.333f), startY - (lineLen/2));
    	bearPath.arcTo(headRectF, 146, 244);
    	
		// Right side of body
    	bearPath.arcTo(rectF, 304, 146);
		
    	// Feet
    	Path footPath = new Path();
    	footPath.addCircle(startX - (width/2.5f), startY - (width/5), width/5, Path.Direction.CW);
    	footPath.addCircle(startX + (width/2.5f), startY - (width/5), width/5, Path.Direction.CW);
    	if (android.os.Build.VERSION.SDK_INT >= 19) {
    		// If API 19 or above, use OP to remove portion of bearPath arc which is inside footPath.
    		bearPath.op(footPath, Op.DIFFERENCE);
    		bearPath.addPath(footPath);
    	}
    	else {
    		bearPath.addPath(footPath);
    	}
		
    	// Left arm
    	bearPath.moveTo(coordinates1[0], coordinates1[1]);
		bearPath.lineTo(
    			coordinates1[0] + ( (width/3f) * (float)Math.sin(Math.toRadians(250)) ),
    			coordinates1[1] - ( (width/3f) * (float)Math.cos(Math.toRadians(250)) ) );
    	
		float newX = coordinates1[0] + ( (width/3f) * (float)Math.sin(Math.toRadians(250)) );
    	float newY = coordinates1[1] - ( (width/3f) * (float)Math.cos(Math.toRadians(250)) );
    	
    	RectF armRectF = new RectF(
    			newX - (width/6f),
    			newY,
    			newX + (width/6f),
    			newY + (width/3f) );
    	bearPath.arcTo(armRectF, 239f, -180);
    	
    	// Right arm
    	newX = startX + (startX - coordinates1[0]);
    	bearPath.moveTo(newX, coordinates1[1]);
    	
    	bearPath.lineTo(
    			newX + ( (width/3f) * (float)Math.sin(Math.toRadians(110)) ),
    			coordinates1[1] - ( (width/3f) * (float)Math.cos(Math.toRadians(110)) ) );
    	
    	newX = newX + ( (width/3f) * (float)Math.sin(Math.toRadians(110)) );
    	newY = coordinates1[1] - ( (width/3f) * (float)Math.cos(Math.toRadians(110)) );
    	
    	armRectF = new RectF(
    			newX - (width/6f),
    			newY,
    			newX + (width/6f),
    			newY + (width/3f) );
    	bearPath.arcTo(armRectF, 301f, 180);
    	
    	// Left ear
    	rectF = new RectF( startX - (width * 0.375f), stopY,
				startX - (width * 0.125f), stopY + (width/4) );
		bearPath.arcTo(rectF, 118f, 200, true);
		
    	// Right ear
		rectF = new RectF( startX + (width * 0.125f), stopY,
				startX + (width * 0.375f), stopY + (width/4) );
		bearPath.arcTo(rectF, 62f, -200, true);
		
		//Eyes
		float midHeadY = stopY + (lineLen/4f);
		bearPath.addCircle(startX - (width * 0.125f), midHeadY,
				width/30, Path.Direction.CW);
		bearPath.addCircle(startX + (width * 0.125f), midHeadY,
				width/30, Path.Direction.CW);
		
		// Nose
		bearPath.addOval(new RectF(
				startX - (width/25), midHeadY + (width/25f), startX + (width/25f), midHeadY + (width/25f)+(width/30f) ),
				Path.Direction.CW);
		
		// Mouth
		bearPath.arcTo(new RectF(
				startX-(width/12.5f), midHeadY+(2*(width/25f)), startX, midHeadY+(2*(width/25f))+(width/25f) ),
				25f, 115, true);
		bearPath.arcTo(new RectF(
				startX, midHeadY+(2*(width/25f)), startX+(width/12.5f), midHeadY+(2*(width/25f))+(width/25f) ),
				155f, -115, true);

    	// Add bear Path to main Path
		path.addPath(bearPath);
    	
		return path;
	}
}
