package org.devoxx4kids.forge.mods;

import org.devoxx4kids.forge.mods.proxy.CommonProxy;
import org.devoxx4kids.forge.mods.util.ModItems;
import org.devoxx4kids.forge.mods.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main 
{
	public static final String MODID = "mymods";
	public static final String NAME = "MyMods";
	public static final String VERSION = "1.0";
	
	//public static Block enderBlock;
	//public static Item enderIngot;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new BlockBreakMessage());
		MinecraftForge.EVENT_BUS.register(new ExplodingMinecarts());
		MinecraftForge.EVENT_BUS.register(new ExplodingAnvils());
		MinecraftForge.EVENT_BUS.register(new DiamondOreTrap());
		MinecraftForge.EVENT_BUS.register(new BiggerTNTExplosions());
		MinecraftForge.EVENT_BUS.register(new PigsDroppingDiamonds());
		MinecraftForge.EVENT_BUS.register(new ZombieKnights());
		MinecraftForge.EVENT_BUS.register(new CreeperReinforcements());
		MinecraftForge.EVENT_BUS.register(new SuperJump());
		//FMLCommonHandler.instance().bus().register(new Parachute());
		MinecraftForge.EVENT_BUS.register(new Parachute());
		MinecraftForge.EVENT_BUS.register(new GolemWallClimb());
		MinecraftForge.EVENT_BUS.register(new BlockFillerPositionSelector());
		
		//enderIngot = new EnderIngot();
		//GameRegistry.registerItem(enderIngot, "enderIngot");
		
		// Recipes not set in JSON
		EnderIngot enderIngot = new EnderIngot();
		GameRegistry.addSmelting(Items.ENDER_PEARL, new ItemStack(ModItems.ENDER_INGOT, 1), 1.0F);  

		BrewingRecipeRegistry.addRecipe(new CakeBrewingRecipe());
		
		//BrewingRecipeRegistry.addRecipe(
		//		PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD), 
		//		new ItemStack(Items.CAKE), 
		//		PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_STRENGTH));

		
	}

	@EventHandler
	public void registerCommands(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new FlamingPigs());
		event.registerServerCommand(new BlockFillCommand());
	}
	
	
}
