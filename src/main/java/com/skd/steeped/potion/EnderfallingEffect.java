package com.skd.steeped.potion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EnderfallingEffect extends MobEffect {
    public EnderfallingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x4E2043);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, ResourceLocation.fromNamespaceAndPath("steeped", "enderfalling_knockback_resistance"), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}
