package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class enemy implements EntityB {
	Random r =new Random();
	private double x,y;
	private Game game;
	private Controller c;
	private BufferedImage enemy;
	private texture tex;
	private int speed = r.nextInt(5)+1;
	public enemy(double x,double y,texture tex,Controller c,Game game) {
		this.x=x;
		this.y=y;
		this.tex=tex;
		this.c=c;
		this.game=game;
		
	}
	public void tick() {
		y=y+speed;
		if(y>(Game.WIDTH*Game.SCALE-100)) {
			y=0;
			x=r.nextInt((Game.WIDTH*Game.SCALE));
			Game.Health-=5;
			
		}
		if(physics.Collision(this,game.ea)) {
			
			c.removeEntity(this);
			game.setEnemy_killed(game.getEnemy_killed()+1);
			Game.score++;
		}
		
	}
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
		
	}
	@Override
	public double getx() {
		// TODO Auto-generated method stub
		return y;
	}
	@Override
	public double gety() {
		// TODO Auto-generated method stub
		return y;
	}
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,32,32) ;
	}
	
	

}
