package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseWallBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class WainscotBlock extends BaseWallBlock implements SimpleWaterloggedBlock {
    public WainscotBlock(Properties properties) {
        super(properties);
    }

    public WainscotBlock() {
        super(
                BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .sound(SoundType.WOOD)
                .strength(5f)
        );
    }
}
