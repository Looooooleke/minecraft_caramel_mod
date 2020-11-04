package looooooleke.util.Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSlimy extends BlockTransparent{

	public BlockSlimy(String name, float hardness) {
		super(name, Material.CLAY);
		setSoundType(SoundType.SLIME);
		setDefaultSlipperiness(0.8f);
		setHarvestLevel("shovel", 0);
		setHardness(hardness);
	}
	@Override
	public boolean isStickyBlock(IBlockState state) {
		return true;
	}
	
	 public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	    {
	        if (entityIn.isSneaking())
	        {
	            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	        }
	        else
	        {
	            entityIn.fall(fallDistance, 0.0F);
	        }
	    }

	    public void onLanded(World worldIn, Entity entityIn)
	    {
	        if (entityIn.isSneaking())
	        {
	            super.onLanded(worldIn, entityIn);
	        }
	        else if (entityIn.motionY < 0.0D)
	        {
	            entityIn.motionY = -entityIn.motionY;

	            if (!(entityIn instanceof EntityLivingBase))
	            {
	                entityIn.motionY *= 0.8D;
	            }
	        }
	    }
	    
	    

}
