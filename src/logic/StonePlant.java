package logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class StonePlant extends SpecialPlant {
	private Image image = 
			new Image(ClassLoader.getSystemResource("walnut.png").toString(),Display.box_w,Display.box_h,false,false);

	private int guard;
	public StonePlant() {
		super("StonePlant", "normal", 0, 20,0,0);
		this.guard = 4;
	}
	@Override
	public void takeDamage(double damage) {
		if(this.guard > 0) {
			this.guard -= 1;
			return;
		}
		if(damage < 0) damage = 0;
		this.hp -= damage;
		if(this.hp <= 0) {
			this.life -= 1;
			if(this.isAlive()) {
				this.hp = this.maxHp;
			}
			else {
				this.hp = 0;
			}
		}
		
	
	}
	public void update(double time) {
		//fire bullet
		
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}
	

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x,y,Display.box_w,Display.box_h);
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}
}
