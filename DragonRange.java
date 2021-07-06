package com.company;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class DragonRange extends Tile{
    public Point2D position;
    public double diameter = 40;
    public double startX;
    public double startY;
    public BufferedImage image;
    public Map map;
    private java.awt.geom.Rectangle2D.Double range;

    public DragonRange(double x, double y, Map theMap){
        this.position = new Point2D.Double(x, y);
        this.startX = x;
        this.startY = y;
        this.range = new Rectangle2D.Double(x, y, 200, 200);
        this.map = theMap;
/*
        try{
            this.image = ImageIO.read(new File("Gold.png"));
        }
        catch(IOException ex){
            String message = "No Gold image";
            JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
        }

*/
    }//end Dragon Range

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public Shape getShape() {
        return this.range;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public void timePassed() {
        //move();
    }

    @Override
    public void die() {

    }

    @Override
    public boolean getIsPaused() {
        return false;
    }

    @Override
    public void setIsPaused(boolean isPaused) {

    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isDragon() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isRock() {
        return false;
    }

    @Override
    public boolean isRuby() {
        return false;
    }

    @Override
    public boolean isImmovable() {
        return false;
    }

    @Override
    public boolean isRange() {
        return true;
    }

    @Override
    void move() {
        //get dragons current position
        double x = map.d1.x;
        double y = map.d1.y;

        this.range.setRect(x-80.0 , y-80.0, 200.0 , 200.0);

    }

    @Override
    void moveLeft() {

    }

    @Override
    void moveUp() {

    }

    @Override
    void moveRight() {

    }

    @Override
    void moveDown() {

    }
}