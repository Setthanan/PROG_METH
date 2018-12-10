package uiInterface;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.AttackPlant;
import logic.Plant;
import logic.Player;
import logic.SolarPower;

public class Table extends Pane {
	private final int H = 600;
	private final int W = 1440;
	private Canvas canvas;
	private final int box_w = W / (Player.collumn * 2);
	private final int box_h = H / Player.row;
	public final int col = Player.collumn * 2;
	public final int row = Player.row;
	private Tile[][] tiles;
	private Player p1, p2;

	public class Tile extends StackPane {
		private int i, j;
		private int dir;
		private Plant plant;
		private Rectangle border = new Rectangle(box_w, box_h);

		public Tile(int i, int j, Plant plant) {
			this.i = i;
			this.j = j;
			this.plant = plant;
			if (j >= col / 2 && plant != null) {
				this.dir = -1;

				if (this.plant instanceof AttackPlant) {
					((AttackPlant) this.plant).getBullet().setX(this.plant.getX());
				}
			} else
				this.dir = 1;
			border.setFill(Color.TRANSPARENT);
			border.setStroke(Color.TRANSPARENT);
			getChildren().addAll(border);
			setAlignment(Pos.CENTER);
			setTranslateY(i * box_h);
			setTranslateX(j * box_w);
			if (j < col / 2) {
				setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (event.isSecondaryButtonDown()) {
							p1.playerPlant[i][j] = null;
						}
					}
				});
			}
		}

		public void setTile(int i, int j, Plant plant) {
			this.i = i;
			this.j = j;
			this.plant = plant;
			if (j >= col / 2 && plant != null) {
				this.dir = -1;
				this.plant.setX(j * box_w);

			}
			if (this.plant instanceof AttackPlant) {
				((AttackPlant) this.plant).getBullet().setX(this.plant.getX());

			}
		}

		public Plant getPlant() {
			return this.plant;
		}

		public void drawPlantInTile(GraphicsContext gc) {
			if (plant == null)
				return;
			if (dir == -1) {
				gc.drawImage(plant.getImage(), j * box_w + box_w, i * box_h, -box_w, box_h);

			} else {
				gc.drawImage(plant.getImage(), j * box_w, i * box_h, box_w, box_h);

			}
		}

	}

	public Table(Player p1, Player p2) {
		this.tiles = new Tile[row][col];
		this.p1 = p1;
		this.p2 = p2;
		this.canvas = new Canvas(W, H);
		this.getChildren().add(canvas);
		updateTable();

	}

	public void updateTable() {
		for (int i = 0; i < row; i++) {
			int min = 0;
			for (int j = 0; j < col / 2; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Tile(i, j, null);
					getChildren().add(tiles[i][j]);
				}
				if (p1.playerPlant[i][j] != null) {
					if (!(p1.playerPlant[i][j].equals(tiles[i][j].getPlant()))) {
						tiles[i][j].setTile(i, j, p1.playerPlant[i][j]);

					}
				}
				if (p1.playerPlant[i][j] == null) {
					tiles[i][j].setTile(i, j, null);
				}

			}
			for (int j = col - 1; j >= col / 2; j--) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Tile(i, j, null);
					getChildren().add(tiles[i][j]);
				}
				if (p2.playerPlant[i][min] != null) {
					if (!(p2.playerPlant[i][min].equals(tiles[i][j].getPlant()))) {
						tiles[i][j].setTile(i, j, p2.playerPlant[i][min]);
					}

				}
				if (p2.playerPlant[i][min] == null) {
					tiles[i][j].setTile(i, j, null);
				}

				min++;

			}
		}

	}

	public void drawPlantInTable(GraphicsContext gc) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (tiles[i][j] != null) {
					(tiles[i][j]).drawPlantInTile(gc);
				}
			}
		}
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public Tile[][] getTile() {
		return tiles;
	}

	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}

	public void removeFromTile(int i, int j, SolarPower obj) {

		tiles[i][j].getChildren().remove(obj);
	}

	public void addToTile(int i, int j, SolarPower obj) {

		tiles[i][j].getChildren().add(obj);
	}

	public void resetTable(PlantStorage storage) {
		p1.resetPlayer();
		p2.resetPlayer();
		updateTable();
		storage.DragAll();
	}

}
