import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Player extends VBox {
	
	/*private int hp;
	private int score;
	private int sunCount;*/
	private ImageView Peashooter;
	private ImageView Sunflower;
	private ImageView Walnut;
	private ImageView Sun;

	public Player(int p) {
		super(10);
		setPrefWidth(180);
		setAlignment(Pos.CENTER);
		if(p==1) {
			setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
		}
		else {
			setBackground(new Background(new BackgroundFill(Color.ORANGERED, null, null)));
		}
		
		Label player = new Label("Player "+p);
		Label score = new Label("Score");
		Label hp = new Label("Hp");
		player.setStyle("-fx-font-size:30; -fx-font-weight:BOLD;");
		score.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		hp.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		
		Peashooter = new ImageView(new Image(ClassLoader.getSystemResource("peashooter.png").toString()));
		Sunflower = new ImageView(new Image(ClassLoader.getSystemResource("sunflower.png").toString()));
		Walnut = new ImageView(new Image(ClassLoader.getSystemResource("walnut.png").toString()));
		Sun = new ImageView(new Image(ClassLoader.getSystemResource("sun.gif").toString()));
		
		
		getChildren().addAll(player,score,hp,Sun,Sunflower,Peashooter,Walnut);
	}	
}
