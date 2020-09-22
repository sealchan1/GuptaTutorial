package org.devoxx4kids.forge.mods;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockFillerPositionSelector 
{
	static List<Integer> pos1 = new ArrayList();
	static List<Integer> pos2 = new ArrayList();
	
	//Debub
	public static String getPositionString (Integer posNumber)
	{
		String retVal; 
		try
		{
			if (posNumber == 1)
				retVal = pos1.get(0) + ", " + pos1.get(1) + ", " + pos1.get(2);
			else if (posNumber == 2)
				retVal = pos2.get(0) + ", " + pos2.get(1) + ", " + pos2.get(2);
			else
				retVal = "There is no position vector number" + posNumber;
		}
		catch (Exception e)
		{
			retVal = "Error forming string " + e.getMessage();
		}
		
		return retVal;
	}
	
	@SubscribeEvent
	public void choosePositions(PlayerInteractEvent event)
	{
		if(event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND) == null 
				|| 
				event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem() != Items.WOODEN_AXE
				||
				!event.getEntityPlayer().capabilities.isCreativeMode)
			return;
		
		if (event instanceof RightClickBlock)
		{
			pos1.clear();
			pos1.add(event.getPos().getX());
			pos1.add(event.getPos().getY());
			pos1.add(event.getPos().getZ());
			event.getEntityPlayer().sendMessage(
					new TextComponentString(TextFormatting.GREEN + "Position 1 set to "
							+ event.getPos().getX()
							+ ", " + event.getPos().getY()
							+ ", " + event.getPos().getZ()));
			event.setCanceled(true);
		}
		else if (event instanceof LeftClickBlock)
		{
			pos2.clear();
			pos2.add(event.getPos().getX());
			pos2.add(event.getPos().getY());
			pos2.add(event.getPos().getZ());
			event.getEntityPlayer().sendMessage(
					new TextComponentString(TextFormatting.GREEN + "Position 2 set to "
							+ event.getPos().getX()
							+ ", " + event.getPos().getY()
							+ ", " + event.getPos().getZ()));
			event.setCanceled(true);
		}
			
	}
}
