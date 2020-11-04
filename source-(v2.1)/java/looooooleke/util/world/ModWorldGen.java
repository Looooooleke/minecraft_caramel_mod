package looooooleke.util.world;


import java.util.Random;

import looooooleke.caramel3.CaramelItems;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int ChunkX,int ChunkZ, World world, IChunkGenerator chunkgenerator, IChunkProvider chunkprovider) {
		
		CaramelItems.gen_world=world;
		CaramelItems.gen_X=ChunkX;
		CaramelItems.gen_Z = ChunkZ;
		CaramelItems.gen_random=random;
		
		switch(world.provider.getDimension()) {
		case 0:	CaramelItems.generateOverworld(random, ChunkX,ChunkZ,world,chunkgenerator,chunkprovider);break;
		case -1:CaramelItems.generateNether(random, ChunkX,ChunkZ,world,chunkgenerator,chunkprovider);break;
		case 1:CaramelItems.generateEnd(random, ChunkX,ChunkZ,world,chunkgenerator,chunkprovider);break;
		default: break;
		}
	}
}
	
