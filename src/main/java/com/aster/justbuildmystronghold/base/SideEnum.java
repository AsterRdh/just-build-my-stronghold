package com.aster.justbuildmystronghold.base;

import net.minecraft.util.StringRepresentable;

public enum SideEnum implements StringRepresentable {
    LEFT("left"),
    RIGHT("right")
    ;

    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private SideEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
