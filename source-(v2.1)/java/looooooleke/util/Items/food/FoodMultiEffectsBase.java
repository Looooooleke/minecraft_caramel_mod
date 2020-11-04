package looooooleke.util.Items.food;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FoodMultiEffectsBase extends FoodBase{
ArrayList<PotionEffect> effects;
	
	public FoodMultiEffectsBase(String name, int amount, float saturation, boolean isAnimalFood, PotionEffect effect) {
		super(name, amount, saturation,isAnimalFood);
		effects = new ArrayList<PotionEffect>();
		setAlwaysEdible();
		this.effects.add(effect);
	}
	
	public FoodMultiEffectsBase add(PotionEffect effect) {
		effects.add(effect);
		return this;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote) {
			for(PotionEffect effect:effects)
			player.addPotionEffect(new PotionEffect(effect.getPotion(),effect.getDuration(),effect.getAmplifier(),effect.getIsAmbient(),effect.doesShowParticles()));
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
}
