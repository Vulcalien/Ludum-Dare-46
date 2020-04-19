package vulc.ld46.level.entity;

public abstract class Mob extends Entity {

	public int dir = 2;
	public int xKnockback, yKnockback;

	public int maxHp;
	public int hp;

	public Mob(int x, int y, int maxHp) {
		this.x = x;
		this.y = y;

		this.maxHp = maxHp;
		hp = maxHp;
	}

	public boolean[] move(int xm, int ym) {
		boolean[] result = super.move(xm, ym);

		if(xm < 0) dir = 1;
		else if(xm > 0) dir = 3;

		// ym's dir overrides xm's dir
		if(ym < 0) dir = 0;
		else if(ym > 0) dir = 2;

		if(xKnockback != 0 || yKnockback != 0) {
			super.move(xKnockback, yKnockback);
			xKnockback = yKnockback = 0;
		}

		return result;
	}

	public void damage(int dmg, int xKnockback, int yKnockback, Entity attacker) {
		hp -= dmg;
		if(hp <= 0) die(attacker);

		this.xKnockback += xKnockback;
		this.yKnockback += yKnockback;
	}

	public void die(Entity killer) {
		remove();
	}

	public boolean blocks(Entity e) {
		return true;
	}

	public boolean isBlockedBy(Entity e) {
		return true;
	}

}
