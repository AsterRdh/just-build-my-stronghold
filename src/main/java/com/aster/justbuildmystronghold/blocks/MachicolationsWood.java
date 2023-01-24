package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MachicolationsWood extends BaseHorizontalWaterBlock {

    private final static VoxelShapeHelper a0 = new VoxelShapeHelper(10.0, 13.0, 0.0, 13.0, 16.0, 6.0);
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(0.0, 13.0, 10.0, 6.0, 16.0, 13.0);
    private final static VoxelShapeHelper a2 = new VoxelShapeHelper(0.0, 13.0, 0.0, 3.0, 16.0, 1.0);
    private final static VoxelShapeHelper a3 = new VoxelShapeHelper(0.0, 13.0, 1.0, 4.0, 16.0, 2.0);
    private final static VoxelShapeHelper a4 = new VoxelShapeHelper(0.0, 13.0, 2.0, 5.0, 16.0, 3.0);
    private final static VoxelShapeHelper a5 = new VoxelShapeHelper(1.0, 13.0, 3.0, 6.0, 16.0, 4.0);
    private final static VoxelShapeHelper a6 = new VoxelShapeHelper(2.0, 13.0, 4.0, 7.0, 16.0, 5.0);
    private final static VoxelShapeHelper a7 = new VoxelShapeHelper(3.0, 13.0, 5.0, 6.0, 16.0, 6.0);
    private final static VoxelShapeHelper a8 = new VoxelShapeHelper(4.0, 13.0, 6.0, 5.0, 16.0, 7.0);
    private final static VoxelShapeHelper a9 = new VoxelShapeHelper(10.0, 13.0, 10.0, 16.0, 16.0, 13.0);
    private final static VoxelShapeHelper a10 = new VoxelShapeHelper(3.0, 13.0, 0.0, 6.0, 16.0, 6.0);
    private final static VoxelShapeHelper a11 = new VoxelShapeHelper(9.0, 13.0, 4.0, 10.0, 16.0, 5.0);
    private final static VoxelShapeHelper a12 = new VoxelShapeHelper(10.0, 13.0, 3.0, 11.0, 16.0, 6.0);
    private final static VoxelShapeHelper a13 = new VoxelShapeHelper(11.0, 13.0, 2.0, 12.0, 16.0, 7.0);
    private final static VoxelShapeHelper a14 = new VoxelShapeHelper(12.0, 13.0, 1.0, 13.0, 16.0, 6.0);
    private final static VoxelShapeHelper a15 = new VoxelShapeHelper(13.0, 13.0, 0.0, 14.0, 16.0, 5.0);
    private final static VoxelShapeHelper a16 = new VoxelShapeHelper(14.0, 13.0, 0.0, 15.0, 16.0, 4.0);
    private final static VoxelShapeHelper a17 = new VoxelShapeHelper(15.0, 13.0, 0.0, 16.0, 16.0, 3.0);

    private final static VoxelShapeHelper[] INNER_LEFT = new VoxelShapeHelper[]{a0,a1,a2,a3,a4,a5,a6,a7,a8};;
    private final static VoxelShapeHelper[] INNER_RIGHT = new VoxelShapeHelper[]{a9,a10,a11,a12,a13,a14,a15,a16,a17};
    private final static VoxelShapeHelper[] OUTER_LEFT = new VoxelShapeHelper[]{a2,a3,a4,a5,a6,a7,a8};
    private final static VoxelShapeHelper[] OUTER_RIGHT = new VoxelShapeHelper[]{a11,a12,a13,a14,a15,a16,a17};
    private final static VoxelShapeHelper[] STRAIGHT = new VoxelShapeHelper[]{a0,a10};

    public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;

    public MachicolationsWood() {
        super( BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());

    }

    public MachicolationsWood(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(SHAPE);


    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos().above());
        if (blockState.getBlock() instanceof BattlementsWoodBase){
            StairsShape value = blockState.getValue(SHAPE);
            Direction facing = blockState.getValue(FACING);
            stateForPlacement = stateForPlacement.setValue(SHAPE,value);
            stateForPlacement = stateForPlacement.setValue(FACING,facing);
        }
        return stateForPlacement;
    }

    @Override
    public BlockState updateShape(BlockState p_57554_, Direction p_57555_, BlockState p_57556_, LevelAccessor p_57557_, BlockPos p_57558_, BlockPos p_57559_) {
        return super.updateShape(p_57554_, p_57555_, p_57556_, p_57557_, p_57558_, p_57559_);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        StairsShape shape = p_60555_.getValue(SHAPE);
        Direction facing = p_60555_.getValue(FACING);

        switch (shape){
            case OUTER_LEFT :return getVoxelShape(facing,OUTER_LEFT);
            case OUTER_RIGHT:return getVoxelShape(facing,OUTER_RIGHT);
            case INNER_LEFT:return getVoxelShape(facing,INNER_LEFT);
            case INNER_RIGHT:return getVoxelShape(facing,INNER_RIGHT);
            default: return getVoxelShape(facing,STRAIGHT);
        }
    }
    private static VoxelShape getVoxelShape(Direction direction,VoxelShapeHelper... helpers){
        switch (direction){
            case EAST:
                return getVoxelShape(-90,helpers);
            case SOUTH:
                return getVoxelShape(180,helpers);
            case WEST:
                return getVoxelShape(90,helpers);
            case NORTH :
            default:
                return getVoxelShape(0,helpers);
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
