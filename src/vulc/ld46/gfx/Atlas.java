package vulc.ld46.gfx;

import static vulc.ld46.item.Item.ITEM_SPR_SIZE;
import static vulc.ld46.level.Level.T_SIZE;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import vulc.bitmap.Bitmap;
import vulc.bitmap.IntBitmap;

public abstract class Atlas {

	private static Bitmap<Integer> tiles;
	private static Bitmap<Integer> entities;
	private static Bitmap<Integer> items;

	public static Bitmap<Integer> attack_particle;

	public static Bitmap<Integer> getTile(int x, int y, int w, int h) {
		return tiles.getSubimage(x * T_SIZE, y * T_SIZE, w * T_SIZE, h * T_SIZE);
	}

	public static Bitmap<Integer> getTile(int x, int y) {
		return getTile(x, y, 1, 1);
	}

	public static Bitmap<Integer> getEntity(int x, int y, int w, int h) {
		return entities.getSubimage(x * T_SIZE, y * T_SIZE, w * T_SIZE, h * T_SIZE);
	}

	public static Bitmap<Integer> getEntity(int x, int y) {
		return getEntity(x, y, 1, 1);
	}

	public static Bitmap<Integer> getItem(int x, int y, int w, int h) {
		return items.getSubimage(x * ITEM_SPR_SIZE, y * ITEM_SPR_SIZE, w * ITEM_SPR_SIZE, h * ITEM_SPR_SIZE);
	}

	public static Bitmap<Integer> getItem(int x, int y) {
		return getItem(x, y, 1, 1);
	}

	public static void init() {
		tiles = new IntBitmap(getBufferedImage("/gfx/tiles.png"));
		entities = new IntBitmap(getBufferedImage("/gfx/entities.png"));
		items = new IntBitmap(getBufferedImage("/gfx/items.png"));

		// attack particle
		{
			attack_particle = new IntBitmap(getBufferedImage("/gfx/attack_particle.png"));

			Bitmap<Integer> result = new IntBitmap(attack_particle.width * 2, attack_particle.height);
			result.draw(attack_particle, 0, 0);
			result.draw(attack_particle.getFlipped(true, false), result.width / 2, 0);
			attack_particle = result;
		}
	}

	private static BufferedImage getBufferedImage(String resource) {
		try {
			return ImageIO.read(Atlas.class.getResourceAsStream(resource));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
