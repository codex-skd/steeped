package com.skd.steeped;

import com.mojang.logging.LogUtils;
import com.skd.steeped.core.ClientEvents;
import com.skd.steeped.core.Config;
import com.skd.steeped.core.Events;
import com.skd.steeped.core.Registration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Steeped.MODID)
public class Steeped {
    public static final String MODID = "steeped";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Steeped(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Steeped loading");

        // COMMON, not SERVER: drink values feed immutable FoodProperties built during registration,
        // and SERVER configs only load after registries are frozen.
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.serverSpec);

        Registration.init(modEventBus);

        modEventBus.addListener(Registration::registerMisc);

        // Game events live on the NeoForge bus; client-only listeners are wired on the client only.
        Events.registerEvents(NeoForge.EVENT_BUS);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientEvents.registerClientEvents(modEventBus);
        }
    }
}
