package com.zacharybarbanell.sand;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Sand.MODID)
public class Sand
{
    public static final String MODID = "sand";

    public static final TagKey<Block> SOIL = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "soil"));
    public static final TagKey<Block> CROP = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "crop"));

    public Sand(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
