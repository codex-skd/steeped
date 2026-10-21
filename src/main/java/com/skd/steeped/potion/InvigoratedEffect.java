package com.skd.steeped.potion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class InvigoratedEffect extends MobEffect {
    public InvigoratedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xD79659);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath("steeped", "invigorated_attack_damage"), 1, AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(Attributes.ATTACK_KNOCKBACK, ResourceLocation.fromNamespaceAndPath("steeped", "invigorated_attack_knockback"), 1, AttributeModifier.Operation.ADD_VALUE);
    }
}
