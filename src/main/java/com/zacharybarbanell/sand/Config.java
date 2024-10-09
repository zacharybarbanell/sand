package com.zacharybarbanell.sand;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Sand.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.DoubleValue EFFECTIVENESS = BUILDER
            .comment("Amount to speed crop growth by.  A value of 1.0 is a 100% increase.")
            .defineInRange("effectiveness", 1.0, 0, 100);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int effectivenessWhole;
    public static double effectivenessFractional;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        double effectiveness = EFFECTIVENESS.get();
        effectivenessWhole = (int) effectiveness;
        effectivenessFractional = effectiveness - effectivenessWhole;
    }
}
