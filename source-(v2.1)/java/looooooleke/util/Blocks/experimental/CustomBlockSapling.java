package looooooleke.util.Blocks.experimental;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import looooooleke.caramel3.Caramel3;
import looooooleke.caramel3.CaramelItems;
import looooooleke.caramel3.util.References;
import looooooleke.util.Blocks.BlockBase;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Interfaces.IMetaName;
import looooooleke.util.Items.ItemBase;
import looooooleke.util.Items.ItemBlockVariants;
import looooooleke.util.world.trees.MyWorldGenTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class CustomBlockSapling extends BlockBush implements IGrowable, IMetaName, IHasModel{

	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 3);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    public List<String> names = new ArrayList<String>();
    public String baseName;
    
	public CustomBlockSapling add(String name) {
		names.add(name);
		return this;
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn,rand, pos, state);
            }
        }
    }
    
    public CustomBlockSapling(String name) {
    	setRegistryName(name);
    	setUnlocalizedName(name);
    	baseName=name;
    	setCreativeTab(Caramel3.MyTab);
    	setSoundType(SoundType.PLANT);
    	setDefaultState(this.blockState.getBaseState().withProperty(STAGE,0));
    	BlockBase.LIST.add(this);
    	ItemBase.LIST.add(new ItemBlockVariants(this,this.getRegistryName()).setBurnTime(100));
    }
    public CustomBlockSapling() {}
    
    @Override public void RegisterModels() {
		NonNullList<ItemStack> metaItems=NonNullList.<ItemStack>create();
		this.getSubBlocks(Caramel3.MyTab, metaItems);
		for(int i=0;i<names.size();i++) {
			System.out.println("_______________________________________________"+i+" "+References.MODID+" "+baseName+"_"+names.get(i));
			Caramel3.proxy.RegisterModel(Item.getItemFromBlock(this), i, new ModelResourceLocation(new ResourceLocation(References.MODID,baseName+"_"+names.get(i)),"inventory"));
		}
	}
    
    
    //____________________________SHAPE______________________________
    @Override public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {	return SAPLING_AABB; }
    @Override public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {return NULL_AABB; }
    @Override public boolean isOpaqueCube(IBlockState state) {return false;}
    @Override public boolean isFullCube(IBlockState state) {return false;}
    //___________________________METAS________________________________________________
    @Override public String getSpecialName(ItemStack stack) {if(stack.getItemDamage()>=names.size()) {System.out.println("_________________________________OUT OF BOUNDS: "+stack.getItemDamage());return names.get(0);}return names.get(stack.getItemDamage());}
    @Override public int damageDropped(IBlockState state) {return getMetaFromState(state);}
    @Override public IBlockState getStateFromMeta(int meta) {return this.getDefaultState().withProperty(TYPE, meta & 3).withProperty(STAGE, (meta & 8)>>3);}
    @Override public int getMetaFromState(IBlockState state) {int i=0;i=i|state.getValue(TYPE).intValue();i=i|8*state.getValue(STAGE); return i;}
    @Override protected BlockStateContainer createBlockState() {return new BlockStateContainer(this,new IProperty[] {TYPE,STAGE});}
    @Override public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {for(int i=0;i<names.size();i++)items.add(new ItemStack(this,1,i));}
    
    //________________________________TREE________________________________________
	@Override public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {return true;}
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return rand.nextDouble()<0.45;
	}
	@Override
	protected boolean canSustainBush(IBlockState state) {
		return state.getBlock()==Blocks.GRASS||state.getBlock()==Blocks.DIRT;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		if(state.getValue(STAGE)==0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
			System.out.println("STAGE = 0");
			// FLAG 1 : Block update
			// FLAG 2 : send change to client
			// FLAG 4 : prevent block from being re-rendered
			// FLAG 8 : re-renders always run on main thread (client)
			// FLAG 16: Observers don't see change
		}else {
			this.generateTree(worldIn,rand,pos,state);
		}
		
	}	

	private void generateTree(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		//System.out.println("_____________________________GEN TREE");
		if(!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
		@SuppressWarnings("deprecation") WorldGenerator gen=(WorldGenerator)new MyWorldGenTree(CaramelItems.LOGS.getStateFromMeta(getMetaFromState(state)&3), 
				CaramelItems.LEAVES.getStateFromMeta(getMetaFromState(state)).withProperty(BlockLeaves2.DECAYABLE, true).withProperty(BlockLeaves2.CHECK_DECAY, false), this, 12+rand.nextInt(6));
		boolean flag=false;
		//int i=0,j=0;
			
		// TODO : 2x2 trees
		
		IBlockState ibs=Blocks.AIR.getDefaultState();
		if(flag) {
			worldIn.setBlockState(pos.add(0,0,0), ibs,4);
			worldIn.setBlockState(pos.add(1,0,0), ibs,4);
			worldIn.setBlockState(pos.add(0,0,1), ibs,4);
			worldIn.setBlockState(pos.add(0,1,0), ibs,4);
		}else {
			worldIn.setBlockState(pos, ibs, 4);
		}
		if(!gen.generate(worldIn,rand,pos)) {
			if(flag) {
				worldIn.setBlockState(pos.add(0,0,0), state,4);
				worldIn.setBlockState(pos.add(1,0,0), state,4);
				worldIn.setBlockState(pos.add(0,0,1), state,4);
				worldIn.setBlockState(pos.add(0,1,0), state,4);
			}else {
				worldIn.setBlockState(pos, state, 4);
			}
		}
		
	}

	
}