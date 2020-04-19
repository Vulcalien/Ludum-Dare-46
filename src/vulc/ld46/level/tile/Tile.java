/*******************************************************************************
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld46.level.tile;

import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;

public abstract class Tile {

	public static final Tile[] TILES = new Tile[128];

	// floor
	public static final Tile COBBLESTONE_FLOOR = new FloorTile(0, 0, 0);
	public static final Tile COBBLESTONE_FLOOR_SKELETON = new FloorTile(30, 0, 0, 0, 3);
	public static final Tile GRASS_FLOOR = new FloorTile(5, 5, 0);

	// wall
	public static final Tile STONE_WALL = new WallTile(10, 0, 1);

	// chest
	public static final Tile CHEST = new ChestTile(20);

	// brazier
	public static final Tile BRAZIER_0 = new BrazierTile(6, 0, 0);
	public static final Tile BRAZIER_1 = new BrazierTile(7, 1, 0);
	public static final Tile BRAZIER_2 = new BrazierTile(16, 0, 1);
	public static final Tile BRAZIER_3 = new BrazierTile(17, 1, 1);

	// coal tile
	public static final Tile COAL_TILE = new CoalTile(11);

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

	public void onSetTile(Level level, int xt, int yt) {
	}

	// return false if could not interact
	// return true if could interact
	public boolean interactOn(Entity e, Level level, int xt, int yt) {
		return false;
	}

}
