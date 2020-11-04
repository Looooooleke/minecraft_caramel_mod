package looooooleke.util.Armor;

import looooooleke.caramel3.Caramel3;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Items.ItemBase;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IHasModel{

	public ArmorBase(String name,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Caramel3.MyTab);
		
		ItemBase.LIST.add(this);
	}
	@Override public void RegisterModels() {Caramel3.proxy.RegisterModel(this);}
}
