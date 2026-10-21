package com.skd.steeped.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Tree {
    private final ModConfigSpec.BooleanValue generate;
    private final ModConfigSpec.DoubleValue regrow_chance;

    public Tree(ModConfigSpec.Builder builder) {
        builder.comment("Options related to tea trees").push("tree");
        generate = builder.comment("If true, tea trees generate in the world")
                       .translation("config.steeped.tree.generation")
                       .define("generation", true);
        regrow_chance = builder.comment("Chance of leaves to regrow every random tick.")
                       .translation("config.steeped.tree.regrow_chance")
                       .defineInRange("regrow_chance", 0.05, 0.0, 1.0);
        builder.pop();
    }

    public boolean generate() {
        return generate.get();
    }

    public double regrowthChance() {
        return regrow_chance.get();
    }
}
