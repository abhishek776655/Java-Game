package com.game.src.main;

import java.awt.image.BufferedImage;


public class texture {
	private spriteSheet ss;
	private spriteSheet ssMissile;
	private spriteSheet enemySS;
	public BufferedImage player,enemy,missile;
	public texture(Game game) {
		ss = new spriteSheet(game.getSpritesheet());
		ssMissile = new spriteSheet(game.getBeamSS());
		enemySS = new spriteSheet(game.getenemy());
		getTextures();
	}
private void getTextures() {
	player = ss.grabImage(1, 1,32,32);
	missile = ssMissile.grabImage(1, 1, 32, 32);
	enemy = enemySS.grabImage(1, 1, 32,32);
	
}
}
