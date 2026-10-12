package com.skd.steeped.core;

import com.skd.steeped.potion.RestfulEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;

public class Events {
    public static void registerEvents(IEventBus bus) {
        bus.addListener(Events::playerWakeUp);
        bus.addListener(Events::entityFall);
        bus.addListener(Events::throwEnderPearl);
    }

    static void playerWakeUp(PlayerWakeUpEvent event) {
        if (event.updateLevel()) {
            return;
        }
        Player player = event.getEntity();
        if (RestfulEffect.removeConflicts(player)) {
            player.removeEffect(Registration.RESTFUL);
        } else {
            MobEffectInstance effect = player.getEffect(Registration.RESTFUL);
            if (effect != null) {
                player.heal((effect.getAmplifier() + 1) * 2);
                player.removeEffect(Registration.RESTFUL);
            }
        }
    }

    static void entityFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effect = entity.getEffect(Registration.ENDERFALLING);
        if (effect != null) {
            event.setDamageMultiplier(event.getDamageMultiplier() * (float) Math.pow(2, -effect.getAmplifier() - 3));
        }
    }

    static void throwEnderPearl(EntityTeleportEvent.EnderPearl event) {
        if (event.getPlayer().hasEffect(Registration.ENDERFALLING)) {
            event.setAttackDamage(0);
        }
    }
}
