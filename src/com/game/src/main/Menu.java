package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	public Rectangle playbutton = new Rectangle(Game.WIDTH/2 +120,150,100,50);
	public Rectangle Quitbutton = new Rectangle(Game.WIDTH/2 +120,250,100,50);
public void render(Graphics g) {
	Graphics2D g2d =(Graphics2D)g;
    
	Font font = new Font("arial",Font.BOLD,50);
	Font fontOption = new Font("arial",Font.BOLD,30);
	g.setFont(font);
	g.setColor(Color.white);
	g.drawString("Space Game",Game.WIDTH/2,100);
	g.setFont(fontOption);
	g.setColor(Color.red);
	g.drawString("Play", playbutton.x+15, playbutton.y+35);
	g.drawString("Quit", Quitbutton.x+15, Quitbutton.y+35);
	g.setColor(Color.white);
	g2d.draw(playbutton);
	g2d.draw(Quitbutton);
}
}
