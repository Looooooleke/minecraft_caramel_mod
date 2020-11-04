package looooooleke.util.Items;

import java.util.ArrayList;

import looooooleke.caramel3.Caramel3;
import looooooleke.caramel3.util.References;
import looooooleke.caramel3.util.text.StringTools;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemMeta extends ItemBase{
	public ArrayList<String> names;
	public String baseName;
	public ItemMeta(String name) {
		super(name);
		names=new ArrayList<String>();
		baseName = name;
		setHasSubtypes(true);
	}
	public ItemMeta add(String name) {
		names.add(name);
		return this;
	}
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		for(int i=0;i<names.size();i++) {
			items.add(new ItemStack(this,1,i));
		}
	}
	public ItemMeta addList(String nameList) {
		String[] n = StringTools.splits(nameList, ' ');
		for(String i:n)names.add(i);
		return this;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return (super.getUnlocalizedName(stack)+"_"+names.get(stack.getMetadata()));
	}
	@Override
	public void RegisterModels() {
		NonNullList<ItemStack> metaItems=NonNullList.<ItemStack>create();
		this.getSubItems(Caramel3.MyTab, metaItems);
		for(int i=0;i<names.size();i++) {
			Caramel3.proxy.RegisterModel(this, i, new ModelResourceLocation(new ResourceLocation(References.MODID,baseName+"_"+names.get(i)),"inventory"));
		}
	}
}
