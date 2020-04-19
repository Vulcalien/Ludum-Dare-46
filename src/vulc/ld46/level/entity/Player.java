package vulc.ld46.level.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import vulc.bitmap.Bitmap;
import vulc.ld46.Game;
import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.InputHandler;
import vulc.ld46.input.InputHandler.Key;
import vulc.ld46.input.InputHandler.KeyType;
import vulc.ld46.item.Item;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.particle.AttackParticle;
import vulc.ld46.level.tile.Tile;

public class Player extends Mob {

	public static final int FIRE_HP = 60 * 60; // ticks

	private final InputHandler input;

	private final Key w, a, s, d,
	        attack, interact;

	public Item weapon = null;

	public boolean hasFire = false;
	public int fireHp = 0;

	private int ticks = 0;
	private int lastAttack = -60;

	private int moveCount = 0;
	private boolean moving = false;
	private int invulnerable = 0;

	public Player() {
		super(-1, -1, 13);
		this.input = Game.INPUT;

		w = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_W);
		a = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_A);
		s = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_S);
		d = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_D);

		attack = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_L);
		interact = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_P);

		xr = 6;
		yr = 11;
	}

	public void tick() {
		ticks++;

		if(invulnerable > 0) invulnerable--;

		int speed = 2;
		int xm = 0, ym = 0;

		if(w.isKeyDown()) ym -= speed;
		if(a.isKeyDown()) xm -= speed;
		if(s.isKeyDown()) ym += speed;
		if(d.isKeyDown()) xm += speed;

		move(xm, ym);

		if(xm != 0 || ym != 0) {
			moving = true;
			moveCount++;
		} else {
			moving = false;
		}

		// attack
		if(attack.isPressed()) {
			int range = 12;
			// x attack radius, y ...
			int xar = 12, yar = 4;

			if(dir == 0) attack(x - xar, y - range - yar, x + xar, y - range + yar);
			else if(dir == 1) attack(x - range - yar, y - xar, x - range + yar, y + xar);
			else if(dir == 2) attack(x - xar, y + range - yar, x + xar, y + range + yar);
			else if(dir == 3) attack(x + range - yar, y - xar, x + range + yar, y + xar);
		}

		if(interact.isPressed()) {
			int range = 18;

			if(dir == 0) interact(x, y - range);
			else if(dir == 1) interact(x - range, y);
			else if(dir == 2) interact(x, y + range);
			else if(dir == 3) interact(x + range, y);
		}

		if(hasFire) {
			fireHp--;
			if(fireHp < 0) lose();
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
			screen.write("dir:" + dir + "\n"
			             + "hp:" + hp,
			             0xffffff, 1, 1);
		}
	}

	public void attack(int x0, int y0, int x1, int y1) {
		// attack delay
		if(ticks - lastAttack < 15) return;
		lastAttack = ticks;

		int knockback = 10;
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
				mob.damage(10, xk, yk, this);
			}
		}
		level.addEntity(new AttackParticle(x0, y0, dir));
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
		}
	}

	private void lose() {
	}

	public void addCoal() {
		fireHp = FIRE_HP;
	}

	public void eatFood() {
		// TODO
	}

}
