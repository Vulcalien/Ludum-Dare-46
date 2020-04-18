package vulc.ld46.level;

import java.io.DataInputStream;
import java.io.IOException;

import vulc.ld46.Game;

public abstract class LevelLoader {

	public static Level load(Game game, String level) {
		try {
			DataInputStream in = new DataInputStream(LevelLoader.class.getResourceAsStream(level));

			Level result = new Level(game, in.readInt(), in.readInt());
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
