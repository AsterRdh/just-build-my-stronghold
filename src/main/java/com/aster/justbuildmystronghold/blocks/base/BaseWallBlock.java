package com.aster.justbuildmystronghold.blocks.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BaseWallBlock extends BaseHorizontalBlock {

    public static final EnumProperty<WallSide> EAST_WALL = BlockStateProperties.EAST_WALL;
    public static final EnumProperty<WallSide> NORTH_WALL = BlockStateProperties.NORTH_WALL;
    public static final EnumProperty<WallSide> SOUTH_WALL = BlockStateProperties.SOUTH_WALL;
    public static final EnumProperty<WallSide> WEST_WALL = BlockStateProperties.WEST_WALL;

    private static final VoxelShape POST_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape NORTH_TEST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape SOUTH_TEST = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_TEST = Block.box(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    private static final VoxelShape EAST_TEST = Block.box(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);

    public BaseWallBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(EAST_WALL, WallSide.NONE)
                        .setValue(NORTH_WALL, WallSide.NONE)
                        .setValue(SOUTH_WALL, WallSide.NONE)
                        .setValue(WEST_WALL, WallSide.NONE)
        );
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add( EAST_WALL);
        stateBuilder.add( NORTH_WALL);
        stateBuilder.add( SOUTH_WALL);
        stateBuilder.add( WEST_WALL);
    }

    @Override
    public BlockState updateShape(BlockState p_58014_, Direction p_58015_, BlockState p_58016_, LevelAccessor p_58017_, BlockPos p_58018_, BlockPos p_58019_) {
        return this.sideUpdate(p_58017_, p_58018_, p_58014_, p_58019_, p_58016_, p_58015_);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        //FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockPos blockpos5 = blockpos.above();
        BlockState blockstate = levelreader.getBlockState(blockpos1);
        BlockState blockstate1 = levelreader.getBlockState(blockpos2);
        BlockState blockstate2 = levelreader.getBlockState(blockpos3);
        BlockState blockstate3 = levelreader.getBlockState(blockpos4);
        BlockState blockstate4 = levelreader.getBlockState(blockpos5);
        boolean flag = this.connectsTo(blockstate, blockstate.isFaceSturdy(levelreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
        boolean flag1 = this.connectsTo(blockstate1, blockstate1.isFaceSturdy(levelreader, blockpos2, Direction.WEST), Direction.WEST);
        boolean flag2 = this.connectsTo(blockstate2, blockstate2.isFaceSturdy(levelreader, blockpos3, Direction.NORTH), Direction.NORTH);
        boolean flag3 = this.connectsTo(blockstate3, blockstate3.isFaceSturdy(levelreader, blockpos4, Direction.EAST), Direction.EAST);
        return this.updateShape(levelreader, this.defaultBlockState(), blockpos5, blockstate4, flag, flag1, flag2, flag3);
    }
//    private BlockState updateShape(LevelReader p_57980_, BlockState p_57981_, BlockPos p_57982_, BlockState p_57983_, boolean p_57984_, boolean p_57985_, boolean p_57986_, boolean p_57987_) {
//        VoxelShape voxelshape = p_57983_.getCollisionShape(p_57980_, p_57982_).getFaceShape(Direction.DOWN);
//        BlockState blockstate = this.updateSides(p_57981_, p_57984_, p_57985_, p_57986_, p_57987_, voxelshape);
//        return blockstate;
//    }

    private BlockState sideUpdate(LevelReader p_57989_, BlockPos p_57990_, BlockState p_57991_, BlockPos p_57992_, BlockState p_57993_, Direction p_57994_) {
        Direction direction = p_57994_.getOpposite();
        boolean flag = p_57994_ == Direction.NORTH ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, NORTH_WALL);
        boolean flag1 = p_57994_ == Direction.EAST ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, EAST_WALL);
        boolean flag2 = p_57994_ == Direction.SOUTH ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, SOUTH_WALL);
        boolean flag3 = p_57994_ == Direction.WEST ? this.connectsTo(p_57993_, p_57993_.isFaceSturdy(p_57989_, p_57992_, direction), direction) : isConnected(p_57991_, WEST_WALL);
        BlockPos blockpos = p_57990_.above();
        BlockState blockstate = p_57989_.getBlockState(blockpos);
        return this.updateShape(p_57989_, p_57991_, blockpos, blockstate, flag, flag1, flag2, flag3);
    }

    private BlockState updateShape(LevelReader p_57980_, BlockState p_57981_, BlockPos p_57982_, BlockState p_57983_, boolean p_57984_, boolean p_57985_, boolean p_57986_, boolean p_57987_) {
        VoxelShape voxelshape = p_57983_.getCollisionShape(p_57980_, p_57982_).getFaceShape(Direction.DOWN);
        BlockState blockstate = this.updateSides(p_57981_, p_57984_, p_57985_, p_57986_, p_57987_, voxelshape);
        return blockstate;
    }
    private BlockState updateSides(BlockState p_58025_, boolean p_58026_, boolean p_58027_, boolean p_58028_, boolean p_58029_, VoxelShape p_58030_) {
        return p_58025_.setValue(NORTH_WALL, this.makeWallState(p_58026_, p_58030_, NORTH_TEST)).setValue(EAST_WALL, this.makeWallState(p_58027_, p_58030_, EAST_TEST)).setValue(SOUTH_WALL, this.makeWallState(p_58028_, p_58030_, SOUTH_TEST)).setValue(WEST_WALL, this.makeWallState(p_58029_, p_58030_, WEST_TEST));
    }
    private WallSide makeWallState(boolean p_58042_, VoxelShape p_58043_, VoxelShape p_58044_) {
        if (p_58042_) {
            return isCovered(p_58043_, p_58044_) ? WallSide.TALL : WallSide.LOW;
        } else {
            return WallSide.NONE;
        }
    }
    private static boolean isCovered(VoxelShape p_58039_, VoxelShape p_58040_) {
        return !Shapes.joinIsNotEmpty(p_58040_, p_58039_, BooleanOp.ONLY_FIRST);
    }
    private boolean connectsTo(BlockState p_58021_, boolean p_58022_, Direction p_58023_) {
        Block block = p_58021_.getBlock();
        boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(p_58021_, p_58023_);
        return !isExceptionForConnection(p_58021_) && p_58022_ || block instanceof IronBarsBlock || flag || block instanceof DoorBlock;
    }
    private static boolean isConnected(BlockState p_58011_, Property<WallSide> p_58012_) {
        return p_58011_.getValue(p_58012_) != WallSide.NONE;
    }
}
