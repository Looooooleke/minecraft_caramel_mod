package looooooleke.util.Blocks;

import java.util.ArrayList;
import java.util.List;

import looooooleke.caramel3.Caramel3;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel{
	public static List<Block> LIST = new ArrayList<Block>();
	
	public BlockBase(String name,Material material) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(Caramel3.MyTab);
		LIST.add(this);
		ItemBase.LIST.add(new ItemBlock(this).setRegistryName(name));
	}
	public BlockBase(String name) {
		this(name, Material.ROCK);
	}

	@Override
	public void RegisterModels() {
		//Caramel3.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
		//Caramel3.proxy.RegisterModel(Item.getItemFromBlock(this));
		Caramel3.proxy.RegisterModel(this);
	}
}
