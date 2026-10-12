package com.skd.steeped.potion;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class RestfulEffect extends MobEffect {
    private static final List<Holder<MobEffect>> CONFLICTING = new ArrayList<>();

    public RestfulEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xAD601A);
    }

    /** Marks an effect as preventing restfulness */
    public static void addConflict(Holder<MobEffect> effect) {
        CONFLICTING.add(effect);
    }

    /**
     * Removes the effects that conflict with restful
     * @return true  If any effect was removed
     */
    public static boolean removeConflicts(LivingEntity entity) {
        boolean hasConflicts = false;
        for (Holder<MobEffect> holder : CONFLICTING) {
            if (entity.hasEffect(holder)) {
                entity.removeEffect(holder);
                hasConflicts = true;
            }
        }
        return hasConflicts;
    }
}
