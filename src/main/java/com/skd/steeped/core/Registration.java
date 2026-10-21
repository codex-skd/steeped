package com.skd.steeped.core;

import com.skd.steeped.Steeped;
import com.skd.steeped.block.TeaTrunkBlock;
import com.skd.steeped.core.Config;
import com.skd.steeped.core.config.Drink;
import com.skd.steeped.data.AddEntryLootModifier;
import com.skd.steeped.data.gen.ShapelessHoneyRecipe;
import com.skd.steeped.item.CocoaItem;
import com.skd.steeped.item.HotTeapotItem;
import com.skd.steeped.item.TeaCupItem;
import com.skd.steeped.item.TeaStickItem;
import com.skd.steeped.item.TeapotItem;
import com.skd.steeped.item.TooltipItem;
import com.skd.steeped.item.WoodBlockItem;
import com.skd.steeped.potion.CaffeinatedEffect;
import com.skd.steeped.potion.EnderfallingEffect;
import com.skd.steeped.potion.InvigoratedEffect;
import com.skd.steeped.potion.RelaxedEffect;
import com.skd.steeped.potion.RestfulEffect;
import com.skd.steeped.worldgen.TeaTreeFeature;
import com.skd.steeped.worldgen.TeaTreeGrower;
import com.skd.steeped.worldgen.TreeGenEnabledPlacement;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class Registration {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Steeped.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Steeped.MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Steeped.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Steeped.MODID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Steeped.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Steeped.MODID);
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, Steeped.MODID);
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Steeped.MODID);

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        FEATURES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        PLACEMENT_MODIFIER_TYPES.register(modEventBus);
        LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
    }

    // Creative tab
    public static final DeferredItem<Item> TEA_LEAF = ITEMS.register("tea_leaf", () -> new TooltipItem(new Item.Properties()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GROUP = CREATIVE_TABS.register("steeped", () ->
        CreativeModeTab.builder()
            .icon(() -> new ItemStack(TEA_LEAF.get()))
            .title(Component.translatable("itemGroup.steeped"))
            .displayItems((displayParameters, output) -> {
                BuiltInRegistries.ITEM.holders()
                    .filter(holder -> holder.key().location().getNamespace().equals(Steeped.MODID))
                    .forEach(holder -> output.accept(holder.value()));
            })
            .build()
    );

    // Mob effects
    public static final DeferredHolder<MobEffect, RestfulEffect> RESTFUL = MOB_EFFECTS.register("restful", RestfulEffect::new);
    public static final DeferredHolder<MobEffect, RelaxedEffect> RELAXED = MOB_EFFECTS.register("relaxed", RelaxedEffect::new);
    public static final DeferredHolder<MobEffect, CaffeinatedEffect> CAFFEINATED = MOB_EFFECTS.register("caffeinated", CaffeinatedEffect::new);
    public static final DeferredHolder<MobEffect, InvigoratedEffect> INVIGORATED = MOB_EFFECTS.register("invigorated", InvigoratedEffect::new);
    public static final DeferredHolder<MobEffect, EnderfallingEffect> ENDERFALLING = MOB_EFFECTS.register("enderfalling", EnderfallingEffect::new);

    // Blocks
    public static final DeferredBlock<FenceBlock> TEA_FENCE = BLOCKS.register("tea_fence", () ->
        new FenceBlock(woodProperties())
    );
    public static final DeferredBlock<FenceGateBlock> TEA_FENCE_GATE = BLOCKS.register("tea_fence_gate", () ->
        new FenceGateBlock(WoodType.OAK, woodProperties())
    );
    public static final DeferredBlock<SaplingBlock> TEA_SAPLING = BLOCKS.register("tea_sapling", () ->
        new SaplingBlock(TeaTreeGrower.INSTANCE, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().strength(0).sound(SoundType.GRASS))
    );
    public static final DeferredBlock<TeaTrunkBlock> TEA_TRUNK = BLOCKS.register("tea_trunk", () ->
        new TeaTrunkBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).randomTicks())
    );
    public static final DeferredBlock<FlowerPotBlock> POTTED_TEA_SAPLING = BLOCKS.register("potted_tea_sapling", () ->
        new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TEA_SAPLING, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).strength(0f).noOcclusion())
    );

    // Items - crafting
    public static final DeferredItem<Item> BLACK_TEA = ITEMS.register("black_tea", () -> new TooltipItem(new Item.Properties()));
    public static final DeferredItem<Item> TEA_STICK = ITEMS.register("tea_stick", () -> new TeaStickItem(new Item.Properties()));
    public static final DeferredItem<Item> ICE_CUBE = ITEMS.register("ice_cube", () -> new TooltipItem(new Item.Properties()));
    public static final DeferredItem<Item> CHORUS_PETAL = ITEMS.register("chorus_petal", () -> new TooltipItem(new Item.Properties()));

    // Items - tea bags
    public static final DeferredItem<Item> TEABAG = ITEMS.register("teabag", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEABAG_BLACK = ITEMS.register("teabag_black", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEABAG_FLORAL = ITEMS.register("teabag_floral", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEABAG_CHORUS = ITEMS.register("teabag_chorus", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEABAG_GREEN = ITEMS.register("teabag_green", () -> new Item(new Item.Properties()));

    // Block items
    public static final DeferredItem<BlockItem> TEA_FENCE_ITEM = ITEMS.register("tea_fence", () -> new WoodBlockItem(TEA_FENCE.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> TEA_FENCE_GATE_ITEM = ITEMS.register("tea_fence_gate", () -> new WoodBlockItem(TEA_FENCE_GATE.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> TEA_SAPLING_ITEM = ITEMS.register("tea_sapling", () -> new BlockItem(TEA_SAPLING.get(), new Item.Properties()));

    // Items - teapots
    public static final DeferredItem<Item> UNFIRED_TEAPOT = ITEMS.register("unfired_teapot", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> TEAPOT = ITEMS.register("teapot", () -> new TeapotItem(new Item.Properties().stacksTo(16)));

    // Items - teacups
    public static final DeferredItem<Item> UNFIRED_CUP = ITEMS.register("unfired_cup", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> CUP = ITEMS.register("cup", () -> new Item(new Item.Properties().stacksTo(16)));

    // Items - filled teapots
    public static final DeferredItem<Item> TEAPOT_WATER = ITEMS.register("teapot_water", () -> new TooltipItem(new Item.Properties().craftRemainder(TEAPOT.get()).stacksTo(1)));
    public static final DeferredItem<Item> TEAPOT_MILK = ITEMS.register("teapot_milk", () -> new TooltipItem(new Item.Properties().craftRemainder(TEAPOT.get()).stacksTo(1)));
    public static final DeferredItem<Item> TEAPOT_HOT = ITEMS.register("teapot_hot", () -> new HotTeapotItem(new Item.Properties().setNoRepair().durability(4).craftRemainder(TEAPOT.get()).stacksTo(1)));
    public static final DeferredItem<Item> TEAPOT_FROTHED = ITEMS.register("teapot_frothed", () -> new HotTeapotItem(new Item.Properties().setNoRepair().durability(4).craftRemainder(TEAPOT.get()).stacksTo(1)));

    // Items - drinks
    public static final DeferredItem<Item> CUP_TEA_BLACK = ITEMS.register("cup_tea_black", () -> new TeaCupItem(teaProperties(Config.SERVER.black_tea), Config.SERVER.black_tea));
    public static final DeferredItem<Item> CUP_TEA_GREEN = ITEMS.register("cup_tea_green", () -> new TeaCupItem(teaProperties(Config.SERVER.green_tea), Config.SERVER.green_tea));
    public static final DeferredItem<Item> CUP_TEA_FLORAL = ITEMS.register("cup_tea_floral", () -> new TeaCupItem(teaProperties(Config.SERVER.floral_tea), Config.SERVER.floral_tea));
    public static final DeferredItem<Item> CUP_TEA_CHAI = ITEMS.register("cup_tea_chai", () -> new TeaCupItem(teaProperties(Config.SERVER.chai_tea), Config.SERVER.chai_tea));
    public static final DeferredItem<Item> CUP_TEA_ICED = ITEMS.register("cup_tea_iced", () -> new TeaCupItem(teaProperties(Config.SERVER.iced_tea), Config.SERVER.iced_tea));
    public static final DeferredItem<Item> CUP_TEA_CHORUS = ITEMS.register("cup_tea_chorus", () -> new TeaCupItem(teaProperties(Config.SERVER.chorus_tea), Config.SERVER.chorus_tea));
    public static final DeferredItem<Item> CUP_COCOA = ITEMS.register("cup_cocoa", () -> new CocoaItem(teaProperties(Config.SERVER.cocoa)));

    // World gen
    public static final DeferredHolder<Feature<?>, TeaTreeFeature> TEA_TREE = FEATURES.register("tea_tree", TeaTreeFeature::new);
    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<TreeGenEnabledPlacement>> TREE_GEN_ENABLED = PLACEMENT_MODIFIER_TYPES.register("tree_gen_enabled", () -> () -> TreeGenEnabledPlacement.CODEC);

    // Recipe serializers
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ShapelessHoneyRecipe>> SHAPELESS_HONEY = RECIPE_SERIALIZERS.register("shapeless_honey", ShapelessHoneyRecipe.Serializer::new);

    // Loot modifiers
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddEntryLootModifier>> ADD_LOOT_ENTRY = LOOT_MODIFIER_SERIALIZERS.register("add_loot_entry", () -> AddEntryLootModifier.CODEC);

    // Registry keys for worldgen datapack
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_TEA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Steeped.MODID, "tea_tree"));
    public static final ResourceKey<PlacedFeature> PLACED_TEA_TREE = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Steeped.MODID, "tea_tree"));

    /** Builds the food properties shared by every cup of tea */
    private static Item.Properties teaProperties(Drink drink) {
        return new Item.Properties().stacksTo(1).durability(2).setNoRepair().craftRemainder(CUP.get()).food(drink.getFoodProperties());
    }

    private static BlockBehaviour.Properties woodProperties() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD);
    }

    public static void registerMisc(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // flammable wood, matching vanilla planks
            FireBlock fire = (FireBlock) Blocks.FIRE;
            fire.setFlammable(TEA_FENCE.get(), 5, 20);
            fire.setFlammable(TEA_FENCE_GATE.get(), 5, 20);
            fire.setFlammable(TEA_TRUNK.get(), 15, 30);

            ComposterBlock.COMPOSTABLES.put(TEA_LEAF.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(BLACK_TEA.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(CHORUS_PETAL.get(), 0.5f);
            ComposterBlock.COMPOSTABLES.put(TEA_SAPLING.get(), 0.3f);

            RestfulEffect.addConflict(CAFFEINATED);
            RestfulEffect.addConflict(INVIGORATED);
        });
    }
}
