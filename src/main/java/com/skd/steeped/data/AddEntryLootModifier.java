package com.skd.steeped.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import java.util.function.Consumer;

/**
 * Loot modifier to inject an additional loot entry into an existing table
 */
public class AddEntryLootModifier extends LootModifier {
    public static final MapCodec<AddEntryLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> codecStart(inst).and(
        inst.group(
            LootPoolEntries.CODEC.fieldOf("entry").forGetter(m -> m.entry),
            LootItemFunctions.ROOT_CODEC.listOf().optionalFieldOf("functions", List.of()).forGetter(m -> m.functions),
            Codec.BOOL.fieldOf("require_empty").orElse(false).forGetter(m -> m.requireEmpty)
        )).apply(inst, AddEntryLootModifier::new));

    private final LootPoolEntryContainer entry;
    private final List<LootItemFunction> functions;
    private final boolean requireEmpty;

    protected AddEntryLootModifier(LootItemCondition[] conditionsIn, LootPoolEntryContainer entry, List<LootItemFunction> functions, boolean requireEmpty) {
        super(conditionsIn);
        this.entry = entry;
        this.functions = functions;
        this.requireEmpty = requireEmpty;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!requireEmpty || generatedLoot.isEmpty()) {
            Consumer<ItemStack> consumer = LootItemFunction.decorate(LootItemFunctions.compose(functions), generatedLoot::add, context);
            entry.expand(context, generator -> generator.createItemStack(consumer, context));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
