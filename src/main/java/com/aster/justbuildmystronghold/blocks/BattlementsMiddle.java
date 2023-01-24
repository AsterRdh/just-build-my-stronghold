package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.HandrailEnum;
import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.base.math.Vec3D;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.block.Blocks.POLISHED_BLACKSTONE_BRICKS;

public class BattlementsMiddle extends BaseHorizontalBlock {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty LINK = BooleanProperty.create("link");
    private final static VoxelShapeHelper a0 = new VoxelShapeHelper(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(11.0, 0.0, 5.0, 16.0, 16.0, 11.0);
    private final static VoxelShapeHelper a2 = new VoxelShapeHelper(5.0, 0.0, 0.0, 11.0, 16.0, 5.0);
    private final static VoxelShapeHelper a3 = new VoxelShapeHelper(5.0, 0.0, 11.0, 11.0, 16.0, 16.0);
    private final static VoxelShapeHelper a4 = new VoxelShapeHelper(0.0, 0.0, 5.0, 5.0, 16.0, 11.0);
    public BattlementsMiddle() {
        super(Properties.of(Material.STONE, MaterialColor.STONE)
                        .sound(SoundType.STONE)
                        .strength(5f)
                        .noOcclusion().noCollission()
                );

    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(NORTH,WEST,SOUTH,EAST,LINK);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();
        state = state.setValue(NORTH,okCatenate(level,clickedPos.north()));
        state = state.setValue(WEST,okCatenate(level,clickedPos.west()));
        state = state.setValue(SOUTH,okCatenate(level,clickedPos.south()));
        state = state.setValue(EAST,okCatenate(level,clickedPos.east()));
        state = state.setValue(LINK,okCatenate(level,clickedPos.below())?level.getBlockState(clickedPos.below()).getValue(LINK):false);
        state=getState(state,level,clickedPos);
        return state;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction changedDirection, BlockState changedBlockState, LevelAccessor levelAccessor, BlockPos pos, BlockPos changePos) {
        state = super.updateShape(state, changedDirection, changedBlockState, levelAccessor, pos, changePos);

        //state = state.setValue(NORTH,false).setValue(WEST,false).setValue(SOUTH,false).setValue(EAST,false);
        state = state.setValue(NORTH,okCatenate(levelAccessor,pos.north()));
        state = state.setValue(WEST,okCatenate(levelAccessor,pos.west()));
        state = state.setValue(SOUTH,okCatenate(levelAccessor,pos.south()));
        state = state.setValue(EAST,okCatenate(levelAccessor,pos.east()));
        state = state.setValue(LINK,okCatenate(levelAccessor,pos.below())?levelAccessor.getBlockState(pos.below()).getValue(LINK):false);
//        switch (changedDirection){
//            case NORTH :
//                state = state.setValue(NORTH,okCatenate(levelAccessor,changePos));
//                break;
//            case WEST:
//                state = state.setValue(WEST,okCatenate(levelAccessor,changePos));
//                break;
//            case SOUTH:
//                state = state.setValue(SOUTH,okCatenate(levelAccessor,changePos));
//                break;
//            case EAST:
//                state = state.setValue(EAST,okCatenate(levelAccessor,changePos));
//                break;
//        }
        state=getState(state,levelAccessor,pos);
        return state;
    }

    private BlockState getState(BlockState state,LevelAccessor level,BlockPos pos){
        int linkCount = 0;
        if (state.getValue(NORTH)) linkCount++;
        if (state.getValue(WEST)) linkCount++;
        if (state.getValue(SOUTH)) linkCount++;
        if (state.getValue(EAST)) linkCount++;
        if (linkCount<2){
            Direction value = state.getValue(FACING);
            if (value == Direction.SOUTH || value == Direction.NORTH){
                state=state.setValue(WEST,true).setValue(EAST,true);
            }else {
                state=state.setValue(NORTH,true).setValue(SOUTH,true);
            }
        }
        return state;
    }

    private boolean okCatenate(LevelAccessor level,BlockPos pos){
        return level.getBlockState(pos).getBlock() instanceof BattlementsMiddle;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape =  Block.box(0, 0, 0, 0, 0, 0) ;
        if (!state.getValue(LINK)) shape = a0.getShape();
        if (state.getValue(NORTH)) shape=shape == null ? a2.getShape() : Shapes.or(shape,a2.getShape());
        if (state.getValue(WEST))  shape=shape == null ? a4.getShape() : Shapes.or(shape,a4.getShape());
        if (state.getValue(SOUTH)) shape=shape == null ? a3.getShape() : Shapes.or(shape,a3.getShape());
        if (state.getValue(EAST))  shape=shape == null ? a1.getShape() : Shapes.or(shape,a1.getShape());
        return shape;
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
