package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {
private double x;
private double y;
BufferedImage image;
    public Bullet(double x,double y,Game game) {
    	this.x=x;
    	this.y=y;
    	spriteSheet ss = new spriteSheet(game.getBeamSS());
    	image = ss.grabImage(1, 1, 32, 32);
    }
    public void tick()
    {x=x+5;
    	
    }
    public void render(Graphics g) {
    	g.drawImage(image,(int)x,(int)y,null);
    }
}