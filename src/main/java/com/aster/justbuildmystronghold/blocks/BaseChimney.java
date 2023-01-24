package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseBlock;
import com.aster.justbuildmystronghold.entity.SmokeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BaseChimney extends BaseBlock implements SimpleWaterloggedBlock {

    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final BooleanProperty SMOKING = BooleanProperty.create("smoking");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final static VoxelShape a0 = new VoxelShapeHelper(2.0, 0.0, 14.0, 16.0, 16.0, 16.0).getShape();
    private final static VoxelShape a1 = new VoxelShapeHelper(14.0, 0.0, 0.0, 16.0, 16.0, 14.0).getShape();
    private final static VoxelShape a2 = new VoxelShapeHelper(0.0, 0.0, 0.0, 14.0, 16.0, 2.0).getShape();
    private final static VoxelShape a3 = new VoxelShapeHelper(0.0, 0.0, 2.0, 2.0, 16.0, 16.0).getShape();
    private int sc = 0;
    public BaseChimney(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(HALF,WATERLOGGED,SMOKING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        state = state.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));

        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(clickedPos.above());
        BlockState blockState2 = level.getBlockState(clickedPos.below());

        if (blockState.getBlock() instanceof BaseChimney){
            state = state.setValue(HALF,Half.BOTTOM);
        }else {
            state = state.setValue(HALF,Half.TOP);
        }
        if (blockState2.getBlock() instanceof BaseChimney){
            blockState2.getBlock().neighborChanged(blockState2,level,clickedPos.below(),blockState2.getBlock(),clickedPos,true);
        }

        return state;
    }
    @Override
    public FluidState getFluidState(BlockState p_57568_) {
        return p_57568_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_57568_);
    }
    public BlockState updateShape(BlockState p_57554_, Direction p_57555_, BlockState p_57556_, LevelAccessor p_57557_, BlockPos p_57558_, BlockPos p_57559_) {
        if (p_57554_.getValue(WATERLOGGED)) {
            p_57557_.scheduleTick(p_57558_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57557_));
        }
        BlockState blockState = p_57557_.getBlockState(p_57558_.above());
        if (blockState.getBlock() instanceof BaseChimney){
            p_57554_ = p_57554_.setValue(HALF,Half.BOTTOM);
        }else {
            p_57554_ = p_57554_.setValue(HALF,Half.TOP);
        }

        return super.updateShape(p_57554_, p_57555_, p_57556_, p_57557_, p_57558_, p_57559_);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        return  Shapes.or(a0, a1, a2, a3);
    }
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        boolean smokey = state.getValue(SMOKING) && state.getValue(HALF)==Half.TOP;
        if (smokey ) {
            for(int i = 0; i < randomSource.nextInt(5) + 1; ++i) {
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        (double)pos.getX() + 0.5D + randomSource.nextDouble() / 3.0D * (double)(randomSource.nextBoolean() ? 1 : -1),
                        (double)pos.getY() + randomSource.nextDouble() + randomSource.nextDouble(),
                        (double)pos.getZ() + 0.5D + randomSource.nextDouble() / 3.0D * (double)(randomSource.nextBoolean() ? 1 : -1),
                        0.0D, 0.07D, 0.0D);
            }
        }

    }
}
