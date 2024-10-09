package com.zacharybarbanell.sand.mixin;

import com.zacharybarbanell.sand.Config;
import com.zacharybarbanell.sand.Sand;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isRandomlyTicking()Z", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onRandomTick(LevelChunk chunk, int randomTickSpeed, CallbackInfo ci, ChunkPos chunkPos, boolean bl, int i,
                              int j, ProfilerFiller profiler, LevelChunkSection[] chunkSections, int m, LevelChunkSection chunkSection, int k, int n,
                              int l, BlockPos blockPos3, BlockState blockState4) {
        if (blockState4.is(Sand.SOIL)) {
            ServerLevel world = (ServerLevel) chunk.getLevel();
            RandomSource random = world.getRandom();
            BlockPos target = blockPos3.above();
            int repetitions = (int) Config.effectivenessWhole;
            if (Config.effectivenessFractional > 0) {
                repetitions += random.nextFloat() < Config.effectivenessFractional ? 1 : 0;
            }
            while(repetitions > 0 && world.getBlockState(target).is(Sand.CROP)) {
                if(world.getBlockState(target.above()).is(Sand.CROP)) {
                    target = target.above();
                }
                else {
                    world.getBlockState(target).randomTick(world, target, random);
                    repetitions--;
                }
            }
        }
    }
}
