package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.base.math.Vec3D;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BattlementsEdge extends BaseHorizontalBlock {


    public BattlementsEdge() {// .hardnessAndResistance(5.0f)
        super(Properties.of(Material.STONE, MaterialColor.STONE)
                .sound(SoundType.STONE)
                .strength(5f)
                .noOcclusion().noCollission()
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);

        return state;
    }


    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        VoxelShapeHelper voxelShapeHelper = new VoxelShapeHelper(new Vec3D(0, 0, 5), new Vec3D(16, 16, 11));
        switch (direction) {
            case EAST:
            case WEST:
                return  voxelShapeHelper.rotate(-90,0,1,0);
            case SOUTH:
            case NORTH:
            default:
                return  voxelShapeHelper.rotate(0,0,1,0);
        }
    }

    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        VoxelShapeHelper voxelShapeHelper = new VoxelShapeHelper(new Vec3D(0, 0, 5), new Vec3D(16, 24, 11));
        switch (direction) {
            case EAST:
            case WEST:
                return  voxelShapeHelper.rotate(-90,0,1,0);
            case SOUTH:
            case NORTH:
            default:
                return  voxelShapeHelper.rotate(0,0,1,0);
        }
    }

}
