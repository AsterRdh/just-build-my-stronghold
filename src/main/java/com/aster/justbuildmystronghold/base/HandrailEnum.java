package com.aster.justbuildmystronghold.base;

import net.minecraft.util.StringRepresentable;

public enum HandrailEnum implements StringRepresentable {
    STRAIGHT("left"),
    INNER_LEFT("inner_left"),
    INNER_RIGHT("inner_right"),
    INNER_OUTER_LEFT("inner_outer_left"),
    INNER_OUTER_RIGHT("inner_outer_right"),
    OUTER_LEFT("outer_left"),
    OUTER_RIGHT("outer_right"),

    NONE("none");

    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private HandrailEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
