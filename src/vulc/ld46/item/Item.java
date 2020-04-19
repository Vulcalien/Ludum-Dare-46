package vulc.ld46.item;

import java.util.ArrayList;
import java.util.List;

import vulc.bitmap.Bitmap;
import vulc.ld46.gfx.Screen;

public abstract class Item {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	public byte id;

	public static final int ITEM_SPR_SIZE = 16;

	public Item() {
		ITEMS.add(this);
		if(ITEMS.size() > 100) {
			System.err.println("error: too many items");
		}
	}

	public boolean isStackable() {
		return true;
	}

	public abstract Bitmap<Integer> getSprite();

	public void render(Screen screen, int x, int y) {
		screen.renderSprite(getSprite(), x, y);
	}

}
