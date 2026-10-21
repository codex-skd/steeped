package com.skd.steeped.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.skd.steeped.core.Config;
import com.skd.steeped.core.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class TreeGenEnabledPlacement extends PlacementFilter {
    public static final TreeGenEnabledPlacement INSTANCE = new TreeGenEnabledPlacement();
    public static final MapCodec<TreeGenEnabledPlacement> CODEC = MapCodec.unit(() -> INSTANCE);

    private TreeGenEnabledPlacement() {}

    @Override
    protected boolean shouldPlace(PlacementContext pContext, RandomSource pRandom, BlockPos pPos) {
        return Config.SERVER.tree.generate();
    }

    @Override
    public PlacementModifierType<?> type() {
        return Registration.TREE_GEN_ENABLED.get();
    }
}
