package org.devoxx4kids.forge.mods;

import java.util.Random;

import org.devoxx4kids.forge.mods.util.IHasModel;
import org.devoxx4kids.forge.mods.util.ModBlocks;
import org.devoxx4kids.forge.mods.util.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EnderBlock extends Block implements IHasModel
{
	public EnderBlock(String name, Material material) 
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setResistance(5.0F);
		this.setHardness(10.0F);
		this.setLightLevel(1.0F);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModel() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		IBlockState block = world.getBlockState(pos.add(0, -1, 0));
		world.setBlockState(pos, block);
		EntityLightningBolt lightning = new EntityLightningBolt(world, 
				pos.getX(), pos.getY(), pos.getZ(), enableStats);
		world.addWeatherEffect(lightning);
	}
	

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
			EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
			return false;
				
		player.sendMessage(new TextComponentString(
				TextFormatting.DARK_PURPLE + "You have clicked on the majestic ENDERIUM BLOCK!!!!!"));
		EntityEnderEye eye = new EntityEnderEye(world, pos.getX() + 0.5, pos.getY() + 1.5,
				pos.getZ() + 0.5);
		eye.motionY = 0.1;
		world.spawnEntity(eye);
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.ENDER_INGOT;
       }
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) 
	{
        return random.nextInt(2) + 3;   
    }
	
	
	/*
	public Item getItemDropped(IBlockState state, Random random, int i2)
	{
		return ModItems.ENDER_INGOT;
	}
	
	public int quantityDropped(Random random)
	{
		return random.nextInt(2) + 3;
	}
	//*/
}
