package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;
import vulc.ld46.level.entity.Player;

public class FireTile extends Tile {

	public FireTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.COBBLESTONE_FLOOR.render(screen, level, xt, yt);
		screen.renderSprite(Atlas.getTile(2, 1).getFlipped((level.ticks / 25) % 2 == 0, false),
		                    Level.tileToPos(xt), Level.tileToPos(yt));
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

	public boolean interactOn(Entity e, Level level, int xt, int yt) {
		level.player.hasFire = true;
		level.player.fireHp = Player.FIRE_HP;
		return true;
	}

}
