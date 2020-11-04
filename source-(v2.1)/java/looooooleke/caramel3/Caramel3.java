package looooooleke.caramel3;

import looooooleke.caramel3.util.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import looooooleke.caramel3.util.proxy.CommonProxy;
import looooooleke.caramel3.util.tabs.CaramelTab;
import looooooleke.util.world.ModWorldGen;
import looooooleke.util.world.trees.ModTreeGen;

@Mod(modid=References.MODID, version = References.VERSION, name = References.NAME)
public class Caramel3 {
	
	public static final CaramelTab MyTab = new CaramelTab();
	@Instance public static Caramel3 instance;
	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.COMMON_PROXY_CLASS) public static CommonProxy proxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new ModWorldGen(),3);
		GameRegistry.registerWorldGenerator(new ModTreeGen(), 0);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		CaramelItems.RegisterSmeltRecipes();
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		
	}
}
