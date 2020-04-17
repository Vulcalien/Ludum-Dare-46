/*******************************************************************************
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld46.level.tile;

import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;

public abstract class Tile {

	public static final Tile[] TILES = new Tile[128];

	public final byte id;

	public Tile(int id) {
		this.id = (byte) id;
		if(TILES[id] != null) {
			throw new RuntimeException("Duplicate tile ids");
		}
		TILES[id] = this;
	}

	public void render(Screen screen, Level level, int xt, int yt) {
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return true;
	}

}
