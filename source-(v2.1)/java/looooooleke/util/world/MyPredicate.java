package looooooleke.util.world;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class MyPredicate implements Predicate<IBlockState>{

	public List<Block> replacables = new ArrayList<Block>();
	public MyPredicate add(IBlockState state) {
		return add(state.getBlock());
	}
	public MyPredicate add(Block block) {
		
		replacables.add(block);
		//System.out.println("____________________________________ADDED STATE");
		return this;
	}
	@Override
	public boolean apply(IBlockState input) {
		//System.out.println("___________________________________________APPLY");
		if(input==null) {
			//System.out.println("____________________________________STATE FALSE");
			return false;
		}
		Block ts[]=replacables.toArray(new Block[0]);
		for(int i=0;i<replacables.size();i++) {
			if(ts[i]==input.getBlock()) {
				
				//System.out.println("_____________________________STATE TRUE");
				return true;
			}
		}
		//System.out.println("____________________________________STATE FALSE");
		return false;
	}
}
