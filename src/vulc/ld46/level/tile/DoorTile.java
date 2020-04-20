package vulc.ld46.level.tile;

import vulc.ld46.gfx.Atlas;
import vulc.ld46.gfx.Screen;
import vulc.ld46.level.Level;
import vulc.ld46.level.LevelLoader;
import vulc.ld46.level.entity.Entity;
import vulc.ld46.sfx.Sounds;

public class DoorTile extends Tile {

	private final boolean exit;

	public DoorTile(int id, boolean exit) {
		super(id);
		this.exit = exit;
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		if(exit) {
			if(level.ticks <= 100) {
				screen.renderSprite(Atlas.getTile(3, 1), Level.tileToPos(xt), Level.tileToPos(yt));
				screen.renderSprite(Atlas.getTile(0, 1), 0xff * level.ticks / 100,
				                    Level.tileToPos(xt), Level.tileToPos(yt));
			} else {
				screen.renderSprite(Atlas.getTile(0, 1), Level.tileToPos(xt), Level.tileToPos(yt));
			}
		} else {
			screen.renderSprite(Atlas.getTile(3, 1), Level.tileToPos(xt), Level.tileToPos(yt));
		}
	}

	public boolean interactOn(Entity e, Level level, int xt, int yt) {
		if(exit) return false;

		if(level.isTutorialLevel && level.tutorialTODO > 0) return false;
		if(level.remainingEnemies > 0) return false;

		if(LevelLoader.hasNext()) {
			level.game.switchToLevel(LevelLoader.next(level.game));
			Sounds.OPEN_CHEST.play();
		}
		return true;
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

}
