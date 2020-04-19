package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.item.Item;
import vulc.ld46.item.stack.Stack;
import vulc.ld46.item.stack.StackableStack;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;

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
		if(data <= 0) return false;

		// add item to inventory
		Stack stack;
		Item item = Item.ITEMS[data - 1];

		if(item.isStackable()) {
			int amount = -1; // TODO
			stack = new StackableStack(item, amount);
		} else {
			stack = new Stack(item);
		}

		if(level.player != null) {
			level.player.inventory.addStack(stack);
		}

		level.setData((byte) -1, xt, yt); // data < 0 => open
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
