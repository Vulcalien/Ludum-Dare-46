package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.Keys;

public class LoseFireMenu extends Menu {

	public LoseFireMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(Keys.interact0.isPressed() || Keys.interact1.isPressed()) {
			game.menu = new StartMenu(game);
		}
	}

	public void render(Screen screen) {
		screen.writeCentredAbs("The Holy Fire", 0xeeeeee,
		                       screen.width / 2, screen.height / 2 - screen.getFont().getHeight());
		screen.writeCentredAbs("has extinguished!", 0xeeeeee,
		                       screen.width / 2, screen.height / 2 + 1);

		screen.setFont(Screen.SMALL_FONT);
		screen.write("Press E to restart", 0x999999, 1, screen.height - screen.getFont().getHeight() - 1);
		screen.setFont(Screen.NORMAL_FONT);
	}

	public boolean levelTicks() {
		return false;
	}

	public boolean levelRenders() {
		return false;
	}

}
