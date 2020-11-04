package looooooleke.util.Tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ToolSet {
	private ToolMaterial material;
	public ToolAxe axe;
	public ToolSword sword;
	public ToolPickaxe pickaxe;
	public ToolSpade shovel;
	public ToolHoe hoe;
	public ToolSet(String name, int harvestLevel,int maxUses,float efficiency,float damage,int enchantability) {
		material = EnumHelper.addToolMaterial("material_"+name, harvestLevel, maxUses, efficiency, damage, enchantability);
		axe=new ToolAxe(name+"_axe",material);
		sword=new ToolSword(name+"_sword",material);
		pickaxe=new ToolPickaxe(name+"_pickaxe",material);
		hoe=new ToolHoe(name+"_hoe",material);
		shovel=new ToolSpade(name+"_shovel",material);
	}
}
