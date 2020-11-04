package looooooleke.util.Items.food;



import looooooleke.caramel3.Caramel3;
import looooooleke.util.Interfaces.IHasModel;
import looooooleke.util.Items.ItemBase;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel{
	public FoodBase(String name, int amount, float saturation, boolean isAnimalFood) {
		super(amount,saturation,isAnimalFood);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Caramel3.MyTab);
		
		ItemBase.LIST.add(this);
	}

	@Override
	public void RegisterModels() {
		Caramel3.proxy.RegisterModel(this);
	}
}
