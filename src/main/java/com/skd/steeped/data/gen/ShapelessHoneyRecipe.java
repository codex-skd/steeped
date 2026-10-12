package com.skd.steeped.data.gen;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.skd.steeped.core.Registration;
import com.skd.steeped.item.TeaCupItem;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;

public class ShapelessHoneyRecipe extends ShapelessRecipe {
    private final Item tea;
    private final Ingredient honey;
    private final String tag;

    public ShapelessHoneyRecipe(String group, Item tea, Ingredient honey, String tag) {
        super(group, CraftingBookCategory.MISC, TeaCupItem.withHoney(new ItemStack(tea), tag), NonNullList.of(Ingredient.EMPTY, Ingredient.of(tea), honey));
        this.tea = tea;
        this.honey = honey;
        this.tag = tag;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registration.SHAPELESS_HONEY.get();
    }

    @Override
    public boolean matches(CraftingInput inv, Level level) {
        if (!super.matches(inv, level)) {
            return false;
        }
        // search the inventory for the tea, ensure it lacks honey
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == tea) {
                return !TeaCupItem.hasHoney(stack, tag);
            }
        }
        // somehow did not find the tea, thats a problem
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider registryAccess) {
        // search the inventory for the tea, copy that for the return
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == tea) {
                return TeaCupItem.withHoney(stack.copyWithCount(1), tag);
            }
        }
        return getResultItem(registryAccess).copy();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inv) {
        NonNullList<ItemStack> list = NonNullList.withSize(inv.size(), ItemStack.EMPTY);
        for (int i = 0; i < list.size(); ++i) {
            ItemStack item = inv.getItem(i);
            if (item.hasCraftingRemainingItem() && item.getItem() != tea) {
                list.set(i, item.getCraftingRemainingItem());
            }
        }
        return list;
    }

    public static class Serializer implements RecipeSerializer<ShapelessHoneyRecipe> {
        public static final MapCodec<ShapelessHoneyRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(ShapelessHoneyRecipe::getGroup),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("tea").forGetter(r -> r.tea),
            Ingredient.CODEC_NONEMPTY.fieldOf("honey").forGetter(r -> r.honey),
            Codec.STRING.optionalFieldOf("tag", "with_honey").forGetter(r -> r.tag)
        ).apply(inst, ShapelessHoneyRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ShapelessHoneyRecipe> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public void encode(RegistryFriendlyByteBuf buffer, ShapelessHoneyRecipe recipe) {
                buffer.writeUtf(recipe.getGroup());
                ItemStack.STREAM_CODEC.encode(buffer, new ItemStack(recipe.tea));
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.honey);
                buffer.writeUtf(recipe.tag);
            }

            @Override
            public ShapelessHoneyRecipe decode(RegistryFriendlyByteBuf buffer) {
                String group = buffer.readUtf();
                Item tea = ItemStack.STREAM_CODEC.decode(buffer).getItem();
                Ingredient honey = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                String tag = buffer.readUtf();
                return new ShapelessHoneyRecipe(group, tea, honey, tag);
            }
        };

        @Override
        public MapCodec<ShapelessHoneyRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapelessHoneyRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
