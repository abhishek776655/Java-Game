package com.game.src.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class gameOverMenu {
	public Rectangle playbutton = new Rectangle(Game.WIDTH/2 +120,200,100,50);
	public Rectangle Quitbutton = new Rectangle(Game.WIDTH/2 +120,300,100,50);
public void render(Graphics g) {
	
	Font font = new Font("arial",Font.BOLD,50);
	Font fontOption = new Font("arial",Font.BOLD,30);
	g.setFont(font);
	g.setColor(Color.white);
	g.drawString("Game Over",Game.WIDTH/2,100);
	g.setFont(fontOption);
	g.setColor(Color.red);
	g.drawString("Your Score",Game.WIDTH/2,250);
	g.drawString(Integer.toString(Game.score),Game.WIDTH/2+300,250);
	
}
}
	
