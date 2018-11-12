package logic;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class GreenBean extends AttackPlant {
	private Image image = new Image(ClassLoader.getSystemResource("images/peashooter.png").toString(),Display.box_w,Display.box_h,false,false);
	private int time = 0 ;
	public GreenBean() {
		super("GreenBean", 10 ,"wind", 50,0,0);
	}

	@Override
	public double calDamage(Plant other) {
		int power = this.bullet.getPower();
		if(power < 0) power = 0;
		return power*calElemental(other);
	}
	public double calDamage(Player other) {
		int power = this.bullet.getPower();
		if(power < 0) power = 0;
		return power;
	}


	@Override
	public void update(double time) {
		//this.time += (int) time;
		//if(this.time < time) {
			this.bullet = new Bullet(x, y+25,false);
			this.time = 0;
		//}
		//this.time++;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y);
		
		
	}
	private boolean intersects(GameObject other) {
		return other.getBounds().intersects(this.getBounds());
		
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x,y,Display.box_w,Display.box_h);
	}
}
