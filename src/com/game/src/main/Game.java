package com.game.src.main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game extends Canvas implements Runnable{
	public static final int WIDTH = 320;
	public static final int HEIGHT= WIDTH/12*9;
	public static final int SCALE = 2;
	public static int score=0;
	public final String Title = "Space Game";
	public static int Health = 100;
	private boolean running = false;
	private Thread thread;
	private BufferedImage BackgroundImage =null;	private BufferedImage spriteSheet=null;

	private BufferedImage beamSS=null;
	private BufferedImage menuImage=null;
	private BufferedImage enemy=null;
	private player p;
	private Controller c;
	private texture tex;
	private int enemy_count=5;
	private int enemy_killed=0;
	public LinkedList<EntityA>ea;
	public LinkedList<EntityB>eb;
	public static enum STATE{
		GAME,
		MENU,
		GAMEOVER};
	public static STATE state = STATE.MENU;
	private Menu menu;
	private gameOverMenu go;
	

	public int getEnemy_count() {
		return enemy_count;
	}
	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	public int getEnemy_killed() {
		return enemy_killed;
	}
	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public void init() {
		requestFocus();
		bufferImageLoader Loader = new bufferImageLoader();
		try {
         BackgroundImage = Loader.loadImage("res/background.jpeg");
			spriteSheet=Loader.loadImage("res/spaceShip.png");
			beamSS = Loader.loadImage("res/beams.png");
			enemy =Loader.loadImage("res/eyelander.png");
			menuImage= Loader.loadImage("res/menu.jpeg");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		addKeyListener(new keyInput(this));
		this.addMouseListener(new  MouseInput());
		tex = new texture(this);
		c= new Controller(this,tex);
		ea=c.getEntityA();
		eb=c.getEntityB();
		menu = new Menu();
		go = new gameOverMenu();
		 
		p =new player(200,200,tex);
		c.create_enemy(enemy_count);
		
		
		
		
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
				//System.out.println("Updates - "+updates+" frames - "+frames);
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
		if(state==STATE.GAME) {
		p.tick();
		c.tick();
		}
		if(enemy_killed>=enemy_count) {
			enemy_count+=2;
			enemy_killed=0;
			c.create_enemy(enemy_count);
		}
		if(Health<=0) {
			state = STATE.GAMEOVER;
		}
		
	}
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(BackgroundImage,0,0, getWidth(), getHeight(),this );
		if(state==STATE.GAME) {

        p.render(g);
        c.render(g);
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200,20);
        g.setColor(Color.red);
        g.drawRect(5, 5, 200, 20);
        g.fillRect(5,5,Health*2,20);
        Font font = new Font("arial",Font.BOLD,15);
    	Font fontOption = new Font("arial",Font.BOLD,20);
    	g.setFont(font);
    	g.setColor(Color.white);
    	g.drawString("Score", Game.WIDTH*Game.SCALE -100, 20);
    	g.drawString(Integer.toString(score), Game.WIDTH*Game.SCALE -20, 20);
      
        
		}
		else if(state==STATE.MENU) {
			g.drawImage(menuImage,0,0, getWidth(), getHeight(),this );
			menu.render(g);
		}
		else if(state==STATE.GAMEOVER) {
			g.drawImage(menuImage,0,0, getWidth(), getHeight(),this );
			go.render(g);
		}
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
	public BufferedImage getenemy() {
		return enemy;
	}
public void keyPressed(KeyEvent e) {
	
		int key = e.getKeyCode();
		if(state==STATE.GAME) {
		if(key== KeyEvent.VK_RIGHT) {
			p.setVelX(8);
			
		}else if(key== KeyEvent.VK_LEFT) {    
			p.setVelX(-8);
		}
		else if(key== KeyEvent.VK_UP) {
		    p.setVelY(-8);}
		else if(key== KeyEvent.VK_DOWN) {
			p.setVelY(8);
		}
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
			c.addEntity(new Bullet(p.getX(),p.getY()-10,tex,this));
		}
		
	}

}
