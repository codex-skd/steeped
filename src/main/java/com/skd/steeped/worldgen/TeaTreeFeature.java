package com.skd.steeped.worldgen;

import com.skd.steeped.block.TeaTrunkBlock;
import com.skd.steeped.block.TeaTrunkBlock.TrunkType;
import com.skd.steeped.core.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.stream.IntStream;

public class TeaTreeFeature extends Feature<NoneFeatureConfiguration> {
    public TeaTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        if (IntStream.range(0, 4).allMatch(i -> world.isEmptyBlock(pos.above(i)))) {
            BlockState trunk = Registration.TEA_TRUNK.get().defaultBlockState().setValue(TeaTrunkBlock.CLIPPED, false);
            world.setBlock(pos, trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.STUMP), 3);
            world.setBlock(pos.above(), trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.BOTTOM), 3);

            int height = 3;
            if (world.isEmptyBlock(pos.above(4))) {
                height += random.nextInt(world.isEmptyBlock(pos.above(5)) ? 3 : 2);
            }
            boolean north, south, west, east;
            for (int i = 2; i < height; i++) {
                north = random.nextBoolean();
                south = random.nextBoolean();
                west = random.nextBoolean();
                east = random.nextBoolean();
                BlockPos branch = pos.above(i);

                if (north) setBlockSafe(world, branch.north(), trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.SOUTH));
                if (east) setBlockSafe(world, branch.east(), trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.WEST));
                if (south) setBlockSafe(world, branch.south(), trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.NORTH));
                if (west) setBlockSafe(world, branch.west(), trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.EAST));

                world.setBlock(branch, trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.MIDDLE), 3);
            }

            world.setBlock(pos.above(height), trunk.setValue(TeaTrunkBlock.TYPE, TrunkType.TOP), 3);
            return true;
        }
        return false;
    }

    private static void setBlockSafe(LevelAccessor world, BlockPos pos, BlockState state) {
        BlockState old = world.getBlockState(pos);
        if (old.isAir() || old.canBeReplaced() || old.is(BlockTags.LEAVES)) {
            world.setBlock(pos, state, 3);
        }
    }
}
