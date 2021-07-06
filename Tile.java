package com.company;
import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class Tile extends Interactable implements Drawable, Temporal {

	public abstract Point2D getPosition();

	@Override
	public abstract void die();

	public abstract boolean getIsPaused();

	public abstract void setIsPaused(boolean isPaused);

	public abstract boolean isPlayer();

	public abstract boolean isDragon();

	public abstract boolean isTreasure();

	public abstract boolean isRock();

	public abstract boolean isRuby();

	public abstract boolean isImmovable();

	public abstract boolean isRange();
}