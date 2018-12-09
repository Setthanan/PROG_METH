package logic;

public abstract class SpecialPlant extends Plant {
	protected double maxHp;
	protected double hp;

	public SpecialPlant(String name, String elemental, int maxHp, int cost, int x, int y) {
		super("Special", name, 1, elemental, cost, x, y);
		if (maxHp < 0)
			maxHp = 0;
		this.hp = maxHp;
		this.maxHp = maxHp;
	}

	@Override
	public void takeDamage(double damage) {
		if (damage < 0)
			damage = 0;
		this.hp -= damage;
		if (this.hp <= 0) {
			this.life -= 1;
			if (this.isAlive()) {
				this.hp = this.maxHp;
			} else {
				this.hp = 0;
			}
		}

	}

	public double getHp() {
		return hp;
	}

	public double getMaxHp() {
		return maxHp;
	}

}
