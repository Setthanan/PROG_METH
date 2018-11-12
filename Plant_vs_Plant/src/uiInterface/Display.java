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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display extends Application{
	
	LinkedList<Bullet> bullets1 = new LinkedList<Bullet>();
	//ArrayList<Bullet> bullets1Used = new ArrayList<Bullet>();
	LinkedList<Bullet> bullets2 = new LinkedList<Bullet>();
	//ArrayList<Bullet> bullets2Used = new ArrayList<Bullet>();

	Pane root = new Pane();
	Canvas canvas = new Canvas(1200,600);
 	GraphicsContext gc = canvas.getGraphicsContext2D();
	private final Set<KeyCode> Keyspressed = new HashSet<KeyCode>();
	private Player p1 = new Player(200, 20000,0,0);
	private Player p2 = new Player(200, 20000,0,0);
	private Counter c1 = new Counter(0,500);
	private Counter c2 = new Counter(600,1100);
	private static final int H = 600;
	private static final int W = 1200;
	public static final int box_w = W/(Player.collumn*2);
	public static final int box_h = H/Player.row;
	private Tile[][] grid	= new Tile[Player.row][Player.collumn*2];
	private boolean goUp1,goDown1,goLeft1,goRight1,digit1 = false;
	private boolean goUp2,goDown2,goLeft2,goRight2,num1 = false;
	


	
	private class Tile extends StackPane{
		private int i,j;
		private Plant plant;
		//private ImageView imageView;
		private Rectangle border = new Rectangle(box_w,box_h);
		public Tile(int i,int j,Plant plant) {
			this.i = i;
			this.j = j;
			this.plant = plant;
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.BLACK);
			/*if(this.plant != null) {
				this.imageView = plant.getImage();
				if(this.j > 5) {
					this.imageView.setScaleX(-1);;
				}
				imageView.setFitWidth(box_w);
				imageView.setFitHeight(box_h);
				getChildren().add(imageView);
			}*/
			
			getChildren().addAll(border);
			setAlignment(Pos.CENTER);
			setTranslateY(i*box_h);
			setTranslateX(j*box_w);
		}
		
		
		
	}
	private Parent createContent() {
		
		root.setPrefSize(W, H);
			for(int i = 0; i < Player.row ;  i++) {
			int min = 0; //to fix playerArray position
			for(int j = 0; j < Player.collumn ; j++) {
			
				Tile tile = new Tile(i,j,p1.playerPlant[i][j]);
				if(p1.playerPlant[i][j] != null) {
					grid[i][j] = tile;
					//root.getChildren().add(grid[i][j]);
				}
			}
			for(int j = Player.collumn*2-1 ; j >= Player.collumn ; j--) {
				
				Tile tile = new Tile(i,j,p2.playerPlant[i][min]);
				if(p2.playerPlant[i][2] != null) {
					grid[i][j] = tile;
					//root.getChildren().add(grid[i][j]);
				}
				min++;
			}
			
			}
			
			return root;
	
		
		
	}

	public void updateTable() {
		for(int i = 0; i < Player.row ;  i++) {
			int min = 0; //to fix playerArray position
			for(int j = 0; j < Player.collumn ; j++) {
				Tile tile = new Tile(i,j,p1.playerPlant[i][j]);
				if(grid[i][j] != null) root.getChildren().remove(grid[i][j]);
				if(p1.playerPlant[i][j] != null) {
					grid[i][j] = tile;
					//root.getChildren().add(grid[i][j]);
					if(p1.playerPlant[i][j] instanceof AttackPlant) {
						if(((AttackPlant) p1.playerPlant[i][j]).getBullet() != null) {
						int x = p1.playerPlant[i][j].getX();
						int y = p1.playerPlant[i][j].getY();
						if(((AttackPlant) p1.playerPlant[i][j]).getBullet().getX() == x
							&&	((AttackPlant) p1.playerPlant[i][j]).getBullet().getX() == y	) {
							((AttackPlant) p1.playerPlant[i][j]).setBullet(x, y);
						}
					}
				}
					p1.playerPlant[i][j].render(gc);
					
					
				}
					
				else {
					grid[i][j] = null;
				}
				
			}
			for(int j = Player.collumn*2-1 ; j >= Player.collumn ; j--) {
				Tile tile = new Tile(i,j,p2.playerPlant[i][min]);
				if(grid[i][j] != null) root.getChildren().remove(grid[i][j]);
				if(p2.playerPlant[i][min] != null) {
					grid[i][j] = tile;
					//root.getChildren().add(grid[i][j]);
					p2.playerPlant[i][min].setX(j*box_w);
					if(p2.playerPlant[i][min] instanceof AttackPlant) {
						if(((AttackPlant) p2.playerPlant[i][min]).getBullet() != null) {
							int x = p2.playerPlant[i][min].getX();
							int y = p2.playerPlant[i][min].getY();
							if(((AttackPlant) p2.playerPlant[i][min]).getBullet().getX() == x
									&&	((AttackPlant) p2.playerPlant[i][min]).getBullet().getX() == y	) {
								((AttackPlant) p2.playerPlant[i][min]).setBullet(x, y);
							}
							Bullet temp = ((AttackPlant) p2.playerPlant[i][min]).getBullet();
							if(temp.getVelX() > 0){
								((AttackPlant) p2.playerPlant[i][min]).getBullet().setVelX(-1*temp.getVelX());
							}
						}
					}
					p2.playerPlant[i][min].render(gc);
				}
				else {
					grid[i][j] = null;
				}
				min++;
			}
			
			}
	}
	
	/*public void tick() {
		handler.tick();
	}*/
	 @Override
	 public void start(Stage stage)  {
		 	root.getChildren().addAll(c1,c2);
		 	root.getChildren().add(canvas);
	        stage.setTitle("Basic JavaFX demo");
	        Scene scene = new Scene(createContent());
	       
 		   System.out.println(p1.getPlant(4, 4));
	        
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
	        
	        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	
				@Override
				public void handle(KeyEvent event) {
					System.out.println(event.getCode());
					switch(event.getCode()) {
					case UP: goUp1 = true; break;
					case DOWN: goDown1 = true; break;
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
	        	   if(goUp1) c2.moveCounter(0, -1);
	        	   if(goDown1) c2.moveCounter(0, 1);
	        	   if(goLeft1)	c2.moveCounter(-1, 0);
	        	   if(goRight1)	c2.moveCounter(1, 0);
	        	   if(goUp2) c1.moveCounter(0, -1);
	        	   if(goDown2) c1.moveCounter(0, 1);
	        	   if(goLeft2)	c1.moveCounter(-1, 0);
	        	   if(goRight2)	c1.moveCounter(1, 0);
	        	   if(digit1) {
	        		   for(int i = 0 ; i < Player.row ; i++) {
	        				 for(int j = 0 ; j <Player.collumn ; j++) {
	        		   p1.spawnPlant(new GreenBean(), i, j);
	        		  
	        				 }
	        		   }
	  
	        		  
	        	   }
	        	   if(num1) {
	        		   for(int i = 0 ; i < Player.row ; i++) {
	        				 for(int j = 0 ; j <Player.collumn ; j++) {
	        		   p2.spawnPlant(new GreenBean(), i, j);
	        		  
	        				 }
	        		   }
	        	   }
	        	   
	        	   
	        	if(a>=30)   		{
	        		System.out.println(a);
	        		loadBullet(a);
	        		a=0;
	        	
	        	}
	        	lastTime  = currentTime;
	        		checkfireBullet(10);
	        		takeBullet();
	        	   
	        	   p1.detectPlant();
	        	   p2.detectPlant();
	        	  //tick();// not finished yet
	        	   gc.clearRect(0, 0, 1200, 600);
	        	  
	        	  updateTable(); // have to  create drawing plant separate with this
	        	  fireBullet();
	        	  a++;
	        	 
	        	  
		        }
		        
			}}.start();
	   
	       
	       
	    }
	 @Override
	 public void stop() {
		 
	 }
	 public void loadBullet(double a) {
		 ///have to fix each bullet should not move together
		
		 for(int i = 0 ; i < Player.row ; i++) {
			 for(int j = 0 ; j <Player.collumn ; j++) {
				 if(p1.playerPlant[i][j] != null && p1.playerPlant[i][j] instanceof AttackPlant) {
					 ((AttackPlant) p1.playerPlant[i][j]).update(2);
					 if(((AttackPlant) p1.playerPlant[i][j]).getBullet() != null) {
						 Bullet temp = ((AttackPlant) p1.playerPlant[i][j]).getBullet();
						 if(!bullets1.contains(temp)) {
							 bullets1.add(((AttackPlant) p1.playerPlant[i][j]).getBullet());
						}	
					 }
				 }
				 
			 }
		 }
	
		 for(int i = 0 ; i < Player.row ; i++) {
			 for(int j = 0 ; j <Player.collumn ; j++) {
				 if(p2.playerPlant[i][j] != null && p2.playerPlant[i][j] instanceof AttackPlant) {
					 ((AttackPlant) p2.playerPlant[i][j]).update(2);
					 if(((AttackPlant) p2.playerPlant[i][j]).getBullet() != null) {
						 Bullet temp = ((AttackPlant) p2.playerPlant[i][j]).getBullet();
						 if(!bullets2.contains(temp)) {
							bullets2.add(((AttackPlant) p2.playerPlant[i][j]).getBullet());
						 }
					 }
				 }
				 
			 }
		 }
	 }
	 public void takeBullet() {
		 for(int k = 0 ; k < bullets1.size() ; k++) {
			 Bullet tempBullet = bullets1.get(k);
			 for(int i = 0 ; i < Player.row ; i++) {
				 for(int j = 0 ; j <Player.collumn ; j++) {
					 if(p2.playerPlant[i][j] != null) {
						 if(tempBullet.getBounds().intersects(p2.playerPlant[i][j].getBounds())) {
							 p2.playerPlant[i][j].takeDamage(1);
							 bullets1.remove(tempBullet);
						 
						 }
					}
				 }
			 }
			 
		 }
		 for(int k = 0 ; k < bullets2.size() ; k++) {
			 Bullet tempBullet = bullets2.get(k);
			 for(int i = 0 ; i < Player.row ; i++) {
				 for(int j = Player.collumn -1 ;  j >= 0 ; j--) {
					 if(p1.playerPlant[i][j] != null) {
						 if(tempBullet.getBounds().intersects(p1.playerPlant[i][j].getBounds())) {
							 p1.playerPlant[i][j].takeDamage(1);
							 bullets2.remove(tempBullet);
						 
						 }
					}
				 }
			 }
			 
		 }
	 }
	 public void fireBullet() {
		 for(int i = 0; i <bullets1.size(); i++) {
			bullets1.get(i).render(gc);
			
		 }
		 for(int i = 0; i <bullets2.size(); i++) {
			
			bullets2.get(i).render(gc);
				
		}
		 
	 }
	 public void checkfireBullet(double time) {
		 for(int i = 0; i <bullets1.size(); i++) {
			bullets1.get(i).update(time);
			
			if(bullets1.get(i).getX() > 2000) bullets1.remove(bullets1.get(i));
			
		 }
		 for(int i = 0; i <bullets2.size(); i++) {
			bullets2.get(i).update(time);
			
			if(bullets2.get(i).getX() < -2000) bullets2.remove(bullets2.get(i));
				
		}
		 
	 }
	 public static void main(String[] args) {

         launch(args);
     }
}
