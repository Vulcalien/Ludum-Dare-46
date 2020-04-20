package vulc.ld46.level.entity;

import java.util.List;

import vulc.bitmap.Bitmap;
import vulc.ld46.Game;
import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.gfx.menu.LoseFireMenu;
import vulc.ld46.gfx.menu.YouDiedMenu;
import vulc.ld46.input.Keys;
import vulc.ld46.item.Item;
import vulc.ld46.item.WeaponItem;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.particle.AttackParticle;
import vulc.ld46.level.tile.Tile;
import vulc.ld46.sfx.Sounds;

public class Player extends Mob {

	public static final int FIRE_HP = 60 * 60; // ticks

	public WeaponItem weapon = null;

	public boolean hasFire = false;
	public int fireHp = 0;

	private int ticks = 0;
	private int lastAttack = -60;

	private int moveCount = 0;
	private boolean moving = false;
	private int invulnerable = 0;

	public Player() {
		super(-1, -1, 100);

		xr = 6;
		yr = 11;

		weapon = (WeaponItem) Item.SWORD_0;
	}

	public void tick() {
		ticks++;

		if(level.game.menu != null) return;

		if(invulnerable > 0) invulnerable--;

		int speed = 2;
		int xm = 0, ym = 0;

		if(Keys.w.isKeyDown()) ym -= speed;
		if(Keys.a.isKeyDown()) xm -= speed;
		if(Keys.s.isKeyDown()) ym += speed;
		if(Keys.d.isKeyDown()) xm += speed;

		move(xm, ym);

		if(xm != 0 || ym != 0) {
			moving = true;
			moveCount++;
		} else {
			moving = false;
		}

		// attack
		if(Keys.attack0.isPressed() || Keys.attack1.isPressed()) {
			int range = 14 + (weapon != null ? weapon.range : 0);
			// x attack radius, y ...
			int xar = 12, yar = 4;

			if(dir == 0) attack(x - xar, y - range - yar, x + xar, y - range + yar);
			else if(dir == 1) attack(x - range - yar, y - xar, x - range + yar, y + xar);
			else if(dir == 2) attack(x - xar, y + range - yar, x + xar, y + range + yar);
			else if(dir == 3) attack(x + range - yar, y - xar, x + range + yar, y + xar);
		}

		if(Keys.interact0.isPressed() || Keys.interact1.isPressed()) {
			int range = 18;

			if(dir == 0) interact(x, y - range);
			else if(dir == 1) interact(x - range, y);
			else if(dir == 2) interact(x, y + range);
			else if(dir == 3) interact(x + range, y);
		}

		if(hasFire) {
			fireHp--;
			if(fireHp < 0) loseFire();
		}
	}

	public void render(Screen screen) {
		// if invulnerable == 0, will render
		// if invulnerable != 0, will show "flash" effect
		if((invulnerable / 8) % 2 == 0) {
			Bitmap<Integer> sprite = null;
			if(moving) {
				if(dir == 0) sprite = Atlas.getEntity(3, 0).getFlipped((moveCount / 10) % 2 == 0, false);
				else if(dir == 1) sprite = Atlas.getEntity(1 + (moveCount / 10) % 2, 0).getFlipped(true, false);
				else if(dir == 2) sprite = Atlas.getEntity(0, 0).getFlipped((moveCount / 10) % 2 == 0, false);
				else if(dir == 3) sprite = Atlas.getEntity(1 + (moveCount / 10) % 2, 0).getFlipped(false, false);
				else return;
			} else {
				if(dir == 0) sprite = Atlas.getEntity(2, 1);
				else if(dir == 1) sprite = Atlas.getEntity(1, 1).getFlipped(true, false);
				else if(dir == 2) sprite = Atlas.getEntity(0, 1);
				else if(dir == 3) sprite = Atlas.getEntity(1, 1);
				else return;
			}
			screen.renderSprite(sprite, x - 12, y - 12);
		}

		if(Game.DEBUG) {
			screen.write("dir:" + dir
			             + "\nhp:" + hp
			             + "\ntutorial todo:" + level.tutorialTODO
			             + "\nlevel enemies:" + level.remainingEnemies,
			             0xffffff, 1, 1);
		}
	}

	public void attack(int x0, int y0, int x1, int y1) {
		// attack cooldown
		if(ticks - lastAttack < 15 + (weapon != null ? weapon.cooldown : 0)) return;
		lastAttack = ticks;

		int knockback = 12 + (weapon != null ? weapon.knockback : 0);
		int xk = 0, yk = 0;

		if(dir == 0) yk -= knockback;
		else if(dir == 1) xk -= knockback;
		else if(dir == 2) yk += knockback;
		else if(dir == 3) xk += knockback;

		List<Entity> entities = level.getEntities(x0, y0, x1, y1);
		for(Entity e : entities) {
			if(e == this) continue;

			if(e instanceof Mob) {
				Mob mob = (Mob) e;
				mob.damage(10 + (weapon != null ? weapon.damage : 0), xk, yk, this);
			}
		}
		level.addEntity(new AttackParticle(x0, y0, dir));
		Sounds.SWORD_ATTACK.play();
	}

	public void interact(int x, int y) {
		int xt = Level.posToTile(x);
		int yt = Level.posToTile(y);

		Tile tile = level.getTile(xt, yt);
		if(tile != null)
		    tile.interactOn(this, level, xt, yt);
	}

	public void touchedBy(Entity e) {
		if(e instanceof MeleeEnemy) {
			e.touchedBy(this);
		}
	}

	public void damage(int dmg, int xKnockback, int yKnockback, Entity attacker) {
		if(invulnerable == 0) {
			super.damage(dmg, xKnockback, yKnockback, attacker);
			invulnerable = 60;
			Sounds.HURT.play();
		}
	}

	public void die(Entity killer) {
		super.die(killer);
		level.game.menu = new YouDiedMenu(level.game);
	}

	private void loseFire() {
		level.game.menu = new LoseFireMenu(level.game);
	}

	public void addCoal() {
		fireHp = FIRE_HP;
		Sounds.FIRE.play();
	}

	public void eatFood() {
		hp += 30;
		if(hp > maxHp) hp = maxHp;

		Sounds.EAT_FOOD.play();
	}

	public void respawn() {
		this.hp = maxHp;
		this.fireHp = FIRE_HP;
	}

}
