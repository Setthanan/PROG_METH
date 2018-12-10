package logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uiInterface.Display;

public class SunFlower extends SpecialPlant {
	private Image image = new Image(ClassLoader.getSystemResource("sunflower.gif").toString(), Display.box_w,
			Display.box_h, false, false);
	private SolarPower solar;
	private boolean state;

	public SunFlower() {
		super("SunFlower", "fire", 3, 50, 0, 0);
		this.solar = new SolarPower(x, y);
		this.state = true;
	}

	public void load() {
		if (this.state) {
			this.solar = new SolarPower(x, y);
			this.state = false;
		}

	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return this.state;
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, Display.box_w, Display.box_h);
	}

	@Override
	public Image getImage() {
		return this.image;
	}

	public SolarPower getSolarPower() {
		return this.solar;
	}

	public void setSolarPower(SolarPower solar) {
		this.solar = solar;
	}
	
}
