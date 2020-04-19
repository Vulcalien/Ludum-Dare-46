package vulc.ld46.level.entity;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;

public class King extends Entity {

	public King(int xt, int yt) {
		this.x = Level.tileToPos(xt) + Level.T_SIZE;
		this.y = Level.tileToPos(yt) + Level.T_SIZE;
	}

	public void render(Screen screen) {
		screen.renderSprite(Atlas.getEntity(0, 3, 2, 2), x - Level.T_SIZE, y - Level.T_SIZE);

		screen.setFont(Screen.SMALL_FONT);

		String text = "bravo hai vinto";
		int wText = Screen.SMALL_FONT.widthOf(text);

		screen.fill(x - wText / 2 - 3 - screen.xOffset,
		            y - Level.T_SIZE * 2 - 3 - screen.yOffset,
		            x + wText / 2 + 3 - screen.xOffset,
		            y - Level.T_SIZE * 2 + screen.getFont().getHeight() + 2 - screen.yOffset,
		            0x444444);

		screen.fill(x - wText / 2 - 1 - screen.xOffset,
		            y - Level.T_SIZE * 2 - 1 - screen.yOffset,
		            x + wText / 2 + 1 - screen.xOffset,
		            y - Level.T_SIZE * 2 + screen.getFont().getHeight() - screen.yOffset,
		            0xffffff);
		screen.writeCentred(text, 0x000000, x, y - Level.T_SIZE * 2);

		screen.setFont(Screen.NORMAL_FONT);
	}

}
