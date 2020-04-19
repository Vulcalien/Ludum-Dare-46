/*******************************************************************************
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld46.gfx;

import vulc.bitmap.Bitmap;
import vulc.bitmap.IntBitmap;
import vulc.bitmap.font.Font;
import vulc.ld46.Game;
import vulc.ld46.level.Level;

public class Screen extends IntBitmap {

	public static final Font SMALL_FONT = new Font(Screen.class.getResourceAsStream("/fonts/tinyfont.fv4"));
	public static final Font NORMAL_FONT = SMALL_FONT.getScaled(2);

	private static final int BACKGROUND_COLOR = 0x000000;

	private final Game game;

	public int xOffset = 0, yOffset = 0;

	public Screen(Game game) {
		super(Game.WIDTH, Game.HEIGHT);
		this.game = game;

		setFont(NORMAL_FONT);
		setTransparent(0xff00ff);
	}

	public void render() {
		clear(BACKGROUND_COLOR);

		Level level = game.level;
		if(level != null) {
			level.render(this, width / Level.T_SIZE + 1, height / Level.T_SIZE + 1);
		}
	}

	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}

	public void renderSprite(Bitmap<Integer> sprite, int transparency, int x, int y) {
		draw(sprite, transparency, x - xOffset, y - yOffset);
	}

	public void renderSprite(Bitmap<Integer> sprite, int x, int y) {
		draw(sprite, x - xOffset, y - yOffset);
	}

	public void writeCentred(String text, int color, int x, int y) {
		super.write(text, color, x - xOffset - getFont().widthOf(text) / 2, y - yOffset);
	}

}
