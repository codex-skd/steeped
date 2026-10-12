package com.skd.steeped.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.resources.ResourceLocation;

public class CaffeinatedEffect extends MobEffect {
    public CaffeinatedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x66300E);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath("steeped", "caffeinated_movement_speed"), 0.06, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath("steeped", "caffeinated_attack_speed"), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}
