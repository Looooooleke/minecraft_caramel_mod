package looooooleke.caramel3.util.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class CommonProxy {
	public void RegisterModel(Item i) {}
	public void RegisterModel(Block b) {}
	public void registerItemRenderer(Item item, int meta, String id) {}
	public void RegisterModel(Item item,int meta, ModelResourceLocation mrl) {}
}
