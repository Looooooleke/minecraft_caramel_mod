package looooooleke.util.Blocks.Metas;

import looooooleke.util.Blocks.BlockMeta;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWood extends BlockMeta{

	public BlockWood(String name) {
		super(name,Material.WOOD,300);
	}
	@Override public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for(int i=0;i<names.size()/4;i++) items.add(new ItemStack(this,1,i));}
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		String ax=facing.getAxis().name();
		if(ax=="Z") return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(type, 4+meta);
		if(ax=="X") return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(type, 8+meta);
		return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(type, meta);
    }
	@Override public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {return true;}
	
	@Override public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,EntityPlayer player) {
		return new ItemStack(this,1,state.getValue(type)%4);}
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.add(new ItemStack(this,1,getMetaFromState(state)%4));
	}
}
