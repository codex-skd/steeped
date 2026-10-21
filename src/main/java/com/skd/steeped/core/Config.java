package com.skd.steeped.core;

import com.skd.steeped.core.config.CocoaDrink;
import com.skd.steeped.core.config.TeaDrink;
import com.skd.steeped.core.config.Teapot;
import com.skd.steeped.core.config.Tree;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static class Server {
        public TeaDrink black_tea, green_tea, floral_tea, chai_tea, chorus_tea, iced_tea;
        public CocoaDrink cocoa;
        public Teapot teapot;
        public Tree tree;

        public Server(ModConfigSpec.Builder builder) {
            builder.comment("Stats for each available drink type").push("drinks");
            floral_tea = new TeaDrink("floral", builder, TeaDrink.TeaEffect.RESTFUL, 1, 0.5, 60, 2);
            green_tea = new TeaDrink("green", builder, TeaDrink.TeaEffect.RELAXED, 3, 0.5, 120, 2);
            black_tea = new TeaDrink("black", builder, TeaDrink.TeaEffect.CAFFEINATED, 4, 0.8, 210, 2);
            chai_tea = new TeaDrink("chai", builder, TeaDrink.TeaEffect.INVIGORATED, 5, 0.6, 150, 1);
            chorus_tea = new TeaDrink("chorus", builder, TeaDrink.TeaEffect.ENDERFALLING, 3, 0.9, 150, 1);
            iced_tea = new TeaDrink("iced", builder, TeaDrink.TeaEffect.ABSORPTION, 3, 0.9, 90, 1);
            cocoa = new CocoaDrink(builder, 4, 0.6);
            builder.pop();

            teapot = new Teapot(builder);
            tree = new Tree(builder);
        }
    }

    public static final ModConfigSpec serverSpec;
    public static final Server SERVER;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        Server server = new Server(builder);
        serverSpec = builder.build();
        SERVER = server;
    }
}
