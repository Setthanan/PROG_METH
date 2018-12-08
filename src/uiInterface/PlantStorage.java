package uiInterface;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GreenBean;
import logic.Plant;
import logic.StonePlant;
import logic.SunFlower;

public class PlantStorage extends VBox {
	///this class is suited for include error exception 
	private final int Num0fSlot = 5;
	private ArrayList<Plant> storage;
	private Canvas plantSlot;
	private ImageView Peashooter;
	public ImageView Sunflower;
	public ImageView Walnut;
	public ImageView Sun;
	public PlantStorage() {
		super(15);
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
		this.storage = new ArrayList<Plant>();
		this.plantSlot = new Canvas(1200,125);
		getChildren().addAll(player,score,hp,Sun,Sunflower,Peashooter,Walnut);
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
