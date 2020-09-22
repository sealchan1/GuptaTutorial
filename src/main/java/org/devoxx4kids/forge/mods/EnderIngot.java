package org.devoxx4kids.forge.mods;

import org.devoxx4kids.forge.mods.util.IHasModel;
import org.devoxx4kids.forge.mods.util.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EnderIngot extends ItemFood implements IHasModel
{
	public EnderIngot()
	{
		super(5, 1.0F, false);
		this.setUnlocalizedName("ender_ingot");
		this.setRegistryName("ender_ingot");
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setAlwaysEdible();
		
		ModItems.ITEMS.add(this);
	}
	
    public EnderIngot(EnderIngot enderIngot) 
    {
		super(5, 1.0F, false);
		
		this.setUnlocalizedName("ender_ingot");
		this.setRegistryName("ender_ingot");
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setAlwaysEdible();
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack item = playerIn.getHeldItem(handIn);
    	
    	EntityEnderman enderman = new EntityEnderman(worldIn);
    	enderman.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.posZ, 0, 0);
    	
		if(!worldIn.isRemote)
			worldIn.spawnEntity(enderman);
    	
    	if(!playerIn.capabilities.isCreativeMode)
    		item.shrink(1);
    	
    	return new ActionResult(EnumActionResult.SUCCESS, item);
    }
	
	/*
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		EntityEnderman enderman = new EntityEnderman(world);
		enderman.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
		
		world.spawnEntity(enderman);
		
		if(!player.capabilities.isCreativeMode)
			stack.shrink(1);
		
		return stack;
	}
	//*/
	
	@Override
	public void registerModel() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
