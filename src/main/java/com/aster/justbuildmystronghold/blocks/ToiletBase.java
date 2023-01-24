package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class ToiletBase extends BaseHorizontalWaterBlock {
    public ToiletBase(Properties properties) {
        super(properties);
    }

    public ToiletBase() {
        super( BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(5.0F)
                .sound(SoundType.STONE)
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        Map<Direction, VoxelShape> voxelShape = getVoxelShape();
        Direction direction = blockState.getValue(FACING);
        return voxelShape.get(direction);
    }

    private static Map<Direction,VoxelShape> getVoxelShape(){
        Map<Direction,VoxelShape> voxelShapeMap=new HashMap<>();
        VoxelShapeHelper a0 = new VoxelShapeHelper(0.0,0.0,0.0,16.0,16.0,8.0);
        VoxelShapeHelper a1 = new VoxelShapeHelper(0.0,0.0,8.0,2.0,12.0,15.0);
        VoxelShapeHelper a2 = new VoxelShapeHelper(14.0,0.0,8.0,16.0,12.0,15.0);
        VoxelShapeHelper a3 = new VoxelShapeHelper(0.0,0.0,15.0,16.0,12.0,17.0);
        VoxelShapeHelper a4 = new VoxelShapeHelper(0.0,12.0,8.0,6.0,13.0,18.0);
        VoxelShapeHelper a5 = new VoxelShapeHelper(10.0,12.0,8.0,16.0,13.0,18.0);
        VoxelShapeHelper a6 = new VoxelShapeHelper(2.0,11.0,9.0,14.0,12.0,10.0);
        VoxelShapeHelper a7 = new VoxelShapeHelper(6.0,12.0,8.0,10.0,13.0,11.0);
        VoxelShapeHelper a8 = new VoxelShapeHelper(6.0,12.0,15.0,10.0,13.0,18.0);
        VoxelShapeHelper[] helpers = {a0,a1,a2,a3,a4,a5,a6,a7,a8};

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
}
