package logic;

import uiInterface.*;

public class Player {
	public Plant[][] playerPlant;
	public final static int row = 5;
	public final static int collumn = 6;
	private double playerHp;
	private double playerMaxHp;
	private int startSolarPower;
	private int solarPower;

	public Player(double playerMaxHp, int solarPower) {
		this.playerPlant = new Plant[row][collumn];
		if (playerMaxHp < 1)
			playerMaxHp = 1;
		if (solarPower < 0)
			solarPower = 0;
		this.playerHp = playerMaxHp;
		this.playerMaxHp = playerMaxHp;
		this.solarPower = solarPower;
		this.startSolarPower = solarPower;
	}
	// detectPlant do delete what plant Life < 0 then delete from game
	// this game will end when one of some player dies

	public void detectPlant() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < collumn; j++) {
				deletePlant(i, j);
			}
		}
	}

	public void spawnPlant(Plant obj, int i, int j) {
		if (this.solarPower <= obj.getCost()) {
			System.out.println("out of energy");
			return;
		}
		// prevent sunPower be negative ;
		if (playerPlant[i][j] != null)
			return;
		// prevent input the same place
		playerPlant[i][j] = obj;
		playerPlant[i][j].setX(j * Display.box_w);
		playerPlant[i][j].setY(i * Display.box_h);
		this.solarPower -= obj.getCost();
		System.out.println("Plant was spawned");
	}

	public void directDamage(double damage) {
		if (damage < 0)
			damage = 0;
		this.playerHp -= damage;
		if (this.playerHp < 0)
			this.playerHp = 0;
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

	public int getSolarPower() {
		return solarPower;
	}

	public void setSolarPower(int power) {
		this.solarPower = power;
	}

	public void resetPlayer() {
		this.playerPlant = new Plant[row][collumn];
		this.playerHp = playerMaxHp;
		this.solarPower = startSolarPower;
	}
}
