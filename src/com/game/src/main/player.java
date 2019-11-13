package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class player implements EntityA {
	private double x;
	private double y;
	private double velX=0;
	private double velY=0;
	private BufferedImage Player;
	private  texture tex;
	player(double x,double y,texture tex){
		this.x=x;
		this.y=y;
		this.tex = tex;
		
	}
	public void tick() {
	x=x+velX;
	y=y+velY;
	if(x<=0)
		x=0;
	else if (y<=0)
		y=0;
	else if (x>=640-32) {
		x=640-32;
	}
	else if(y>=480-32) {
		y=480-32;
	}

	}
	public void render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y, null);
	}
		public double getX() {
			return x;
		}
		public double getY() {
			return y;
		}
		public void setX(double x) {
			this.x=x;
		}
		public void setY(double y) {
			this.y=y;
		}
		public void setVelX(double velX) {
			this.velX=velX;
		}
		public void setVelY(double velY) {
			this.velY=velY;
		}
		@Override
		public double getx() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public double gety() {
			// TODO Auto-generated method stub
			return 0;
		}
		public Rectangle getBounds() {
			
			return new Rectangle((int)x,(int)y,32,32) ;
		}
}
