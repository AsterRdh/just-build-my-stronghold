package com.aster.justbuildmystronghold.base;

import net.minecraft.util.StringRepresentable;

public enum HandrailBaseEnum implements StringRepresentable {
    NORMAL("normal"),
    STAIRS1("stairs1"),
    STAIRS2("stairs2"),
    SLAB("slab");

    private final String name;
    @Override
    public String getSerializedName() {
        return this.name;
    }
    private HandrailBaseEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
