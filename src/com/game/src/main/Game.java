package com.game.src.main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	public static final int WIDTH = 320;
	public static final int HEIGHT= WIDTH/12*9;
	public static final int SCALE = 2;
	public final String Title = "Space Game";
	private boolean running = false;
	private Thread thread;
	private BufferedImage spriteSheet=null;
	private BufferedImage BackgroundImage =null;
	private BufferedImage beamSS=null;
	private player p;
	private Controller c;
	public void init() {
		requestFocus();
		bufferImageLoader Loader = new bufferImageLoader();
		try {
			BackgroundImage = Loader.loadImage("res/background.jpeg");
			spriteSheet=Loader.loadImage("res/spaceShip.png");
			beamSS = Loader.loadImage("res/beams.png");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		addKeyListener(new keyInput(this));
		c= new Controller(this);
		 
		p =new player(200,200,this);
		
		
		
		
	}
	private synchronized void start() {
		if(running) {
			return ;
		}
		running =true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 30.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer =System.currentTimeMillis();
		long updates=0;
		long frames=0;
		while(running) {
			long now = System.nanoTime();
			delta+= (now-lastTime)/ns;
			lastTime=now;
			if(delta>=1) {
				tick();
				delta--;
				updates++;
			}
			render();
			frames++;
			
			if((System.currentTimeMillis()-timer)>1000) {
				timer+=1000;
				System.out.println("Updates - "+updates+" frames - "+frames);
				frames=0;
				updates=0;
			}
		
		
		}
		stop();
		
	}
	private synchronized void stop() {
		if(!running) {
			return ;
		}
		running =false;
		try { 
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	private void tick() {
		p.tick();
		c.tick();
		
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(BackgroundImage,0,0, getWidth(), getHeight(),this );
        p.render(g);
        c.render(g);
		
		g.dispose();
		bs.show();
		
	}
	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		JFrame frame = new JFrame(game.Title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
        frame.setLocationRelativeTo(null);	
        frame.setVisible(true);
        game.start();
		
	}
	public BufferedImage getSpritesheet() {
		return spriteSheet;
	}
	public BufferedImage getBeamSS() {
		return beamSS;
	}
public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key== KeyEvent.VK_RIGHT) {
			p.setVelX(3);
			
		}else if(key== KeyEvent.VK_LEFT) {    
			p.setVelX(-3);
		}
		else if(key== KeyEvent.VK_UP) {
		    p.setVelY(-3);}
		else if(key== KeyEvent.VK_DOWN) {
			p.setVelY(3);
		}
		
	}
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
		if(key== KeyEvent.VK_RIGHT) {
			p.setVelX(0);
			
		}else if(key== KeyEvent.VK_LEFT) {    
			p.setVelX(0);
		}
		else if(key== KeyEvent.VK_UP) {
		    p.setVelY(0);}
		else if(key== KeyEvent.VK_DOWN) {
			p.setVelY(0);
		}
		else if(key== KeyEvent.VK_SPACE) {
			c.addBullet(new Bullet(p.getX()+16,p.getY()-3,this));;
		}
		
	}

}
