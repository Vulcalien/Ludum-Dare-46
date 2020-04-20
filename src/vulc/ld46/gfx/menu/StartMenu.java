package vulc.ld46.gfx.menu;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.input.Keys;
import vulc.ld46.level.LevelLoader;

public class StartMenu extends Menu {

	private int ticks = 0;
	private int selected = 1;

	public StartMenu(Game game) {
		super(game);
	}

	public void tick() {
		ticks++;
		if(Keys.a.isPressed()) selected--;
		if(Keys.d.isPressed()) selected++;

		if(selected < 0) selected = 2;
		if(selected > 2) selected = 0;

		if(Keys.interact0.isPressed() || Keys.interact1.isPressed()) {
			if(selected == 0) {
				game.menu = new CreditsMenu(game);
			} else if(selected == 1) {
				game.menu = null;
				game.switchToLevel(LevelLoader.next(game));
			} else if(selected == 2) {
				game.menu = new InstructionsMenu(game);
			}
		}
	}

	public void render(Screen screen) {
		screen.writeCentredAbs("Credits", selected == 0 ? 0xffff99 : 0xcccccc,
		                       screen.width / 4,
		                       screen.height * 3 / 4 + (selected == 0 && ticks / 40 % 2 == 0 ? 0 : 2));

		screen.writeCentredAbs("Play Holy Fire Knight", selected == 1 ? 0xffff99 : 0xcccccc,
		                       screen.width / 2,
		                       screen.height * 3 / 8 - (selected == 1 && ticks / 40 % 2 == 0 ? 0 : 2));

		screen.writeCentredAbs("Instructions", selected == 2 ? 0xffff99 : 0xcccccc,
		                       screen.width * 3 / 4,
		                       screen.height * 3 / 4 - (selected == 2 && ticks / 40 % 2 == 0 ? 0 : 2));

		screen.setFont(Screen.SMALL_FONT);
		screen.write("Use A and D to move", 0xaaaaaa, 1, screen.height - screen.getFont().getHeight() - 1);
		String text = "Press E to interact";
		screen.write(text, 0xaaaaaa, screen.width - screen.getFont().widthOf(text) - 1,
		             screen.height - screen.getFont().getHeight() - 1);
		screen.write("Copyright 2020 Vulcalien", 0x999999, 1, 1);
		screen.setFont(Screen.NORMAL_FONT);
	}

	public boolean levelRenders() {
		return false;
	}

	public boolean levelTicks() {
		return false;
	}

}
