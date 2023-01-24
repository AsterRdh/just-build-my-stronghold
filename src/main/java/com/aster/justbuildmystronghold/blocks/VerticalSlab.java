package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.base.math.DirectionUtil;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerticalSlab extends BaseHorizontalWaterBlock {
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(0, 0, 0, 8, 16, 16);
    public VerticalSlab(Properties properties) {
        super(properties);
    }

    public VerticalSlab() {
        super(  BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(5.0F)
                .sound(SoundType.STONE)
                .noOcclusion());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();//context.getHorizontalDirection()
        BlockPos blockpos = context.getClickedPos();
        Vec3 vec3 = context.getClickLocation();
        double d0 = vec3.z - (double)blockpos.getZ()-0.5;
        double d1 = vec3.x - (double)blockpos.getX()-0.5;
        if (direction == Direction.WEST){
            double tempD1=d1*-1;
            d1=d0;
            d0=tempD1;
        }else if (direction == Direction.NORTH){
//            double tempD0=d0;
            d0=d0*-1;
            d1=d1*-1;
//            d1=tempD0*-1;
        }else if (direction == Direction.EAST){
            double tempD0=d0;
            d0=d1;
            d1=tempD0*-1;
        }
        Direction direction2;
        if (Math.abs(d0)>Math.abs(d1)){
            if (d0>0){
                direction2 = direction;
            }else {
                direction2 = direction.getOpposite();
            }
        }else {
            if (d1>0){
                direction2 = DirectionUtil.getLeft(direction);
            }else {
                direction2 = DirectionUtil.getRight(direction);
            }
        }


        BlockState blockState = super.getStateForPlacement(context);
        blockState = blockState.setValue(FACING,direction2);
        return blockState;
    }



    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        VoxelShape shape;
        switch (direction){
            case NORTH -> shape = a1.rotate(-90,0,1,0);
            case WEST  -> shape = a1.rotate(0,0,1,0);
            case SOUTH -> shape = a1.rotate(90,0,1,0);
            case EAST  -> shape = a1.rotate(180,0,1,0);
            default -> shape = a1.rotate(0,0,1,0);
        }

        return shape;
    }
}
