package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.HandrailEnum;
import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class WoodHandrail extends BaseHandrail{
    @Override
    protected VoxelShapeHelper[] getShapeHelper(HandrailEnum shape) {
        return new VoxelShapeHelper[0];
    }

    @Override
    protected VoxelShapeHelper[] getVoxelShapeHelpers2(HandrailEnum shape) {
        return new VoxelShapeHelper[0];
    }

    public WoodHandrail() {
        super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }
}
