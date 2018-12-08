package logic;

import java.util.ArrayList;

public class PlantStorage {
	///this class is suited for include error exception 
	public ArrayList<Plant> storage;
	public PlantStorage() {
		this.storage = new ArrayList<Plant>();
	}
	public String addPlant(Plant plant) {
		if( storage.size() < 7){	
			storage.add(plant);
			///this will make size of storage equal 8
			return plant.toString();
		}
		else {
			return "Cant put plant anymore";
		}
	}
	public Plant pickPlant(int i) {
		///must check data that get from this method is not null in Player Class
		 Plant obj = null;
		if(i>=0 && i < storage.size()) {
			if ( storage.get(i) instanceof GreenBean) obj = new GreenBean();
			if( storage.get(i) instanceof SunFlower) obj = new SunFlower();
			
		}
		return obj;
	}
	public void listPlant() {
		// to show list of plant that consist of name+type+element
		for(int i = 0 ; i < storage.size() ; i++) {
			Plant plant = storage.get(i);
			System.out.println(plant.getName()+" "+plant.getPlantKind()+" "+plant.getElemental());
		}
	}
}
