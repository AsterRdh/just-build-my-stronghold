package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.*;
import com.aster.justbuildmystronghold.base.lang.MapMap;
import com.aster.justbuildmystronghold.base.math.BlockPosUtil;
import com.aster.justbuildmystronghold.base.math.DirectionUtil;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;


public abstract class BaseHandrail extends BaseHorizontalWaterBlock {
    public static final IntegerProperty GROUP = IntegerProperty.create("group",1,4);
    public static final IntegerProperty TYPE = IntegerProperty.create("type",1,6);

    private final static VoxelShapeHelper a0 = new VoxelShapeHelper(0.0, 0.0, 2.0, 16.0, 15.0, 6.0);
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(0.0, 0.0, 10.0, 16.0, 15.0, 14.0);
    private final static VoxelShapeHelper a2 = new VoxelShapeHelper(10.0, 0.0, 2.0, 14.0, 15.0, 16.0);
    private final static VoxelShapeHelper a3 = new VoxelShapeHelper(0.0, 0.0, 2.0, 10.0, 15.0, 6.0);
    private final static VoxelShapeHelper a4 = new VoxelShapeHelper(10.0, 0.0, 0.0, 14.0, 15.0, 14.0);
    private final static VoxelShapeHelper a5 = new VoxelShapeHelper(0.0, 0.0, 10.0, 10.0, 15.0, 14.0);
    private final static VoxelShapeHelper a6 = new VoxelShapeHelper(10.0, 0.0, 10.0, 14.0, 15.0, 16.0);
    private final static VoxelShapeHelper a7 = new VoxelShapeHelper(10.0, 0.0, 0.0, 14.0, 15.0, 6.0);
    private final static VoxelShapeHelper a8 = new VoxelShapeHelper(0.0, 15.0, 2.0, 8.0, 21.0, 6.0);
    private final static VoxelShapeHelper a9 = new VoxelShapeHelper(0.0, 15.0, 10.0, 8.0, 21.0, 14.0);
    private final static VoxelShapeHelper a10 = new VoxelShapeHelper(10.0, 0.0, 6.0, 14.0, 15.0, 16.0);
    private final static VoxelShapeHelper a11 = new VoxelShapeHelper(0.0, 0.0, 2.0, 14.0, 15.0, 6.0);
    private final static VoxelShapeHelper a12 = new VoxelShapeHelper(10.0, 0.0, 0.0, 14.0, 15.0, 10.0);
    private final static VoxelShapeHelper a13 = new VoxelShapeHelper(0.0, 0.0, 10.0, 14.0, 15.0, 14.0);
    private final static VoxelShapeHelper a14 = new VoxelShapeHelper(0.0, 0.0, 10.0, 10.0, 21.0, 14.0);
    private final static VoxelShapeHelper a15 = new VoxelShapeHelper(0.0, 0.0, 2.0, 10.0, 21.0, 6.0);
    private final static VoxelShapeHelper a16 = new VoxelShapeHelper(0.0, 0.0, 2.0, 8.0, 21.0, 6.0);
    private final static VoxelShapeHelper a17 = new VoxelShapeHelper(8.0, -8.0, 2.0, 16.0, 13.0, 6.0);
    private final static VoxelShapeHelper a18 = new VoxelShapeHelper(0.0, 0.0, 10.0, 8.0, 21.0, 14.0);
    private final static VoxelShapeHelper a19 = new VoxelShapeHelper(8.0, -8.0, 10.0, 16.0, 13.0, 14.0);
    private final static VoxelShapeHelper a20 = new VoxelShapeHelper(10.0, 15.0, 8.0, 14.0, 21.0, 16.0);
    private final static VoxelShapeHelper a21 = new VoxelShapeHelper(10.0, 15.0, 0.0, 14.0, 21.0, 8.0);
    private final static VoxelShapeHelper a22 = new VoxelShapeHelper(2.0, 0.0, 10.0, 6.0, 15.0, 16.0);
    private final static VoxelShapeHelper a23 = new VoxelShapeHelper(0.0, 0.0, 10.0, 2.0, 15.0, 14.0);
    private final static VoxelShapeHelper a24 = new VoxelShapeHelper(2.0, 0.0, 0.0, 6.0, 15.0, 6.0);
    private final static VoxelShapeHelper a25 = new VoxelShapeHelper(0.0, 0.0, 2.0, 2.0, 15.0, 6.0);
    private final static VoxelShapeHelper a26 = new VoxelShapeHelper(0.0, 0.0, 2.0, 8.0, 15.0, 6.0);
    private final static VoxelShapeHelper a27 = new VoxelShapeHelper(0.0, 0.0, 10.0, 8.0, 15.0, 14.0);
    private final static VoxelShapeHelper a28 = new VoxelShapeHelper(2.0, 0.0, 6.0, 6.0, 15.0, 16.0);
    private final static VoxelShapeHelper a29 = new VoxelShapeHelper(2.0, 0.0, 2.0, 8.0, 15.0, 6.0);
    private final static VoxelShapeHelper a30 = new VoxelShapeHelper(2.0, 0.0, 0.0, 6.0, 15.0, 10.0);
    private final static VoxelShapeHelper a31 = new VoxelShapeHelper(2.0, 0.0, 10.0, 8.0, 15.0, 14.0);
    private final static VoxelShapeHelper a32 = new VoxelShapeHelper(2.0, 0.0, 14.0, 6.0, 15.0, 16.0);
    private final static VoxelShapeHelper a33 = new VoxelShapeHelper(2.0, 0.0, 0.0, 6.0, 15.0, 2.0);

