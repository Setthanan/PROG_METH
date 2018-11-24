import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
	
	private Plant plant;
	
	public boolean hasPlant() {
		return plant != null;
	}
	
	public Plant getPlant() {
		return plant;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public Tile(int x, int y) {
		setWidth(World.table_size);
		setHeight(World.HEIGHT);
		
		relocate(x*World.table_size, y*World.table_size);
	}

}
