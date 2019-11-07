package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	private LinkedList<Bullet> b =new LinkedList<Bullet>();
	Bullet tempBullet;
	Game game;
	public void tick() {
	for(int i=0;i<b.size();i++) {
		tempBullet = b.get(i);
		tempBullet.tick();
	}}
	public Controller(Game game) {
		this.game=game; 
	}
	public void render(Graphics g) {
		for(int i=0;i<b.size();i++) {
			tempBullet = b.get(i);
			tempBullet.render(g);
		}
	
	}
     public void addBullet(Bullet block) {
    	 b.add(block);
     }
     public void removeBullet(Bullet block) {
    	 b.remove(block);
     }
     
}