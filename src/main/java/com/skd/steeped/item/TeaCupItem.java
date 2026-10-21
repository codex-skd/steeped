package com.skd.steeped.item;

import com.skd.steeped.core.config.TeaDrink;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class TeaCupItem extends Item {
    public static final String HONEY_TAG = "with_honey";
    private static final Component WITH_HONEY = Component.translatable("item.steeped.cup.with_honey")
            .withStyle(style -> style.withColor(0xFF9116));
    private final TeaDrink drink;

    public TeaCupItem(Properties props, @Nullable TeaDrink drink) {
        super(props);
        this.drink = drink;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return stack.has(DataComponents.FOOD) ? UseAnim.DRINK : UseAnim.NONE;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getDamageValue() > 0;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        if (stack.getDamageValue() + 1 >= stack.getMaxDamage()) {
            return super.getCraftingRemainingItem(stack);
        }
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        return copy;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        FoodProperties food = stack.get(DataComponents.FOOD);
        if (food == null) {
            return stack;
        }
        boolean hasHoney = hasHoney(stack, HONEY_TAG);
        ItemStack result = stack.getCraftingRemainingItem();
        // teas conflict with each other, drop the effects of any tea drunk before
        entity.removeEffectsCuredBy(TeaDrink.TEA_CURE);
        entity.eat(level, stack, food);
        if (this.drink != null) {
            // we handle effects directly so it can be stack sensitive
            MobEffectInstance effectInstance = drink.getEffect(hasHoney);
            if (effectInstance != null) {
                entity.addEffect(effectInstance);
            }
        }
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (hasHoney(stack, HONEY_TAG)) {
            tooltip.add(WITH_HONEY);
        }
    }

    /** Adds honey to the given tea */
    public static ItemStack withHoney(ItemStack stack, String tag) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean(tag, true);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        return stack;
    }

    /** Checks if the given tea contains honey */
    public static boolean hasHoney(ItemStack stack, String tag) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        return data != null && data.copyTag().getBoolean(tag);
    }
}
