package com.skd.steeped.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CocoaDrink extends Drink {
    private final ModConfigSpec.EnumValue<ClearType> clear_effects;

    public CocoaDrink(ModConfigSpec.Builder builder, int hunger, double saturation) {
        super("cocoa", builder, hunger, saturation);
        this.clear_effects = builder.comment("If ALL, drinking cocoa clears status effects like milk", "If NEGATIVE, clears only negative effects (default)", "If NONE, does not clear effects", "For all values, regular cocoa clears just the first effect, while cocoa with cinnamon clears all")
                                 .translation("steeped.config.tea.clear_effects")
                                 .defineEnum("clear_effects", ClearType.NEGATIVE);
        builder.pop();
    }

    public boolean clearsEffects() {
        return clear_effects.get() != ClearType.NONE;
    }

    public boolean clearsPositive() {
        return clear_effects.get() == ClearType.ALL;
    }

    public enum ClearType {
        ALL,
        NEGATIVE,
        NONE
    }
}
