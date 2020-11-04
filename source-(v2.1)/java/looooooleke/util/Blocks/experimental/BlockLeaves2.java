package looooooleke.util.Blocks.experimental;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import looooooleke.caramel3.Caramel3;
import looooooleke.caramel3.CaramelItems;
import looooooleke.caramel3.util.References;
import looooooleke.util.Blocks.BlockBase;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Interfaces.IMetaName;
import looooooleke.util.Items.ItemBase;
import looooooleke.util.Items.ItemBlockVariants;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeaves2 extends BlockLeaves implements IMetaName,IHasModel{

	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);
	public List<String> names = new ArrayList<String>();
	public String baseName;
	public static Random r = new Random();
	public float saplingDropChance = 0.05f;
	
	public BlockLeaves2(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		baseName=name;
		setCreativeTab(Caramel3.MyTab);
		setSoundType(SoundType.PLANT);
		BlockBase.LIST.add(this);
		ItemBase.LIST.add(new ItemBlockVariants(this).setRegistryName(name));
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,int fortune) {
		if(r.nextFloat()<saplingDropChance) drops.add(new ItemStack(CaramelItems.SAPLINGS,1,getMetaFromState(state)%4));
		if(names.get(getMetaFromState(state)%4)=="caramel"&&r.nextFloat()<0.02)drops.add(new ItemStack(CaramelItems.CARAMEL,1,0));
		if(names.get(getMetaFromState(state)%4)=="chocolate"&&r.nextFloat()<0.02)drops.add(new ItemStack(CaramelItems.CHOCOLATE,1,0));
	}
	
	public BlockLeaves2 add(String name) {
		names.add(name);
		return this;
	}
	
	@Override public int getMetaFromState(IBlockState state) {
		int i = state.getValue(TYPE);
		if(!((Boolean)state.getValue(DECAYABLE).booleanValue())){i+=4;}
		if(!((Boolean)state.getValue(CHECK_DECAY).booleanValue())){i+=8;}
		return i;
	}

	@Override public String getSpecialName(ItemStack stack) {
		if(stack.getItemDamage()>=names.size()) {
			System.out.println("_________________________________OUT OF BOUNDS: "+stack.getItemDamage());
			return names.get(0);
		}
		return names.get(stack.getItemDamage());
	}
	
	@Override public void RegisterModels() {
		NonNullList<ItemStack> metaItems=NonNullList.<ItemStack>create();
		this.getSubBlocks(Caramel3.MyTab, metaItems);
		for(int i=0;i<names.size();i++) {
			System.out.println("_______________________________________________"+i+" "+References.MODID+" "+baseName+"_"+names.get(i));
			Caramel3.proxy.RegisterModel(Item.getItemFromBlock(this), i, new ModelResourceLocation(new ResourceLocation(References.MODID,baseName+"_"+names.get(i)),"inventory"));
		}
	}
	
	
	// SilkDrop damageDropped dropApple saplingDropChance onSheared getWoodType isOpaqueC getBlockLayer createBlockState
	// shouldSideBeRendered getStateFromMeta getSubBlocks
		
	@Override protected ItemStack getSilkTouchDrop(IBlockState state) {return new ItemStack(this,1,state.getValue(TYPE));}
	@Override public int damageDropped(IBlockState state) {return state.getValue(TYPE);}
	@Override protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}
	@Override protected int getSaplingDropChance(IBlockState state) {return 20;}
	@Override public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {return NonNullList.withSize(1, new ItemStack(this,1,world.getBlockState(pos).getValue(TYPE)));}
	@Override public EnumType getWoodType(int meta) {return null;}
	@Override public boolean isOpaqueCube(IBlockState state) {return Blocks.LEAVES.isOpaqueCube(state);}
	@Override public BlockRenderLayer getBlockLayer() {return Blocks.LEAVES.getBlockLayer();}
	@Override protected BlockStateContainer createBlockState() {return new BlockStateContainer(this,new IProperty[] {TYPE,DECAYABLE,CHECK_DECAY});}
	//@Override public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,EnumFacing side) { return !this.leavesFancy && blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);}
	@Override public IBlockState getStateFromMeta(int meta) {return this.getDefaultState().withProperty(TYPE, meta % 4).withProperty(DECAYABLE, ((meta%8)/4==1)).withProperty(CHECK_DECAY, (meta/8)==1);}
	@Override public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {for(int i=0;i<2;i++) {items.add(new ItemStack(this,1,i));}}
	

}
