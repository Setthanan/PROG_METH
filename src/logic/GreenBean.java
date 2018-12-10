package logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uiInterface.Display;

public class GreenBean extends AttackPlant {
	private Image image = new Image(ClassLoader.getSystemResource("peashooter.gif").toString(), Display.box_w,
			Display.box_h, false, false);
	public Image Peashooter = new Image(ClassLoader.getSystemResource("peashooter.png").toString());

	public GreenBean() {
		super("GreenBean", 5, "wind", 100, 0, 0);
	}

	@Override
	public double calDamage(Plant other) {
		double power = this.bullet.getPower();
		if (power < 0)
			power = 0;
		return power * calElemental(other);
	}

	@Override
	public double calDamage(Player other) {
		double power = this.bullet.getPower();
		if (power < 0)
			power = 0;
		return power;
	}

	public Image getImage() {
		return this.image;
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, Display.box_w, Display.box_h);
	}
}
