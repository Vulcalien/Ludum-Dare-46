package vulc.ld46.item;

import vulc.bitmap.Bitmap;
import vulc.ld46.item.resource.Resource;

public class ResourceItem extends Item {

	public final Resource resource;
	public int amount;

	public ResourceItem(Resource resource, int amount) {
		this.resource = resource;
		this.amount = amount;
	}

	public Bitmap<Integer> getSprite() {
		return resource.getSprite();
	}

}
