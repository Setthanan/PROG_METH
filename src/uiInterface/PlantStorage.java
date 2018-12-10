package uiInterface;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GreenBean;
import logic.Plant;
import logic.Player;
import logic.StonePlant;
import logic.SunFlower;
import uiInterface.Table.Tile;

public class PlantStorage extends VBox {
	/// this class is suited for include error exception
	private ImageView Peashooter;
	public ImageView Sunflower;
	public ImageView Walnut;
	public ImageView Sun;
	private Player p1;
	private Table table;
	private ProgressBar hpBar;
	private Label solar,player,hp;

	public PlantStorage(Table table, Player p1) {
		super(10);
		this.table = table;
		this.p1 = p1;
		this.hpBar = new ProgressBar(p1.getPlayerHp() / p1.getPlayerMaxHp());
		hpBar.setPrefSize(100, 20);
		hpBar.setStyle("-fx-accent: red; ");
		super.setPadding(new Insets(25, 10, 30, 10));
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
		player = new Label("Player ");
		hp = new Label("Hp");
		this.solar = new Label(String.valueOf(p1.getSunPower()));
		player.setStyle("-fx-font-size:30; -fx-font-weight:BOLD;");
		solar.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		hp.setStyle("-fx-font-size:25; -fx-font-weight:BOLD;");
		Peashooter = new ImageView(new Image(ClassLoader.getSystemResource("peashooter.png").toString()));
		Sunflower = new ImageView(new Image(ClassLoader.getSystemResource("sunflower.png").toString()));
		Walnut = new ImageView(new Image(ClassLoader.getSystemResource("walnut.png").toString()));
		Sun = new ImageView(new Image(ClassLoader.getSystemResource("sun.gif").toString()));
		drag(Peashooter, "peashooter",new GreenBean());
		drag(Sunflower, "sunflower",new SunFlower());
		drag(Walnut, "walnut",new StonePlant());
		HBox hbox = new HBox();
		hbox.getChildren().addAll(Sun, solar);
		getChildren().addAll(player,hp, hpBar, hbox, Sunflower, Peashooter, Walnut);

	}

	public void drag(ImageView iv, String name,Plant plant) {
		iv.setCursor(Cursor.CLOSED_HAND);
		for (int i = 0; i < table.row; i++) {
			for (int j = 0; j < table.col / 2; j++) {
				
				Tile tiles = (table.getTile())[i][j];
				iv.setOnDragDetected(new EventHandler<MouseEvent>() {
					
					@Override
					public void handle(MouseEvent event) {
						System.out.println("grab");
						if(p1.getSunPower() < plant.getCost()) {
							return;
						}
						
						
						Dragboard db = iv.startDragAndDrop(TransferMode.MOVE);
						ClipboardContent content = new ClipboardContent();
						content.putString(name);
						db.setContent(content);
						db.setDragView(iv.getImage(),50,50);
						event.consume();
					}

				});
				if (tiles == null)
					return;
				tiles.setOnDragOver(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						if (event.getGestureSource() != tiles
								&& event.getDragboard().hasContent(DataFormat.PLAIN_TEXT)) {
							event.acceptTransferModes(TransferMode.MOVE);
						}
						event.consume();
					}
				});

				tiles.setOnDragEntered(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						if (event.getGestureSource() != tiles
								&& event.getDragboard().hasContent(DataFormat.PLAIN_TEXT)) {
							System.out.println("enter!!!");
						}
						event.consume();
					}
				});

				tiles.setOnDragExited(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						System.out.println("exit...");
						event.consume();
					}
				});

				tiles.setOnDragDropped(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						Dragboard db = event.getDragboard();
						boolean success = false;
						int i = (int) ((event.getSceneY() - 15) / 120);
						int j = (int) ((event.getSceneX() - 132) / 120);
						System.out.println(event.getSceneX() + " " + event.getSceneY() + "," + i + " " + j);
						System.out.println(db.getString());

						if (db.hasString()) {
							if (db.getContent(DataFormat.PLAIN_TEXT).equals("peashooter")) {
								p1.spawnPlant(new GreenBean(), i, j);
							}
							if (db.getContent(DataFormat.PLAIN_TEXT).equals("sunflower")) {
								p1.spawnPlant(new SunFlower(), i, j);
							}
							if (db.getContent(DataFormat.PLAIN_TEXT).equals("walnut")) {
								p1.spawnPlant(new StonePlant(), i, j);

							}
							success = true;
						}
						event.setDropCompleted(success);
						event.consume();
					}
				});
			}
		}
	}

	public void updateHpBar() {
		if (p1.getPlayerHp() <= 0) {
			hpBar.setProgress(0);
			return;
		}
		hpBar.setProgress(p1.getPlayerHp() / p1.getPlayerMaxHp());
	}
	
	public void resetProgressBar() {
		if(hpBar.getProgress() == 0) {
			hpBar.setProgress(p1.getPlayerMaxHp());
		}
	}

	public void updateSolarPower() {
		solar.setText(String.valueOf(p1.getSunPower()));
	}

	public void updateStorage() {
		updateHpBar();
		updateSolarPower();
	}
	public ProgressBar getProgressBar() {
		return this.hpBar;
	}
	public void DragAll() {
		drag(Peashooter, "peashooter",new GreenBean());
		drag(Sunflower, "sunflower",new SunFlower());
		drag(Walnut, "walnut",new StonePlant());
	}
}
