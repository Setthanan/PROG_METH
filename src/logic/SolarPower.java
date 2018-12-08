package logic;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import uiInterface.Display;

public class SolarPower extends Rectangle {
	private Image image = new Image(ClassLoader.getSystemResource("sunflower.gif").toString(),50,50,false,false);
	private int x;
	private int y;
	private int i , j;
	private boolean isClicked;
	private  EventHandler<MouseEvent> handler;
	public SolarPower(int x,int y) {
		relocate(x, y+Display.box_h);
		setWidth(50);
		setHeight(50);
		setFill(Color.RED);
		
		this.x = x;
		this.y = y;
		this.i = 0;
		this.j = 0;
		
		
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
	public void drawSolarPower(GraphicsContext gc) {
		//gc.drawImage(image, x, y);
	}
	public int getI() {
		return this.i;
	}
	public int getJ() {
		return this.j;
	}
	public void setI(int i) {
		this.i =i;
	}
	public void setJ(int j) {
		this.j =j;
	}
	public EventHandler<MouseEvent> getHandler(){
		return handler;
	}
}
