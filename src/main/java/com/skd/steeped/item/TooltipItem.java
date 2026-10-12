package com.skd.steeped.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class TooltipItem extends Item {
    private final Component tooltipLine;

    public TooltipItem(Properties props) {
        super(props);
        this.tooltipLine = Component.translatable(this.getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(tooltipLine);
    }
}
