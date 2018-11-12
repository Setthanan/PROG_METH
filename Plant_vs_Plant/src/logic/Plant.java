package logic;

import javafx.scene.image.ImageView;

public abstract class Plant extends GameObject implements Elemental, Cloneable {
	protected int cost;
	protected String plantKind;
	protected String name;
	protected int life;
	protected String elemental;

	public Plant(String plantKind,String name,int life,String elemental,int cost, int x ,int y) {
		super(x,y,ID.Plant);
		this.plantKind = plantKind;
		this.name = name;
		if(life < 1 ) life = 1;
		this.life = life;
		this.elemental = elemental;
		if (cost < 0) cost = 0;
		this.cost = cost;

		
	}
	public boolean isAlive() {
		if(this.life > 0) return true;
		else return false;
	}
	public double calElemental(Plant other) {
		// for Damage calculated
		if (this.elemental.equals(other.elemental)) {
			return 1;
		}
		else {
			if(this.elemental.equals("fire")) {
				if( other.elemental.equals("water")) {
					return 0.5;
				}
				if( other.elemental.equals("wind")) {
					return 2;
				}
				else {
					return 1;
				}
			}
			if(this.elemental.equals("water")) {
				if( other.elemental.equals("wind")) {
					return 0.5;
				}
				if( other.elemental.equals("fire")) {
					return 2;
				}
				else {
					return 1;
				}
			}
			if(this.elemental.equals("wind")) {
				if( other.elemental.equals("fire")) {
					return 0.5;
				}
				if( other.elemental.equals("water")) {
					return 2;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
	}
	public abstract void takeDamage(double d);
	public String getPlantKind() {
		return plantKind;
	}
	public String getName() {
		return name;
	}
	public int getLife() {
		return life;
	}
	public int getCost() {
		return cost;
	}
	public String getElemental() {
		return elemental;
	}
	
}	
