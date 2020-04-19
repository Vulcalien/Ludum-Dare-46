package vulc.ld46.level.entity;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.particle.TextParticle;

public class King extends Mob {

	private static final String[] TEXTS = {
	    "a1",
	    "text 2",
	    "text 3"
	};

	private int currentText = -1;
	private TextParticle textParticle;

	public King(int xt, int yt) {
		super(Level.tileToPos(xt) + Level.T_SIZE, Level.tileToPos(yt) + Level.T_SIZE, 1_000_000);

		xr = 18;
		yr = 18;
	}

	public void render(Screen screen) {
		screen.renderSprite(Atlas.getEntity(0, 3, 2, 2), x - Level.T_SIZE, y - Level.T_SIZE);
	}

	public void damage(int dmg, int xKnockback, int yKnockback, Entity attacker) {
		currentText++;
		if(textParticle != null) level.removeEntity(textParticle);

		if(TEXTS.length > currentText) {
			textParticle = new TextParticle(x, y - Level.T_SIZE, TEXTS[currentText]);
			level.addEntity(textParticle);
		} else {
			currentText = -1;
		}
	}

}
