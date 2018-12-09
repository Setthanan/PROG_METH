package uiInterface;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GreenBean;
import logic.Plant;
import logic.Player;
import logic.StonePlant;
import logic.SunFlower;
import uiInterface.Table.Tile;

public class PlantStorage extends VBox {
	///this class is suited for include error exception 
	private final int Num0fSlot = 5;
	private ArrayList<Plant> storage;
	private Canvas plantSlot;
	private ImageView Peashooter;
	public ImageView Sunflower;
	public ImageView Walnut;
	public ImageView Sun;
	
	private GreenBean shooter = new GreenBean();
	private SunFlower flower = new SunFlower();
	private StonePlant walnut = new StonePlant();
	
	private Player p1 ;
	private Table table ;
	private Tile tiles;
	
	
	public PlantStorage(Table table,Player p1) {
		super(15);
		this.table = table;
		this.p1 = p1;
		super.setPadding(new Insets(30, 10, 30, 10));
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
		Label player = new Label("Player ");
		Label score = new Label("Score");
		Label hp = new Label("Hp");
		player.setStyle("-fx-font-size:30; -fx-font-weight:BOLD;");
		score.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		hp.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		Peashooter = new ImageView(new Image(ClassLoader.getSystemResource("peashooter.png").toString()));
		Sunflower = new ImageView(new Image(ClassLoader.getSystemResource("sunflower.png").toString()));
		Walnut = new ImageView(new Image(ClassLoader.getSystemResource("walnut.png").toString()));
		Sun = new ImageView(new Image(ClassLoader.getSystemResource("sun.gif").toString()));
		drag(Peashooter,"peashooter");
		drag(Sunflower,"sunflower");
		drag(Walnut,"walnut");
		this.storage = new ArrayList<Plant>();
		this.plantSlot = new Canvas(1200,125);
		
		getChildren().addAll(player,score,hp,Sun,Sunflower,Peashooter,Walnut);
		
	}
	
	public void drag(ImageView iv,String name) {
		for(int i = 0; i< table.row; i++) {
			for(int j = 0; j<table.col/2; j++) {
				Tile tiles = table.getTile(i,j);
				System.out.println(i+","+j+" "+tiles);
				iv.setOnDragDetected(new EventHandler<MouseEvent>() {
		
					@Override
					public void handle(MouseEvent event) {
						System.out.println("grab");
						Dragboard db = iv.startDragAndDrop(TransferMode.MOVE);
						ClipboardContent content = new ClipboardContent();
						content.putString(name);
						db.setContent(content);
						db.setDragView(iv.getImage());
						event.consume();
					}
					
				});
				if(tiles == null) return;
				tiles.setOnDragOver(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent event) {
						if(event.getGestureSource() != tiles && event.getDragboard().hasContent(DataFormat.PLAIN_TEXT)) {
							event.acceptTransferModes(TransferMode.MOVE);
							System.out.println(event.getSceneX()+" "+event.getSceneY());
						}
						event.consume();
					}
				});
				
				tiles.setOnDragEntered(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent event) {
						if(event.getGestureSource() != tiles && event.getDragboard().hasContent(DataFormat.PLAIN_TEXT)) {
							System.out.println("enter!!!");
							
						}
						event.consume();
					}
				});
				
				tiles.setOnDragExited(new EventHandler<DragEvent>() {
		
					@Override
					public void handle(DragEvent event) {
						System.out.println("exit...");
						event.consume();
					}
				});
				
				
				tiles.setOnDragDropped(new EventHandler<DragEvent>() {
					
					@Override
					public void handle(DragEvent event) {
						Dragboard db = event.getDragboard();
						boolean success = false;
<<<<<<< HEAD
							int i = (int)((event.getSceneY()-15)/120);
							int j = (int)((event.getSceneX()-132)/120);
							System.out.println(event.getSceneX()+" "+event.getSceneY()+","+i+" "+j);
							System.out.println(db.getString());
							
						if(db.hasString()) {
							if(db.getContent(DataFormat.PLAIN_TEXT).equals("peashooter")) {
=======
							int i = (int)(Math.floor(event.getScreenY())/Display.box_h)/2;
							int j = (int)(Math.floor(event.getScreenX())/Display.box_w)/2;
							System.out.println(i+" "+j);
						if(db.hasImage()) {
							if(db.getImage().equals(shooter.getImage())) {
>>>>>>> 6b81c6cee2137eb775679b96abbb810c275bedf9
								p1.spawnPlant(new GreenBean(), i, j);
							}
							if(db.getContent(DataFormat.PLAIN_TEXT).equals("sunflower")) {
								p1.spawnPlant(new SunFlower(), i, j);
							}
							if(db.getContent(DataFormat.PLAIN_TEXT).equals("walnut")) {
								p1.spawnPlant(new StonePlant(), i, j);
								
							}
							success = true;
						}
						event.setDropCompleted(success);
						event.consume();
					}
				});
			}
		}
	}
	/*public void drawPlantSlot() {
		for(int i = 0; i < Num0fSlot ; i++) {
			GraphicsContext gc = plantSlot.getGraphicsContext2D();
			VBox slot = new VBox();
			slot.setPrefSize(300,300);
			slot.getChildren().add(plantSlot);
			gc.drawImage(storage.get(i).getImage(), 100*i, 0);
			gc.strokeRect(0, 0, 100*i, 125);
			this.getChildren().add(slot);
			
		}
	}*/
	/*public void updatePlantSlot() {
			drawPlantSlot();
	}*/
	public String addPlant(Plant plant) {
		if( storage.size() < Num0fSlot){	
			storage.add(plant);
			///this will make size of storage equal 8
			return plant.toString();
		}
		else {
			return "Cant put plant anymore";
		}
	}
	/*public Plant pickPlant(int i) {
		///must check data that get from this method is not null in Player Class
		 Plant obj = null;
		if(i>=0 && i < storage.size()) {
			if ( storage.get(i) instanceof GreenBean) obj = new GreenBean();
			if( storage.get(i) instanceof SunFlower) obj = new SunFlower();
			
		}
		return obj;
	}*/
	public void listPlant() {
		// to show list of plant that consist of name+type+element
		for(int i = 0 ; i < storage.size() ; i++) {
			Plant plant = storage.get(i);
			System.out.println(plant.getName()+" "+plant.getPlantKind()+" "+plant.getElemental());
		}
	}
	
	
}
