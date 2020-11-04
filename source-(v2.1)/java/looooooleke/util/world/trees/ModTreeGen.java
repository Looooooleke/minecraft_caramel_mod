package looooooleke.util.world.trees;

import java.util.Random;

import looooooleke.caramel3.CaramelItems;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModTreeGen implements IWorldGenerator{
	public ModTreeGen() {
	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		CaramelItems.gen_random=random;
		CaramelItems.gen_X=chunkX;
		CaramelItems.gen_Z=chunkZ;
		CaramelItems.gen_world = world;
		switch(world.provider.getDimension()) {
		case 0:
			CaramelItems.generateOverworldTrees();
		}
		
	}
	
	
	

}
