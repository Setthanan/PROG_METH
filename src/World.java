import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class World extends Application{
	public static final int table_size = 120;
	public static final int WIDTH = 12;
	public static final int HEIGHT = 5;
	private ImageView main_menu;
	private ImageView click;
	private Button start;
	private Button back;
	private Scene scene1,scene2;
	private Audio audio,menu;
	private Tile[][] board = new Tile[WIDTH][HEIGHT];
	
	private Parent createArray() {
		ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResource("Background3.jpg").toString()));
		imageView.setPreserveRatio(true);
		StackPane array = new StackPane();
		array.setPrefSize(WIDTH * table_size, HEIGHT * table_size);		
		for(int x = 0; x<WIDTH; x++) {
			for(int y = 0; y<HEIGHT; y++) {
				Tile tile = new Tile(x, y);
				board[x][y] = tile;
				array.getChildren().add(tile);
			}
		}
		array.getChildren().addAll(imageView);
		return array;
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		main_menu = new ImageView(new Image(ClassLoader.getSystemResource("first_screen.jpg").toString()));
		click = new ImageView(new Image(ClassLoader.getSystemResource("click_to_start.gif").toString()));
		audio = new Audio("background.wav"); 
		menu = new Audio("menu.wav"); 
		menu.play_audio();

		start = new Button();
		start.setGraphic(click);
		start.setStyle("-fx-background-color:transparent;");
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(scene2);
				audio.play_audio();
				menu.stop_audio();
			}
		});
		
		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(20, 20, 20, 20));
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.getChildren().add(start);
		
		StackPane root1 = new StackPane();
		root1.setPrefSize(WIDTH * table_size, HEIGHT * table_size);
		root1.getChildren().addAll(main_menu,buttonBox);
		scene1 = new Scene(root1);
		
		back = new Button("back");
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(scene1);
				menu.play_audio();
				audio.stop_audio();
				
			}
		});
		
		
		VBox root2 = new VBox();
		root2.setPrefSize(WIDTH * table_size, HEIGHT * table_size);
		root2.setPadding(new Insets(15, 15, 15, 15));
		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		
		HBox playerBox1 = new HBox();
		playerBox1.getChildren().addAll(player1);
		
		HBox playerBox2 = new HBox();
		playerBox2.getChildren().addAll(player2);
		
		HBox playerBox = new HBox();
		playerBox.getChildren().addAll(playerBox1,playerBox2);
		
		root2.getChildren().addAll(createArray(),playerBox,back);
		
		scene2 = new Scene(root2);
		
		primaryStage.setScene(scene1);
		primaryStage.setTitle("Plant vs Plant");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
