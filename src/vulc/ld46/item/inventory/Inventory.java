package vulc.ld46.item.inventory;

import java.util.ArrayList;
import java.util.List;

import vulc.ld46.item.Item;
import vulc.ld46.item.ResourceItem;

public class Inventory {

	private final List<Item> items = new ArrayList<Item>();

	public void addItem(Item item) {
		if(item instanceof ResourceItem) {
			addResource((ResourceItem) item);
		} else {
			items.add(item);
		}
	}

	public void addResource(ResourceItem resItem) {
		for(Item item : items) {
			if(item instanceof ResourceItem) {
				ResourceItem inInventory = (ResourceItem) item;
				inInventory.amount += resItem.amount;
				return;
			}
		}
	}

	public void removeResource(ResourceItem resItem) {
		for(Item item : items) {
			if(item instanceof ResourceItem) {
				ResourceItem inInventory = (ResourceItem) item;
				inInventory.amount -= resItem.amount;

				if(resItem.amount <= 0) {
					items.remove(inInventory);
				}
				return;
			}
		}
	}

}
