package logic;
import uiInterface.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Player extends GameObject {
	public Plant[][] playerPlant;
	public final static int row = 5;
	public final static int collumn = 6;
	private double playerHp;
	private double playerMaxHp;
	private int sunPower;
	public static double time;
	public Player(double playerMaxHp,int sunPower ,int x ,int y){
		super(x, y, ID.Player);
		this.playerPlant = new Plant[row][collumn];
		if(playerMaxHp < 1) playerMaxHp = 1;
		this.playerHp = playerMaxHp;
		this.playerMaxHp = playerMaxHp;
		this.sunPower = sunPower;
	}
	//Let P1.start(P2); P2.start(P1);    
	//then detect what plant Life < 0 then delete from game
	//this game will end when one of some player dies
	//if the both player die game will be draw
	public void start(Player other) {
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < collumn ; j++) {
				Plant obj = playerPlant[i][j];
				if(obj instanceof Plant) {
					if(obj.isAlive()) {
						//check type and set plant ready to fire
						if(obj instanceof AttackPlant) {
							Plant target = this.target(i,other);
							if(target == null) {
								//if there are no plant,let damage player
								other.directDamage(((AttackPlant) obj).calDamage(this));
								System.out.println("Direct");
							}
							else {
								((AttackPlant) obj).fireAt(target);
								System.out.println("target : "+target);
							}
						}
					/*	if(obj instanceof SpecialPlant) {
							if()
						}*/
					}
				}
			}
		}
	}
	public void detectPlant() {
		for(int i=0; i < this.row ; i++) {
			for(int j = 0; j < this.collumn ; j++) {
				deletePlant(i, j);
			}
		}
	}
	public Plant target(int i,Player other) {
		Plant obj = null;
		for(int j = 0 ; j < collumn ; j++) {
			if(other.playerPlant[i][j] instanceof Plant) {
				obj = other.playerPlant[i][j];
				return obj;
			}
		}
		return obj;
	}
	public void spawnPlant(Plant obj ,int i , int j) {
		if(this.sunPower <= obj.getCost()) {
			System.out.println("out of energy");
			return;
		}
		//prevent sunPower be negative ;
		if(playerPlant[i][j] instanceof Plant) return;
		//prevent input the same place
		playerPlant[i][j] = obj;
		playerPlant[i][j].setX(j*Display.box_w);
		playerPlant[i][j].setY(i*Display.box_h);
		this.sunPower -= obj.getCost();
		System.out.println("Plant was spawned");
	}
	public void increaseSunPower() {
		this.sunPower += 1;
	}
	public void directDamage(double damage) {
		if(damage < 0) damage = 0;
		this.playerHp -= damage;
	}
	public boolean isPlayerAlive() {
		if(this.playerHp > 0) return true;
		else return false;
	}
	public boolean deletePlant(int i,int j) {
		//throw exception for out of boundary,
		if( 0 > i || i >= row || 0 > j || j >= collumn ) return false;		
		if(playerPlant[i][j] instanceof Plant) {
				if( !(playerPlant[i][j].isAlive())) {
					playerPlant[i][j] = null;
				}
				

			return true;
		}

		else return false;
	}
	public Plant[][] getPlayerPlant() {
		return playerPlant;
	}
	public double getPlayerHp() {
		return playerHp;
	}
	public double getPlayerMaxHp() {
		return playerMaxHp;
	}
	public int getSunPower() {
		return sunPower;
	}
	public Plant getPlant(int i,int j) {
		if( 0 > i || i >= row || 0 > j || j >= collumn ) return null; // throw exception
		if(playerPlant[i][j] == null) return null;
		return playerPlant[i][j];
	}
	@Override
	public void update(double time) {
		//hp check
		
	}

	@Override
	public void render(GraphicsContext gc) {
		//gc.drawImage(image, x, y);
	}
	private boolean intersects(GameObject other) {
		return other.getBounds().intersects(this.getBounds()); //check when bullet hit player
		
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D(x,y,Display.box_w,Display.box_h);//player size
	}
	
}
