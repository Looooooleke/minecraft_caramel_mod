package looooooleke.util.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockOre extends BlockBase{
	public Item itemDropped = new ItemBlock(this);
	public int min_amount_dropped=1, max_amount_dropped=1;
	public int exp_dropped =0;
	
	public BlockOre(String name, Material material,int harvestLevel,String tool,float hardness,Item drop,int drop_min,int drop_max,int exp_drop) {
		super(name, material);
		itemDropped = drop;
		setHardness(hardness);
		setHarvestLevel(tool, harvestLevel, this.getDefaultState());
		if(drop_max>drop_min) {
			min_amount_dropped = drop_min;
			max_amount_dropped = drop_max;
		}else {
			min_amount_dropped = drop_max;
			max_amount_dropped = drop_min;
		}
		exp_dropped = exp_drop;
	}
	
	public BlockOre(String name, int harvestLevel,float hardness,Item drop,int drop_min,int drop_max,int exp_drop) {
		this(name,Material.ROCK,harvestLevel,"pickaxe",hardness,drop,drop_min,drop_max,exp_drop);
	}
	public BlockOre(String name, int harvestLevel,float hardness) {
		super(name,Material.ROCK);
		setHardness(hardness);
		setHarvestLevel("pickaxe",harvestLevel,this.getDefaultState());
	}
	
	public BlockOre set(Item drop, int drop_min, int drop_max, int exp_drop) {
		itemDropped = drop;
		if(drop_max>drop_min) {
			min_amount_dropped = drop_min;
			max_amount_dropped = drop_max;
		}else {
			min_amount_dropped = drop_max;
			max_amount_dropped = drop_min;
		}
		exp_dropped = exp_drop;
		return this;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(max_amount_dropped-min_amount_dropped)+min_amount_dropped;
	}
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return itemDropped;
	}
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return exp_dropped;
	}
}
