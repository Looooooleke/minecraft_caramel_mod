package looooooleke.util.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTransparent extends BlockBase{
	public BlockTransparent(String name, Material material) {
		super(name,material);
	}
	 @SideOnly(Side.CLIENT)

	 	@Override
	    public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }

	 @Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	


	
	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

            if (blockState != iblockstate)
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
	
}
