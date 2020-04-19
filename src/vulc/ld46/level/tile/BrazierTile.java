package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;

public class BrazierTile extends WallTile {

	// x, y sprite
	private int xs, ys;

	public BrazierTile(int id, int xs, int ys) {
		super(id, 6 + xs, ys);
		this.xs = xs;
		this.ys = ys;
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.GRASS_FLOOR.render(screen, level, xt, yt);
		super.render(screen, level, xt, yt);
		if(level.won && xs == 1 && ys == 1) {
			screen.renderSprite(Atlas.getTile(6, 2, 2, 2).getFlipped((level.ticks / 25) % 2 == 0, false),
			                    level.ticksSinceWon > 90 ? 0xff : 0xff * level.ticksSinceWon / 90,
			                    Level.tileToPos(xt - 1), Level.tileToPos(yt - 1));
		}
	}

}
