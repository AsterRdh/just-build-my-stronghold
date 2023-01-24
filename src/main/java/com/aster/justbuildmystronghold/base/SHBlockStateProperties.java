package com.aster.justbuildmystronghold.base;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class SHBlockStateProperties {
    public static final IntegerProperty SHAPE = IntegerProperty.create("shape", 0, 2);
    public static final IntegerProperty HandrailEnum = IntegerProperty.create("shape", 0, 2);
    public static final EnumProperty<HandrailEnum> HANDRAIL_SHAPE = EnumProperty.create("handrail_shape", HandrailEnum.class);
    public static final EnumProperty<TorchEnum> TORCH = EnumProperty.create("torch_enum", TorchEnum.class);
    public static final DirectionProperty TORCH_FACING = DirectionProperty.create("torch_facing", Direction.Plane.HORIZONTAL);

    public static final EnumProperty<SideEnum> SIDE = EnumProperty.create("side", SideEnum.class);
}
