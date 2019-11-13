package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	Random r =new Random();
	private LinkedList<EntityA> ea =new LinkedList<EntityA>();
	private LinkedList<EntityB> eb =new LinkedList<EntityB>();
	EntityA entA;
	EntityB entB;
	Game game;
	texture tex;
	public void tick() {
	for(int i=0;i<ea.size();i++) {
		entA = ea.get(i);
		entA.tick();
	}
	for(int i=0;i<eb.size();i++) {
		entB = eb.get(i);
		entB.tick();
	}
	}
	public Controller(Game game,texture tex) {
		this.game=game; 
		this.tex=tex;
		
			
			
		
	}
	public void render(Graphics g) {
		for(int i=0;i<ea.size();i++) {
			entA = ea.get(i);
			entA.render(g);
		}
		for(int i=0;i<eb.size();i++) {
			entB = eb.get(i);
			entB.render(g);
		}
	}
	public void addEntity(EntityA block) {
		ea.add(block);
	}
	public void addEntity(EntityB block) {
		eb.add(block);
	}
	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	public void removeEntity(EntityB block) {
		eb.remove(block);
	}
   
     public void create_enemy(int enemy_count) {
    	 for(int i=0;i<enemy_count;i++) {
    		 addEntity(new enemy(r.nextInt(game.WIDTH*game.SCALE), -10, tex,this,game));
    	 }
     }
     public LinkedList<EntityA>getEntityA(){
    	 return ea;
     }
     public LinkedList<EntityB>getEntityB(){
    	 return eb;
     }
}
