package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.Keys;
import vulc.ld46.level.LevelLoader;

public class YouDiedMenu extends Menu {

	public YouDiedMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(Keys.interact0.isPressed() || Keys.interact1.isPressed()) {
			game.menu = null;
			game.switchToLevel(LevelLoader.current(game));
			game.player.respawn();
		}
	}

	public void render(Screen screen) {
		screen.writeCentredAbs("You Died!", 0xeeeeee,
		                       screen.width / 2, screen.height / 2 - screen.getFont().getHeight());

		screen.setFont(Screen.SMALL_FONT);
		screen.write("Press E to respawn", 0x999999, 1, screen.height - screen.getFont().getHeight() - 1);
		screen.setFont(Screen.NORMAL_FONT);
	}

	public boolean levelTicks() {
		return false;
	}

	public boolean levelRenders() {
		return false;
	}

}
