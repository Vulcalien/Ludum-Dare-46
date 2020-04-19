package vulc.ld46.level.entity.particle;

import vulc.ld46.gfx.Screen;

public class TextParticle extends Particle {

	public static final String[] TEXTS = {
	    "Welcome to [game name]", // TODO
	    "Use WASD to move",
	    "Use L to attack",
	    "Use P to interact (chests and others)",
	    "Use L (attack) to talk to the king"
	};

	public String text;

	public TextParticle(int x, int y, String text) {
		super(-1, x, y);
		this.text = text;
	}

	public void render(Screen screen) {
		screen.setFont(Screen.SMALL_FONT);

		int wText = Screen.SMALL_FONT.widthOf(text);
		int hText = Screen.SMALL_FONT.getHeight();

		screen.fill(x - wText / 2 - 3 - screen.xOffset,
		            y - hText / 2 - 3 - screen.yOffset,
		            x + wText / 2 + 3 - screen.xOffset,
		            y + hText / 2 + 2 - screen.yOffset,
		            0x444444);

		screen.fill(x - wText / 2 - 1 - screen.xOffset,
		            y - hText / 2 - 1 - screen.yOffset,
		            x + wText / 2 + 1 - screen.xOffset,
		            y + hText / 2 - screen.yOffset,
		            0xffffff);
		screen.writeCentred(text, 0x000000, x, y - hText / 2);

		screen.setFont(Screen.NORMAL_FONT);
	}

}
