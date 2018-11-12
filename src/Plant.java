import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant {
	private ImageView Pea;
	private ImageView Flower;
	private ImageView fullWalnut;
	
	public Plant() {
		Pea = new ImageView(new Image(ClassLoader.getSystemResource("peashooter.gif").toString()));
		Flower = new ImageView(new Image(ClassLoader.getSystemResource("sunflower.gif").toString()));
		fullWalnut = new ImageView(new Image(ClassLoader.getSystemResource("walnut_full.gif").toString()));
	}
	
	public ImageView getPea() {
		return Pea;
	}
	
	public ImageView getFlower() {
		return Flower;
	}
	
	public ImageView getWalnut() {
		return fullWalnut;
	}

}
