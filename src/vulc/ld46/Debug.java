package vulc.ld46;

import java.awt.event.KeyEvent;

import vulc.ld46.input.InputHandler.Key;
import vulc.ld46.input.InputHandler.KeyType;
import vulc.ld46.level.Level;
import vulc.ld46.level.LevelLoader;
import vulc.ld46.level.entity.Player;
import vulc.ld46.level.entity.TestEnemy;
import vulc.ld46.level.tile.Tile;

public abstract class Debug {

	private static Game game;
	private static Key restartGame;

	public static void init(Game game) {
		Debug.game = game;
		restartGame = Game.INPUT.new Key(KeyType.KEYBOARD, KeyEvent.VK_F5);

		game.level = new Level(game, 100, 100);
//		game.level.addEntity(new Player(12, 12));
		game.level.addEntity(new TestEnemy(120, 120));

		game.level.setTile(Tile.COBBLESTONE_FLOOR_SKELETON, 5, 7);
		game.level.setTile(Tile.STONE_WALL, 6, 7);

		game.level = LevelLoader.load(game, "/levels/test-map");
		game.level.addEntity(new Player(Level.tileToPos(12), Level.tileToPos(12)));
	}

	public static void tick() {
		if(restartGame.isPressed()) {
			game.init();
		}
	}

}
