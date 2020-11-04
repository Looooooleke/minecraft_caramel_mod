package looooooleke.caramel3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import looooooleke.util.world.ModWorldGenMinable;
import looooooleke.util.Armor.ArmorSet;
import looooooleke.util.Blocks.*;
import looooooleke.util.Blocks.Metas.BlockWood;
import looooooleke.util.Blocks.experimental.BlockLeaves2;
import looooooleke.util.Blocks.experimental.CustomBlockSapling;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Items.ItemBase;
import looooooleke.util.Items.food.FoodEffectsBase;
import looooooleke.util.Items.food.FoodMultiEffectsBase;
import looooooleke.util.Tools.ToolSet;
import looooooleke.util.world.MyPredicate;
import looooooleke.util.world.trees.MyWorldGenTree;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.actors.threadpool.Arrays;

public class CaramelItems {
	
	
	public static World gen_world;
	public static int gen_X, gen_Z;
	public static Random gen_random;
	
	//__________________________________PREDICATES___________________________________
	
	public static final MyPredicate PR_NETHER 		= 	new MyPredicate().add(Blocks.NETHERRACK);
	public static final MyPredicate PR_OVERWORLD 	= 	new MyPredicate().add(Blocks.STONE);
	public static final MyPredicate PR_END 			= 	new MyPredicate().add(Blocks.END_STONE);
	
	//_________________________________________FOOD / POTIONS / EFFECTS___________________________________________
	
	public static final PotionEffect EFFECT_SPEED 		=	new PotionEffect(MobEffects.SPEED,300,0,false, true);
	public static final PotionEffect EFFECT_HASTE 		=	new PotionEffect(MobEffects.HASTE, 300, 0, false, true);
	public static final PotionEffect EFFECT_JUMP 		=	new PotionEffect(MobEffects.JUMP_BOOST,300,0,false, true);
	public static final PotionEffect EFFECT_STRENGTH 	=	new PotionEffect(MobEffects.STRENGTH,300,0,false, true);
	
	public static final Item SNICKER_BAR 	=	new FoodMultiEffectsBase("snicker_bar", 6, 3f, false,EFFECT_SPEED).add(EFFECT_HASTE).add(EFFECT_JUMP).add(EFFECT_STRENGTH);
	public static final Item CHOCOLATE 		= 	new FoodEffectsBase("chocolate", 4, 2.0f, false,EFFECT_HASTE);
	public static final Item CARAMEL 		= 	new FoodEffectsBase("caramel", 3, 1.5f, false,new PotionEffect(MobEffects.SPEED,300,0,false,true));
	
	
	
	//______________________________________NORMAL ITEMS / BLOCKS___________________________________________
	
	public static final BlockBase 	BLOCK_CARAMEL 			= new BlockSlimy("block_caramel", 3.0f),
									BLOCK_CHOCOLATE 		= new BlockHard("block_chocolate", 3.0f, 0),
									ORE_CARAMEL 			= new BlockOre("ore_caramel", 1, 5.0f).set(CARAMEL, 1, 3, 2),
									ORE_CHOCOLATE			= new BlockOre("ore_chocolate", 1, 4.0f).set(CHOCOLATE, 1, 3, 3);
	
	public static final ToolSet 	TOOLSET_CARAMEL			= new ToolSet("caramel", 2, 350, 6, 3, 10),
									TOOLSET_CHOCOLATE		= new ToolSet("chocolate", 2, 500, 5, 2, 10);
	
	public static final ArmorSet 	ARMORSET_CARAMEL 		= new ArmorSet("caramel", 4, 6, 4, 4, 8),
									ARMORSET_CHOCOLATE 		= new ArmorSet("chocolate", 3, 5, 4, 3, 10);
	
	

	
	 // ______________________________________META ITEMS / BLOCKS_______________________________________
	
	//public static final Item TEST2 			= 	new ItemMeta("test").addList("1 2");
	
	public static final Block 	PLANKS 		= 	new BlockMeta("planks",Material.WOOD,300).addList("caramel chocolate").set(2.0f, 20, "axe", 0).setSoundType(SoundType.WOOD),
								LOGS 		= 	new BlockWood("log").addList("caramel chocolate caramel_x chocolate_x caramel_z chocolate_z caramel_b chocolate_b").set(2.0f, 20, "axe", 0).setSoundType(SoundType.WOOD),
								LEAVES 		= 	new BlockLeaves2("leaves").add("caramel").add("chocolate"),
								SAPLINGS 	= 	new CustomBlockSapling("sapling").add("caramel").add("chocolate");
	
	//____________________________________TREES_____________________________________________________
	
	public static IPlantable sapling=new CustomBlockSapling();
	public static final MyWorldGenTree CARAMEL_TREE = new MyWorldGenTree(LOGS.getDefaultState().withProperty(BlockWood.type, 0),LEAVES.getDefaultState().withProperty(BlockLeaves2.TYPE, 0),sapling,12);
	public static final MyWorldGenTree CHOCOLATE_TREE = new MyWorldGenTree(LOGS.getDefaultState().withProperty(BlockWood.type, 1),LEAVES.getDefaultState().withProperty(BlockLeaves2.TYPE, 1),sapling,12);
	
	//_______________________________REGISTRY / GENERATION : MAIN FUNCTIONS____________________________
	
	public static void RegisterModels(ModelRegistryEvent event) {
		// (items and blocks generally added to ItemBase/BlockBase lists in constructor)
		RegisterILM(ItemBase.LIST);
		RegisterBLM(BlockBase.LIST);
	}
	
