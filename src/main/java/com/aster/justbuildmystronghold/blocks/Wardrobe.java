package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.WardrobeEnum;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalBlock;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Wardrobe extends BaseHorizontalWaterBlock {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final EnumProperty<WardrobeEnum> TYPE = EnumProperty.create("wardrobe",WardrobeEnum.class);
    public static final IntegerProperty STYLE = IntegerProperty.create("style",0,2);

    public Wardrobe() {
        super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(OPEN).add(HINGE).add(TYPE).add(STYLE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        WardrobeEnum wardrobeType = WardrobeEnum.SINGLE;
        Direction direction = context.getHorizontalDirection();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        boolean isOpen = false;

        //设置左开还是右开
        Vec3 clickLocation = context.getClickLocation();
        BlockPos clickedPos = context.getClickedPos();
        DoorHingeSide side = DoorHingeSide.LEFT;
        int zPos = clickedPos.getZ();
        double zLoc = clickLocation.z();
        int XPos = clickedPos.getX();
        double XLoc = clickLocation.x();
        switch (direction){
            case NORTH :
                side = (zLoc-zPos-0.5) > 0?DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
                break;
            case SOUTH:
                side = -(zLoc-zPos-0.5) > 0?DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
                break;
            case WEST:
                side = (XLoc-XPos-0.5) > 0?DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
                break;
            case EAST:
                side = -(XLoc-XPos-0.5) > 0?DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
                break;

        }

        //判断是单体柜子还是两层的
        Level level = context.getLevel();
        BlockState belowBlockState = level.getBlockState(clickedPos.below());
        if (belowBlockState.is(this)){
            WardrobeEnum value = belowBlockState.getValue(TYPE);
            if (value == WardrobeEnum.SINGLE){
                wardrobeType = WardrobeEnum.UPPER;
                isOpen = belowBlockState.getValue(OPEN);
                side = belowBlockState.getValue(HINGE);

                belowBlockState = belowBlockState.setValue(TYPE,WardrobeEnum.LOWER);
                level.setBlock(clickedPos.below(),belowBlockState,0);
            }
        }else {
            BlockState aboveBlockState = level.getBlockState(clickedPos.above());
            if (aboveBlockState.is(this)){
                WardrobeEnum value = aboveBlockState.getValue(TYPE);
                if (value == WardrobeEnum.SINGLE){
                    wardrobeType = WardrobeEnum.LOWER;
                    isOpen = aboveBlockState.getValue(OPEN);
                    side = aboveBlockState.getValue(HINGE);

                    aboveBlockState = aboveBlockState.setValue(TYPE,WardrobeEnum.LOWER);
                    level.setBlock(clickedPos.above(),aboveBlockState,0);
                }
            }
        }

        return this.defaultBlockState()
                .setValue(FACING, direction)
                .setValue(HINGE,side)
                .setValue(TYPE, wardrobeType)
                .setValue(OPEN,isOpen)
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos blockPos, BlockState state) {
        super.destroy(level, blockPos, state);
//        BlockState belowBlockState = level.getBlockState(blockPos.below());
//        level.getBlockState(blockPos.below()).getBlock().onNeighborChange(belowBlockState,level,blockPos.below(),blockPos);
//        BlockState aboveBlockState = level.getBlockState(blockPos.above());
//        aboveBlockState.getBlock().onNeighborChange(aboveBlockState,level,blockPos.above(),blockPos);
    }

    private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) && blockstate.getValue(TYPE) == WardrobeEnum.SINGLE ? blockstate.getValue(FACING) : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        boolean open =!state.getValue(OPEN);
        state = state.setValue(OPEN,open);
        level.setBlock(pos,state,0);
        level.levelEvent(player, state.getValue(OPEN) ? 1006 : 1012, pos, 0);

        WardrobeEnum value = state.getValue(TYPE);
        if (value==WardrobeEnum.LOWER){
            BlockState blockState = level.getBlockState(pos.above());
            if (blockState.is(this)){
                blockState = blockState.setValue(OPEN,open);
                level.setBlock(pos.above(),blockState,2);
            }
        }else if (value==WardrobeEnum.UPPER){
            BlockState blockState = level.getBlockState(pos.below());
            if (blockState.is(this)){
                blockState = blockState.setValue(OPEN,open);
                level.setBlock(pos.below(),blockState,2);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state2, boolean bool) {
        super.onPlace(state, level, pos, state2, bool);


    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);



//        WardrobeEnum wardrobeType = state.getValue(TYPE);
//        Boolean open = false;
//        BlockState belowBlockState = level.getBlockState(pos.below());
//        if (belowBlockState.is(this)){
//            WardrobeEnum value = belowBlockState.getValue(TYPE);
//            open = belowBlockState.getValue(OPEN);
//            if (value==WardrobeEnum.LOWER){
//                wardrobeType=WardrobeEnum.UPPER;
//            }
//        }else {
//            BlockState aboveBlockState = level.getBlockState(pos.above());
//            if (aboveBlockState.is(this)){
//                WardrobeEnum value = aboveBlockState.getValue(TYPE);
//                open = aboveBlockState.getValue(OPEN);
//                if (value==WardrobeEnum.UPPER){
//                    wardrobeType=WardrobeEnum.LOWER;
//                }
//            }
//        }
//        state.setValue(TYPE,wardrobeType).setValue(OPEN,open);
//        ((Level)level).setBlock(pos,state,0);


    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block bloce, BlockPos changePos, boolean p_60514_) {
        WardrobeEnum value = blockState.getValue(TYPE);
        BlockState aboveBlockState = level.getBlockState(pos.above());
        BlockState belowBlockState = level.getBlockState(pos.below());
        if (value==WardrobeEnum.LOWER && bloce instanceof Wardrobe ){
            if (!aboveBlockState.is(this)){
                blockState = blockState.setValue(TYPE,WardrobeEnum.SINGLE);
            }
        }else if (value==WardrobeEnum.UPPER && bloce instanceof Wardrobe){
            if (!belowBlockState.is(this)){
                blockState = blockState.setValue(TYPE,WardrobeEnum.SINGLE);
            }
        }
        if (aboveBlockState.is(this) && aboveBlockState.getValue(TYPE)==WardrobeEnum.UPPER){
            blockState = blockState.setValue(TYPE,WardrobeEnum.LOWER);
        }else if (belowBlockState.is(this) && belowBlockState.getValue(TYPE)==WardrobeEnum.LOWER){
            blockState = blockState.setValue(TYPE,WardrobeEnum.UPPER);
        }



        level.setBlock(pos,blockState,2);

//        WardrobeEnum value = blockState.getValue(TYPE);
//        Boolean open = false;
//        if (value==WardrobeEnum.LOWER){
//            BlockState state = level.getBlockState(pos.above());
//            if (blockState.is(this)){
//                open = state.getValue(OPEN);
//            }
//        }else if (value==WardrobeEnum.UPPER){
//            BlockState state = level.getBlockState(pos.below());
//            if (blockState.is(this)){
//                open = state.getValue(OPEN);
//            }
//        }
//
//        blockState = blockState.setValue(OPEN,open);
//        level.setBlock(pos,blockState,0);



    }
}
