package logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends GameObject {
	/// due to there are many type of bullet , i will extend this class to build
	/// other.
	protected double power;
	protected boolean readyState;
	protected final int w = 25;
	private AttackPlant owner;
	private Image image = new Image(ClassLoader.getSystemResource("Pea.png").toString(), w, w, false, false);;

	public Bullet(AttackPlant owner, int x, int y, boolean visible) {
		super(x, y);
		this.power = 1;
		this.readyState = true;
		this.velX = 1;
		this.owner = owner;

	}

	
	public double getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	

	public void update() {
		// x,y position

		this.x += velX ;
	}

	public void render(GraphicsContext gc) {

		// gc.fillOval(x, y+25, w, w);//Image(image, x, y);
		gc.drawImage(image, x, y + 25);

		// image = new
		// Image(ClassLoader.getSystemResource("Pea.png").toString(),w,w,false,false);

	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y + 25, w, w); // w is size of bullet
	}

	public AttackPlant getOwner() {
		return this.owner;
	}

	public void setOwner(AttackPlant owner) {
		this.owner = owner;
	}
}