    private final static VoxelShapeHelper[] HANDRAIL_BASE_1_1 = new VoxelShapeHelper[]{a0};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_1_2 = new VoxelShapeHelper[]{a1};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_1_3 = new VoxelShapeHelper[]{a2,a3};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_1_4 = new VoxelShapeHelper[]{a4,a5};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_1_5 = new VoxelShapeHelper[]{a5,a6};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_1_6 = new VoxelShapeHelper[]{a3,a7};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_2_1 = new VoxelShapeHelper[]{a0,a8};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_2_2 = new VoxelShapeHelper[]{a1,a9};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_2_3 = new VoxelShapeHelper[]{a8,a10,a11};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_2_4 = new VoxelShapeHelper[]{a9,a12,a13};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_2_5 = new VoxelShapeHelper[]{a6,a14};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_2_6 = new VoxelShapeHelper[]{a7,a15};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_3_1 = new VoxelShapeHelper[]{a16,a17};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_3_2 = new VoxelShapeHelper[]{a18,a19};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_3_3 = new VoxelShapeHelper[]{a8,a10,a11,a20};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_3_4 = new VoxelShapeHelper[]{a9,a12,a13,a21};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_3_5 = new VoxelShapeHelper[]{a22,a23};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_3_6 = new VoxelShapeHelper[]{a24,a25};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_4_1 = new VoxelShapeHelper[]{a17,a26};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_4_2 = new VoxelShapeHelper[]{a19,a27};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_4_3 = new VoxelShapeHelper[]{a17,a28,a29};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_4_4 = new VoxelShapeHelper[]{a19,a30,a31};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_4_5 = new VoxelShapeHelper[]{a19,a31,a32};
    private final static VoxelShapeHelper[] HANDRAIL_BASE_4_6 = new VoxelShapeHelper[]{a17,a29,a33};
    private final static MapMap<Integer,Integer,VoxelShapeHelper[]> map=new MapMap<>();



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(GROUP).add(TYPE);
    }


    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        Integer group = blockState.getValue(GROUP);
        Integer type = blockState.getValue(TYPE);
        Direction facing = blockState.getValue(FACING);
        VoxelShapeHelper[] helpers = map.get(group, type);
        if (helpers==null)
            return super.getShape(blockState,level,pos,context);
        return getVoxelShape(facing,helpers);
    }






    public boolean useShapeForLightOcclusion(BlockState p_56967_) {
        return true;
    }


    protected abstract VoxelShapeHelper[] getShapeHelper(HandrailEnum shape);
    protected abstract VoxelShapeHelper[] getVoxelShapeHelpers2(HandrailEnum shape);

    public BaseHandrail(Properties properties) {
        super(properties);
        map.put(1,1,HANDRAIL_BASE_1_1);
        map.put(1,2,HANDRAIL_BASE_1_2);
        map.put(1,3,HANDRAIL_BASE_1_3);
        map.put(1,4,HANDRAIL_BASE_1_4);
        map.put(1,5,HANDRAIL_BASE_1_5);
        map.put(1,6,HANDRAIL_BASE_1_6);
        map.put(2,1,HANDRAIL_BASE_2_1);
        map.put(2,2,HANDRAIL_BASE_2_2);
        map.put(2,3,HANDRAIL_BASE_2_3);
        map.put(2,4,HANDRAIL_BASE_2_4);
        map.put(2,5,HANDRAIL_BASE_2_5);
        map.put(2,6,HANDRAIL_BASE_2_6);
        map.put(3,1,HANDRAIL_BASE_3_1);
        map.put(3,2,HANDRAIL_BASE_3_2);
        map.put(3,3,HANDRAIL_BASE_3_3);
        map.put(3,4,HANDRAIL_BASE_3_4);
        map.put(3,5,HANDRAIL_BASE_3_5);
        map.put(3,6,HANDRAIL_BASE_3_6);
        map.put(4,1,HANDRAIL_BASE_4_1);
        map.put(4,2,HANDRAIL_BASE_4_2);
        map.put(4,3,HANDRAIL_BASE_4_3);
        map.put(4,4,HANDRAIL_BASE_4_4);
        map.put(4,5,HANDRAIL_BASE_4_5);
        map.put(4,6,HANDRAIL_BASE_4_6);

    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        Direction direction = context.getHorizontalDirection();
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        Vec3 clickLocation = context.getClickLocation();
        double x = clickLocation.x()-blockpos.getX()-0.5;
        double z = clickLocation.z()-blockpos.getZ()-0.5;
        if (direction==Direction.EAST){
            double tempX = x;
            x = z ;
            z = tempX ;
        }else if (direction==Direction.SOUTH){
            z = z * -1;
        }else if(direction==Direction.WEST){
            double tempX=x;
            x = z * -1;
            z = tempX * -1;
        }else {
            x = x * -1;
        }

        Direction trueDirection = null;
        if (Math.abs(x)>Math.abs(z)){
            if (x>0){
                trueDirection=DirectionUtil.getRight(direction);
            }else {
                trueDirection=DirectionUtil.getLeft(direction);
            }
        }else {
            if (z>0){
                trueDirection=direction.getOpposite();
            }else {
                trueDirection=direction;
            }
        }

        state = state.setValue(FACING,trueDirection);

        return state;
    }





    private Direction getDirection(BlockState state,Direction value){
        if (state.getBlock() instanceof BaseHandrail){
            Direction direction = state.getValue(FACING);
            return getDirection1(direction, value);
        }else {
            return null;
        }
    }

    private static Direction getDirection1( Direction direction, Direction value) {
        if (direction == value) return Direction.NORTH;
        if (direction == value.getOpposite()) return Direction.SOUTH;
        if (direction == DirectionUtil.getRight(value)) return Direction.EAST;
        return Direction.WEST;
    }

    private static VoxelShape getVoxelShape(int rotation, VoxelShapeHelper... helpers){
        VoxelShape res = helpers[0].rotate(rotation,0,1,0);
        for (int i =1 ; i<helpers.length;i++){
            res = Shapes.or(res, helpers[i].rotate(rotation,0,1,0));
        }
        return res;
    }

    private static VoxelShape getVoxelShape(Direction direction, VoxelShapeHelper... helpers){
        switch (direction){
            case NORTH :return getVoxelShape(0,helpers);
            case WEST  :return getVoxelShape( 90,helpers);
            case SOUTH :return getVoxelShape(180,helpers);
            case EAST  :return getVoxelShape( -90,helpers);
            default:    return getVoxelShape(0,helpers);
        }
    }
}
