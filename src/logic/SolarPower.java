package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class SolarPower extends Rectangle {
	private Image image = new Image(ClassLoader.getSystemResource("sun.gif").toString());
	private ImagePattern sun = new ImagePattern(image);
	private int x;
	private int y;
	private int i, j;
	private boolean isClicked;

	public SolarPower(int x, int y) {
		this.x = x;
		this.y = y;
		this.i = 0;
		this.j = 0;
		relocate(this.x, this.y + Display.box_h);
		setWidth(50);
		setHeight(50);
		setFill(sun);
	}

	public Image getImage() {
		return this.image;
	}

	public boolean getIsClicked() {
		return this.isClicked;
	}

	public void setIsClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}
	public void drawSolarPower(GraphicsContext gc) {
		gc.drawImage(image,x,y,20,20);
	}
}
