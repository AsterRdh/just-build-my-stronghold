package com.aster.justbuildmystronghold.base.math;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class BlockPosUtil {
    public static BlockPos getFront(Direction direction,BlockPos pos){
        switch (direction){
            case EAST: return pos.east();
            case WEST: return pos.west();
            case SOUTH: return pos.south();
            case NORTH:
            case DOWN:
            case UP :
            default:
                return pos.above();
        }
    }

    public static BlockPos getBack(Direction direction,BlockPos pos) {
        switch (direction) {
            case EAST:
                    return pos.west();
            case WEST:
                return pos.east();
            case SOUTH:
                return pos.north();
            case NORTH:
            case DOWN:
            case UP:
            default:
                return pos.south();
        }
    }

    public static BlockPos getLeft(Direction direction,BlockPos pos) {
        switch (direction) {
            case EAST:
                return pos.north();
            case WEST:
                return pos.south();
            case SOUTH:
                return pos.east();
            case NORTH:
            case DOWN:
            case UP:
            default:
                return pos.west();
        }
    }

    public static BlockPos getRight(Direction direction,BlockPos pos) {
        switch (direction) {
            case EAST:
                return pos.south();
            case WEST:
                return pos.north();
            case SOUTH:
                return pos.west();
            case NORTH:
            case DOWN:
            case UP:
            default:
                return pos.east();
        }
    }


}
