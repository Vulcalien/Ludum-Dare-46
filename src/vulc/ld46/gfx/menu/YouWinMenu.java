package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;

public class YouWinMenu extends Menu {

	public YouWinMenu(Game game) {
		super(game);
	}

	public void render(Screen screen) {
		screen.fill(screen.width / 2 - screen.width / 4, screen.height / 2 - screen.height / 4,
		            screen.width / 2 + screen.width / 4, screen.height / 2 + screen.height / 4,
		            0x333333, 0xcc);

		screen.writeCentredAbs("You Win!", 0xffffff,
		                       screen.width / 2, screen.height / 2 - screen.getFont().getHeight());

		screen.setFont(Screen.SMALL_FONT);
		screen.write("Thanks for playing =D", 0xffffff,
		             screen.width / 2 - screen.width / 4 + 1,
		             screen.height / 2 + screen.height / 4 - screen.getFont().getHeight());
		screen.setFont(Screen.NORMAL_FONT);
	}

	public boolean levelTicks() {
		return true;
	}

	public boolean levelRenders() {
		return true;
	}

}
