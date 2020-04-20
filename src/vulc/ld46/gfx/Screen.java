/*******************************************************************************
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld46.gfx;

import vulc.bitmap.Bitmap;
import vulc.bitmap.IntBitmap;
import vulc.bitmap.font.Font;
import vulc.ld46.Game;
import vulc.ld46.item.WeaponItem;
import vulc.ld46.level.Level;
import vulc.ld46.level.entity.Player;

public class Screen extends IntBitmap {

	public static final Font SMALL_FONT = new Font(Screen.class.getResourceAsStream("/fonts/tinyfont.fv4"));
	public static final Font NORMAL_FONT = SMALL_FONT.getScaled(2);

	private static final int BACKGROUND_COLOR = 0x000000;

	private final Game game;

	public int xOffset = 0, yOffset = 0;

	public Screen(Game game) {
		super(Game.WIDTH, Game.HEIGHT);
		this.game = game;

		setFont(NORMAL_FONT);
		setTransparent(0xff00ff);
	}

	public void render() {
		clear(BACKGROUND_COLOR);

		boolean levelRenders = true;
		if(game.menu != null) {
			levelRenders = game.menu.levelRenders();
		}

		if(levelRenders) {
			Level level = game.level;
			if(level != null) {
				level.render(this, width / Level.T_SIZE + 1, height / Level.T_SIZE + 1);

				fill(0, height - 20, width, height, 0xbbbbbb);

				fill(1, height - 19, 102, height - 2, 0x333333);
				draw(Atlas.heart, 104, height - 11);

				fill(width - 103, height - 19, width - 2, height - 2, 0x333333);
				draw(Atlas.fire_icon, width - 114, height - 11);

				// weapon
				fill(width / 2 - 9, height - 10 - 9, width / 2 + 8, height - 10 + 8, 0x333333);

				Player player = level.player;
				if(player != null) {
					fill(2, height - 18, 1 + 100 * player.hp / player.maxHp, height - 3, 0xff0000); // hp bar

					if(player.hasFire) {
						fill(width - 2 - 100 * player.fireHp / Player.FIRE_HP, height - 18,
						     width - 3, height - 3,
						     0x0000ff); // fire bar
					}

					if(player.weapon != null) {
						WeaponItem sword = player.weapon;

						draw(sword.getSprite(), width / 2 - 8, height - 10 - 8);

						setFont(SMALL_FONT);
						super.write("DMG:" + sword.damage, 0x000000, width / 2 - 50, height - 19);
						super.write("KNB:" + sword.knockback, 0x000000, width / 2 - 50, height - 10);
						super.write("RNG:" + sword.range, 0x000000, width / 2 + 12, height - 19);
						super.write("CLD:" + sword.cooldown, 0x000000, width / 2 + 12, height - 10);
						setFont(NORMAL_FONT);
					}
				}
			}
		}
		if(game.menu != null) game.menu.render(this);
	}

	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}

	public void renderSprite(Bitmap<Integer> sprite, int transparency, int x, int y) {
		draw(sprite, transparency, x - xOffset, y - yOffset);
	}

	public void renderSprite(Bitmap<Integer> sprite, int x, int y) {
		draw(sprite, x - xOffset, y - yOffset);
	}

	public void writeCentred(String text, int color, int x, int y) {
		super.write(text, color, x - xOffset - getFont().widthOf(text) / 2, y - yOffset);
	}

	public void writeCentredAbs(String text, int color, int x, int y) {
		super.write(text, color, x - getFont().widthOf(text) / 2, y);
	}

}
