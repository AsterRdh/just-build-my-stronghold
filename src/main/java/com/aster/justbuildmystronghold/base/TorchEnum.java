package com.aster.justbuildmystronghold.base;

import net.minecraft.util.StringRepresentable;

public enum TorchEnum implements StringRepresentable {
    NONE("straight"),
    INNER("inner"),
    OUTER("outer");

    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private TorchEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
