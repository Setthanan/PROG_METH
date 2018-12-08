package uiInterface;


import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Counter extends Rectangle  {
	//have to create getSlot function to choose plant
	private int maxX = 0;
	private int minX = 0;
	private int preX = 0;
	private int preY = 0;
	public Counter(int minX,int maxX){
		this.maxX = maxX;
		this.minX = minX;
		setWidth(Display.box_w);
		setHeight(Display.box_h);
		setFill(Color.TRANSPARENT);
		setStroke(Color.ROYALBLUE);
		setStrokeWidth(3);
		this.preX = minX;
		relocate(preX, preY);
		
	} 
    public void moveCounter(int dirx,int diry) {
		int xTran = Display.box_w*dirx,yTran = Display.box_h*diry;
		if(preX == maxX && xTran > 0) xTran = -maxX+minX;
		if(preY + yTran < 0) yTran = 4*Display.box_h;
		if(preX == minX && xTran < 0) xTran = maxX-minX;
		if(preY + yTran > 480) yTran = -preY;
		
		
		this.preX += xTran;
		this.preY += yTran;
		relocate(preX, preY);
		System.out.println(preX+","+preY);
	}
    
}
