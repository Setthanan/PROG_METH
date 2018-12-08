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
	private Image image = new Image(ClassLoader.getSystemResource("peashooter.png").toString(),Display.box_w,Display.box_h,false,false);
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


	
	public void update() {
		//this.time += (int) time;
		int r;
		if(this.time < time) {
			if(x>= 600)  r = x;
			else  r = x+80;
					
			this.bullet = new Bullet(r, y+25,false);
			this.time = 0;
		}
		this.time++;
	}

	
	public void render(GraphicsContext gc) {
		if(x>=600) {
			gc.drawImage(image, x+Display.box_w, y,-Display.box_w,Display.box_h);
		}
		else {
			gc.drawImage(image, x, y,Display.box_w,Display.box_h);
		}
		
		
	}
	public Image getImage() {
		return this.image;
	}
	

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x,y,Display.box_w,Display.box_h);
	}
}
