package org.devoxx4kids.forge.mods;

import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class CakeBrewingRecipe implements IBrewingRecipe
{

	@Override
	public boolean isInput(ItemStack input) 
	{
		return PotionUtils.getPotionFromItem(input) == PotionTypes.AWKWARD;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) 
	{
		return ingredient.getItem() == Items.CAKE;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) 
	{
		if(isInput(input) && isIngredient(ingredient))
			return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRENGTH);
		else
			return ItemStack.EMPTY;
	}

}
