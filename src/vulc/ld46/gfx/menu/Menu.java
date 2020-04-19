package vulc.ld46.gfx.menu;

public abstract class Menu {

	public void tick() {
	}

	public void render() {
	}

	public boolean levelTicks() {
		return true;
	}

	public boolean levelRenders() {
		return true;
	}

}
