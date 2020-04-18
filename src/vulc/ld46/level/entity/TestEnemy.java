package vulc.ld46.level.entity;

import vulc.bitmap.IntBitmap;
import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;

public class TestEnemy extends Mob {

	public TestEnemy(int x, int y) {
		super(x, y, 100);
		xr = yr = 12;
		hp = 100;
	}

	public void tick() {
		move(0, 0);
	}

	public void render(Screen screen) {
		// TODO
		screen.renderSprite(new IntBitmap(24, 24, 0xffff00), x - 12, y - 12);

		if(Game.DEBUG) {
		}
	}

}
