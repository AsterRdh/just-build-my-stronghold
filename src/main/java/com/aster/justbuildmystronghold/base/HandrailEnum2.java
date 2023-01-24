package com.aster.justbuildmystronghold.base;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

public enum HandrailEnum2 implements StringRepresentable {
    NONE("none"),
    NORTH("north"),
    SOUTH("south"),
    WEST("west"),
    EAST("east")

    ;

    public static HandrailEnum2 get(Direction direction){
        switch (direction){
            case WEST : return HandrailEnum2.WEST;
            case NORTH : return HandrailEnum2.NORTH;
            case EAST : return HandrailEnum2.EAST;
            case SOUTH : return HandrailEnum2.SOUTH;
            default: return HandrailEnum2.NONE;
        }
    }


    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private HandrailEnum2(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
