package com.skd.steeped.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SimplyTags {
    public static void init() {
        Items.init();
    }

    public static class Items {
        public static final TagKey<Item> TEA_CROP = forgeTag("crops/tea");
        public static final TagKey<Item> TEAS = tag("teas");
        public static final TagKey<Item> EXCLUSIVE_TEAS = tag("teas/exclusive");
        public static final TagKey<Item> ICE_CUBES = forgeTag("ice_cubes");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("steeped", name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
        }

        private static void init() {}
    }
}
