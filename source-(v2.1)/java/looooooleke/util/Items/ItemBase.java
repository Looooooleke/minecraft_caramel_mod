package looooooleke.util.Items;

import java.util.ArrayList;
import java.util.List;

import looooooleke.caramel3.Caramel3;
import looooooleke.util.Interfaces.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{

	public static List<Item> LIST = new ArrayList<Item>();
	
	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(Caramel3.MyTab);
		LIST.add(this);
	}
	
	public ItemBase(Item item) {
		setRegistryName(item.getRegistryName());
		setUnlocalizedName(item.getUnlocalizedName());
		setCreativeTab(Caramel3.MyTab);
		LIST.add(this);
	}
	
	
	@Override public void RegisterModels() {Caramel3.proxy.RegisterModel(this);}

}
