package looooooleke.util.Armor;

import looooooleke.caramel3.util.References;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorSet {
	public ArmorBase[] set;
	public ArmorMaterial mat;
	public ArmorSet(String name,String textureName,int durability,int[] reductionAmounts,int enchantability,SoundEvent soundOnEquip, float toughness) {
		mat=EnumHelper.addArmorMaterial("armor_"+name, textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
		set = new ArmorBase[4];
		set[0]=new ArmorBase(name+"_helmet", mat, 1, EntityEquipmentSlot.HEAD);
		set[1]=new ArmorBase(name+"_chestplate", mat, 1, EntityEquipmentSlot.CHEST);
		set[2]=new ArmorBase(name+"_leggings", mat, 2, EntityEquipmentSlot.LEGS);
		set[3]=new ArmorBase(name+"_boots", mat, 1, EntityEquipmentSlot.FEET);
	}
	public ArmorSet(String name, int reductionHelm,int reductionChest,int reductionLegs,int reductionFeet,int durability) {
		this(name,References.MODID+":"+name,durability,new int[] {reductionHelm,reductionChest,reductionLegs,reductionFeet},10,SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f);
	}
}
