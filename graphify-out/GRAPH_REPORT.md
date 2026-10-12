# Graph Report - .  (2026-10-12)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 392 nodes · 701 edges · 39 communities (19 shown, 20 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 4 edges (avg confidence: 0.8)
- Token cost: 1,071 input · 417 output

## Graph Freshness
- Built from commit: `94a002f8`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Block Registration
- Tea Block
- Tea Drink
- Teapot Config
- Fluid Teapot
- Client Setup
- Honey Recipe
- Tea Item
- Loot Modifiers
- Cocoa Item
- Tea Tree Feature
- Tree Placement
- Drink Properties
- Utility Functions
- Block Items
- Item Tags
- Build Scripts
- Ice Cube Texture
- Tea Leaf Texture
- Tea Stick Texture
- Teabag Texture
- Black Teabag Texture
- Chamomile Teabag Texture
- Chorus Teabag Texture
- Floral Teabag Texture
- Green Teabag Texture
- Teapot Texture
- Boiling Teapot Texture
- Frothed Teapot Texture
- Milk Teapot Texture
- Unfired Teapot Texture
- Water Teapot Texture
- Caffeinated Mob Effect
- Enderfalling Mob Effect
- Invigorated Mob Effect
- Relaxed Mob Effect
- Restful Mob Effect

## God Nodes (most connected - your core abstractions)
1. `Registration` - 37 edges
2. `TeaTrunkBlock` - 16 edges
3. `TeaCupItem` - 15 edges
4. `ShapelessHoneyRecipe` - 14 edges
5. `FluidTeapotWrapper` - 14 edges
6. `TrunkType` - 13 edges
7. `TeaDrink` - 13 edges
8. `Drink` - 11 edges
9. `TeaEffect` - 11 edges
10. `Teapot` - 11 edges

## Surprising Connections (you probably didn't know these)
- `Registration` --references--> `TeaTrunkBlock`  [EXTRACTED]
  src/main/java/com/skd/steeped/core/Registration.java → src/main/java/com/skd/steeped/block/TeaTrunkBlock.java
- `Server` --references--> `TeaDrink`  [EXTRACTED]
  src/main/java/com/skd/steeped/core/Config.java → src/main/java/com/skd/steeped/core/config/TeaDrink.java
- `Registration` --references--> `AddEntryLootModifier`  [EXTRACTED]
  src/main/java/com/skd/steeped/core/Registration.java → src/main/java/com/skd/steeped/data/AddEntryLootModifier.java
- `Registration` --references--> `ShapelessHoneyRecipe`  [EXTRACTED]
  src/main/java/com/skd/steeped/core/Registration.java → src/main/java/com/skd/steeped/data/gen/ShapelessHoneyRecipe.java
- `Registration` --references--> `Items`  [EXTRACTED]
  src/main/java/com/skd/steeped/core/Registration.java → src/main/java/com/skd/steeped/data/SimplyTags.java

## Import Cycles
- None detected.

## Communities (39 total, 20 thin omitted)

### Community 0 - "Block Registration"
Cohesion: 0.07
Nodes (36): Blocks, ConfiguredFeature, CreativeModeTab, DeferredBlock, DeferredHolder, DeferredItem, DeferredRegister, FenceBlock (+28 more)

### Community 1 - "Tea Block"
Cohesion: 0.09
Nodes (33): Block, BlockGetter, BlockHitResult, BooleanProperty, CollisionContext, EnumProperty, ItemInteractionResult, LevelReader (+25 more)

### Community 2 - "Tea Drink"
Cohesion: 0.09
Nodes (27): EffectCure, MobEffectInstance, Nullable, getEffect(), Builder, Holder, IntValue, MobEffect (+19 more)

### Community 3 - "Teapot Config"
Cohesion: 0.08
Nodes (19): EnumValue, ModConfigSpec, ClearType, ALL, NEGATIVE, NONE, CocoaDrink, Builder (+11 more)

