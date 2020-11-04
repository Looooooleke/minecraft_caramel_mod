package looooooleke.util.Items;

import looooooleke.util.Interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBlockVariants extends ItemBlock{
	
	int burnTime=0;

	public ItemBlockVariants(Block block) {
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	public ItemBlockVariants setBurnTime(int time) {
		burnTime=time;
		return this;
	}
	
	public ItemBlockVariants(Block block,ResourceLocation resourceLocation) {
		this(block);
		setRegistryName(resourceLocation);
	}
	public String getUnlocalizedName(ItemStack stack) {
		return (super.getUnlocalizedName()+"_"+((IMetaName)this.block).getSpecialName(stack));
	}
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return burnTime;
	}

}
