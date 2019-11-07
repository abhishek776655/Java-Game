package com.game.src.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class keyInput extends KeyAdapter {
	Game game;
	public keyInput(Game game){
		this.game=game;
	}
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
		
	}
    public void keyReleased(KeyEvent e) {
    	game.keyReleased(e);
		
	}


}
