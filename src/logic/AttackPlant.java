package logic;

public abstract class AttackPlant extends Plant   {
	protected double hp;
	protected double maxHp;
	protected Bullet bullet;
	public AttackPlant(String name,int maxHp , String elemental ,int cost, int x, int y) {
		super("Attack", name, 1 , elemental ,cost, x, y);
		if(maxHp < 1) maxHp = 1;
		this.hp = maxHp;
		this.maxHp = maxHp;
		this.bullet = new Bullet(x, y, true); // x ,y position
		
	}
	@Override
	public void takeDamage(double damage) {
		if(damage < 0) damage = 0;
		this.hp -= damage;
		if(this.hp <= 0) {
			this.life -= 1;
			if(this.isAlive()) {
				this.hp = this.maxHp;
			}
			else {
				this.hp = 0;
			}
		}
		
	
	}
	
	public abstract double calDamage(Plant other);
	public abstract double calDamage(Player other);
	public boolean fireAt(Plant other) {
		if(this.bullet.isReady()) {
			other.takeDamage(calDamage(other));
			return true;
		}
		else {
			return false;
		}
	}
	public double getHp() {
		return hp;
	}
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	public void setBullet(int x,int y) {
		this.bullet.setX(x);
		this.bullet.setY(y);
	}
	public Bullet getBullet() {
		return bullet;
	}
	public double getMaxHp() {
		return maxHp;
	}
		  
	
}
