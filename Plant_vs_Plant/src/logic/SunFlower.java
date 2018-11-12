package logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class SunFlower extends SpecialPlant {
	private Image image = new Image(ClassLoader.getSystemResource("images/sunflower.png").toString(),Display.box_w,Display.box_h,false,false);
	public SunFlower() {
		super("SunFlower", "fire", 10, 25, 0, 0);
		
	}

	@Override
	public void update(double time) {
		//sun energy
		
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
