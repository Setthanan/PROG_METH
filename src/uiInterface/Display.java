package uiInterface;

import logic.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display extends Application {

	// StackPane root = new StackPane();
	private Player p1 = new Player(200, 2000, 0, 0);
	private Player p2 = new Player(200, 200000, 0, 0);
	private Counter c1 = new Counter(0, 500);
	private Counter c2 = new Counter(600, 1100);
	private static final int H = 5;
	private static final int W = 12;
	public static final int table_size = 120;
	public static final int box_w = (W * table_size) / (Player.collumn * 2);
	public static final int box_h = (H * table_size) / Player.row;
	private boolean goUp1, goDown1, goLeft1, goRight1, digit1 = false;
	private boolean goUp2, goDown2, goLeft2, goRight2, num1 = false;
	private PlantStorage storages;
	private Table table = new Table(p1, p2);
	private Container container = new Container(p1, p2);
	private RandomSpawn com = new RandomSpawn(p2);
	private Timeline timeline1, timeline2, timeline3, timeline4;
	private ImageView main_menu, click, yard, exitBtn;
	private Button start, exit, back, pause, resume;
	private Scene scene1, scene2, scene3;
	private Audio audio, menu;

	public Display() {
		storages = new PlantStorage(table, p1);
		main_menu = new ImageView(new Image(ClassLoader.getSystemResource("pvp.png").toString()));
		click = new ImageView(new Image(ClassLoader.getSystemResource("xplay.png").toString()));
		yard = new ImageView(new Image(ClassLoader.getSystemResource("Background3.jpg").toString()));
		exitBtn = new ImageView(new Image(ClassLoader.getSystemResource("exit.png").toString()));
		audio = new Audio("background.wav");
		menu = new Audio("menu.wav");
		menu.play_audio();
		back = new Button("MAIN MENU");
		pause = new Button("PAUSE");
		resume = new Button("RESUME");
		exit = new Button();
		exit.setGraphic(exitBtn);
		exit.setStyle("-fx-background-color:transparent;");
		start = new Button();
		start.setGraphic(click);
		start.setStyle("-fx-background-color:transparent;");
	}

	/*
	 * public void tick() { handler.tick(); }
	 */
	@Override
	public void start(Stage primaryStage) {

		HBox buttonBox = new HBox(20);
		buttonBox.setPadding(new Insets(20, 20, 20, 20));
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.getChildren().addAll(start, exit);

		VBox root1 = new VBox();
		root1.setAlignment(Pos.CENTER);
		root1.setPrefSize((W + 1) * table_size, H * table_size);
		root1.getChildren().addAll(main_menu, buttonBox);
		scene1 = new Scene(root1);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(table);

		StackPane tile = new StackPane();
		tile.getChildren().addAll(yard, vbox);

		HBox playScene = new HBox();
		playScene.setPrefSize(W * table_size, H * table_size);
		// playScene.setPadding(new Insets(15, 15, 15, 15));
		playScene.getChildren().addAll(storages, tile);

		HBox buttonBox2 = new HBox(10);
		buttonBox2.setAlignment(Pos.CENTER_RIGHT);
		buttonBox2.getChildren().addAll(pause, resume, back);

		VBox root2 = new VBox(5);
		root2.setPadding(new Insets(15, 15, 15, 15));
		root2.getChildren().addAll(playScene, buttonBox2);
		scene2 = new Scene(root2);

		VBox root3 = new VBox(10);
		root3.setAlignment(Pos.CENTER);
		root3.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))); // *****change background*****
		root3.setPrefSize(W * table_size, H * table_size);
		root3.setPadding(new Insets(15, 15, 15, 15));
		root3.getChildren().addAll(resume, back);
		scene3 = new Scene(root3);

		start.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				start.setStyle("-fx-background-color:Lightgreen;");

			}
		});
		start.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				start.setStyle("-fx-background-color:transparent;");

			}
		});
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				table.resetTable();
				container.resetContainer();
				timeline1.playFromStart();
				timeline2.playFromStart();
				timeline3.playFromStart();
				timeline4.playFromStart();
				primaryStage.setScene(scene2);
				audio.play_audio();
				menu.stop_audio();
			}
		});

		exit.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color:lightpink;");

			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exit.setStyle("-fx-background-color:transparent;");

			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline1.stop();
				timeline2.stop();
				timeline3.stop();
				timeline4.stop();
				/*primaryStage.close();
				Platform.runLater(() -> new Display().start(new Stage()));
				*/
				primaryStage.setScene(scene1);
				// menu.play_audio();
				audio.stop_audio();

			}
		});
		pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline1.pause();
				timeline2.pause();
				timeline3.pause();
				timeline4.pause();
				primaryStage.setScene(scene3);
				audio.pause();
			}
		});
		resume.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline1.play();
				timeline2.play();
				timeline3.play();
				timeline4.play();
				primaryStage.setScene(scene2);
				audio.play_audio();
			}
		});

		primaryStage.setScene(scene1);
		primaryStage.setTitle("Plant Vs Plant ");
		primaryStage.setResizable(false);
		primaryStage.show();

		scene2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				System.out.println(event.getCode());
				switch (event.getCode()) {
				case UP:
					c2.moveCounter(0, -1);
					break;
				case DOWN:
					c2.moveCounter(0, 1);
					break;
				case LEFT:
					goLeft1 = true;
					break;
				case RIGHT:
					goRight1 = true;
					break;
				case W:
					goUp2 = true;
					break;
				case S:
					goDown2 = true;
					break;
				case A:
					goLeft2 = true;
					break;
				case D:
					goRight2 = true;
					break;
				case DIGIT1:
					digit1 = true;
					break;
				case NUMPAD1:
					num1 = true;
					break;
				}
			}

		});
		scene2.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goUp1 = false;
					break;
				case DOWN:
					goDown1 = false;
					break;
				case LEFT:
					goLeft1 = false;
					break;
				case RIGHT:
					goRight1 = false;
					break;
				case W:
					goUp2 = false;
					break;
				case S:
					goDown2 = false;
					break;
				case A:
					goLeft2 = false;
					break;
				case D:
					goRight2 = false;
					break;
				case DIGIT1:
					digit1 = false;
					break;
				case NUMPAD1:
					num1 = false;
					break;
				}
			}
		});

		EventHandler event = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				if (goUp1)
					c2.moveCounter(0, -1);
				if (goDown1)
					c2.moveCounter(0, 1);
				if (goLeft1)
					c2.moveCounter(-1, 0);
				if (goRight1)
					c2.moveCounter(1, 0);
				if (goUp2)
					;
				if (goDown2)
					;
				if (goLeft2)
					c1.moveCounter(-1, 0);
				if (goRight2)
					c1.moveCounter(1, 0);
				if (digit1) {

					for (int i = 0; i < Player.row; i++) {
						for (int j = 0; j < Player.collumn; j++) {

							p2.spawnPlant(new GreenBean(), i, j);
						}
					}

				}
				if (num1) {

				}

			}

		};
		EventHandler event2 = new EventHandler() {

			@Override
			public void handle(Event event) {
				// com.addRand(3);
				// com.updateToTable();
				container.addSolarPower(table);
				container.addBulletContainer();
			}

		};
		EventHandler event3 = new EventHandler() {

			@Override
			public void handle(Event event) {

				table.updateTable();
				container.updateSolarPower(table);
				container.updateCollision();
				container.updateBulletMovement(1);
				p1.detectPlant();
				p2.detectPlant();
				container.updateOutOfRangeBullet(p1);
				storages.updateStorage();

			}

		};
		EventHandler event4 = new EventHandler() {

			@Override
			public void handle(Event event) {

			}

		};
		EventHandler event5 = new EventHandler() {

			@Override
			public void handle(Event event) {
				table.getCanvas().getGraphicsContext2D().clearRect(0, 0, 1500, 600);

				container.drawBullet(table.getCanvas().getGraphicsContext2D());
				table.drawPlantInTable(table.getCanvas().getGraphicsContext2D());
			}

		};
		timeline1 = new Timeline();
		timeline2 = new Timeline();
		timeline3 = new Timeline();
		timeline4 = new Timeline();

		KeyFrame key1 = new KeyFrame(Duration.millis(100), event);
		KeyFrame key2 = new KeyFrame(Duration.millis(10000), event2);
		KeyFrame key3 = new KeyFrame(Duration.millis(10), event3);
		KeyFrame key4 = new KeyFrame(Duration.millis(10000), event4);
		KeyFrame key5 = new KeyFrame(Duration.millis(1), event5);
		timeline1.getKeyFrames().addAll(key1);
		timeline2.getKeyFrames().addAll(key2);
		timeline3.getKeyFrames().addAll(key3);
		timeline4.getKeyFrames().addAll(key5);

		timeline1.setCycleCount(Animation.INDEFINITE);
		timeline2.setCycleCount(Animation.INDEFINITE);
		timeline3.setCycleCount(Animation.INDEFINITE);
		timeline4.setCycleCount(Animation.INDEFINITE);

		timeline1.play();
		timeline2.play();
		timeline3.play();
		timeline4.play();

	}

	public static void main(String[] args) {

		launch(args);
	}
}
