package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.Keys;

public class CreditsMenu extends Menu {

	public CreditsMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(Keys.interact0.isPressed() || Keys.interact1.isPressed()) {
			game.menu = new StartMenu(game);
		}
	}

	public void render(Screen screen) {
		screen.write("Author: Vulcalien"
		             + "\n"
		             + "\nImages, Sounds and Engine"
		             + "\nmade by me."
		             + "\n"
		             + "\nTesters:"
		             + "\n  MendolaFrancesco", 0xeeeeee, 1, 1);

		screen.setFont(Screen.SMALL_FONT);
		screen.write("press E to return to the start menu", 0x999999,
		             1, screen.height - screen.getFont().getHeight() - 1);
		screen.setFont(Screen.NORMAL_FONT);
	}

}
