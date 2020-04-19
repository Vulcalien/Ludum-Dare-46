package vulc.ld46.level;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import vulc.ld46.Game;
import vulc.ld46.level.entity.King;
import vulc.ld46.level.entity.MeleeEnemy;
import vulc.ld46.level.entity.particle.TextParticle;

public abstract class LevelLoader {

	public static final String[][] LEVEL_LIST = {
	    new String[] {"brazier-map", "brazier-welcome.entities"},
	};

	public static int currentLevel = -1;

	public static Level next(Game game) {
		currentLevel++;
		return load(game,
		            "/levels/" + LEVEL_LIST[currentLevel][0],
		            "/levels/" + LEVEL_LIST[currentLevel][1]);
	}

	public static Level load(Game game, String level, String entities) {
		try {
			Level result;
			try(DataInputStream in = new DataInputStream(LevelLoader.class.getResourceAsStream(level))) {

				result = new Level(game, in.readInt(), in.readInt());

				for(int i = 0; i < result.tiles.length; i++) {
					result.tiles[i] = in.readByte();
				}
			}
			try(InputStream in = LevelLoader.class.getResourceAsStream(entities)) {
				int id;
				while((id = in.read()) != -1) {
					if(id == 0) {
						result.xSpawn = in.read();
						result.ySpawn = in.read();
					} else if(id == 1) {
						result.addEntity(new MeleeEnemy(in.read(), in.read()));
					} else if(id == 10) {
						result.addEntity(new King(in.read(), in.read(), in.read()));
					} else if(id == 11) {
						result.addEntity(new TextParticle(Level.tileToPos(in.read()) + Level.T_SIZE / 2,
						                                  Level.tileToPos(in.read()) + Level.T_SIZE / 2,
						                                  TextParticle.TEXTS[in.read()]));
					}
				}
			}
			return result;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
