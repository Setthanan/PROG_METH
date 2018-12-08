package logic;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class SunFlower extends SpecialPlant {
	private Image image = new Image(ClassLoader.getSystemResource("sunflower.png").toString(),Display.box_w,Display.box_h,false,false);
	private SolarPower solar;
	private boolean state;
	
	public SunFlower() {
		super("SunFlower", "fire", 10, 25, 0, 0);
		this.solar = new SolarPower(x, y);
		this.state = true;
	}

	public void load() {
		if(this.state) {
			this.solar = new SolarPower(x,y);
			this.state = false;
			System.out.println("loaded");
		}
		
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean getState() {
		return this.state;
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
	public SolarPower getSolarPower() {
		return this.solar;
	}

	public void setSolarPower(SolarPower solar) {
		this.solar = solar;
	}
}
