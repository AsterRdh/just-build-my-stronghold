package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.base.math.Vec3D;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindowMini extends DoorBlock {
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57561_) {
        super.createBlockStateDefinition(p_57561_);
    }
    public WindowMini(Properties properties) {
        super(properties);
    }

    public WindowMini() {
        super(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                .strength(3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState p_52807_, BlockGetter p_52808_, BlockPos p_52809_, CollisionContext p_52810_) {
        Direction direction = p_52807_.getValue(FACING);
        boolean isOpen = p_52807_.getValue(OPEN);
        DoorHingeSide side = p_52807_.getValue(HINGE);
        DoubleBlockHalf half = p_52807_.getValue(HALF);
        VoxelShapeHelper voxelShapeHelper;
        VoxelShapeHelper voxelShapeHelperOpen;
        switch (half){
            case UPPER:
                if (side==DoorHingeSide.RIGHT){
                    voxelShapeHelper = new VoxelShapeHelper(new Vec3D(0, 0, 14), new Vec3D(9, 10, 16));
                    voxelShapeHelperOpen = new VoxelShapeHelper(new Vec3D(7.65, 0, 10.8), new Vec3D(16.75, 10, 16.1));
                } else  {
                    voxelShapeHelper = new VoxelShapeHelper(new Vec3D(7, 0, 14), new Vec3D(16, 10, 16));
                    voxelShapeHelperOpen = new VoxelShapeHelper(new Vec3D(-0.75, 0, 10.8), new Vec3D(8.35, 10, 16.1));
                }
                break;
            case LOWER :
            default:
                if (side==DoorHingeSide.RIGHT){
                    voxelShapeHelper = new VoxelShapeHelper(new Vec3D(0, 6, 14), new Vec3D(9, 16, 16));
                    voxelShapeHelperOpen = new VoxelShapeHelper(new Vec3D(7.65, 6, 10.8), new Vec3D(16.75, 16, 16.1));
                } else  {
                    voxelShapeHelper = new VoxelShapeHelper(new Vec3D(7, 6, 14), new Vec3D(16, 16, 16));
                    voxelShapeHelperOpen = new VoxelShapeHelper(new Vec3D(-0.75, 6, 10.8), new Vec3D(8.35, 16, 16.1));
                }
                break;
        }

        //voxelShapeHelperOpen
        VoxelShapeHelper voxelShapeHelperF=isOpen?voxelShapeHelperOpen:voxelShapeHelper;
        VoxelShape voxelShape;
        switch (direction) {
            case EAST:
                voxelShape = voxelShapeHelperF.rotate(90,0,1,0);
                break;
            case WEST:
                voxelShape = voxelShapeHelperF.rotate(-90,0,1,0);
                break;
            case SOUTH:
                voxelShape = voxelShapeHelperF.rotate(0,0,1,0);
                break;
            case NORTH:
            default:
                voxelShape = voxelShapeHelperF.rotate(180,0,1,0);
        }
        return voxelShape;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockState = this.defaultBlockState();
        Level level = context.getLevel();
        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context)) {
            boolean flag = level.hasNeighborSignal(blockpos) || level.hasNeighborSignal(blockpos.above());
            blockState=blockState.setValue(FACING, context.getHorizontalDirection()).setValue(HINGE, this.getHinge(context)).setValue(POWERED, Boolean.valueOf(flag)).setValue(OPEN, Boolean.valueOf(flag)).setValue(HALF, DoubleBlockHalf.LOWER);
        }
        return blockState;
    }

    protected DoorHingeSide getHinge(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        int j = direction.getStepX();
        int k = direction.getStepZ();
        Vec3 vec3 = context.getClickLocation();
        double d0 = vec3.x - (double)blockpos.getX();
        double d1 = vec3.z - (double)blockpos.getZ();
        return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
    }


    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos) {
        //todo 重写这个方法 让他可以贴在方块上放置
        return true;

//        Direction facing = blockState.getValue(FACING);
//        DoubleBlockHalf half = blockState.getValue(HALF);
//        DoorHingeSide side = blockState.getValue(HINGE);
//        Direction opposite = facing.getOpposite();
//        //VoxelShapeHelper testShape = new VoxelShapeHelper(7, 0, 14,9, 16, 16) ;
//        //Shape shape//
//        VoxelShape shape;
//        if (side==DoorHingeSide.LEFT){
//            shape = Block.box(15, 0, 8,16, 16, 10);
//        }else {
//            shape = Block.box(6, 0, 15,8, 16, 16);
//        }
//
//
//        if (half==DoubleBlockHalf.LOWER){
//            BlockPos nextPost = getNextPost(pos, facing);
//            BlockPos nextUpperPost = nextPost.above();
//            BlockPos lowerPost = pos.below();
//            BlockState nextBlock = level.getBlockState(nextPost);
//            BlockState nextUpperBlock = level.getBlockState(nextUpperPost);
//            BlockState lowerBlock = level.getBlockState(lowerPost);
//
//            boolean nextFlag = isSupporting(shape,level,nextBlock,nextPost,opposite);
//            boolean nextUpperFlag = isSupporting(shape,level,nextUpperBlock,nextUpperPost,opposite);
//            return lowerBlock.isFaceSturdy(level,lowerPost, Direction.UP) || nextFlag || nextUpperFlag;
//        }else {
//            BlockPos nextPost = getNextPost(pos, facing);
//            BlockPos nextLowerPost = nextPost.below();
//            BlockState nextBlock = level.getBlockState(nextPost);
//            BlockState nextLowerBlock = level.getBlockState(nextLowerPost);
//            boolean nextFlag = isSupporting(shape,level,nextBlock,nextPost,opposite);
//            boolean nextLowerFlag = isSupporting(shape,level,nextLowerBlock,nextLowerPost,opposite);
//            return nextFlag || nextLowerFlag;
//        }
        // BlockPos blockpos = p_52785_.below();
        //      BlockState blockstate = p_52784_.getBlockState(blockpos);
        //      return p_52783_.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(p_52784_, blockpos, Direction.UP) : blockstate.is(this);

       // return super.canSurvive(p_52783_, p_52784_, p_52785_);
    }

    public boolean isSupporting(VoxelShape shape, BlockGetter getter, BlockState blockState,BlockPos pos, Direction direction) {
        return !Shapes.joinIsNotEmpty(blockState.getBlockSupportShape(getter, pos).getFaceShape(direction), shape, BooleanOp.ONLY_SECOND);
    }

    protected BlockPos getNextPost(BlockPos pos,Direction facing){
        BlockPos newPos;
        switch (facing){
            case EAST -> newPos=pos.east();
            case WEST -> newPos=pos.west();
            case NORTH -> newPos=pos.north();
            case SOUTH -> newPos=pos.south();
            default -> newPos=pos;
        }
        return newPos;
    }
}
