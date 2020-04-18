package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;

public class WallTile extends Tile {

	private final int xSprite, ySprite;

	private final boolean decorated;
	private int xDecoration = -1, yDecoration = -1;

	public WallTile(int id, int xSprite, int ySprite, int xDecoration, int yDecoration) {
		super(id);
		this.xSprite = xSprite;
		this.ySprite = ySprite;

		decorated = true;
		this.xDecoration = xDecoration;
		this.yDecoration = yDecoration;
	}

	public WallTile(int id, int xSprite, int ySprite) {
		super(id);
		this.xSprite = xSprite;
		this.ySprite = ySprite;

		decorated = false;
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		screen.renderSprite(Atlas.getTile(xSprite, ySprite), Level.tileToPos(xt), Level.tileToPos(yt));
		if(decorated) {
			screen.renderSprite(Atlas.getTile(xDecoration, yDecoration), Level.tileToPos(xt), Level.tileToPos(yt));
		}
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

}
