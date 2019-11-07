package com.game.src.main;

import java.awt.image.BufferedImage;

public class spriteSheet {
	private BufferedImage image;
	public spriteSheet(BufferedImage image) {
		this.image=image;
		
	}
	public BufferedImage grabImage(int col ,int row ,int width,int height){
		
           
        		BufferedImage img = image.getSubimage((col*32)-32,(row*32)-32, width, height);
           return img;
}
}
