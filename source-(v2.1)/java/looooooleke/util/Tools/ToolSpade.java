package looooooleke.util.Tools;

import looooooleke.caramel3.Caramel3;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Items.ItemBase;

import net.minecraft.item.ItemSpade;

public class ToolSpade extends ItemSpade implements IHasModel{
	public ToolSpade(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Caramel3.MyTab);
		
		ItemBase.LIST.add(this);
	}
	@Override public void RegisterModels() {Caramel3.proxy.RegisterModel(this);}
}
