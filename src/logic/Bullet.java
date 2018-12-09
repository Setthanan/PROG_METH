package logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class Bullet extends GameObject{
	/// due to there are many type of bullet , i will extend this class to build other.
	protected int power;
	protected boolean readyState;
	protected final int w = 25;
	private Image image = new Image(ClassLoader.getSystemResource("Pea.png").toString(),w,w,false,false);;
	private int id = 0;
	private static int num = 0;
	public Bullet(int x,int y,boolean visible) {
		super(x,y,ID.Bullet);
		this.power = 1;
		this.readyState = true;
		this.velX = 1;
		this.id = num;
		num++;
		
		
	}
	public boolean fire() {
		if(this.readyState) {
			this.readyState = false;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean loadBullet() {
		if(!this.readyState) {
			this.readyState = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isReady() {
		return this.readyState;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public boolean isReadyState() {
		return readyState;
	}
	public void setReadyState(boolean readyState) {
		this.readyState = readyState;
	}
	public void update(double time) {
		//x,y position
		
		this.x += velX*time;
		System.out.println(this.id +" "+this.x);
	}

	
	public void render(GraphicsContext gc) {
		
		//gc.fillOval(x, y+25, w, w);//Image(image, x, y);
		gc.drawImage(image, x, y+25);
		
		//image = new Image(ClassLoader.getSystemResource("Pea.png").toString(),w,w,false,false);
	
	}
	

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x,y+25,w,w); // w is size of bullet
	}
	
}
