package com.aster.justbuildmystronghold.base.math;

import net.minecraft.core.Direction;

public class DirectionUtil {
    public static Direction getLeft(Direction direction){
        switch (direction){
            case NORTH : return Direction.WEST;
            case EAST:   return Direction.NORTH;
            case SOUTH:  return Direction.EAST;
            case WEST:
            default:
                return Direction.SOUTH;
        }

    }
    public static Direction getRight(Direction direction){
        return getLeft(direction).getOpposite();
    }
}
