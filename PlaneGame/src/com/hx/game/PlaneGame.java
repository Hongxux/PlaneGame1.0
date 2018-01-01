package com.hx.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PlaneGame extends JFrame {
	MyPanle mp = null;
	Thread t = null;
	Thread move = null;

	public PlaneGame(){
		mp = new MyPanle();
		this.setBounds(500, 100, 600, 700);
		
		this.addKeyListener(mp);
		t =new Thread(mp);
		t.start();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		this.getContentPane().setVisible(true);
		this.setVisible(true);
		this.add(mp);
		
		
	}
	public static void main(String[] args) {
		PlaneGame pg = new PlaneGame();
	}
}
class MyPanle extends JPanel implements Runnable, KeyListener{
	Plane p = null;
	ArrayList<Plane> ps = new ArrayList<Plane>();
	private Image offScreenImage = null;
	public MyPanle(){
		p = new Plane(250, 500, 10);
		for(int i = 0; i < 35; i++){
			Shorts s = new Shorts(p.getX() + 5, p.getY() - 2, i + 5);;
			Shorts s2 = new Shorts(p.getX() + 8, p.getY() - 2, i + 5);;
			p.ShortsLeft.add(s);
			p.ShortsRight.add(s2);
		}
		for(int i = 0; i < 30; i++){
			Random rd = new Random();
			//int x = (int)rd.nextInt(400) + 1;
			//x = x*45;
			
			Plane p1 = new Plane(i * 45, 0, 5);

			p1.isLive = true;

			ps.add(p1);
		}

		for(int j = 0; j < ps.size(); j++){
			Plane p1 = ps.get(j);
			for(int i = 0; i < 25; i++){
				Shorts s = new Shorts(p.getX() + 10, p.getY() + 12, i + 5);;
				Shorts s2 = new Shorts(p.getX() + 17, p.getY() + 12, i + 5);;
				p1.ShortsLeft.add(s);
				p1.ShortsRight.add(s2);
			}
		}
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		super.paint(g);
		g.setColor(Color.blue);
		this.setBackground(Color.black);
		this.drawPlane(p.getX(), p.getY(), g,1);
		g.setColor(Color.yellow);
		for(int i = 0; i < p.ShortsLeft.size(); i++){
			Shorts s = p.ShortsLeft.get(i);
			Shorts s2 = p.ShortsRight.get(i);
			
			if(!s.isLive){
				s.setX(p.x + 5);
				s.SetY(p.y - 2);
				s.isLive = true;
			}
			if(!s2.isLive){
				s2.setX(p.x + 8);
				s2.SetY(p.y - 2);
				s2.isLive = true;
			}
			this.drawShorts(s, g, 1);
			this.drawShorts(s2, g, 1);
			
		}
		
		for(int i = 0 ,j = 0; i < ps.size(); i++){
			Plane p = ps.get(i);
			if(j < 3){
				if(p.isLive){
					this.drawPlane(p.x, p.y, g ,2);
					p.Canv = true;
					j++;
					
				}else{
					p.Canv = false;
				}
			}else{
				break;
			}
			
		}
		g.setColor(Color.green);
		for(int j = 0; j < ps.size(); j++){
			Plane p1 = ps.get(j);
			if(p1.isLive){
				if(p1.Canv){
					for(int i = 0; i < p1.ShortsLeft.size(); i++){
						Shorts s = p1.ShortsLeft.get(i);
						Shorts s2 = p1.ShortsRight.get(i);
						
						if(!s.isLive){
							s.setX(p1.x + 20);
							s.SetY(p1.y + 24);
							s.isLive = true;
						}
						if(!s2.isLive){
							s2.setX(p1.x + 34);
							s2.SetY(p1.y + 24);
							s2.isLive = true;
						}
						this.drawShorts(s, g, 2);
						this.drawShorts(s2, g, 3);
					}
				}
				
			}
		}
			
		
		g.setColor(c);
	}
	/**
	 * 
	 * @param ps 被打的

	 */
	public void hitPlane(){
		//宽25 高 30
		for(int j = 0; j < ps.size(); j++){
			Plane p1 = ps.get(j);
			if(p1.isLive && p1.Canv){
				for(int i = 0; i < p.ShortsLeft.size(); i++){
					Shorts s = p.ShortsLeft.get(i);
					if(s.isLive){
						if(s.x >= p1.x && s.x <= p1.x + 25 && s.y >= p1.y && s.y <= p1.y +30){
							s.isLive = false;
							p1.isLive = false;
							p1.Canv = false;
						}
					}
					
				}
				for(int i = 0; i < p.ShortsRight.size(); i++){
					Shorts s = p.ShortsRight.get(i);
					if(s.isLive){
						if(s.x >= p1.x && s.x <= p1.x + 25 && s.y >= p1.y && s.y <= p1.y +30){
							s.isLive = false;
							p1.isLive = false;

						}
					}
					
				}
			}
			
		}
		
	}
	
