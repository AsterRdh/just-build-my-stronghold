package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class MachicolationsType1 extends BaseHorizontalBlock {
    protected static final Map<Direction,VoxelShape> Shape = getVoxelShape();

    public MachicolationsType1() {// .hardnessAndResistance(5.0f)
        super(Properties.of(Material.STONE, MaterialColor.STONE)
                .sound(SoundType.STONE)
                .strength(5f)
                .noOcclusion().noCollission()
        );

    }


    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        return Shape.get(direction);
    }

    private static Map<Direction,VoxelShape>  getVoxelShape(){
        Map<Direction,VoxelShape> voxelShapeMap=new HashMap<>();
        VoxelShapeHelper a1 = new VoxelShapeHelper(0, 13, 0, 3, 16, 11);
        VoxelShapeHelper a2 = new VoxelShapeHelper(0, 11, 0, 3, 13, 8);
        VoxelShapeHelper a3 =new VoxelShapeHelper(0, 9, 0, 3, 11, 5);
        VoxelShapeHelper a4 =new VoxelShapeHelper(0, 6, 0, 3, 9, 2);

        VoxelShapeHelper b1 =new VoxelShapeHelper(13, 13, 0, 16, 16, 11);
        VoxelShapeHelper b2 =new VoxelShapeHelper(13, 11, 0, 16, 13, 8);
        VoxelShapeHelper b3 =new VoxelShapeHelper(13, 9, 0, 16, 11, 5);
        VoxelShapeHelper b4 =new VoxelShapeHelper(13, 6, 0, 16, 9, 2);
        VoxelShapeHelper[] helpers={a1,a2,a3,a4,b1,b2,b3,b4};
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
