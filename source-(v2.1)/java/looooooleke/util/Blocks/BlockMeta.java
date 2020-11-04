package looooooleke.util.Blocks;

import java.util.ArrayList;

import looooooleke.caramel3.Caramel3;
import looooooleke.caramel3.util.References;
import looooooleke.caramel3.util.text.StringTools;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Interfaces.IMetaName;
import looooooleke.util.Items.ItemBase;
import looooooleke.util.Items.ItemBlockVariants;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockMeta extends Block implements IMetaName,IHasModel{
	public ArrayList<String> names;
	public String baseName;
	public static PropertyInteger type=PropertyInteger.create("type", 0, 15);
	
	public BlockMeta(String name) {
		this(name,Material.ROCK);
	}
	public BlockMeta(String name,Material material) {
		super(material);
		names=new ArrayList<String>();
		baseName = name;
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(Caramel3.MyTab);
		BlockBase.LIST.add(this);
		ItemBase.LIST.add(new ItemBlockVariants(this).setRegistryName(name));
	}
	
	public BlockMeta(String name,Material material,int burnTime) {
		super(material);
		names=new ArrayList<String>();
		baseName = name;
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(Caramel3.MyTab);
		BlockBase.LIST.add(this);
		ItemBase.LIST.add(new ItemBlockVariants(this).setBurnTime(burnTime).setRegistryName(name));
	}
	
	public BlockMeta add(String name) {
		names.add(name);
		return this;
	}
	public BlockMeta set(float hardness,float explosionRes,String tool,int harvestLevel) {
		setHardness(hardness);
		setResistance(explosionRes);
		setHarvestLevel(tool, harvestLevel);
		return this;
	}
	@Override
	public BlockMeta setSoundType(SoundType sound) {
		super.setSoundType(sound);
		return this;
	}
	
	public BlockMeta addList(String nameList) {
		String[] n = StringTools.splits(nameList, ' ');
		for(String i:n)names.add(i);
		return this;
	}
	
	@Override public void RegisterModels() {
		NonNullList<ItemStack> metaItems=NonNullList.<ItemStack>create();
		this.getSubBlocks(Caramel3.MyTab, metaItems);
		for(int i=0;i<names.size();i++) {
			Caramel3.proxy.RegisterModel(Item.getItemFromBlock(this), i, new ModelResourceLocation(new ResourceLocation(References.MODID,baseName+"_"+names.get(i)),"inventory"));
		}	
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		return (super.getUnlocalizedName()+"_"+names.get(stack.getMetadata()));
	}
	
	
	
	@Override public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(type, meta);}
	@Override public int getMetaFromState(IBlockState state) {
		return state.getValue(type);}
	

	@Override public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for(int i=0;i<names.size();i++) items.add(new ItemStack(this,1,i));}
	@Override public int damageDropped(IBlockState state) {
		return state.getValue(type);}
	@Override public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,EntityPlayer player) {
		return new ItemStack(this,1,state.getValue(type));}
	@Override protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this,new IProperty[] {type});}
	
	
	@Override public String getSpecialName(ItemStack stack) {
		return names.get(stack.getMetadata());}
}