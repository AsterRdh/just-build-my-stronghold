package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.HandrailEnum;
import com.aster.justbuildmystronghold.base.SHBlockStateProperties;
import com.aster.justbuildmystronghold.base.SideEnum;
import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.base.lang.MapMap;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class WindowMiniFrame extends BaseHorizontalWaterBlock {

    public static final EnumProperty<SideEnum> SIDE = SHBlockStateProperties.SIDE;
    private final static VoxelShapeHelper a0 = new VoxelShapeHelper(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);
    private final static VoxelShapeHelper a1 = new VoxelShapeHelper(8.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    private final static VoxelShapeHelper a2 = new VoxelShapeHelper(8.0, 14.0, 0.0, 16.0, 16.0, 16.0);
    private final static VoxelShapeHelper a3 = new VoxelShapeHelper(8.0, 2.0, 6.0, 11.0, 14.0, 14.0);
    private final static VoxelShapeHelper a4 = new VoxelShapeHelper(8.0, 2.0, 0.0, 9.0, 14.0, 1.0);
    private final static VoxelShapeHelper a5 = new VoxelShapeHelper(8.0, 2.0, 1.0, 9.4, 14.0, 2.0);
    private final static VoxelShapeHelper a6 = new VoxelShapeHelper(8.0, 2.0, 2.0, 9.8, 14.0, 3.0);
    private final static VoxelShapeHelper a7 = new VoxelShapeHelper(8.0, 2.0, 3.0, 10.2, 14.0, 4.0);
    private final static VoxelShapeHelper a8 = new VoxelShapeHelper(8.0, 2.0, 4.0, 10.6, 14.0, 5.0);
    private final static VoxelShapeHelper a9 = new VoxelShapeHelper(8.0, 2.0, 5.0, 11.0, 14.0, 6.0);
    private final static VoxelShapeHelper a10 = new VoxelShapeHelper(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private final static VoxelShapeHelper a11 = new VoxelShapeHelper(0.0, 0.0, 0.0, 8.0, 2.0, 16.0);
    private final static VoxelShapeHelper a12 = new VoxelShapeHelper(0.0, 14.0, 0.0, 8.0, 16.0, 16.0);
    private final static VoxelShapeHelper a13 = new VoxelShapeHelper(5.0, 2.0, 6.0, 8.0, 14.0, 14.0);
    private final static VoxelShapeHelper a14 = new VoxelShapeHelper(7.0, 2.0, 0.0, 8.0, 14.0, 1.0);
    private final static VoxelShapeHelper a15 = new VoxelShapeHelper(6.6, 2.0, 1.0, 8.0, 14.0, 2.0);
    private final static VoxelShapeHelper a16 = new VoxelShapeHelper(6.2, 2.0, 2.0, 8.0, 14.0, 3.0);
    private final static VoxelShapeHelper a17 = new VoxelShapeHelper(5.8, 2.0, 3.0, 8.0, 14.0, 4.0);
    private final static VoxelShapeHelper a18 = new VoxelShapeHelper(5.4, 2.0, 4.0, 8.0, 14.0, 5.0);
    private final static VoxelShapeHelper a19 = new VoxelShapeHelper(5.0, 2.0, 5.0, 8.0, 14.0, 6.0);

    private final static VoxelShapeHelper[] helpers1 = new VoxelShapeHelper[]{a0,a1,a2,a3,a4,a5,a6,a7,a8,a9};
    private final static VoxelShapeHelper[] helpers2 = new VoxelShapeHelper[]{a10,a11,a12,a13,a14,a15,a16,a17,a18,a19};


    private final static MapMap<SideEnum, Direction,VoxelShape> voxelShapeMap=new MapMap<>();

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(SIDE);
    }
    public WindowMiniFrame() {
        super(  BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(5.0F)
                .sound(SoundType.STONE)
                .noOcclusion());

        voxelShapeMap.put(SideEnum.LEFT,Direction.NORTH,getVoxelShape(180,helpers1));
        voxelShapeMap.put(SideEnum.LEFT,Direction.WEST,getVoxelShape(-90,helpers1));
        voxelShapeMap.put(SideEnum.LEFT,Direction.SOUTH,getVoxelShape(0,helpers1));
        voxelShapeMap.put(SideEnum.LEFT,Direction.EAST,getVoxelShape(90,helpers1));

        voxelShapeMap.put(SideEnum.RIGHT,Direction.NORTH,getVoxelShape(180,helpers2));
        voxelShapeMap.put(SideEnum.RIGHT,Direction.WEST,getVoxelShape(-90,helpers2));
        voxelShapeMap.put(SideEnum.RIGHT,Direction.SOUTH,getVoxelShape(0,helpers2));
        voxelShapeMap.put(SideEnum.RIGHT,Direction.EAST,getVoxelShape(90,helpers2));

    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        state = state.setValue(SIDE,this.getHinge(context));
        return state;
    }

    protected SideEnum getHinge(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        int j = direction.getStepX();
        int k = direction.getStepZ();
        Vec3 vec3 = context.getClickLocation();
        double d0 = vec3.x - (double)blockpos.getX();
        double d1 = vec3.z - (double)blockpos.getZ();
        return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? SideEnum.LEFT : SideEnum.RIGHT;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        SideEnum side = blockState.getValue(SIDE);

        return voxelShapeMap.get(side,direction);
    }

    private static VoxelShape getVoxelShape(int rotation, VoxelShapeHelper... helpers){
        VoxelShape res = helpers[0].rotate(rotation,0,1,0);
        for (int i =1 ; i<helpers.length;i++){
            res =   Shapes.or(res, helpers[i].rotate(rotation,0,1,0));
        }
        return res;
    }

}
