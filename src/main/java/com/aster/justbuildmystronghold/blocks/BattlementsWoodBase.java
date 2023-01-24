package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.HandrailEnum;
import com.aster.justbuildmystronghold.base.SHBlockStateProperties;
import com.aster.justbuildmystronghold.base.TorchEnum;
import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.base.math.DirectionUtil;
import com.aster.justbuildmystronghold.base.math.Vec3D;
import com.aster.justbuildmystronghold.blocks.base.BaseWallBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BattlementsWoodBase extends StairBlock {

    public static final EnumProperty<TorchEnum> TORCH = SHBlockStateProperties.TORCH;
    public static final DirectionProperty TORCH_FACING = SHBlockStateProperties.TORCH_FACING;
    protected final ParticleOptions flameParticle;
    private final static VoxelShapeHelper a0 = new VoxelShapeHelper(0.0, 0.0, 0.0, 16.0, 16.0, 4.0);
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(0.0, 0.0, 4.0, 4.0, 16.0, 16.0);
    private final static VoxelShapeHelper a2 = new VoxelShapeHelper(12.0, 0.0, 4.0, 16.0, 16.0, 16.0);
    private final static VoxelShapeHelper a3 = new VoxelShapeHelper(0.0, 0.0, 0.0, 4.0, 16.0, 4.0);
    private final static VoxelShapeHelper a4 = new VoxelShapeHelper(12.0, 0.0, 0.0, 16.0, 16.0, 4.0);

    private final static VoxelShapeHelper b1 = new VoxelShapeHelper(0, 0, 0, 16, 24, 4);
    private final static VoxelShapeHelper b2 = new VoxelShapeHelper(12, 0, 4, 16, 24, 16);
    private final static VoxelShapeHelper b3 = new VoxelShapeHelper(0, 0, 4, 4, 24, 16);
    private final static VoxelShapeHelper b4 = new VoxelShapeHelper(12, 0.0, 0, 16, 24, 4);
    private final static VoxelShapeHelper b5 = new VoxelShapeHelper(0, 0, 0, 4, 24, 4);


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(TORCH,TORCH_FACING);
    }


    public BattlementsWoodBase(BlockState state, Properties properties) {
        super(state, properties);
        this.flameParticle =  ParticleTypes.FLAME ;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        BlockState upperState = level.getBlockState(blockpos.above());
        Half half = upperState.getBlock() instanceof BattlementsWoodBase? Half.BOTTOM : Half.TOP;
        blockstate = blockstate.setValue(HALF,half).setValue(TORCH,TorchEnum.NONE);
        blockstate = blockstate.setValue(SHAPE, getStairsShape(blockstate, level, blockpos));
        return blockstate;
    }

    @Override
    public BlockState updateShape(BlockState selfBlockState, Direction direction, BlockState changeBlockState, LevelAccessor level, BlockPos selfPos, BlockPos ChangePos) {
        if (selfBlockState.getValue(WATERLOGGED)) {
            level.scheduleTick(selfPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        BlockState blockState = level.getBlockState(selfPos.above());
        if (blockState.getBlock() instanceof BattlementsWoodBase){
            selfBlockState = selfBlockState.setValue(HALF,Half.BOTTOM);
        }else {
            selfBlockState = selfBlockState.setValue(HALF,Half.TOP);
        }
        return selfBlockState;
    }


    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState state2, boolean p_56912_) {
        super.onRemove(state, level, blockPos, state2, p_56912_);
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos blockPos, BlockState blockState) {
        super.destroy(level, blockPos, blockState);
        if (blockState.getValue(TORCH) !=TorchEnum.NONE && level instanceof ServerLevel){
            Containers.dropItemStack((ServerLevel)level, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), new ItemStack(Items.TORCH,1));
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        VoxelShapeHelper[] helpers = getVoxelShapeHelpers(blockState);
        switch (direction){
            case NORTH :return getVoxelShape(0,helpers);
            case WEST  :return getVoxelShape( 90,helpers);
            case SOUTH :return getVoxelShape(180,helpers);
            case EAST  :return getVoxelShape( -90,helpers);
            default:    return getVoxelShape(0,helpers);
        }
    }

    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        VoxelShapeHelper[] helpers = getVoxelShapeHelpers2(blockState);
        switch (direction){
            case NORTH :return getVoxelShape(0,helpers);
            case WEST  :return getVoxelShape( 90,helpers);
            case SOUTH :return getVoxelShape(180,helpers);
            case EAST  :return getVoxelShape( -90,helpers);
            default:    return getVoxelShape(0,helpers);
        }

    }

    private static VoxelShape getVoxelShape(int rotation, VoxelShapeHelper... helpers){
        VoxelShape res = helpers[0].rotate(rotation,0,1,0);
        for (int i =1 ; i<helpers.length;i++){
            res = Shapes.or(res, helpers[i].rotate(rotation,0,1,0));
        }
        return res;
    }

    protected VoxelShapeHelper[] getVoxelShapeHelpers(BlockState blockState){

        Half half = blockState.getValue(HALF);
        StairsShape shape = blockState.getValue(SHAPE);
        switch (shape){
            case STRAIGHT :
                return new VoxelShapeHelper[]{a0};
            case INNER_LEFT:
                return new VoxelShapeHelper[]{a0,a1};
            case INNER_RIGHT:
                return new VoxelShapeHelper[]{a0,a2};
            case OUTER_LEFT:
                return new VoxelShapeHelper[]{a3};
            case OUTER_RIGHT:
                return new VoxelShapeHelper[]{a4};
        }
        return null;
    }

    protected VoxelShapeHelper[] getVoxelShapeHelpers2(BlockState blockState){
        StairsShape shape = blockState.getValue(SHAPE);
        switch (shape){
            case STRAIGHT : return new VoxelShapeHelper[]{b1};
            case INNER_LEFT : return new VoxelShapeHelper[]{b1,b3};
            case INNER_RIGHT : return new VoxelShapeHelper[]{b1,b2};
            case OUTER_LEFT : return new VoxelShapeHelper[]{b5};
            case OUTER_RIGHT : return new VoxelShapeHelper[]{b4};
        }
        return null;
    }

    protected static StairsShape getStairsShape(BlockState blockState, BlockGetter level, BlockPos pos) {
        Direction direction = blockState.getValue(FACING);
        BlockState belowBlockState = level.getBlockState(pos.below());
        if ( blockState.getValue(HALF)==Half.TOP){
            if (belowBlockState.getBlock() instanceof BattlementsWoodBase && direction==belowBlockState.getValue(FACING)){
                return belowBlockState.getValue(SHAPE);
            }
        }

        BlockState blockstate = level.getBlockState(pos.relative(direction));
        if (isStairs(blockstate) && blockState.getValue(HALF) == blockstate.getValue(HALF)) {
            Direction direction1 = blockstate.getValue(FACING);
            if (direction1.getAxis() != blockState.getValue(FACING).getAxis() && canTakeShape(blockState, level, pos, direction1.getOpposite())) {
                if (direction1 == direction.getCounterClockWise()) {
                    return StairsShape.OUTER_LEFT;
                }

                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState blockstate1 = level.getBlockState(pos.relative(direction.getOpposite()));
        if (isStairs(blockstate1) && blockState.getValue(HALF) == blockstate1.getValue(HALF)) {
            Direction direction2 = blockstate1.getValue(FACING);
            if (direction2.getAxis() != blockState.getValue(FACING).getAxis() && canTakeShape(blockState, level, pos, direction2)) {
                if (direction2 == direction.getCounterClockWise()) {
                    return StairsShape.INNER_LEFT;
                }

                return StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    protected static boolean canTakeShape(BlockState blockState, BlockGetter level, BlockPos pos, Direction direction) {
        BlockState blockstate = level.getBlockState(pos.relative(direction));
        return !isStairs(blockstate) || blockstate.getValue(FACING) != blockState.getValue(FACING) || blockstate.getValue(HALF) != blockState.getValue(HALF);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.is(Items.TORCH)){
            if (!player.isCreative() && state.getValue(TORCH)==TorchEnum.NONE){
                itemInHand.split(1);
            }

            Direction direction = hitResult.getDirection();
            Direction facing = state.getValue(FACING);
            if (facing.getOpposite()==direction){
                state=state.setValue(TORCH,TorchEnum.INNER).setValue(TORCH_FACING,direction.getOpposite());
            }else if (facing==direction){
                state=state.setValue(TORCH,TorchEnum.OUTER).setValue(TORCH_FACING,direction.getOpposite());
            }else {
                StairsShape value = state.getValue(SHAPE);
                if (value==StairsShape.STRAIGHT || value == StairsShape.OUTER_LEFT || value == StairsShape.OUTER_RIGHT) return InteractionResult.FAIL;
                if (value==StairsShape.INNER_LEFT){
                    Direction left = DirectionUtil.getLeft(facing);

                    if (direction.getOpposite()==left){
                        state=state.setValue(TORCH,TorchEnum.INNER).setValue(TORCH_FACING,left);
                    }else if (direction==left){
                        state=state.setValue(TORCH,TorchEnum.OUTER).setValue(TORCH_FACING,left.getOpposite());
                    }

                }else if (value==StairsShape.INNER_RIGHT){
                    Direction right = DirectionUtil.getRight(facing);
                    if (direction.getOpposite()==right){
                        state=state.setValue(TORCH,TorchEnum.INNER).setValue(TORCH_FACING,right);
                    }else if (direction==right){
                        state=state.setValue(TORCH,TorchEnum.OUTER).setValue(TORCH_FACING,right.getOpposite());
                    }

                }

            }
            level.setBlock(blockPos,state,10);
            return InteractionResult.SUCCESS;
        }else if(itemInHand.isEmpty()){
            Containers.dropItemStack(level, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), new ItemStack(Items.TORCH,1));
            state=state.setValue(TORCH,TorchEnum.NONE);
            level.setBlock(blockPos,state,10);

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        TorchEnum value = state.getValue(TORCH);
        if (value!=TorchEnum.NONE){
            double d0 = 0;
            double d1 = (double)pos.getY() + 0.9D;
            double d2 = 0;
            if (value==TorchEnum.INNER){
                d0 = (double)pos.getX() + 0.5D;
                d2 = (double)pos.getZ() + 0.5D;
            }else {
                Direction facing = state.getValue(TORCH_FACING);
                switch (facing){
                    case EAST :
                        d2 = (double)pos.getZ() + 0.5D;
                        d0 = (double)pos.getX() - 0.25D;
                        break;
                    case WEST:
                        d2 = (double)pos.getZ() + 0.5D;
                        d0 = (double)pos.getX() + 1.25D;
                        break;
                    case NORTH:
                        d2 = (double)pos.getZ() + 1.25D;
                        d0 = (double)pos.getX() + 0.5D;
                        break;
                    case SOUTH:
                        d2 = (double)pos.getZ() - 0.25D;
                        d0 = (double)pos.getX() + 0.5D;
                        break;
                }
            }
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            level.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }





    }

}
