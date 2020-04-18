package vulc.ld46.level.entity.particle;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;

public class AttackParticle extends Particle {

	private final int dir;

	public AttackParticle(int x0, int y0, int dir) {
		super(20, x0, y0);

		this.dir = dir;
	}

	public void render(Screen screen) {
		screen.renderSprite(Atlas.attack_particle.getRotated(-dir),
		                    0xff * remainingTime / lifeTime,
		                    x, y);
	}

}
