package vulc.ld46.level;

import vulc.ld46.Game;
import vulc.ld46.level.entity.King;

public class BrazierLevel extends Level {

	public BrazierLevel(Game game, int width, int height) {
		super(game, width, height);

		game.player.x = tileToPos(10);
		game.player.y = tileToPos(15) + T_SIZE / 2;
		addEntity(game.player);

		addEntity(new King(7, 8));
	}

}
