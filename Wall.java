package com.company;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Wall extends Tile{

    public double x;
    public double y;
    public Point2D position;
    private int size=40;
    public BufferedImage image;

    public Wall(double x, double y){
        this.x = x;
        this.y = y;
        this.position = new Point2D.Double(x,y);

        //try{
        // this.image = ImageIO.read(new File("Rock.png"));
        //}
        //catch(IOException ex){
        //String message = "No Rock image";
        //JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
        //}
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return Color.BLUE;
    }

    @Override
    public Shape getShape() {
        // TODO Auto-generated method stub
        return new Rectangle2D.Double(position.getX(), position.getY(), this.size,this.size);
    }

    @Override
    public BufferedImage getImage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }

    @Override
    public Point2D getPosition() {
        // TODO Auto-generated method stub
        return position;
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
    public boolean isPlayer() {
        // TODO Auto-generated method stub
        return false;
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
    void move() {
        // TODO Auto-generated method stub

    }

    @Override
    void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    void moveUp() {
        // TODO Auto-generated method stub

    }

    @Override
    void moveRight() {
        // TODO Auto-generated method stub

    }

    @Override
    void moveDown() {
        // TODO Auto-generated method stub

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
        return true;
    }

    @Override
    public boolean isRange() {
        return false;
    }

}