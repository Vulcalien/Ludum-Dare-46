package vulc.ld46.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import vulc.ld46.input.InputHandler.Key;
import vulc.ld46.input.InputHandler.KeyType;

public abstract class Keys {

	public static Key w, a, s, d,
	        attack0, interact0,
	        attack1, interact1;

	public static void init(InputHandler input) {
		w = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_W);
		a = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_A);
		s = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_S);
		d = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_D);

		attack0 = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_L);
		attack1 = input.new Key(KeyType.MOUSE, MouseEvent.BUTTON1);

		interact0 = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_P);
		interact1 = input.new Key(KeyType.KEYBOARD, KeyEvent.VK_E);
	}

}