	public boolean PlaneLive(Plane p){
		if(p.x < 0 || p.x > 600 || p.y < 0 || p.y > 700){
			return false;
		}
		return true;
	}
	public void drawPlane(int x, int y, Graphics g , int n){//n的值 为1是英雄坦克 为2 是敌方坦克                                  
		if(n == 1){
			//宽25 高 30
			
			g.fill3DRect(x, y, 3, 15, false);
			//画出右边矩形
			g.fill3DRect(x+13, y, 3, 15, false);
			//画出中间矩形
			g.fill3DRect(x+3, y+3, 10, 10, false);
			//画出炮管
			g.fill3DRect(x+5, y-2, 2, 10,false);
			//画出炮管
			g.fill3DRect(x+8, y-2, 2, 10,false);
		}else{//敌人的飞机比例放大2倍
			
			g.fill3DRect(x, y, 10, 60, false);
			//画出右边矩形
			g.fill3DRect(x+50, y, 10, 60, false);
			//画出中间矩形
			g.fill3DRect(x+10, y+10, 40, 40, false);
			//画出炮管
			g.fill3DRect(x+20, y+24, 6, 40,false);
			//画出炮管
			g.fill3DRect(x+34, y+24, 6, 40,false);
		}
		
		
		
		
		this.repaint();
	}
	public boolean shortsisLive(Shorts s){
		if(s.getY() < 0 || s.getY() > 600){
			s.isLive = false;
			return false;
		}
		return true;
	}
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!PlaneLive(p)){
				throw new Error("死亡");
			}
			for(int i = 0; i < p.ShortsLeft.size(); i++){
				Shorts s = p.ShortsLeft.get(i);
				if(s != null && this.shortsisLive(s)){
					s.y -= s.spend;
				}
				Shorts s2 = p.ShortsRight.get(i);
				if(s2 != null && this.shortsisLive(s2)){
					s2.y -= s2.spend;
				}
			}
			hitPlane();
			Random rd = new Random();
			for(int i = 0, j = 0; i < ps.size(); i++){
				
				if(j < 3){
					int num = (int)rd.nextInt(4) + 1;
					Plane p1 = ps.get(i);
					if(p1.isLive && p1.Canv){
						switch(num){
						case 2 ://1上 2下 3左 4右
							if(PlaneLive(p1)){
								for(int k = 0; k < 5; k++){
									p1.moveDown();
								}
							}else{
								p1.isLive = false;

							}
							
							break;
						case 3 ://1上 2下 3左 4右
							if(PlaneLive(p1)){
								for(int k = 0; k < 10; k++){
									p1.moveLeft();
								}
							}else{
								for(int k = 0; k < 10; k++){
									p1.moveRight();
								}
							}
							
							break;
						case 4 ://1上 2下 3左 4右
							if(PlaneLive(p1)){
								for(int k = 0; j < 2; j++){
									p1.moveRight();
								}
							}else{
								for(int k = 0; j < 2; j++){
									p1.moveLeft();
								}
							}
							break;
							
						}
					}
				}
				
				
				
				
				
			}
			for(int j = 0; j < ps.size(); j++){
				Plane p1 = ps.get(j);
				for(int i = 0; i < p1.ShortsLeft.size(); i++){
					Shorts s = p1.ShortsLeft.get(i);
					if(s != null && this.shortsisLive(s)){
						s.y += s.spend;
					}
					Shorts s2 = p1.ShortsRight.get(i);
					if(s2 != null && this.shortsisLive(s2)){
						s2.y += s2.spend;
					}
				}
			}
			
			this.repaint();
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W){
			p.moveUp();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			p.moveDown();
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			p.moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			p.moveRight();
		}
		
		this.repaint();
	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void update(Graphics g) {
	       if(offScreenImage == null) {
	           offScreenImage = this.createImage(800, 600);
	       }
	       Graphics gOffScreen = offScreenImage.getGraphics();
	       Color c = gOffScreen.getColor();
	       gOffScreen.setColor(Color.GREEN);
	       gOffScreen.fillRect(0, 0, 800, 600);
	       gOffScreen.setColor(c);
	       paint(gOffScreen);
	       g.drawImage(offScreenImage, 0, 0, null);
	    }
	public void drawShorts(Shorts s, Graphics g, int n){//n==1是英雄 n==2是敌人
		if(n == 1){
			g.draw3DRect(s.getX(), s.getY(), 1, 1, false);
		}else{
			g.draw3DRect(s.getX(), s.getY(), 4, 4, false);
		}
		
	}
	
	
}

