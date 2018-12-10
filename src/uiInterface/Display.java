package uiInterface;

import logic.*;

import java.sql.Time;

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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	private Player p1 = new Player(2, 2000, 0, 0);
	private Player p2 = new Player(20, 200000, 0, 0);
	private static final int H = 5;
	private static final int W = 12;
	public static final int table_size = 120;
	public static final int box_w = (W * table_size) / (Player.collumn * 2);
	public static final int box_h = (H * table_size) / Player.row;
	private PlantStorage storages;
	private Table table = new Table(p1, p2);
	private Container container = new Container(p1, p2);
	private RandomSpawn com = new RandomSpawn(p2);
	private Timeline timeline1, timeline2, timeline3, timeline4, timeline5;
	private ImageView main_menu, click, yard, exitBtn;
	private Button start, exit, back, pause, resume, restart;
	private Scene scene1, scene2, scene3, scene4, scene5;
	private Audio audio, menu, lose, winGame, stopGame;
	private ProgressBar enemyHpBar = new ProgressBar();

	public Display() {
		storages = new PlantStorage(table, p1);
		main_menu = new ImageView(new Image(ClassLoader.getSystemResource("pvp.png").toString()));
		click = new ImageView(new Image(ClassLoader.getSystemResource("xplay.png").toString()));
		yard = new ImageView(new Image(ClassLoader.getSystemResource("Background3.jpg").toString()));
		exitBtn = new ImageView(new Image(ClassLoader.getSystemResource("exit.png").toString()));
		audio = new Audio("background.wav");
		menu = new Audio("menu.wav");
		winGame = new Audio("game_end.wav");
		lose = new Audio("atebrains.wav");
		stopGame = new Audio("chomp.wav");
		menu.play_audio();
		back = new Button("MAIN MENU");
		restart = new Button("RESTART");
		pause = new Button("PAUSE");
		resume = new Button("RESUME");
		exit = new Button();
		exit.setGraphic(exitBtn);
		exit.setStyle("-fx-background-color:transparent;");
		start = new Button();
		start.setGraphic(click);
		start.setStyle("-fx-background-color:transparent;");
		enemyHpBar.setProgress(p2.getPlayerHp() / p2.getPlayerMaxHp());
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

		VBox enemyBox = new VBox();
		enemyBox.setAlignment(Pos.CENTER);
		enemyBox.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		enemyBox.getChildren().addAll(enemyHpBar);

		HBox playScene = new HBox();
		// playScene.setPrefSize((W+1) * table_size, H * table_size);
		// playScene.setPadding(new Insets(15, 15, 15, 15));
		playScene.setAlignment(Pos.CENTER);
		playScene.getChildren().addAll(storages, tile, enemyBox);

		HBox buttonBox2 = new HBox(10);
		buttonBox2.setAlignment(Pos.CENTER);
		buttonBox2.getChildren().addAll(pause, resume, back);

		VBox root2 = new VBox();
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

		StackPane lost = new StackPane();
		Label lostText = new Label("YOU LOST");
		lostText.setScaleX(5);
		lostText.setScaleY(5);
		lostText.setTextFill(Color.WHITE);
		VBox root4 = new VBox(10);
		root4.setAlignment(Pos.CENTER);
		root4.setTranslateY(200);

		root4.getChildren().add(restart);
		lost.getChildren().add(lostText);
		lost.getChildren().add(root4);
		lost.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		lost.setPrefSize(W * table_size, H * table_size);
		scene4 = new Scene(lost);

		StackPane win = new StackPane();
		Label winText = new Label("YOU WIN");
		winText.setScaleX(5);
		winText.setScaleY(5);
		winText.setTextFill(Color.WHITE);
		VBox root5 = new VBox(10);
		root5.setAlignment(Pos.CENTER);
		root5.setTranslateY(200);

		// root5.getChildren().add();
		win.getChildren().add(winText);
		win.getChildren().add(root5);
		win.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		win.setPrefSize(W * table_size, H * table_size);
		scene5 = new Scene(win);

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
				container.resetContainer(table);
				table.resetTable(storages);
				timeline1.playFromStart();
				timeline2.playFromStart();
				timeline3.playFromStart();
				timeline4.playFromStart();
				timeline5.playFromStart();

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
				timeline5.stop();

				/*
				 * primaryStage.close(); Platform.runLater(() -> new Display().start(new
				 * Stage()));
				 */
				primaryStage.setScene(scene1);
				menu.play_audio();
				audio.stop_audio();

			}
		});
		restart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline1.stop();
				timeline2.stop();
				timeline3.stop();
				timeline4.stop();
				timeline5.stop();

				/*
				 * primaryStage.close(); Platform.runLater(() -> new Display().start(new
				 * Stage()));
				 */
				primaryStage.setScene(scene1);
				menu.play_audio();
				audio.stop_audio();
				winGame.stop_audio();
				lose.stop_audio();

			}
		});
		pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline1.pause();
				timeline2.pause();
				timeline3.pause();
				timeline4.pause();
				timeline5.pause();
				primaryStage.setScene(scene3);
				audio.pause();
				stopGame.play_once();
			}
		});
		resume.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline1.play();
				timeline2.play();
				timeline3.play();
				timeline4.play();
				timeline5.play();
				primaryStage.setScene(scene2);
				audio.play_audio();
				stopGame.stop_audio();
			}
		});

		primaryStage.setScene(scene1);
		primaryStage.setTitle("Plant Vs Plant ");
		primaryStage.setResizable(false);
		primaryStage.show();

		EventHandler event1 = new EventHandler() {

			@Override
			public void handle(Event event) {
				com.addRand(2);
				com.updateToTable();
				container.addBulletContainer();
			}

		};
		EventHandler event2 = new EventHandler() {

			@Override
			public void handle(Event event) {

				table.updateTable();
				container.updateSolarPower(table);
				container.updateCollision();
				container.updateBulletMovement(1);
				p1.detectPlant();
				p2.detectPlant();
				container.updateOutOfRangeBullet();
				storages.updateStorage();

			}

		};
		EventHandler event3 = new EventHandler() {

			@Override
			public void handle(Event event) {
				container.addSolarPower(table);
				p1.setSolarPower(p1.getSunPower() + 10);
			}

		};
		EventHandler event4 = new EventHandler() {

			@Override
			public void handle(Event event) {
				table.getCanvas().getGraphicsContext2D().clearRect(0, 0, 1500, 600);
				if (storages.getProgressBar().getProgress() == 0) {
					timeline1.stop();
					timeline2.stop();
					timeline3.stop();
					timeline4.stop();
					timeline5.stop();
					primaryStage.setScene(scene4);
					lose.play_once();
					audio.stop_audio();
				}
				if (enemyHpBar.getProgress() == 0) {
					timeline1.stop();
					timeline2.stop();
					timeline3.stop();
					timeline4.stop();
					timeline5.stop();
					primaryStage.setScene(scene5);
					winGame.play_once();
					audio.stop_audio();

				}
				container.drawBullet(table.getCanvas().getGraphicsContext2D());
				container.drawSolarPowers(table.getCanvas().getGraphicsContext2D());
				table.drawPlantInTable(table.getCanvas().getGraphicsContext2D());
				
			}

		};
		EventHandler event5 = new EventHandler() {

			@Override
			public void handle(Event event) {
				enemyHpBar.setProgress(p2.getPlayerHp() / p2.getPlayerMaxHp());
			}

		};
		timeline1 = new Timeline();
		timeline2 = new Timeline();
		timeline3 = new Timeline();
		timeline4 = new Timeline();
		timeline5 = new Timeline();

		KeyFrame key1 = new KeyFrame(Duration.millis(1), event2);
		KeyFrame key2 = new KeyFrame(Duration.millis(10000), event1);
		KeyFrame key3 = new KeyFrame(Duration.millis(1), event4);
		KeyFrame key4 = new KeyFrame(Duration.millis(15000), event3);
		KeyFrame key5 = new KeyFrame(Duration.millis(15000), event5);
		timeline1.getKeyFrames().addAll(key1);
		timeline2.getKeyFrames().addAll(key2);
		timeline3.getKeyFrames().addAll(key3);
		timeline4.getKeyFrames().addAll(key4);
		timeline5.getKeyFrames().addAll(key5);

		timeline1.setCycleCount(Animation.INDEFINITE);
		timeline2.setCycleCount(Animation.INDEFINITE);
		timeline3.setCycleCount(Animation.INDEFINITE);
		timeline4.setCycleCount(Animation.INDEFINITE);
		timeline5.setCycleCount(Animation.INDEFINITE);

	}

	public static void main(String[] args) {

		launch(args);
	}
}
