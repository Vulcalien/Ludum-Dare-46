package vulc.ld46;

import java.awt.event.KeyEvent;

import vulc.ld46.input.InputHandler.Key;
import vulc.ld46.input.InputHandler.KeyType;
import vulc.ld46.level.Level;
import vulc.ld46.level.LevelLoader;
import vulc.ld46.level.tile.Tile;

public abstract class Debug {

	private static Game game;
	private static Key restartGame;
	private static Key debugWinGame;

	public static void init(Game game) {
		Debug.game = game;
		restartGame = Game.INPUT.new Key(KeyType.KEYBOARD, KeyEvent.VK_F5);
		debugWinGame = Game.INPUT.new Key(KeyType.KEYBOARD, KeyEvent.VK_F9);

		game.level = new Level(game, 100, 100);

		game.player.x = 12;
		game.player.y = 12;
		game.level.addEntity(game.player);

//		game.level.addEntity(new MeleeEnemy(120, 120));

		game.level.setTile(Tile.COBBLESTONE_FLOOR_SKELETON, 5, 7);
		game.level.setTile(Tile.STONE_WALL, 6, 7);
		game.level.setTile(Tile.CHEST, 8, 8);

		game.level.setTile(Tile.COAL_TILE, 10, 10);
		game.level.setTile(Tile.FIRE_TILE, 3, 3);

		game.switchToLevel(LevelLoader.load(game, "/levels/brazier-map"));

		game.level.player.hasFire = true;
	}

	public static void tick() {
		if(restartGame.isPressed()) {
			System.out.println("asdads");
			game.init();
		}
		if(debugWinGame.isPressed()) {
			game.level.won = true;
		}
	}

}
