package vulc.ld46.item.inventory;

import java.util.ArrayList;
import java.util.List;

import vulc.ld46.item.stack.Stack;
import vulc.ld46.item.stack.StackableStack;

public class Inventory {

	private final List<Stack> stacks = new ArrayList<Stack>();

	public void addStack(Stack stack) {
		if(stack instanceof StackableStack) {
			addResource((StackableStack) stack);
		} else {
			stacks.add(stack);
		}
	}

	public void addResource(StackableStack resStack) {
		for(Stack stack : stacks) {
			if(stack instanceof StackableStack) {
				StackableStack inInventory = (StackableStack) stack;
				inInventory.amount += resStack.amount;
				return;
			}
		}
	}

	public void removeResource(StackableStack resStack) {
		for(Stack stack : stacks) {
			if(stack instanceof StackableStack) {
				StackableStack inInventory = (StackableStack) stack;
				inInventory.amount -= resStack.amount;

				if(resStack.amount <= 0) {
					stacks.remove(inInventory);
				}
				return;
			}
		}
	}

}
