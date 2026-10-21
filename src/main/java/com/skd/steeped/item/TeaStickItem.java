package com.skd.steeped.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class TeaStickItem extends TooltipItem {
    public TeaStickItem(Properties props) {
        super(props);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 100;
    }
}
