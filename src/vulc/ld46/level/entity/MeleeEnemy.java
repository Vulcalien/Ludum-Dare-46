package vulc.ld46.level.entity;

import vulc.bitmap.Bitmap;
import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.sfx.Sounds;

public class MeleeEnemy extends Mob {

	private int moveCount = 0;
	private boolean moving = false;

	private int confusedTime = 0;

	public MeleeEnemy(int xt, int yt) {
		super(Level.tileToPos(xt) + Level.T_SIZE / 2, Level.tileToPos(yt) + Level.T_SIZE / 2,
		      100);

		xr = 6;
		yr = 11;
	}

	public void tick() {
		if(confusedTime > 0) {
			confusedTime--;
			move(0, 0);
			return;
		}

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

		if(xm != 0 || ym != 0) {
			moving = true;
			moveCount++;
		} else {
			moving = false;
		}
	}

	public void render(Screen screen) {
		Bitmap<Integer> sprite = null;
		if(moving) {
			if(dir == 0) sprite = Atlas.getEntity(3, 6).getFlipped((moveCount / 10) % 2 == 0, false);
			else if(dir == 1) sprite = Atlas.getEntity(1 + (moveCount / 10) % 2, 6).getFlipped(true, false);
			else if(dir == 2) sprite = Atlas.getEntity(0, 6).getFlipped((moveCount / 10) % 2 == 0, false);
			else if(dir == 3) sprite = Atlas.getEntity(1 + (moveCount / 10) % 2, 6).getFlipped(false, false);
			else return;
		} else {
			if(dir == 0) sprite = Atlas.getEntity(2, 7);
			else if(dir == 1) sprite = Atlas.getEntity(1, 7).getFlipped(true, false);
			else if(dir == 2) sprite = Atlas.getEntity(0, 7);
			else if(dir == 3) sprite = Atlas.getEntity(1, 7);
			else return;
		}
		screen.renderSprite(sprite, x - 12, y - 12);
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
			player.damage(9, xk, yk, this);
		}
	}

	public void die(Entity killer) {
		super.die(killer);
		Sounds.ENEMY_DIE.play();
		level.remainingEnemies--;
	}

	public void damage(int dmg, int xKnockback, int yKnockback, Entity attacker) {
		super.damage(dmg, xKnockback, yKnockback, attacker);
		confusedTime = 15;
	}

}
