package logic;

import javafx.geometry.Rectangle2D;

public abstract class GameObject {
	protected int x, y;
	protected float velX = 0, velY = 0;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public abstract Rectangle2D getBounds();

	public boolean intersects(GameObject other) {
		return other.getBounds().intersects(this.getBounds());

	}

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

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

}
