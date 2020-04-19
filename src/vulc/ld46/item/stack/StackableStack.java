package vulc.ld46.item.stack;

import vulc.ld46.item.Item;

public class StackableStack extends Stack {

	public int amount;

	public StackableStack(Item resource, int amount) {
		super(resource);
		this.amount = amount;
	}

}
