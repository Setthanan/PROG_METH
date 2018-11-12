import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class World extends Application{
	public static final int table_size = 120;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 5;
	public int[][] Board = new int[WIDTH-3][HEIGHT];
	
	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox();
		root.setPrefSize(WIDTH * table_size, HEIGHT * table_size);
		root.setPadding(new Insets(15, 15, 15, 15));
		
		//background image
		String image_path = ClassLoader.getSystemResource("Background3.jpg").toString();
		ImageView imageView = new ImageView(new Image(image_path));
		imageView.setPreserveRatio(true);
		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		
		root.getChildren().addAll(player1, imageView, player2);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Plant vs Plant");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
