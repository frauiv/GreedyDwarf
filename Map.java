package com.company;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import static java.lang.Thread.sleep;

public class Map implements Drawable, Temporal{
	protected static final long UPDATE_INTERVAL_MS = 250;
	private int width;
	private int height;
	private Color color;
	public Player p1;
	public Dragon d1;
	public Treasure t1;
	public Rock r1;
	public Ruby b1;
	public GameState game;
	public DragonRange range;
	private java.awt.geom.Rectangle2D.Double background;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private ArrayList<Tile> tilesToAdd = new ArrayList<Tile>();
	private ArrayList<Tile> tilesToRemove = new ArrayList<Tile>();
	public boolean paused = false;
	double totalPoints;

	public ArrayList<Point2D> tileLocations;
	private JFrame gameFrame;

	public Map(int width, int height, Color color, JFrame gameFrame){
		this.width = width;
		this.height = height;
		this.color = color;
		this.gameFrame = gameFrame;
		this.game = new GameState();
		this.background = new Rectangle2D.Double(0, 0, this.width, this.height);
		this.tiles = this.game.loadLevel(this);
		this.p1 = getPlayer();
		this.d1 = getDragon();
		this.totalPoints = 0;

		//this.t1 = getTreasure();
		//this.r1 = getRock();
		//this.b1 = getRuby();


		Runnable tickTock = new Runnable() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void run() {
				try {
					while (true) {
						sleep(UPDATE_INTERVAL_MS);
						//checks x and y are equal for dragon and player, removes player
						if(Map.this.p1.getShape().contains(d1.x, d1.y)){
							Map.this.p1.points -= 5;
							System.out.println(Map.this.p1.points);
							if (Map.this.p1.points <= 0){
								Map.this.tilesToRemove.add(getPlayer());
								gameFrame.setVisible(false);
								//System.exit(0);
							}
						}
						if(Map.this.getRange().getShape().contains(p1.x,p1.y)){
							Map.this.d1.move = true;
						}
						//checks x and y are equal for player and treasure, removes treasure
						if(Map.this.getTreasure(Map.this.p1.x,Map.this.p1.y)!= null){
							Map.this.p1.points+= 1.0;
							Map.this.d1.move = true;
							Map.this.p1.move();
							System.out.println("Points: "+Map.this.p1.points);
							Map.this.tilesToRemove.add(getTreasure(Map.this.p1.x,Map.this.p1.y));
						}
						//checks x and y are equal for player and diamond, removes diamond
						else if(Map.this.getRock(Map.this.p1.x,Map.this.p1.y)!= null){
							Map.this.p1.points+= 10.0;
							Map.this.d1.move = true;
							Map.this.p1.move();
							System.out.println("Points: "+Map.this.p1.points);
							Map.this.tilesToRemove.add(getRock(Map.this.p1.x,Map.this.p1.y));
						}
						//checks x and y are equal for player and ruby, removes ruby
						else if(Map.this.getRuby(Map.this.p1.x,Map.this.p1.y)!= null) {
							Map.this.p1.points += 5.0;
							Map.this.d1.move = true;
							Map.this.p1.move();
							System.out.println("Points: "+Map.this.p1.points);
							Map.this.tilesToRemove.add(getRuby(Map.this.p1.x, Map.this.p1.y));
						}


						// checks if the player collected the treasure and if the player came to the wall
						else if(((Map.this.p1.y <= 0.0) || (Map.this.p1.y >= 760.0)) && Map.this.p1.points != 0){
							//System.out.println("Before "+Map.this.p1.x + " , " + Map.this.p1.y);
							System.out.println(Map.this.p1.points);
							Map.this.d1.move = false;
							Map.this.totalPoints = Map.this.p1.points;
							System.out.println("Points total: "+Map.this.totalPoints);
							Map.this.tiles = Map.this.game.levelUp();
							Map.this.p1 = Map.this.getPlayer();
							Map.this.d1 = Map.this.getDragon();

							//System.out.print("After "+Map.this.p1.x + " , " + Map.this.p1.y);

						}
						catchPlayer();
						sleep(250);
						timePassed();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(tickTock).start();
	}

	@Override
	public void timePassed() {
		//runs all timePassed functions for all temporal objects in tiles
		for (Tile t : this.tiles) {
			t.timePassed();
		}
		//removes all tiles scheduled to be removed by the map thread from the main tile list, done this way to avoid threading issues
		for (Tile t : this.tilesToRemove) {
			this.tiles.remove(t);
		}
		//clear the list so objects aren't removed twice
		tilesToRemove.clear();
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}
	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsPaused() {
		return false;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		return this.background;
	}

	@Override
	public Point2D getPosition() {
		return null;
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	//basic map for testing
	public void initializeMap(){
		this.tiles.add(new Dragon(400, 0,this, false));
		this.tiles.add(new Treasure(400, 120, this));
		this.tiles.add(new Player(400, 760, this));
	}

	//gets a list of all Tiles that are drawable
	public ArrayList<Drawable> getDrawableParts() {
		ArrayList<Drawable> temp = new ArrayList<Drawable>();
		for (int i = 0; i < this.tiles.size(); i++) {
			temp.add(this.tiles.get(i));
		}

		return temp;

	}


	public ArrayList<Tile> getTiles(){
		return this.tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	//finds player object in tile list
	public Player getPlayer(){
		for (int i = 0; i < this.tiles.size(); i++) {
			if(this.tiles.get(i).isPlayer()){
				return (Player) this.tiles.get(i);
			}
		}
		return null;

	}

	//finds treasure object in tile list
	public Treasure getTreasure(double x, double y){
		for (int i = 0; i < this.tiles.size(); i++) {
			if((this.tiles.get(i).isTreasure()) && (this.tiles.get(i).getShape().contains(x, y))){
				return (Treasure) this.tiles.get(i);
			}
		}
		return null;

	}

	//finds diamond object in tile list
	public Rock getRock(double x, double y){
		for (int i = 0; i < this.tiles.size(); i++) {
			if((this.tiles.get(i).isRock())&& (this.tiles.get(i).getShape().contains(x, y))){
				return (Rock) this.tiles.get(i);
			}
		}
		return null;

	}

	//returns ruby object from tile list
	public Ruby getRuby(double x, double y){
		for (int i = 0; i < this.tiles.size(); i++) {
			if(this.tiles.get(i).isRuby()&& (this.tiles.get(i).getShape().contains(x, y))){
				return (Ruby) this.tiles.get(i);
			}
		}
		return null;

	}

	//returns dragon object from tile list
	public Dragon getDragon(){
		for (int i = 0; i < this.tiles.size(); i++) {
			if(this.tiles.get(i).isDragon()){
				return (Dragon) this.tiles.get(i);
			}
		}
		return null;

	}

	//returns dragon range from tile list
	public DragonRange getRange(){
		for (int i = 0; i < this.tiles.size(); i++){
			if(this.tiles.get(i).isRange()){
				return (DragonRange) this.tiles.get(i);
			}
		}
		return null;
	}

	public void catchPlayer(){
		if(Map.this.d1.move == true){
			if(Map.this.p1.y < Map.this.d1.y){
				Map.this.d1.moveUp();
			}
			else {
				Map.this.d1.moveDown();
			}
			if(Map.this.p1.x < Map.this.d1.x){
				Map.this.d1.moveLeft();
			}
			else{
				Map.this.d1.moveRight();
			}
		}
	}

}
