package uiInterface;

import logic.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display extends Application{
	
	LinkedList<Bullet> bullets1 = new LinkedList<Bullet>();
	//ArrayList<Bullet> bullets1Used = new ArrayList<Bullet>();
	LinkedList<Bullet> bullets2 = new LinkedList<Bullet>();
	//ArrayList<Bullet> bullets2Used = new ArrayList<Bullet>();
	
	StackPane root = new StackPane();
	Canvas canvas = new Canvas(1200,600);
 	GraphicsContext gc = canvas.getGraphicsContext2D();
	private Player p1 = new Player(200, 20000,0,0);
	private Player p2 = new Player(200, 20000,0,0);
	private Counter c1 = new Counter(0,500);
	private Counter c2 = new Counter(600,1100);
	private static final int H = 600;
	private static final int W = 1200;
	public static final int box_w = W/(Player.collumn*2);
	public static final int box_h = H/Player.row;
	private boolean goUp1,goDown1,goLeft1,goRight1,digit1 = false;
	private boolean goUp2,goDown2,goLeft2,goRight2,num1 = false;
	private PlantStorage storages = new PlantStorage();
	private Table table = new Table(p1,p2);
	private Container container = new Container(p1, p2);
	
	
	

	/*public void tick() {
		handler.tick();
	}*/
	 @Override
	 public void start(Stage stage)  {
		 	HBox hbox = new HBox();
		 	table.getChildren().addAll(c1,c2);
		 	hbox.getChildren().addAll(storages,table);
		 	root.setPrefSize(W, H+300);
		 	root.getChildren().add(hbox);
		 	
	        stage.setTitle("Basic JavaFX demo");
	        Scene scene = new Scene(root);
	       
 		   System.out.println(p1.getPlant(4, 4));
	        
	        stage.setScene(scene);
	        stage.setResizable(true);
	        stage.show();
	        
	        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	
				@Override
				public void handle(KeyEvent event) {
					System.out.println(event.getCode());
					switch(event.getCode()) {
					case UP: c2.moveCounter(0, -1); break;
					case DOWN: c2.moveCounter(0, 1); break;
					case LEFT: goLeft1 = true; break;
					case RIGHT: goRight1 = true; break;
					case W: goUp2 = true; break;
					case S: goDown2 = true; break;
					case A: goLeft2 = true; break;
					case D: goRight2 = true; break;
					case DIGIT1 : digit1 = true; break;
					case NUMPAD1 : num1 = true; break;
					}
				}
	        	
			});
	       scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case UP: goUp1 = false; break;
				case DOWN: goDown1 = false; break;
				case LEFT: goLeft1 = false; break;
				case RIGHT: goRight1 = false; break;
				case W: goUp2 = false; break;
				case S: goDown2 = false; break;
				case A: goLeft2 = false; break;
				case D: goRight2 = false; break;
				case DIGIT1 : digit1 = false; break;
				case NUMPAD1 : num1 = false; break;
				}
			}
		});
	  
	       new AnimationTimer() {
	    	   double lastTime = 0;
	    	   int a = 0;
	    	   int i = 0;
			@Override
			public void handle(long currentTime) {
				
				
				double deltaTime = (currentTime - lastTime) / 1000000;

		        
		  
		        if(deltaTime > 60) {
		        	table.getCanvas().getGraphicsContext2D().clearRect(0, 0, 1200, 600);
	        	   if(goUp1) c2.moveCounter(0, -1);
	        	   if(goDown1) c2.moveCounter(0, 1);
	        	   if(goLeft1)	c2.moveCounter(-1, 0);
	        	   if(goRight1)	c2.moveCounter(1, 0);
	        	   if(goUp2) c1.moveCounter(0, -1);
	        	   if(goDown2) c1.moveCounter(0, 1);
	        	   if(goLeft2)	c1.moveCounter(-1, 0);
	        	   if(goRight2)	c1.moveCounter(1, 0);
	        	   if(digit1) {
	        		   
	        		   p1.spawnPlant(new SunFlower(), 1, 0);
	        		   p1.spawnPlant(new SunFlower(), 2, 0);
	        		   p1.spawnPlant(new SunFlower(), 1, 4);
	        		   p1.spawnPlant(new SunFlower(), 4, 0);
	        
	        		  
	        	   }
	        	   if(num1) {
	        		   p2.spawnPlant(new GreenBean(), 1, 1);
	    
	        	   }
	        	   
	        	   table.updateTable();
	        	if(a > 30)  {
	        		System.out.println(a);
	        		//loadBullet(a);
	        		container.addBulletContainer();
	        		container.addSolarPower(table);
	        		
	        		a = 0;
	        	}
	        	if(deltaTime > 70) {
	        		
	        	}
	        	lastTime  = currentTime;
	        	container.updateSolarPower(table);
	        	   container.updateBulletMovement(5);
	        	   container.updateCollision();
	        	   p1.detectPlant();
	        	   p2.detectPlant();
	        	   container.updateOutOfRangeBullet();
	        	   container.drawBullet(table.getCanvas().getGraphicsContext2D());
	        	   
	        	   table.drawPlantInTable(table.getCanvas().getGraphicsContext2D());
	        	   container.drawSolars(table.getCanvas().getGraphicsContext2D());
	        	   
	        	  //tick();// not finished yet
	        	   
	        	   
	        	    // have to  create drawing plant separate with this
	        	   
	        	  a++;
	        	  
	        	  
	        	  storages.updatePlantSlot();
	        	  
		        }
		        
			}}.start();
	   
	       
	       
	    }
	 @Override
	 public void stop() {
		 
	 }
	
	 public static void main(String[] args) {

         launch(args);
     }
}
