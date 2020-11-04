package looooooleke.caramel3.util.tabs;

import looooooleke.caramel3.CaramelItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CaramelTab extends CreativeTabs{
	public CaramelTab() {super("caramel");}
	@Override public ItemStack getTabIconItem() {return new ItemStack(CaramelItems.CARAMEL);}
}
