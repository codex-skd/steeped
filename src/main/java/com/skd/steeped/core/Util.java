package com.skd.steeped.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public final class Util {
    private Util() {}

    public static List<ItemStack> getBlockLoot(net.minecraft.world.level.block.state.BlockState state, ServerLevel world, BlockPos pos, @Nullable Player player, ItemStack tool, ResourceLocation location) {
        LootParams.Builder builder = new LootParams.Builder(world)
            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
            .withParameter(LootContextParams.BLOCK_STATE, state)
            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, world.getBlockEntity(pos))
            .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
            .withParameter(LootContextParams.TOOL, tool);
        LootParams context = builder.create(LootContextParamSets.BLOCK);
        LootTable table = world.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, location));
        return table.getRandomItems(context);
    }
}
