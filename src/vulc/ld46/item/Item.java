package vulc.ld46.item;

import vulc.bitmap.Bitmap;
import vulc.ld46.gfx.Screen;

public abstract class Item {

	public static final int ITEM_SPR_SIZE = 16;

	public abstract Bitmap<Integer> getSprite();

	public void render(Screen screen, int x, int y) {
		screen.renderSprite(getSprite(), x, y);
	}

}
