package com.aster.justbuildmystronghold.blocks.entity;

import com.aster.justbuildmystronghold.register.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BigBedEntity extends BlockEntity {
    private DyeColor color;

    public BigBedEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, DyeColor color) {
        super(blockEntity, pos, state);
        this.color = color;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
