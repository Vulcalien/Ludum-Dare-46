package vulc.ld46.level.tile;

import java.util.Random;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.item.Item;
import vulc.ld46.item.WeaponItem;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Entity;
import vulc.ld46.level.entity.Player;
import vulc.ld46.sfx.Sounds;

public class ChestTile extends Tile {

	private static final Random RANDOM = new Random();

	public ChestTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		byte data = level.getData(xt, yt);

		if(data <= 0) Tile.COBBLESTONE_FLOOR.render(screen, level, xt, yt);
		screen.renderSprite(Atlas.getTile(data > 0 ? 0 : 1, 2), Level.tileToPos(xt), Level.tileToPos(yt));

		if(data < 0) {
			int item = Math.abs(data) - 1;

			if(item == 120) {
				screen.renderSprite(Atlas.getItem(9, 9), Level.tileToPos(xt) + 4, Level.tileToPos(yt) - 4);
			} else {
				screen.renderSprite(Atlas.getItem(item % 10, item / 10), Level.tileToPos(xt) + 4,
				                    Level.tileToPos(yt) - 4);
			}
		}
	}

	public boolean interactOn(Entity e, Level level, int xt, int yt) {
		byte data = level.getData(xt, yt);

		// open the chest
		if(data > 0) {
			level.setData((byte) -data, xt, yt); // data < 0 => open
			if(level.isTutorialLevel) level.tutorialTODO--;
			Sounds.OPEN_CHEST.play();
			return true;
		}

		if(data == 0) return false;
		byte item = (byte) (Math.abs(data) - 1);

		Player player = level.player;
		if(item == 120) {
			player.eatFood();
			level.setData((byte) 0, xt, yt);
		} else {
			Item playerWeapon = player.weapon;
			Item inChest = Item.ITEMS.get(item);

			if(inChest instanceof WeaponItem) {
				player.weapon = (WeaponItem) inChest;
			} else {
				System.err.println("Error: non-sword item");
			}

			if(playerWeapon != null) {
				level.setData((byte) -(playerWeapon.id + 1), xt, yt);
			} else {
				level.setData((byte) 0, xt, yt);
			}
		}
		return true;
	}

	public void onSetTile(Level level, int xt, int yt) {
		int item;

		if(RANDOM.nextInt(100) <= 40) {
			item = 120; // food
		} else {
			item = RANDOM.nextInt(Item.ITEMS.size());
		}
		level.setData((byte) (item + 1), xt, yt);

		// data = item's id + 1
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

}
