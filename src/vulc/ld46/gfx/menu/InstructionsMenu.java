package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.Keys;

public class InstructionsMenu extends Menu {

	public InstructionsMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(Keys.interact0.isPressed() || Keys.interact1.isPressed()) {
			game.menu = new StartMenu(game);
		}
	}

	public void render(Screen screen) {
		screen.write("Use WASD to move"
		             + "\nUse E to interact"
		             + "\nUse L to attack"
		             + "\n"
		             + "\nTake the fire, complete"
		             + "\nthe dungeon"
		             + "\nand put the fire in"
		             + "\nthe brazier", 0xeeeeee, 1, 1);

		screen.setFont(Screen.SMALL_FONT);
		screen.write("press E to return to the start menu", 0x999999,
		             1, screen.height - screen.getFont().getHeight() - 1);
		screen.setFont(Screen.NORMAL_FONT);
	}

}
