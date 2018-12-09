package logic;

import uiInterface.*;

public class Player {
	public Plant[][] playerPlant;
	public final static int row = 5;
	public final static int collumn = 6;
	private double playerHp;
	private double playerMaxHp;
	private int startSunpower;
	private int sunPower;

	public Player(double playerMaxHp, int sunPower, int x, int y) {
		this.playerPlant = new Plant[row][collumn];
		if (playerMaxHp < 1)
			playerMaxHp = 1;
		this.playerHp = playerMaxHp;
		this.playerMaxHp = playerMaxHp;
		this.sunPower = sunPower;
		this.startSunpower = sunPower;
	}
	// detectPlant do delete what plant Life < 0 then delete from game
	// this game will end when one of some player dies
	// if the both player die game will be draw

	public void detectPlant() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < collumn; j++) {
				deletePlant(i, j);
			}
		}
	}

	public Plant target(int i, Player other) {
		Plant obj = null;
		for (int j = 0; j < collumn; j++) {
			if (other.playerPlant[i][j] instanceof Plant) {
				obj = other.playerPlant[i][j];
				return obj;
			}
		}
		return obj;
	}

	public void spawnPlant(Plant obj, int i, int j) {
		if (this.sunPower <= obj.getCost()) {
			System.out.println("out of energy");
			return;
		}
		// prevent sunPower be negative ;
		if (playerPlant[i][j] instanceof Plant)
			return;
		// prevent input the same place
		playerPlant[i][j] = obj;
		playerPlant[i][j].setX(j * Display.box_w);
		playerPlant[i][j].setY(i * Display.box_h);
		this.sunPower -= obj.getCost();
		System.out.println("Plant was spawned");
	}

	public void increaseSunPower() {
		this.sunPower += 1;
	}

	public void directDamage(double damage) {
		if (damage < 0)
			damage = 0;
		this.playerHp -= damage;
		if(this.playerHp <0) this.playerHp = 0;
	}

	public boolean isPlayerAlive() {
		if (this.playerHp > 0)
			return true;
		else
			return false;
	}

	public boolean deletePlant(int i, int j) {
		// throw exception for out of boundary,
		if (0 > i || i >= row || 0 > j || j >= collumn)
			return false;
		if (playerPlant[i][j] instanceof Plant) {
			if (!(playerPlant[i][j].isAlive())) {
				playerPlant[i][j] = null;
			}

			return true;
		}

		else
			return false;
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

	public void setSolarPower(int power) {
		this.sunPower = power;
	}

	public void resetPlayer() {
		this.playerPlant = new Plant[row][collumn];
		this.playerHp = playerMaxHp;
		this.sunPower = startSunpower;
	}
}
