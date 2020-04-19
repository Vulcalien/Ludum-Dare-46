package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.item.Item;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;
import vulc.ld46.level.entity.Player;

public class ChestTile extends Tile {

	public ChestTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		byte data = level.getData(xt, yt);

		if(data <= 0) Tile.COBBLESTONE_FLOOR.render(screen, level, xt, yt);
		screen.renderSprite(Atlas.getTile(data > 0 ? 0 : 1, 2), Level.tileToPos(xt), Level.tileToPos(yt));
	}

	public boolean interactOn(Entity e, Level level, int xt, int yt) {
		byte data = level.getData(xt, yt);

		// open the chest
		if(data > 0) {
			level.setData((byte) -data, xt, yt); // data < 0 => open
			return true;
		}

		byte item = (byte) Math.abs(data);

		Player player = level.player;
		if(item == 120) {
			player.eatFood();
		} else {
			Item playerWeapon = player.weapon;
			Item inChest = Item.ITEMS.get(item - 1);

			player.weapon = inChest;
			level.setData((byte) -playerWeapon.id, xt, yt);
		}
		return true;
	}

	public void onSetTile(Level level, int xt, int yt) {
		// TODO set item id as data (random item) 1 to 127
		// data = item's id + 1
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

}
