package com.skd.steeped.item;

import com.skd.steeped.core.Config;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;

import java.util.List;

public class CocoaItem extends TeaCupItem {
    public static final String CINNAMON_TAG = "with_cinnamon";
    private static final Component WITH_CINNAMON = Component.translatable("item.steeped.cup.with_cinnamon")
            .withStyle(style -> style.withColor(0x805232));

    public CocoaItem(Properties props) {
        super(props, null);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return stack.has(DataComponents.FOOD) ? UseAnim.DRINK : UseAnim.NONE;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        FoodProperties food = stack.get(DataComponents.FOOD);
        if (food == null) {
            return stack;
        }
        ItemStack result = getCraftingRemainingItem(stack);
        boolean hasCinnamon = hasHoney(stack, CINNAMON_TAG);
        entity.eat(level, stack, food);
        if (!level.isClientSide && Config.SERVER.cocoa.clearsEffects()) {
            // logic basically copied from living entity, so we can choose which effects to remove
            List<Holder<MobEffect>> effects = List.copyOf(entity.getActiveEffectsMap().keySet());
            for (Holder<MobEffect> holder : effects) {
                MobEffectInstance effect = entity.getActiveEffectsMap().get(holder);
                if (effect == null) {
                    continue;
                }
                if ((Config.SERVER.cocoa.clearsPositive() || !effect.getEffect().value().isBeneficial())
                        && effect.getCures().contains(EffectCures.MILK)) {
                    entity.removeEffect(holder);
                    // need cinnamon to remove more than one effect
                    if (!hasCinnamon) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (hasHoney(stack, CINNAMON_TAG)) {
            tooltip.add(WITH_CINNAMON);
        }
    }
}
