package com.company;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Player extends Creature {

	public double x;
	public double y;
	public double points;
	public double speed;
	public Point2D position;
	public Map map;
	public BufferedImage image;
	public ArrayList<Tile> tiles;


	public Player(double x, double y, Map theMap){
		this.x = x;
		this.y = y;
		this.map = theMap;
		this.position = new Point2D.Double(this.x, this.y);
		this.speed = 40;

		try{
			this.image = ImageIO.read(new File("Gnome.png"));
		}
		catch(IOException ex){
			String message = "No Player image";
			JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public Color getColor() {
		return Color.BLACK;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle2D.Double(position.getX(), position.getY(), 40.0, 40.0);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void timePassed() {
		// no idle action nothing to do here
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		//might need this later
	}

	@Override
	public boolean getIsPaused() {
		return false;
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}

	@Override
	public void die() {
		// might need this later
	}


	@Override
	void move() {
		double totalPoints = map.game.getTotalPossiblePoints();
		double currPoints = this.points;

		//get ranges
		double range1 = totalPoints/4;
		double range2 = totalPoints/2;
		double range3 = totalPoints-range1;

		//adjust speed
		if(currPoints == 0){ this.speed =40; }										//normal speed
		else if(currPoints > 0 && currPoints <= range1){ this.speed = 30; }			//1/4 speed
		else if(currPoints > range1 && currPoints <= range2){this.speed = 20;}		//1/2 speed
		else if(currPoints > range2 && currPoints <= range3){this.speed = 10;}		//3/4 speed
		else if(currPoints > range3){ this.speed = 5; }								//slowest possible speed


	}

	@Override
	void moveLeft() {
		this.tiles = map.getTiles();
		if(this.x - this.speed < 0){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.x-this.speed == tiles.get(i).getPosition().getX()) && (this.y == tiles.get(i).getPosition().getY())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.x = this.x - this.speed;
		this.position = new Point2D.Double(this.x,this.y);
	}

	@Override
	void moveUp() {
		this.tiles = map.getTiles();
		//if(this.y - this.speed <= 0.0){
		//	return;
		//}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.y-this.speed == tiles.get(i).getPosition().getY()) && (this.x == tiles.get(i).getPosition().getX())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.y = this.y - this.speed;
		this.position = new Point2D.Double(this.x,this.y);
	}

	@Override
	void moveRight() {
		this.tiles = map.getTiles();
		if(this.x + this.speed > 800){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.x+ this.speed == tiles.get(i).getPosition().getX())&&(this.y == tiles.get(i).getPosition().getY())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.x = this.x + this.speed;
		this.position = new Point2D.Double(this.x,this.y);
	}

	@Override
	void moveDown() {
		this.tiles = map.getTiles();
		if(this.y + this.speed > 800){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.y+ this.speed == tiles.get(i).getPosition().getY()) && (this.x == tiles.get(i).getPosition().getX())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.y = this.y + this.speed;
		this.position = new Point2D.Double(this.x,this.y);
	}

	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isDragon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRuby() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isImmovable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRange() {
		return false;
	}

}