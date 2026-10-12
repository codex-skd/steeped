package com.skd.steeped.core.config;

import net.minecraft.world.food.FoodProperties;
import net.neoforged.neoforge.common.ModConfigSpec;

/** Holds the configurable stats of a drink and builds the matching food properties. */
public class Drink {
    protected final ModConfigSpec.IntValue hunger;
    protected final ModConfigSpec.DoubleValue saturation;

    /**
     * Creates a new drink settings category for the given drink.
     * Note this does not call pop on the builder category, the class extending this is expected to call that in the constructor
     * @param builder     Config builder
     * @param name        Name of the drink
     * @param hunger      Default hunger value
     * @param saturation  Default saturation value
     */
    protected Drink(String name, ModConfigSpec.Builder builder, int hunger, double saturation) {
        builder.comment(String.format("Stats for %s", name)).push(name);
        this.hunger = builder.comment("Hunger restored when drinking this drink.")
                         .translation("steeped.config.tea.hunger")
                         .defineInRange("hunger", hunger, 0, 20);
        this.saturation = builder.comment("Saturation restored when drinking this drink.")
                               .translation("steeped.config.tea.saturation")
                               .defineInRange("saturation", saturation, 0D, 10D);
    }

    /**
     * Gets the hunger value from the config. Food properties are immutable and built while registries are
     * being filled, before configs load, so the default is used until the config is available.
     */
    public int hunger() {
        return com.skd.steeped.core.Config.serverSpec.isLoaded() ? hunger.get() : hunger.getDefault();
    }

    /** Gets the saturation value from the config */
    public float saturation() {
        return (com.skd.steeped.core.Config.serverSpec.isLoaded() ? saturation.get() : saturation.getDefault()).floatValue();
    }

    /** Builds the food properties for this drink, reading the current config values */
    public FoodProperties getFoodProperties() {
        return new FoodProperties.Builder()
                .nutrition(hunger())
                .saturationModifier(saturation())
                .alwaysEdible()
                .fast()
                .build();
    }
}
