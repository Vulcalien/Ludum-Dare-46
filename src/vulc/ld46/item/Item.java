package vulc.ld46.item;

import java.util.ArrayList;
import java.util.List;

import vulc.bitmap.Bitmap;
import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;

public abstract class Item {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	public byte id;

	public static final Item SWORD_0 = new WeaponItem(0, 0, 0, 0);
	public static final Item SWORD_1 = new WeaponItem(5, 3, 10, 4);
	public static final Item AXE_0 = new WeaponItem(3, 2, 15, 6);
	public static final Item MACE_0 = new WeaponItem(2, 1, 10, 6);
	public static final Item PICK_0 = new WeaponItem(7, 1, 15, 0);

	public static final int ITEM_SPR_SIZE = 16;

	public Item() {
		ITEMS.add(this);
		if(ITEMS.size() > 100) {
			throw new RuntimeException("error: too many items");
		}
		id = (byte) (ITEMS.size() - 1);
	}

	public boolean isStackable() {
		return true;
	}

	public Bitmap<Integer> getSprite() {
		return Atlas.getItem(id % 10, id / 10);
	}

	public void render(Screen screen, int x, int y) {
		screen.renderSprite(getSprite(), x, y);
	}

}
