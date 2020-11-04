package looooooleke.caramel3.util.handlers;

import looooooleke.caramel3.CaramelItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber public class RegistryHandler {
	@SubscribeEvent public static void onItemRegister(RegistryEvent.Register<Item> event) {CaramelItems.RegisterItems(event);}
	@SubscribeEvent public static void onBlockRegister(RegistryEvent.Register<Block> event) { CaramelItems.RegisterBlocks(event);}
	@SubscribeEvent public static void onModelRegister(ModelRegistryEvent event) {CaramelItems.RegisterModels(event);}
}
