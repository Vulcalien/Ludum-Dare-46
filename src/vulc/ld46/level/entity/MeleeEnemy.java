package vulc.ld46.level.entity;

import vulc.bitmap.IntBitmap;
import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;

public class MeleeEnemy extends Mob {

	public MeleeEnemy(int x, int y) {
		super(x, y, 100);
		xr = yr = 12;
		hp = 100;
	}

	public void tick() {
		int speed = 1;
		int xm = 0, ym = 0;

		Player player = level.player;
		if(player != null) {
			if(player.x < x) xm -= speed;
			else if(player.x > x) xm += speed;

			if(player.y < y) ym -= speed;
			else if(player.y > y) ym += speed;
		}
		move(xm, ym);
	}

	public void render(Screen screen) {
		// TODO
		screen.renderSprite(new IntBitmap(24, 24, 0xffff00), x - 12, y - 12);

		if(Game.DEBUG) {
		}
	}

	public void touchedBy(Entity e) {
		int knockback = 24;
		int xk = 0, yk = 0;

		if(dir == 0) yk -= knockback;
		else if(dir == 1) xk -= knockback;
		else if(dir == 2) yk += knockback;
		else if(dir == 3) xk += knockback;

		if(e instanceof Player) {
			Player player = (Player) e;
			player.damage(10, xk, yk, this);
		}
	}

}
