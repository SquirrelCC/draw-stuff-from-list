package com.scconsulting.listsample;

import android.graphics.Path;

public class Tree {

	public Tree() {
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
	 * Draw coniferous tree into a Path, to be drawn to a Canvas in the calling Activity.
	 * 
	 * Use the starting x coordinate, and the starting and ending y coordinates of the user's swiped line
	 * to determine the height and position of the tree.
	 * 
	 * The user's line is not corrected if swiped upside down. In that case, the tree is drawn upside down.
	 * This can make a nice reflection on a mountain lake.
	 */
	public static Path drawPath(Path path, float startX, float startY, float stopX, float stopY) {
		
		float lineLen = (startY - stopY);
		float h = .9f * lineLen;
		float w = h;
		float indent = .1f * w;
		float deltaX = .2f * w;
		float deltaY = .25f * h;
		stopX = startX;
		
		// Left side
		float newX = stopX;
		float newY = stopY;
		path.moveTo(newX, newY);
		
		for (int i=0; i<3; i++) {
			newX -= deltaX;
			newY += deltaY;
			path.lineTo(newX, newY);
			
			newX += indent;
			path.lineTo(newX, newY);
		}
		
		newX -= deltaX;
		newY += deltaY;
		path.lineTo(newX, newY);
		
		newX += w;
		path.lineTo(newX, newY);
		
		// Right side
		for (int i=0; i<3; i++) {
			newX -= deltaX;
			newY -= deltaY;
			path.lineTo(newX, newY);
			
			newX += indent;
			path.lineTo(newX, newY);
		}
		
		newX -= deltaX;
		newY -= deltaY;
		path.lineTo(newX, newY);
		path.close();
		
		// Trunk
		newX -= .025f * w;
		newY += h;
		path.moveTo(newX, newY);
		newY += .1f * h;
		path.lineTo(newX, newY);
		newX += .05f * w;
		path.lineTo(newX, newY);
		newY -= .1f * h;
		path.lineTo(newX, newY);
		
		return path;
	}
}
