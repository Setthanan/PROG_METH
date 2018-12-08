package uiInterface;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.GreenBean;
import logic.Plant;
import logic.StonePlant;
import logic.SunFlower;

public class PlantStorage extends HBox {
	///this class is suited for include error exception 
	private final int Num0fSlot = 5;
	private ArrayList<Plant> storage;
	public Canvas plantSlot;
	public ArrayList<Plant> randList;
	public PlantStorage() {
		setPrefSize(0, 0);
		this.storage = new ArrayList<Plant>();
		this.plantSlot = new Canvas(1200,125);
		this.randList = new ArrayList<Plant>();
		randList.add(new GreenBean());
		randList.add(new SunFlower());
		randList.add(new StonePlant());
		for(int i =0 ; i < Num0fSlot ; i++) {
			storage.add(randList.get((int) (Math.random()*3)+0));
		}
	}
	public void drawPlantSlot() {
		for(int i = 0; i < Num0fSlot ; i++) {
			GraphicsContext gc = plantSlot.getGraphicsContext2D();
			HBox slot = new HBox();
			slot.setPrefSize(300,300);
			slot.getChildren().add(plantSlot);
			gc.drawImage(storage.get(i).getImage(), 100*i, 0);
			gc.strokeRect(0, 0, 100*i, 125);
			this.getChildren().add(slot);
			
		}
	}
	public void updatePlantSlot() {
			drawPlantSlot();
	}
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
