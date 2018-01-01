package com.hx.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Plane{
	int x;
	boolean isLive = true;
	boolean Canv = false;
	ArrayList<Shorts> ShortsLeft = new ArrayList<Shorts>();
	ArrayList<Shorts> ShortsRight = new ArrayList<Shorts>();
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSpend() {
		return spend;
	}
	public void setSpend(int spend) {
		this.spend = spend;
	}
	int y;
	int spend;
	public Plane(int x, int y, int spend){
		this.x = x;
		this.y = y;
		this.spend = spend;
	}
	public void moveUp(){
		this.setY(this.getY() - spend);
	}
	public void moveDown(){
		this.setY(this.getY() + spend);
	}
	public void moveLeft(){
		this.setX(this.getX() - spend);
	}
	public void moveRight(){
		this.setX(this.getX() + spend);
	}
}
