package vulc.ld46.level.entity.particle;

import vulc.ld46.gfx.Screen;

public class TextParticle extends Particle {

	public static final String[] TEXTS = {
	    // brazier-welcome
	    "Welcome to Holy Fire Knight",
	    "Press WASD to move",
	    "Press L to attack",
	    "Press E to interact (chests, doors...)",
	    "Press L (attack) to talk to the king",

	    // room-0
	    "Press E to get the Holy Fire", // id 5
	    "If you don't use coal, the fire will extinguish",
	    "Keep it alive!",
	    "Press E to open the chest",
	    "You can find weapons or food here",
	    "Take coal to keep the fire alive",

	    "Kill all enemies to open the door", // id 11

	    "Use E to set the brazier on fire"
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
