package vulc.ld46.level.entity;

import java.awt.event.KeyEvent;
import java.util.List;

import vulc.bitmap.IntBitmap;
import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.InputHandler;
import vulc.ld46.input.InputHandler.Key;
import vulc.ld46.input.InputHandler.KeyType;
import vulc.ld46.level.entity.particle.AttackParticle;

public class Player extends Mob {

	private final InputHandler input;

	private final Key w, a, s, d,
	        attack, interact,
	        inventory;

	private int ticks = 0;
	private int lastAttack = -60;

	public Player(int x, int y) {
		super(x, y, 13);
		this.input = Game.INPUT;

		w = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_W);
		a = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_A);
		s = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_S);
		d = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_D);

		attack = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_L);
		interact = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_P);
		inventory = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_I);
		// TODO
		xr = yr = 12;
	}

	public void tick() {
		ticks++;

		int speed = 2;
		int xm = 0, ym = 0;

		if(w.isKeyDown()) ym -= speed;
		if(a.isKeyDown()) xm -= speed;
		if(s.isKeyDown()) ym += speed;
		if(d.isKeyDown()) xm += speed;

		move(xm, ym);

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
	}

	public void render(Screen screen) {
		// TODO
		screen.renderSprite(new IntBitmap(24, 24, 0xff0000), x - 12, y - 12);

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

}
