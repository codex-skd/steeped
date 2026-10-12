package com.skd.steeped.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Teapot {
    private final ModConfigSpec.BooleanValue infinite_water;
    private final ModConfigSpec.BooleanValue fill_from_cauldron;
    private final ModConfigSpec.BooleanValue milk_cow;
    private final ModConfigSpec.IntValue teapot_capacity;

    public Teapot(ModConfigSpec.Builder builder) {
        builder.comment("Options related to filling the teapot").push("teapot");
        infinite_water = builder.comment("If true, the teapot will not consume water source blocks when filling. It will still consume water from tank and cauldrons.")
                             .translation("steeped.config.teapot.infinite_water")
                             .define("infinite_water", false);
        fill_from_cauldron = builder.comment("If true, the teapot can be filled with water from a cauldron")
                                 .translation("steeped.config.teapot.fill_from_cauldron")
                                 .define("fill_from_cauldron", true);
        milk_cow = builder.comment("If true, cows can be milked using a teapot to fill it with milk")
                       .translation("steeped.config.teapot.milk_cow")
                       .define("milk_cow", true);
        teapot_capacity = builder.comment("Amount of fluid consumed when filling a teapot from a tank")
                              .translation("steeped.config.teapot.teapot_capacity")
                              .defineInRange("teapot_capacity", 1000, 1, 256000);
        builder.pop();
    }

    public boolean infiniteWater() {
        return infinite_water.get();
    }

    public boolean fillFromCauldron() {
        return fill_from_cauldron.get();
    }

    public boolean canMilkCows() {
        return milk_cow.get();
    }

    public int teapotCapacity() {
        return teapot_capacity.get();
    }
}
