package org.devoxx4kids.forge.mods.util;

import java.util.ArrayList;
import java.util.List;

import org.devoxx4kids.forge.mods.EnderBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ENDER_BLOCK = new EnderBlock("ender_block", Material.IRON);
	
}
