package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class QuarterSlab extends StairBlock {
    public QuarterSlab(BlockState state, Properties properties) {
        super(state, properties);
    }

//    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
//    public static final EnumProperty<StairsShape> STAIRS_SHAPE = BlockStateProperties.STAIRS_SHAPE;



//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
//        super.createBlockStateDefinition(stateBuilder);
//        stateBuilder.add(HALF,STAIRS_SHAPE);
//    }

//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext context) {
//        BlockState blockState = super.getStateForPlacement(context);
//        Direction direction = context.getClickedFace();
//        BlockPos blockpos = context.getClickedPos();
//        blockState = blockState.setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP);
//        return blockState;
//    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        Half half = blockState.getValue(HALF);
        StairsShape shape = blockState.getValue(SHAPE);
        VoxelShapeHelper a1 = new VoxelShapeHelper(0, 8, 8, 16, 16, 16);
        VoxelShapeHelper b2L = new VoxelShapeHelper(8, 0, 8, 16, 8, 16);
        VoxelShapeHelper b2R = new VoxelShapeHelper(0, 0, 8, 8, 8, 16);

        VoxelShapeHelper c1L = new VoxelShapeHelper(8, 8, 0, 16, 16, 8);
        VoxelShapeHelper c1R = new VoxelShapeHelper(0, 8, 0, 8, 16, 8);

        VoxelShapeHelper a2 = new VoxelShapeHelper(0, 0, 8, 16, 8, 16);
        VoxelShapeHelper b1L = new VoxelShapeHelper(8, 8, 8, 16, 16, 16);
        VoxelShapeHelper b1R = new VoxelShapeHelper(0, 8, 8, 8, 16, 16);

        VoxelShapeHelper c2L = new VoxelShapeHelper(8, 0, 0, 16, 8, 8);
        VoxelShapeHelper c2R = new VoxelShapeHelper(0, 0, 0, 8, 8, 8);

        List<VoxelShapeHelper> shapes=new ArrayList<>();
        switch (shape){
            case STRAIGHT :
                shapes.add(half==Half.TOP?a1:a2);
                break;
            case INNER_LEFT:
                shapes.add(half==Half.TOP?a1:a2);
                shapes.add(half==Half.TOP?c1L:c2L);
                break;
            case INNER_RIGHT:
                shapes.add(half==Half.TOP?a1:a2);
                shapes.add(half==Half.TOP?c1R:c2R);
                break;
            case OUTER_LEFT:
                shapes.add(half==Half.TOP?b1L:b2L);
                break;
            case OUTER_RIGHT:
                shapes.add(half==Half.TOP?b1R:b2R);
                break;
        }

        switch (direction){
            case WEST : return getVoxelShape(-90,shapes.toArray(new VoxelShapeHelper[0]));
            case EAST:  return getVoxelShape(90,shapes.toArray(new VoxelShapeHelper[0]));
            case NORTH: return getVoxelShape(180,shapes.toArray(new VoxelShapeHelper[0]));
            case SOUTH:
            default: return getVoxelShape(0,shapes.toArray(new VoxelShapeHelper[0]));
        }

    }

    private static VoxelShape getVoxelShape(int rotation, VoxelShapeHelper... helpers){
        VoxelShape res = helpers[0].rotate(rotation,0,1,0);
        for (int i =1 ; i<helpers.length;i++){
            res =   Shapes.or(res, helpers[i].rotate(rotation,0,1,0));
        }
        return res;
    }
}
