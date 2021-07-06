package com.company;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Drawable {
	/**
	 * Returns the fill color that should be drawn to represent this object.
	 *
	 * @return the fill color
	 */
	Color getColor();

	/**
	 * Returns the shape the should be drawn to represent this object.
	 *
	 * @return the shape to draw
	 */
	Shape getShape();



	Point2D getPosition();

	BufferedImage getImage();
}