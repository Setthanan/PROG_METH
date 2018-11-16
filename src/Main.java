import java.awt.Rectangle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	Scene scene1,scene2;
	public static final int table_size = 1;
	public static final int WIDTH = 1038;
	public static final int HEIGHT = 779;
	private ImageView main_menu;
	private ImageView click;
	private Button start;
	private Button back;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		main_menu = new ImageView(new Image(ClassLoader.getSystemResource("CrusadersQuest1.png").toString()));
		click = new ImageView(new Image(ClassLoader.getSystemResource("click_to_start.gif").toString()));
		
		start = new Button();
		start.setGraphic(click);
		start.setStyle("-fx-background-color:transparent;");
		start.setOnAction(e->primaryStage.setScene(scene2));
		
		VBox buttonBox = new VBox();
		buttonBox.setPadding(new Insets(60, 60, 60, 60));
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.getChildren().add(start);
		
		StackPane root1 = new StackPane();
		root1.getChildren().addAll(main_menu,buttonBox);
		scene1 = new Scene(root1);
		
		back = new Button("back");
		back.setOnAction(e->primaryStage.setScene(scene1));
		
		StackPane root2 = new StackPane();
		root2.getChildren().addAll(back);
		scene2 = new Scene(root2,WIDTH,HEIGHT);
		
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	
	/*private static class MenuButton extends StackPane{
		private Text text;
		
		public MenuButton(String name) {
			text = new Text(name);
			text.setFill(Color.WHITE);
			
			
			
			getChildren().addAll(text);
		}
		
		
		
	}*/
	
	public static void main(String[]args) {
		launch(args);
	}
	

}
