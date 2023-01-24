package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import com.aster.justbuildmystronghold.blocks.base.BaseWallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ShelfBlock extends BaseHorizontalWaterBlock {

    public static final BooleanProperty TOP = BooleanProperty.create("shelf_top");

    public ShelfBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(TOP);

    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);
        boolean bottom = isTop(context.getLevel(),context.getClickedPos());
        stateForPlacement = stateForPlacement.setValue(TOP,bottom);
        return stateForPlacement;
    }


    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
        BlockState blockState1 = super.updateShape(blockState, direction, blockState2, level, pos, pos2);
        boolean bottom = isTop(level,pos);
        blockState1 = blockState1.setValue(TOP,bottom);
        return blockState1;
    }

    private boolean isTop(LevelReader level, BlockPos pos){
        BlockState blockState = level.getBlockState(pos.below());
        return blockState.is(this);

    }
}
