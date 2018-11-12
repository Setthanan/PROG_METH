import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Player extends VBox {
	
	private int hp;
	private int score;
	private int sun;
	
	public Player(int x) {
		super(10);
		setPrefSize(180, 600);
		setAlignment(Pos.CENTER);
		if(x==1) {
			setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
		}
		else {
			setBackground(new Background(new BackgroundFill(Color.ORANGERED, null, null)));
		}
		
		Label player = new Label("Player "+x);
		Label score = new Label("Score");
		Label hp = new Label("Hp");
		player.setStyle("-fx-font-size:30; -fx-font-weight:BOLD;");
		score.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		hp.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		
		ImageView Peashooter = new ImageView(new Image(ClassLoader.getSystemResource("peashooter.png").toString()));
		ImageView Sunflower = new ImageView(new Image(ClassLoader.getSystemResource("sunflower.png").toString()));
		ImageView Walnut = new ImageView(new Image(ClassLoader.getSystemResource("walnut.png").toString()));
		ImageView Sun = new ImageView(new Image(ClassLoader.getSystemResource("sun.gif").toString()));
		
		getChildren().addAll(player,score,hp,Sun,Sunflower,Peashooter,Walnut);
	}
	
	
	

}
