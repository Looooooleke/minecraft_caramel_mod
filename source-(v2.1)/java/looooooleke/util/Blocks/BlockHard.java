package looooooleke.util.Blocks;

import net.minecraft.block.material.Material;

public class BlockHard extends BlockBase{

	public BlockHard(String name, float hardness, int mineLevel) {
		super(name,Material.ROCK);
		setHardness(hardness);
		setHarvestLevel("pickaxe",mineLevel);
		setResistance(10*hardness);
	}

}
