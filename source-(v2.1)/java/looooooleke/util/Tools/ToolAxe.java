package looooooleke.util.Tools;

import looooooleke.caramel3.Caramel3;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Items.ItemBase;

import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel{
	public ToolAxe(String name,ToolMaterial mat) {
		super(mat,mat.getAttackDamage()+4,-3.0f);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Caramel3.MyTab);
		
		ItemBase.LIST.add(this);
	}
	@Override public void RegisterModels() {Caramel3.proxy.RegisterModel(this);}
}
