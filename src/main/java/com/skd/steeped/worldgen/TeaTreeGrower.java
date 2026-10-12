package com.skd.steeped.worldgen;

import com.skd.steeped.core.Registration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

/** Tree grower pointing at the tea tree configured feature from the datapack */
public class TeaTreeGrower {
    public static final TreeGrower INSTANCE = new TreeGrower("steeped:tea", Optional.empty(), Optional.of(Registration.CONFIGURED_TEA_TREE), Optional.empty());
}
