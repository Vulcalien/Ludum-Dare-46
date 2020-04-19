package vulc.ld46.level;

import java.io.DataInputStream;
import java.io.IOException;

import vulc.ld46.Game;

public abstract class LevelLoader {

	public static enum LevelType {
		BRAZIER, DUNGEON
	}

	public static Level load(Game game, String level, LevelType type) {
		try {
			DataInputStream in = new DataInputStream(LevelLoader.class.getResourceAsStream(level));

			Level result;
			if(type == LevelType.DUNGEON) {
				result = new Level(game, in.readInt(), in.readInt());
			} else if(type == LevelType.BRAZIER) {
				result = new BrazierLevel(game, in.readInt(), in.readInt());
			} else {
				throw new RuntimeException("how did we get here?");
			}

			for(int i = 0; i < result.tiles.length; i++) {
				result.tiles[i] = in.readByte();
			}
			return result;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
