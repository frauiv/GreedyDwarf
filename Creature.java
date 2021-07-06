package com.company;
import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class Creature extends Tile  {

	public abstract Color getColor();
	public abstract Point2D getPosition();
	
	@Override
	public abstract void die();

	public abstract boolean getIsPaused();

	public abstract void setIsPaused(boolean isPaused);
	
	
	
}
