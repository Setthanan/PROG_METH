import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant {
	
	public Plant() {
		ImageView Pea = new ImageView(new Image(ClassLoader.getSystemResource("peashooter.gif").toString()));
		ImageView flower = new ImageView(new Image(ClassLoader.getSystemResource("sunflower.gif").toString()));
		ImageView fullWalnut = new ImageView(new Image(ClassLoader.getSystemResource("walnut_full.gif").toString()));
		
		
	}
}
