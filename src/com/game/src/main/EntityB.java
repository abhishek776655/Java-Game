package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB {
public void tick();
public void render(Graphics g);
public double getx();
public double gety();
public Rectangle getBounds();

}