### Community 4 - "Fluid Teapot"
Cohesion: 0.15
Nodes (16): Fluid, FluidAction, FluidStack, IFluidHandlerItem, InteractionResult, InteractionResultHolder, FluidTeapotWrapper, ItemStack (+8 more)

### Community 5 - "Client Setup"
Cohesion: 0.12
Nodes (14): EnderPearl, FMLClientSetupEvent, LivingFallEvent, Logger, Mod, ModContainer, PlayerWakeUpEvent, ClientEvents (+6 more)

### Community 6 - "Honey Recipe"
Cohesion: 0.19
Nodes (15): CraftingInput, Ingredient, NonNullList, Provider, RegistryFriendlyByteBuf, ShapelessRecipe, Item, ItemStack (+7 more)

### Community 7 - "Tea Item"
Cohesion: 0.12
Nodes (14): Item, HotTeapotItem, ItemStack, Override, ItemStack, Override, RecipeType, TeaStickItem (+6 more)

### Community 8 - "Loot Modifiers"
Cohesion: 0.26
Nodes (11): LootContext, LootItemCondition, LootItemFunction, LootModifier, LootPoolEntryContainer, ObjectArrayList, AddEntryLootModifier, IGlobalLootModifier (+3 more)

### Community 9 - "Cocoa Item"
Cohesion: 0.26
Nodes (9): CocoaItem, Component, ItemStack, Level, LivingEntity, Override, TooltipContext, TooltipFlag (+1 more)

### Community 10 - "Tea Tree Feature"
Cohesion: 0.29
Nodes (8): Feature, FeaturePlaceContext, LevelAccessor, NoneFeatureConfiguration, BlockPos, BlockState, Override, TeaTreeFeature

### Community 11 - "Tree Placement"
Cohesion: 0.27
Nodes (8): PlacementContext, PlacementFilter, BlockPos, MapCodec, Override, PlacementModifierType, RandomSource, TreeGenEnabledPlacement

### Community 12 - "Drink Properties"
Cohesion: 0.27
Nodes (5): FoodProperties, Drink, Builder, DoubleValue, IntValue

### Community 13 - "Utility Functions"
Cohesion: 0.31
Nodes (7): BlockPos, BlockState, ItemStack, Player, ResourceLocation, ServerLevel, Util

### Community 14 - "Block Items"
Cohesion: 0.33
Nodes (6): BlockItem, Block, ItemStack, Override, RecipeType, WoodBlockItem

### Community 15 - "Item Tags"
Cohesion: 0.42
Nodes (4): Items, Item, SimplyTags, TagKey

### Community 16 - "Build Scripts"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **37 isolated node(s):** `STUMP`, `BOTTOM`, `MIDDLE`, `TOP`, `NORTH` (+32 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **20 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Registration` connect `Block Registration` to `Tea Block`, `Honey Recipe`, `Loot Modifiers`, `Tea Tree Feature`, `Tree Placement`, `Drink Properties`, `Item Tags`?**
  _High betweenness centrality (0.082) - this node is a cross-community bridge._
- **Why does `TeaCupItem` connect `Tea Drink` to `Block Registration`, `Cocoa Item`, `Honey Recipe`, `Tea Item`?**
  _High betweenness centrality (0.072) - this node is a cross-community bridge._
- **Why does `AddEntryLootModifier` connect `Loot Modifiers` to `Block Registration`?**
  _High betweenness centrality (0.065) - this node is a cross-community bridge._
- **What connects `STUMP`, `BOTTOM`, `MIDDLE` to the rest of the system?**
  _37 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Block Registration` be split into smaller, more focused modules?**
  _Cohesion score 0.06801346801346801 - nodes in this community are weakly interconnected._
- **Should `Tea Block` be split into smaller, more focused modules?**
  _Cohesion score 0.09268292682926829 - nodes in this community are weakly interconnected._
- **Should `Tea Drink` be split into smaller, more focused modules?**
  _Cohesion score 0.09102564102564102 - nodes in this community are weakly interconnected._