package looooooleke.util.world;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ModWorldGenMinable extends WorldGenerator
{
   IBlockState oreState;
   int count;
   Predicate<IBlockState> pbs;

    public ModWorldGenMinable(IBlockState state, int blockCount){
        this(state, blockCount, new ModWorldGenMinable.StonePredicate());
    	System.out.println("_______________STONEPREDICATE");
    }

    public ModWorldGenMinable(IBlockState state, int blockCount, Predicate<IBlockState> pibs)
    {
        oreState=state;
        count=blockCount;
        pbs=pibs;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	//System.out.println("___________________________________________GENERATE "+count);
        float f = rand.nextFloat() * (float)Math.PI;
        double d0 = (double)((float)(position.getX() + 8) + MathHelper.sin(f) * (float)this.count / 8.0F);
        double d1 = (double)((float)(position.getX() + 8) - MathHelper.sin(f) * (float)this.count / 8.0F);
        double d2 = (double)((float)(position.getZ() + 8) + MathHelper.cos(f) * (float)this.count / 8.0F);
        double d3 = (double)((float)(position.getZ() + 8) - MathHelper.cos(f) * (float)this.count / 8.0F);
        double d4 = (double)(position.getY() + rand.nextInt(3) - 2);
        double d5 = (double)(position.getY() + rand.nextInt(3) - 2);
        //int tel=0;

        for (int i = 0; i < this.count; ++i)
        {
            float f1 = (float)i / (float)this.count;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d8 = d2 + (d3 - d2) * (double)f1;
            double d9 = rand.nextDouble() * (double)this.count / 16.0D;
            double d10 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor(d6 - d10 / 2.0D);
            int k = MathHelper.floor(d7 - d11 / 2.0D);
            int l = MathHelper.floor(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1)
            {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int j2 = l; j2 <= k1; ++j2)
                            {
                                double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    BlockPos blockpos = new BlockPos(l1, i2, j2);

                                    IBlockState state = worldIn.getBlockState(blockpos);
                                    //System.out.println("___________Pgen");
                                    if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, this.pbs))
                                    {
                                        worldIn.setBlockState(blockpos, this.oreState, 2);
                                        //tel++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println(tel);
        return true;
    }
    
    public boolean generate_small(World worldIn, Random rand, BlockPos position) {
    	//System.out.println("___________________________________________GENERATE "+count);
    	if(count<1||count>8) {
    		System.out.println("_______________GEFAALD: COUNT BUITEN RANGE");
    	}
    	//int tel=0;
    	boolean ifSet;
    	int setLoops;
    	boolean states[] = new boolean[8];
    	if(count<4) {
    		for(int i=0;i<8;i++) states[i]=false;
    		ifSet=true;
    		setLoops=count;
    	}else {
    		for(int i=0;i<8;i++) states[i]=true;
    		ifSet=false;
    		setLoops=8-count;
    		}
    	
    	for(int i=0;i<setLoops;i++) {
    		int t=rand.nextInt(8);
    		states[t]=ifSet;
    	}
    	
    	int x0=position.getX(); int y0=position.getY(); int z0=position.getZ();
    	int iterator=0;
    	for(int x = x0;x<x0+2;x++) {for(int y = y0;y<y0+2;y++) {for(int z=z0;z<z0+2;z++) {
    				if(states[iterator]) {
    					BlockPos blockpos = new BlockPos(x, y, z);
                        IBlockState state = worldIn.getBlockState(blockpos);
                       // System.out.println("?");
                        if (this.pbs.apply(state)){
                        	//System.out.println("+");
                            worldIn.setBlockState(blockpos, this.oreState, 2);
                            //tel++;
                        }
    				}
    				iterator++;
    	}}}
    	//System.out.println(iterator+" "+tel);
    	return true;
    }

    static class StonePredicate implements Predicate<IBlockState> {
            private StonePredicate() {}

            public boolean apply(IBlockState p_apply_1_){
            	System.out.println("___________________STONEPREDICATE");
                if (p_apply_1_ != null && p_apply_1_.getBlock() == Blocks.STONE){
                    BlockStone.EnumType blockstone$enumtype = (BlockStone.EnumType)p_apply_1_.getValue(BlockStone.VARIANT);
                    return blockstone$enumtype.isNatural();
                }else {
                    return false;
                }
            }
     }
}