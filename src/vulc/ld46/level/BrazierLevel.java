package vulc.ld46.level;

import vulc.ld46.Game;
import vulc.ld46.level.entity.King;
import vulc.ld46.level.entity.Player;

public class BrazierLevel extends Level {

	public BrazierLevel(Game game, int width, int height) {
		super(game, width, height);
		addEntity(new Player(tileToPos(10), tileToPos(15) + T_SIZE / 2));
		addEntity(new King(7, 8));
	}

}
