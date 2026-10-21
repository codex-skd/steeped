package com.skd.steeped.core;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEvents {
    public static void registerClientEvents(IEventBus bus) {
        bus.addListener(ClientEvents::registerBlockColors);
        bus.addListener(ClientEvents::registerMisc);
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, index) -> {
            if (world == null || pos == null) {
                return net.minecraft.world.level.FoliageColor.getDefaultColor();
            }
            return net.minecraft.client.renderer.BiomeColors.getAverageFoliageColor(world, pos);
        }, Registration.TEA_TRUNK.get());
    }

    public static void registerMisc(FMLClientSetupEvent event) {
        RenderType cutoutMipped = RenderType.cutoutMipped();
        ItemBlockRenderTypes.setRenderLayer(Registration.TEA_SAPLING.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(Registration.POTTED_TEA_SAPLING.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(Registration.TEA_TRUNK.get(), cutoutMipped);
    }
}
