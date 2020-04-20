
package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;

public abstract class Menu {

	public final Game game;

	public Menu(Game game) {
		this.game = game;
	}

	public void tick() {
	}

	public void render(Screen screen) {
	}

	public boolean levelTicks() {
		return true;
	}

	public boolean levelRenders() {
		return true;
	}

}
