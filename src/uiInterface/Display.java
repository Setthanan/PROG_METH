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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	
	
	//StackPane root = new StackPane();
	Canvas canvas = new Canvas(1200,600);
 	GraphicsContext gc = canvas.getGraphicsContext2D();
	private Player p1 = new Player(200, 20000,0,0);
	private Player p2 = new Player(200, 20000,0,0);
	private Counter c1 = new Counter(0,500);
	private Counter c2 = new Counter(600,1100);
	private static final int H = 5;
	private static final int W = 12;
	public static final int table_size = 120;
	public static final int box_w = (W*table_size)/(Player.collumn*2);
	public static final int box_h = (H*table_size)/Player.row;
	private boolean goUp1,goDown1,goLeft1,goRight1,digit1 = false;
	private boolean goUp2,goDown2,goLeft2,goRight2,num1 = false;
	private PlantStorage storages = new PlantStorage();
	private Table table = new Table(p1,p2);
	private Container container = new Container(p1, p2);
	
	private ImageView main_menu,click,yard;
	private Button start,back,pause,resume;
	private Scene scene1,scene2,scene3;
	private Audio audio,menu;
	private PlantStorage player;
	
	private Thread t;
	private boolean check;
	
	public Display() {
		player = new PlantStorage();
		main_menu = new ImageView(new Image(ClassLoader.getSystemResource("first_screen.jpg").toString()));
		click = new ImageView(new Image(ClassLoader.getSystemResource("click_to_start.gif").toString()));
		yard = new ImageView(new Image(ClassLoader.getSystemResource("Background3.jpg").toString()));
		audio = new Audio("background.wav"); 
		menu = new Audio("menu.wav"); 
		menu.play_audio();
		back = new Button("MAIN MENU");
		pause = new Button("PAUSE");
		resume = new Button("RESUME");
		start = new Button();
		start.setGraphic(click);
		start.setStyle("-fx-background-color:transparent;");
		check = false;
	}
	
	
	

	/*public void tick() {
		handler.tick();
	}*/
	 @Override
	 public void start(Stage primaryStage)  {
		 	
		 VBox buttonBox = new VBox();
			buttonBox.setPadding(new Insets(20, 20, 20, 20));
			buttonBox.setAlignment(Pos.BOTTOM_CENTER);
			buttonBox.getChildren().add(start);
			
			StackPane root1 = new StackPane();
			root1.setPrefSize(W * table_size, H * table_size);
			root1.getChildren().addAll(main_menu,buttonBox);
			scene1 = new Scene(root1);
			
			VBox vbox = new VBox();
		 	table.getChildren().addAll(c1,c2);
		 	vbox.getChildren().addAll(table);
			
			StackPane tile = new StackPane();
		 	tile.getChildren().addAll(yard,vbox);
	        
			HBox playScene = new HBox();
			playScene.setPrefSize(W * table_size, H * table_size);
			//playScene.setPadding(new Insets(15, 15, 15, 15));
			playScene.getChildren().addAll(storages,tile);
			
			HBox buttonBox2 = new HBox(10);
			buttonBox2.setAlignment(Pos.CENTER_RIGHT);
			buttonBox2.getChildren().addAll(pause,resume,back);
			
			VBox root2 = new VBox(5);
			root2.setPadding(new Insets(15, 15, 15, 15));
			root2.getChildren().addAll(playScene,buttonBox2);
			scene2 = new Scene(root2);
			
			VBox root3 = new VBox(10);
			root3.setAlignment(Pos.CENTER);
			root3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))); // *****change background*****
			root3.setPrefSize(W * table_size, H * table_size);
			root3.setPadding(new Insets(15, 15, 15, 15));
			root3.getChildren().addAll(resume,back);
			scene3 = new Scene(root3);
			
			start.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					primaryStage.setScene(scene2);
					audio.play_audio();
					menu.stop_audio();
				}
			});
			back.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					//primaryStage.close();
					Platform.runLater( () -> new Display().start( new Stage() ) );
					primaryStage.setScene(scene1);
					//menu.play_audio();
					audio.stop_audio();
					
				}
			});
			pause.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					primaryStage.setScene(scene3);
					audio.pause();
					check = true;
					thread();
				}
			});
			resume.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					primaryStage.setScene(scene2);
					audio.play_audio();
					check = false;
					thread();
				}
			});
	       
 		   System.out.println(p1.getPlant(4, 4));
	        
	        primaryStage.setScene(scene1);
	        primaryStage.setTitle("Basic JavaFX demo");
	        primaryStage.setResizable(false);
	        primaryStage.show();
	        
	        scene2.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	
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
	       scene2.setOnKeyReleased(new EventHandler<KeyEvent>() {

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
	        	  
	        	  
	        	  //storages.updatePlantSlot();
	        	  
		        }
		        
			}}.start();
	   
	       
	       
	    }
	 @Override
	 public void stop() {
		 
	 }
	 
	 public void thread() {
		t = new Thread(() -> {
			while(check) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		});
		t.start();
			
	 }
	
	 public static void main(String[] args) {

         launch(args);
     }
}
