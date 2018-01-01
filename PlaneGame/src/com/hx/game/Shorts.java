package com.hx.game;

public class Shorts {
	int x, y, spend;
	boolean isLive = true;
	public Shorts(int x, int y, int spend) {
		super();
		this.x = x;
		this.y = y;
		this.spend = spend;
	}
	public int getY() {
		return y;
	}
	public void SetY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getSpend() {
		return spend;
	}

	public void setSpend(int spend) {
		this.spend = spend;
	}
	
}
