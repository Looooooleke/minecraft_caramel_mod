package looooooleke.util.world.trees;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class MyWorldGenTree extends WorldGenAbstractTree{
	
	public IBlockState LOG,LEAF;
	public int minHeight;
	public IPlantable sapling;
	
	public MyWorldGenTree(IBlockState log,IBlockState leaf,IPlantable sapling, int minHeight) {
		super(false);
		LOG= log;
		LEAF = leaf;
		this.sapling= sapling;
		this.minHeight = minHeight;
	}
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		int height = minHeight+rand.nextInt(3);
		int x = pos.getX(),y = pos.getY(),z=pos.getZ();
		boolean flag=true;
		
		for(int i=y;i<y+height;i++) {
			int b0=2;
			if(i==y||i==y+1)b0=0;
			if(i>=y+1+height-2)b0=0;
			for(int j=x-b0;(j<=x+b0)&&flag;j++) {
				
				for(int k=z-b0;(k<=z+b0)&&flag;k++) {
					if(i>=0&&i<worldIn.getHeight()) {
						if(!this.isReplaceable(worldIn, new BlockPos(j,i,k))) flag=false;
					}
				}
			}
		}
		
		if(!flag) return false;
		BlockPos down=pos.down();
		IBlockState state=worldIn.getBlockState(down);
		boolean isSoil=state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, sapling);
		if(!isSoil) return false;
		if(y+height>worldIn.getHeight()-1)return false;
		
		state.getBlock().onPlantGrow(state, worldIn, down, pos);
		
		for(int yPos=y+height-3;yPos<y+height;yPos++) {
			int b1 = yPos-y-height;
			int b2 = 1-b1/2;
			for(int xPos = x-b2;xPos<=x+b2;xPos++) {
				int b3= xPos-x;
				for(int zPos = z-b2;zPos<=z+b2;zPos++) {
					int b4 = zPos-z;
					if(Math.abs(b3)!=b2||Math.abs(b4)!=b2||rand.nextInt(2)!=0||b1!=0) {
						BlockPos treePos = new BlockPos(xPos,yPos,zPos);
						IBlockState treeState = worldIn.getBlockState(treePos);
						if(treeState.getBlock().isAir(treeState, worldIn, treePos)) {
							this.setBlockAndNotifyAdequately(worldIn, treePos, LEAF);
							this.setBlockAndNotifyAdequately(worldIn, treePos.add(0,-0.25*height,0), LEAF);
							this.setBlockAndNotifyAdequately(worldIn, treePos.add(0,-0.5*height,0), LEAF);
						}
					}
				}
			}
		}
		
	for(int logH=0;logH<height-1;logH++) {
		BlockPos up=pos.up(logH);
		IBlockState blockstate = worldIn.getBlockState(up);
		if(blockstate.getBlock().isAir(blockstate, worldIn, up)||blockstate.getBlock().isLeaves(blockstate, worldIn, up)) {
			this.setBlockAndNotifyAdequately(worldIn, pos.up(logH), LOG);
		}
	}
		
		return true;
	}
}
