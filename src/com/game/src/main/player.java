package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class player {
	private double x;
	private double y;
	private double velX=0;
	private double velY=0;
	private BufferedImage Player;
	player(double x,double y,Game game){
		this.x=x;
		this.y=y;
		spriteSheet ss=new spriteSheet(game.getSpritesheet());
		Player = ss.grabImage(1, 1, 32, 32);
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
		g.drawImage(Player, (int)x, (int)y, null);
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
}
