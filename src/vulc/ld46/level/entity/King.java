package vulc.ld46.level.entity;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.particle.TextParticle;

public class King extends Mob {

	private static final String[][] TEXTS = {
	    new String[] {
	        "text TODO", // TODO
	    },
	    new String[] {

		}
	};

	private final String[] dialogueSet;

	private int currentText = -1;
	private TextParticle textParticle;

	public King(int xt, int yt, int dialogueSet) {
		super(Level.tileToPos(xt) + Level.T_SIZE, Level.tileToPos(yt) + Level.T_SIZE, 1_000_000);

		xr = 18;
		yr = 18;

		this.dialogueSet = TEXTS[dialogueSet];
	}

	public void render(Screen screen) {
		screen.renderSprite(Atlas.getEntity(0, 3, 2, 2), x - Level.T_SIZE, y - Level.T_SIZE);
	}

	public void damage(int dmg, int xKnockback, int yKnockback, Entity attacker) {
		currentText++;
		if(textParticle != null) textParticle.remove();

		if(dialogueSet.length > currentText) {
			textParticle = new TextParticle(x, y - Level.T_SIZE, dialogueSet[currentText]);
			level.addEntity(textParticle);
		} else {
			currentText = -1;
		}
	}

}