	public static void RegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemBase.LIST.toArray(new Item[0]));
	}
	
	public static void RegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockBase.LIST.toArray(new Block[0]));
	}
	
	public static void RegisterSmeltRecipes() {
		GameRegistry.addSmelting(Items.SUGAR, new ItemStack(CARAMEL), 3);
		GameRegistry.addSmelting(LOGS, new ItemStack(Items.COAL,1,1), 1);
	}
	
	
	//_______________________________________GENERATE ORES__________________________________________________
	public static void generateOverworld(Random r, int x,int z, World w, IChunkGenerator chunkgenerator, IChunkProvider chunkprovider) {
		//									 Y		Size  Chances		Predicate
		generateSmallOre(ORE_CARAMEL,		0,22,	3,5,	10,		PR_OVERWORLD);
		generateSmallOre(ORE_CHOCOLATE,		16,48,	3,5,	12, 	PR_OVERWORLD);
	}
	
	public static void generateNether(Random r, int x,int z, World w, IChunkGenerator chunkgenerator, IChunkProvider chunkprovider) {
		//									 Y			Size  Chances		Predicate
		generateSmallOre(BLOCK_CARAMEL,		35,100,		3,2,	5,			PR_NETHER);
		generateSmallOre(BLOCK_CHOCOLATE,	35,100,		3,3,	4,			PR_NETHER);
	}
	
	public static void generateEnd(Random r, int x,int z, World w, IChunkGenerator chunkgenerator, IChunkProvider chunkprovider) {
		//								 Y			Size 	 Chances		Predicate
		generateOre(BLOCK_CARAMEL,		10,200,		6,2,		50,			PR_END);
		generateOre(BLOCK_CHOCOLATE,	10,200,		7,1,		40,			PR_END);
	}
	//______________________________________GENERATE TREES___________________________________________________________
	public static void generateOverworldTrees() {
		SpawnTrees(CHOCOLATE_TREE,0.003,-1,0);
		SpawnTrees(CARAMEL_TREE,0.003,-1,0);
	}
	
	
	//______________________________________________SUB FUNCTIONS_______________________________________________________________
	
	
	//________________________________________________REGISTRY_______________________________________________
	
	public static void RegisterILM(List<Item> items) { // item list models
		for(Item ib:items.toArray(new Item[0])) if(ib instanceof IHasModel) ((IHasModel)ib).RegisterModels();
	}
	
	public static void RegisterBLM(List<Block> blocks) { // block list models
		for(Block ib:blocks.toArray(new Block[0])) if(ib instanceof IHasModel) ((IHasModel)ib).RegisterModels();
	}
		
	//______________________________________________GENERATE ORES_________________________________________________________________
	
	public static void generateOre(Block block, int minY, int maxY,int minSize,int sizeDiff,int chances,MyPredicate predicate) {
		for(int i=0;i<chances;i++) {
			IBlockState ore = block.getDefaultState();
			BlockPos pos=new BlockPos(16*gen_X+gen_random.nextInt(16),minY+gen_random.nextInt(maxY-minY),16*gen_Z+gen_random.nextInt(16));
			ModWorldGenMinable generator = new ModWorldGenMinable(ore, minSize+gen_random.nextInt(sizeDiff),predicate);
			generator.generate(gen_world, gen_random, pos);
		}
	}
	public static void generateSmallOre(Block block, int minY, int maxY,int minSize,int sizeDiff,int chances,MyPredicate predicate) {
		for(int i=0;i<chances;i++) {
			IBlockState ore = block.getDefaultState();
			BlockPos pos=new BlockPos(16*gen_X+gen_random.nextInt(16),minY+gen_random.nextInt(maxY-minY),16*gen_Z+gen_random.nextInt(16));
			if(sizeDiff>0) {
				ModWorldGenMinable generator = new ModWorldGenMinable(ore, minSize+gen_random.nextInt(sizeDiff),predicate);
				generator.generate_small(gen_world, gen_random, pos);
			}else {
				ModWorldGenMinable generator = new ModWorldGenMinable(ore, minSize,predicate);
				generator.generate_small(gen_world, gen_random, pos);
			}
		}
	}
	//______________________________________________________SPAWN TREES_______________________________________________________________________
	@SuppressWarnings("unchecked")
	public static void SpawnTrees(WorldGenerator gen, double ChancesToSpawn,int minHeight,int maxHeight,Class<?>... biome) {
		double c=ChancesToSpawn;
		int chances = 0;
		for(;chances<ChancesToSpawn&&chances<20;chances++);
		chances--;
		c -=chances;
		if(gen_random.nextDouble()<c)chances++;
		ArrayList<Class<?>> biomes=new ArrayList<Class<?>>();
		if(biome.length!=0)biomes = new ArrayList<Class<?>>(Arrays.asList(biome));
		int heightDiff=maxHeight-minHeight+1;
		for(int i=0;i<chances;i++) {
			BlockPos pos = new BlockPos(gen_X*16+gen_random.nextInt(15),minHeight+gen_random.nextInt(heightDiff),gen_Z*16+gen_random.nextInt(15));
			if(minHeight<0)pos=gen_world.getHeight(pos);
			Class<?> posBiome = gen_world.getBiomeForCoordsBody(pos).getClass();
			if(biomes.contains(posBiome)||biome.length==0) gen.generate(gen_world, gen_random, pos);
		}
	}
	
}
