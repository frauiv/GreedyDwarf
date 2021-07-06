package com.company;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Dragon extends Creature {
	public double x;
	public double y;
	public boolean move;
	public Point2D position;
	private Map map;
	private int diameter=40;
	public BufferedImage image;
	public ArrayList<Tile> tiles;

	public Dragon(double x, double y, Map theMap, boolean move){
		this.x = x;
		this.y = y;
		this.position = new Point2D.Double(x,y);
		this.map = theMap;
		this.move = move;
		try{
			this.image = ImageIO.read(new File("Dragon.png"));
		}
		catch(IOException ex){
			String message = "No Dragon image";
			JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
		}
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

	//this is for idle action or non-key reactive movement
	@Override
	public void timePassed() {
		this.move();
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub

	}

	@Override
	void move() {
		Random random = new Random();
		//store current x and y
		double currX = this.x;
		double currY = this.y;


		//generate random number to get the
		int min = 1;
		int max = 5;

		int moveDirection = random.nextInt(max - min) + min;


		// 1-up, 2-right, 3-down, 4-left
		if(moveDirection == 1){ this.moveUp(); }

		if(moveDirection == 2){ this.moveRight();}

		if(moveDirection == 3){ this.moveDown(); }

		if(moveDirection == 4){ this.moveLeft(); }

		//if new position is over treasure then stay at old position
		if(this.x == 40 && this.y == 120){
			this.x = currX;
			this.y = currY;
			this.position = new Point2D.Double(this.x, this.y);
		}

		map.getRange().move();

	}

	@Override
	void moveLeft() {
		this.tiles = map.getTiles();
		if(this.x - 40 < 0){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.x-40 == tiles.get(i).getPosition().getX()) && (this.y == tiles.get(i).getPosition().getY())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.x = this.x - 40;
		this.position = new Point2D.Double(this.x,this.y);
	}



	@Override
	void moveUp() {
		this.tiles = map.getTiles();
		if(this.y - 40 < 0){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.y-40 == tiles.get(i).getPosition().getY()) && (this.x == tiles.get(i).getPosition().getX())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.y = this.y - 40;
		this.position = new Point2D.Double(this.x,this.y);
	}

	@Override
	void moveRight() {
		this.tiles = map.getTiles();
		if(this.x + 40 > 800){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.x+40 == tiles.get(i).getPosition().getX())&&(this.y == tiles.get(i).getPosition().getY())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.x = this.x + 40;
		this.position = new Point2D.Double(this.x,this.y);
	}



	@Override
	void moveDown() {
		this.tiles = map.getTiles();
		if(this.y + 40 > 800){
			return;
		}
		for(int i = 0; i<map.getTiles().size();i++){
			if((this.y+40 == tiles.get(i).getPosition().getY()) && (this.x == tiles.get(i).getPosition().getX())){
				if(tiles.get(i).isImmovable()){
					return;
				}
			}
		}
		this.y = this.y + 40;
		this.position = new Point2D.Double(this.x,this.y);
	}

	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDragon() {
		// TODO Auto-generated method stub
		return true;
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