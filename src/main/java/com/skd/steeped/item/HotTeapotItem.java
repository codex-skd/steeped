package com.skd.steeped.item;

import net.minecraft.world.item.ItemStack;

public class HotTeapotItem extends TooltipItem {
    public HotTeapotItem(Properties props) {
        super(props);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        if (copy.getDamageValue() >= copy.getMaxDamage()) {
            return super.getCraftingRemainingItem(stack);
        }
        return copy;
    }
}
