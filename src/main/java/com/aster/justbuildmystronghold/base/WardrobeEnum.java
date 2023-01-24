package com.aster.justbuildmystronghold.base;

import net.minecraft.util.StringRepresentable;

public enum WardrobeEnum implements StringRepresentable {
    SINGLE("single"),
    UPPER("upper"),
    LOWER("lower");

    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private WardrobeEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
