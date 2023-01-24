package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.SHBlockStateProperties;
import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseSlab extends BaseHorizontalWaterBlock {

    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final IntegerProperty SHAPE = SHBlockStateProperties.SHAPE;

    public BaseSlab(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(HINGE,SHAPE,HALF);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        DoorHingeSide side = blockState.getValue(HINGE);
        Integer shape = blockState.getValue(SHAPE);
        Half half = blockState.getValue(HALF);


        Map<Direction, VoxelShape> voxelShape = getVoxelShape(side,shape,half);
        Direction direction = blockState.getValue(FACING);
        return voxelShape.get(direction);
    }

    private static Map<Direction,VoxelShape> getVoxelShape(DoorHingeSide side,Integer shape, Half half){

        List<VoxelShapeHelper> helperList=new ArrayList<>();
        if (half==Half.TOP){
            if (side == DoorHingeSide.LEFT){
                helperList.add( new VoxelShapeHelper(0,0,0,8, 16, 16));
                helperList.add( new VoxelShapeHelper(8,8, 0,16, 16, 16));
                switch (shape){
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }else {
                helperList.add( new VoxelShapeHelper(8, 0, 0,16, 16, 16));
                helperList.add( new VoxelShapeHelper(0, 8, 0,8, 16, 16));
                switch (shape){
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }

        }else {
            if (side == DoorHingeSide.LEFT){
                helperList.add( new VoxelShapeHelper(0,0,0,8, 16, 16));
                helperList.add( new VoxelShapeHelper(8, 0, 0,16, 8, 16));
            }else {
                helperList.add( new VoxelShapeHelper(8, 0, 0,16, 16, 16));
                helperList.add( new VoxelShapeHelper(0, 0, 0,8, 8, 16));
            }

        }

        VoxelShapeHelper[] helpers = helperList.toArray(new VoxelShapeHelper[0]);

        Map<Direction,VoxelShape> voxelShapeMap=new HashMap<>();
        voxelShapeMap.put(Direction.NORTH,getVoxelShape(0,helpers));
        voxelShapeMap.put(Direction.WEST,getVoxelShape(90,helpers));
        voxelShapeMap.put(Direction.SOUTH,getVoxelShape(180,helpers));
        voxelShapeMap.put(Direction.EAST,getVoxelShape(-90,helpers));
        return voxelShapeMap;
    }


    private static VoxelShape getVoxelShape(int rotation, VoxelShapeHelper... helpers){
        VoxelShape res = helpers[0].rotate(rotation,0,1,0);
        for (int i =1 ; i<helpers.length;i++){
            res =   Shapes.or(res, helpers[i].rotate(rotation,0,1,0));
        }
        return res;
    }



    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockState = super.getStateForPlacement(context);
        blockState = blockState
                .setValue(HINGE,getHinge(context))
                .setValue(SHAPE,0)
                .setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP);
        return blockState;
    }

    protected DoorHingeSide getHinge(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        int j = direction.getStepX();
        int k = direction.getStepZ();
        Vec3 vec3 = context.getClickLocation();
        double d0 = vec3.x - (double)blockpos.getX();
        double d1 = vec3.z - (double)blockpos.getZ();
        return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
    }

}
