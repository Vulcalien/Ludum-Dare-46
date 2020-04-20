/*******************************************************************************
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld46.level;

import java.util.ArrayList;
import java.util.List;

import vulc.ld46.Game;
import vulc.ld46.gfx.Screen;
import vulc.ld46.gfx.menu.YouWinMenu;
import vulc.ld46.level.entity.Entity;
import vulc.ld46.level.entity.MeleeEnemy;
import vulc.ld46.level.entity.Player;
import vulc.ld46.level.entity.particle.Particle;
import vulc.ld46.level.tile.Tile;

public class Level {

	// Tile size: the number of pixels per tile
	public static final int T_SIZE = 24;

	public final Game game;
	public Player player;

	public int xSpawn, ySpawn;

	public final int width, height;
	public final byte[] tiles;
	public final byte[] data;

	public final List<Entity> entities = new ArrayList<Entity>();
	public final List<Entity> particlesToRender = new ArrayList<Entity>();
	public final List<Entity>[] entitiesInTile;

	public boolean won = false;
	public int ticksSinceWon = 0;

	public int ticks = 0;

	public boolean isTutorialLevel = false;
	public int tutorialTODO = 3;

	public int remainingEnemies = 0;

	@SuppressWarnings("unchecked")
	public Level(Game game, int width, int height) {
		this.game = game;

		this.width = width;
		this.height = height;

		this.tiles = new byte[width * height];
		this.data = new byte[width * height];

		this.entitiesInTile = new ArrayList[width * height];
		for(int i = 0; i < entitiesInTile.length; i++) {
			entitiesInTile[i] = new ArrayList<Entity>();
		}
	}

	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);

			int xt0 = posToTile(e.x);
			int yt0 = posToTile(e.y);

			e.tick();

			if(e.removed) {
				removeEntityFromTile(e, xt0, yt0);
				removeEntity(e);
				i--;
			} else {
				int xt1 = posToTile(e.x);
				int yt1 = posToTile(e.y);

				if(xt1 != xt0 || yt1 != yt0) {
					removeEntityFromTile(e, xt0, yt0);
					insertEntityInTile(e, xt1, yt1);
				}
			}
		}
		ticks++;
		if(won) {
			ticksSinceWon++;
			if(ticksSinceWon > 120) {
				game.menu = new YouWinMenu(game);
			}
		}
	}

	public void render(Screen screen, int xTiles, int yTiles) {
		if(player != null) {
			screen.xOffset = player.x - screen.width / 2;
			screen.yOffset = player.y - screen.height / 2;
		}

		int xt0 = posToTile(screen.xOffset);
		int yt0 = posToTile(screen.yOffset);
		int xt1 = xt0 + xTiles - 1;
		int yt1 = yt0 + yTiles - 1;

		for(int yt = yt0; yt <= yt1; yt++) {
			if(yt < 0 || yt >= height) continue;

			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt < 0 || xt >= width) continue;

				getTile(xt, yt).render(screen, this, xt, yt);
			}
		}

		particlesToRender.clear();

		List<Entity> entities = getEntitiesInTile(xt0 - 1, yt0 - 1, xt1 + 1, yt1 + 1);
		for(Entity e : entities) {
			if(e instanceof Particle) {
				particlesToRender.add(e);
			} else {
				e.render(screen);
			}
		}

		for(Entity p : particlesToRender) {
			p.render(screen);
		}
	}

	public void setTile(Tile tile, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		tile.onSetTile(this, xt, yt);
		tiles[xt + yt * width] = tile.id;
	}

	public Tile getTile(int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return null;
		return Tile.TILES[tiles[xt + yt * width]];
	}

	public void setData(byte data, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		this.data[xt + yt * width] = data;
	}

	public byte getData(int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return 0;
		return data[xt + yt * width];
	}

	public void addEntity(Entity e) {
		entities.add(e);
		insertEntityInTile(e, posToTile(e.x), posToTile(e.y));
		e.removed = false;
		e.level = this;

		if(e instanceof Player) {
			Player p = (Player) e;
			this.player = p;
		}

		if(e instanceof MeleeEnemy) {
			remainingEnemies++;
		}
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
		removeEntityFromTile(e, posToTile(e.y), posToTile(e.y));
		e.removed = true;

		if(e == player) player = null;
	}

	private void insertEntityInTile(Entity e, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		entitiesInTile[xt + yt * width].add(e);
	}

	private void removeEntityFromTile(Entity e, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		entitiesInTile[xt + yt * width].remove(e);
	}

	public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();

		int xt0 = posToTile(x0) - 1;
		int yt0 = posToTile(y0) - 1;
		int xt1 = posToTile(x1) + 1;
		int yt1 = posToTile(y1) + 1;

		for(int yt = yt0; yt <= yt1; yt++) {
			if(yt < 0 || yt >= height) continue;

			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt < 0 || xt >= width) continue;

				List<Entity> inTile = entitiesInTile[xt + yt * width];
				for(int i = 0; i < inTile.size(); i++) {
					Entity e = inTile.get(i);

					if(e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		return result;
	}

	public List<Entity> getEntitiesInTile(int xt0, int yt0, int xt1, int yt1) {
		List<Entity> result = new ArrayList<Entity>();
		for(int yt = yt0; yt <= yt1; yt++) {
			if(yt < 0 || yt >= height) continue;

			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt < 0 || xt >= width) continue;

				result.addAll(entitiesInTile[xt + yt * width]);
			}
		}
		return result;
	}

	public static int tileToPos(int tile) {
		return tile * T_SIZE;
	}

	public static int posToTile(int pos) {
		return Math.floorDiv(pos, T_SIZE);
	}

}
