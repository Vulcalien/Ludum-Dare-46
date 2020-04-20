package vulc.ld46.item;

public class WeaponItem extends Item {

	public final int damage;
	public final int range;
	public final int cooldown;
	public final int knockback;

	public WeaponItem(int damage, int range, int cooldown, int knockback) {
		this.damage = damage;
		this.range = range;
		this.cooldown = cooldown;
		this.knockback = knockback;
	}

}
