package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet implements EntityA {
private double x;
private double y;
BufferedImage image;
private texture tex;
Game game;
    public Bullet(double x,double y,texture tex,Game game) {
    	this.x=x;
    	this.y=y;
    	this.tex=tex;
    	this.game=game;
    	
    	
    }
    public void tick()
    {y=y-5;
    if(physics.Collision(this,game.eb)) {
    	System.out.println("detected");
    }
    	
    }
    public void render(Graphics g) {
    	g.drawImage(tex.missile,(int)x,(int)y,null);
    }
    public double getY() {
		return y;
	}
	@Override
	public double getx() {
		// TODO Auto-generated method stub
		return x;
	}
	@Override
	public double gety() {
		// TODO Auto-generated method stub
		return y;
	}
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32) ;
	}
}