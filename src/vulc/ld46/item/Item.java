package vulc.ld46.item;

import vulc.bitmap.Bitmap;
import vulc.ld46.gfx.Screen;

public abstract class Item {

	public static final Item[] ITEMS = new Item[100];
	public byte id;

	public static final int ITEM_SPR_SIZE = 16;

	public Item(int id) {
		this.id = (byte) id;
		if(ITEMS[id] != null) {
			throw new RuntimeException("Duplicate item ids");
		}
		ITEMS[id] = this;
	}

	public boolean isStackable() {
		return true;
	}

	public abstract Bitmap<Integer> getSprite();

	public void render(Screen screen, int x, int y) {
		screen.renderSprite(getSprite(), x, y);
	}

}
