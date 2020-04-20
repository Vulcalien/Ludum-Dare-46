package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;
import vulc.ld46.level.entity.Player;

public class CoalTile extends Tile {

	public CoalTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.COBBLESTONE_FLOOR.render(screen, level, xt, yt);
		screen.renderSprite(Atlas.getTile(1, 1), Level.tileToPos(xt), Level.tileToPos(yt));
		if(level.getData(xt, yt) == 0) {
			screen.renderSprite(Atlas.getItem(8, 9), Level.tileToPos(xt) + 4, Level.tileToPos(yt));
		}
	}

	public boolean interactOn(Entity e, Level level, int xt, int yt) {
		if(level.getData(xt, yt) != 0) return false;

		Player player = level.player;
		if(!player.hasFire) return false;
		player.addCoal();
		if(level.isTutorialLevel) level.tutorialTODO--;

		level.setData((byte) 1, xt, yt);
		return true;
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

}
