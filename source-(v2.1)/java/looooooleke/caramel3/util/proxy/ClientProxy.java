package looooooleke.caramel3.util.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{
	public void RegisterModel(Item i) {
		ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(),"inventory"));
	}
	public void RegisterModel(Block b) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(Item.getItemFromBlock(b).getRegistryName(),"inventory"));
	}
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	public void RegisterModel(Item item,int meta, ModelResourceLocation mrl) {
		ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
	}
}
