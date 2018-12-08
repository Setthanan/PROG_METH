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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class World extends Application{
	public static final int table_size = 120;
	public static final int WIDTH = 12;
	public static final int HEIGHT = 5;
	private ImageView main_menu,click,yard;
	private Button start,back,pause,resume;
	private Scene scene1,scene2,scene3;
	private Audio audio,menu;
	private P player;
	
	public World() {
		player = new P();
		main_menu = new ImageView(new Image(ClassLoader.getSystemResource("first_screen.jpg").toString()));
		click = new ImageView(new Image(ClassLoader.getSystemResource("click_to_start.gif").toString()));
		yard = new ImageView(new Image(ClassLoader.getSystemResource("Background3.jpg").toString()));
		audio = new Audio("background.wav"); 
		menu = new Audio("menu.wav"); 
		menu.play_audio();
		back = new Button("MAIN MENU");
		pause = new Button("PAUSE");
		resume = new Button("RESUME");
		start = new Button();
		start.setGraphic(click);
		start.setStyle("-fx-background-color:transparent;");
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(20, 20, 20, 20));
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.getChildren().add(start);
		
		StackPane root1 = new StackPane();
		root1.setPrefSize(WIDTH * table_size, HEIGHT * table_size);
		root1.getChildren().addAll(main_menu,buttonBox);
		scene1 = new Scene(root1);
		
		HBox playScene = new HBox();
		playScene.setPrefSize(WIDTH * table_size, HEIGHT * table_size);
		//playScene.setPadding(new Insets(15, 15, 15, 15));
		playScene.getChildren().addAll(player,yard);
		
		HBox buttonBox2 = new HBox(10);
		buttonBox2.setAlignment(Pos.CENTER_RIGHT);
		buttonBox2.getChildren().addAll(pause,resume,back);
		
		VBox root2 = new VBox(5);
		root2.setPadding(new Insets(15, 15, 15, 15));
		root2.getChildren().addAll(playScene,buttonBox2);
		scene2 = new Scene(root2);
		
		VBox root3 = new VBox(10);
		root3.setAlignment(Pos.CENTER);
		root3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))); // *****change background*****
		root3.setPrefSize(WIDTH * table_size, HEIGHT * table_size);
		root3.setPadding(new Insets(15, 15, 15, 15));
		root3.getChildren().addAll(resume,back);
		scene3 = new Scene(root3);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(scene2);
				audio.play_audio();
				menu.stop_audio();
			}
		});
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(scene1);
				menu.play_audio();
				audio.stop_audio();
				
			}
		});
		pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(scene3);
				audio.pause();
			}
		});
		resume.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.setScene(scene2);
				audio.play_audio();
			}
		});
		
		
		primaryStage.setScene(scene1);
		primaryStage.setTitle("Plant vs Plant");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
