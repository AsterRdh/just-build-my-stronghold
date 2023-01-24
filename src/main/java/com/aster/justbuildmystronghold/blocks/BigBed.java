package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalBlock;
import com.aster.justbuildmystronghold.blocks.entity.BigBedEntity;
import com.aster.justbuildmystronghold.register.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BigBed extends BedBlock {

    private final static VoxelShapeHelper a0 = new VoxelShapeHelper(0.0, 3.0, 2.0, 16.0, 9.0, 18.0);
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(0.0, 0.0, 15.0, 3.0, 3.0, 18.0);
    private final static VoxelShapeHelper a2 = new VoxelShapeHelper(13.0, 0.0, 15.0, 16.0, 3.0, 18.0);
    private final static VoxelShapeHelper a3 = new VoxelShapeHelper(0.0, 0.0, 2.0, 3.0, 3.0, 5.0);
    private final static VoxelShapeHelper a4 = new VoxelShapeHelper(13.0, 0.0, 2.0, 16.0, 3.0, 5.0);
    private final static VoxelShapeHelper a5 = new VoxelShapeHelper(0.0, 0.0, 0.0, 16.0, 16.0, 2.0);
    private final static VoxelShapeHelper a6 = new VoxelShapeHelper(0.0, 3.0, 2.0, 16.0, 9.0, 18.0);

    private final static VoxelShapeHelper[] BIG_BED_LOWER = {a0,a1,a2};
    private final static VoxelShapeHelper[] BIG_BED_UPPER = {a3,a4,a5,a6};

    public BigBed(DyeColor color,Properties properties) {
        super(color,properties);
    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
//        super.createBlockStateDefinition(stateBuilder);
//        stateBuilder.add(PART,OCCUPIED);
//    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = getConnectedDirection(state).getOpposite();
        BedPart value = state.getValue(PART);
        if (value == BedPart.FOOT){
            switch (direction) {
                case NORTH:
                    return getVoxelShape(180,BIG_BED_LOWER);
                case SOUTH:
                    return getVoxelShape(0,BIG_BED_LOWER);
                case WEST:
                    return getVoxelShape(-90,BIG_BED_LOWER);
                default:
                    return getVoxelShape(90,BIG_BED_LOWER);
            }
        }else {
            switch (direction) {
                case NORTH:
                    return getVoxelShape(0,BIG_BED_UPPER);
                case SOUTH:
                    return getVoxelShape(180,BIG_BED_UPPER);
                case WEST:
                    return getVoxelShape(90,BIG_BED_UPPER);
                default:
                    return getVoxelShape(-90,BIG_BED_UPPER);
            }
        }


    }

    private static VoxelShape getVoxelShape(int rotation, VoxelShapeHelper... helpers){
        VoxelShape res = helpers[0].rotate(rotation,0,1,0);
        for (int i =1 ; i<helpers.length;i++){
            res =   Shapes.or(res, helpers[i].rotate(rotation,0,1,0));
        }
        return res;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_60576_) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BigBedEntity(BlockEntityRegister.BIG_BED.get(),pos, state, getColor());
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }
}
