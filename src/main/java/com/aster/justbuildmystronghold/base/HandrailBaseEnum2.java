package com.aster.justbuildmystronghold.base;

import net.minecraft.util.StringRepresentable;

public enum HandrailBaseEnum2 implements StringRepresentable {
    STRAIGHT("straight"),
    MIDDLE("middle"),

    START("start"),
    END("end");

    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private HandrailBaseEnum2(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
