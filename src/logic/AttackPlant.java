package logic;

public abstract class AttackPlant extends Plant {
	protected double hp;
	protected double maxHp;
	protected Bullet bullet;

	public AttackPlant(String name, double maxHp, String elemental, int cost, int x, int y) {
		super("Attack", name, 1, elemental, cost, x, y);
		if (maxHp < 1)
			maxHp = 1;
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.bullet = new Bullet(this, x, y); // x ,y position

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

	public abstract double calDamage(Plant other);

	public abstract double calDamage(Player other);

	public double getHp() {
		return hp;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public double getMaxHp() {
		return maxHp;
	}

}
